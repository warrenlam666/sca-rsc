package org.warren.sca.rsc.postmaninfo.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.warren.sca.rsc.common.postmaninfo.PostmanWorkService;
import org.warren.sca.rsc.common.postmaninfo.dto.PostmanWorkDTO;
import org.warren.sca.rsc.postmaninfo.constant.PostmanWorkStatus;
import org.warren.sca.rsc.postmaninfo.constant.PostmanWorkType;
import org.warren.sca.rsc.postmaninfo.mapper.PostmanWorkMapper;
import org.warren.sca.rsc.postmaninfo.message.OldOrderSource;
import org.warren.sca.rsc.postmaninfo.pojo.po.PostmanWorkPO;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
@org.apache.dubbo.config.annotation.Service
public class PostmanWorkServiceImpl implements PostmanWorkService {

    @Autowired
    private PostmanWorkMapper postmanWorkMapper;

    @Autowired
    private OldOrderSource oldOrderSource;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPickUpTask(long orderId, int postmanId) {
        System.out.println("xid: "+ RootContext.getXID());
        postmanWorkMapper.insert(new PostmanWorkPO(postmanId, orderId, PostmanWorkType.PICK_UP, PostmanWorkStatus.UNDONE, new Timestamp(System.currentTimeMillis())));
    }

    @Override
    public void addDistributionTask(long orderId, int postmanId) {
        postmanWorkMapper.insert(new PostmanWorkPO(postmanId, orderId, PostmanWorkType.DISPATCHER, PostmanWorkStatus.UNDONE, new Timestamp(System.currentTimeMillis())));
    }

    @Override
    public void finishPickUpTask(int postmanId, long orderId) {
        postmanWorkMapper.insert(new PostmanWorkPO(postmanId, orderId, PostmanWorkType.PICK_UP, PostmanWorkStatus.FINISH, new Timestamp(System.currentTimeMillis())));
        logger.info("向消息队列投递包裹发出消息:{}", orderId);
        oldOrderSource.oldOrderOutput().send(MessageBuilder.withPayload(orderId).build());
    }

    @Override
    public void finishDistributionTask(int postmanId, long orderId) {
        postmanWorkMapper.insert(new PostmanWorkPO(postmanId, orderId, PostmanWorkType.DISPATCHER, PostmanWorkStatus.FINISH, new Timestamp(System.currentTimeMillis())));
    }

    @Override
    public List<PostmanWorkDTO> listAllTask(int postmanId, int offset, int count) {
        return boxingPOToDTO(
                postmanWorkMapper.selectList(new QueryWrapper<PostmanWorkPO>().eq("postman_id", postmanId))
        );
    }

    @Override
    public List<PostmanWorkDTO> listAllUnDoneTask(int postmanId) {
        return boxingPOToDTO(
                postmanWorkMapper.selectList(new QueryWrapper<PostmanWorkPO>().eq("postman_id", postmanId).eq("status", PostmanWorkStatus.UNDONE))
        );
    }

    public List<PostmanWorkDTO> boxingPOToDTO(List<PostmanWorkPO> pos){
        return pos.stream()
                .map(postmanWorkPO -> {
                    PostmanWorkDTO dto = new PostmanWorkDTO();
                    BeanUtils.copyProperties(postmanWorkPO, dto);
                    return dto;
                }).collect(Collectors.toList());
    }
}
