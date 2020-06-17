package org.warren.sca.rsc.postmaninfo.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.warren.sca.rsc.common.postmaninfo.PostmanAreaInChargeService;
import org.warren.sca.rsc.postmaninfo.constant.RedisConstant;
import org.warren.sca.rsc.postmaninfo.mapper.AreaInChargeMapper;
import org.warren.sca.rsc.postmaninfo.pojo.po.AreaInChargePO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@org.apache.dubbo.config.annotation.Service
public class PostmanAreaInChargeServiceImpl implements PostmanAreaInChargeService {

    @Autowired
    private AreaInChargeMapper areaInChargeMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void areaInChargeAdd(int postman, int areaId) {
        areaInChargeMapper.insert(new AreaInChargePO(postman, areaId));
    }

    @Override
    public void areaInChargeDelete(int postman, int areaId) {
        areaInChargeMapper.delete(new QueryWrapper<AreaInChargePO>().eq("areaId", areaId).eq("postman_id", postman));

        //刷新redis上的记录。保持同步
        closeAROM(postman);
        openAROM(postman);
    }

    //将快递员id注册到redis地区对应sorted set
    @Override
    public void openAROM(int postmanId){
        List<Integer> areas = listAreaCodeInCharge(postmanId);
        for (Integer area : areas){
            redisTemplate.opsForZSet().add(
                    RedisConstant.getPostmanOfArea(area),       //sorted set的key
                    String.valueOf(postmanId),                  // value
                    0                                       //score,在业务中是指该快递员的工作量
            );
        }
    }

    @Override
    public void closeAROM(int postmanId){
        List<Integer> areas = listAreaCodeInCharge(postmanId);
        for (Integer area : areas){
            redisTemplate.opsForZSet().remove(
                    RedisConstant.getPostmanOfArea(area),
                    String.valueOf(postmanId)
            );
        }
    }

    public List<Integer> listAreaCodeInCharge(int postmanId){
        return areaInChargeMapper
                .selectList(new QueryWrapper<AreaInChargePO>().eq("postman_id", postmanId))
                .stream()
                .map(AreaInChargePO::getAreaId).collect(Collectors.toList());
    }
}
