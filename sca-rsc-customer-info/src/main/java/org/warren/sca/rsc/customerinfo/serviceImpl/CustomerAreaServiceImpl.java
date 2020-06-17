package org.warren.sca.rsc.customerinfo.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.warren.sca.rsc.common.customerinfo.CustomerAreaService;
import org.warren.sca.rsc.customerinfo.mapper.AreaMapper;
import org.warren.sca.rsc.customerinfo.pojo.po.AreaPO;

import java.util.Comparator;

@org.apache.dubbo.config.annotation.Service
public class CustomerAreaServiceImpl implements CustomerAreaService {

    @Autowired
    private AreaMapper areaMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getAddressStringByAreaId(int id) {
        StringBuilder builder = new StringBuilder();
        areaMapper.selectList(
                new QueryWrapper<AreaPO>()
                        .eq("id", id).or()
                        .eq("id", id/100*100).or()
                        .eq("id", id/10000*10000)
        ).stream().sorted(Comparator.comparingInt(AreaPO::getId)).forEach(builder::append);
        logger.info(builder.toString());
        return builder.toString();
    }

}
