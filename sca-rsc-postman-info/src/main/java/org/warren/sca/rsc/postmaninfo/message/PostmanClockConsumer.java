package org.warren.sca.rsc.postmaninfo.message;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.warren.sca.rsc.postmaninfo.mapper.PostmanClockMapper;
import org.warren.sca.rsc.postmaninfo.pojo.po.PostmanClockPO;

@Component
public class PostmanClockConsumer {

    @Autowired
    private PostmanClockMapper postmanClockMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @StreamListener(PostmanClockSource.POSTMAN_CLOCK_INPUT)
    public void onMessage(@Payload ClockMessage clockMessage) {
        if (isMessageAlreadyProcessed(clockMessage))
            return;
        logger.info("[RSC][快递员打卡消息消费者] 消息内容：{}", clockMessage);
        PostmanClockPO postmanClockPO = new PostmanClockPO();
        BeanUtils.copyProperties(clockMessage, postmanClockPO);
        postmanClockMapper.insert(postmanClockPO);
    }

    /* 检查消息是否重复消费 */
    public boolean isMessageAlreadyProcessed(ClockMessage message){
        return postmanClockMapper.selectOne(
                new QueryWrapper<PostmanClockPO>()
                        .eq("postman_id", message.getPostmanId())
                        .eq("time", message.getTime())
                        .eq("type", message.getType())
        ) != null;
    }

}
