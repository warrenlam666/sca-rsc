package org.warren.sca.rsc.postmaninfo.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.warren.sca.rsc.common.postmaninfo.PostmanClockService;
import org.warren.sca.rsc.postmaninfo.constant.PostmanClockConstant;
import org.warren.sca.rsc.postmaninfo.message.ClockMessage;
import org.warren.sca.rsc.postmaninfo.message.PostmanClockSource;

import java.sql.Timestamp;
import java.util.Date;

@org.apache.dubbo.config.annotation.Service
public class PostmanClockServiceImpl implements PostmanClockService {

    @Autowired
    private PostmanClockSource postmanClockSource;

    private final static Logger logger = LoggerFactory.getLogger(PostmanClockServiceImpl.class);

    @Override
    public void clockIn(int postmanId){
        clock(postmanId, PostmanClockConstant.POSTMAN_CLOCK_IN);
    }

    @Override
    public void clockOut(int postmanId) {
        clock(postmanId, PostmanClockConstant.POSTMAN_CLOCK_OUT);
    }

    public void clock(int postmanId, int clockType){
        //这里应该先要判断是否已经签到过，如果重复签就抛异常，但是太懒不想写
        logger.info("快递员{}上下班打卡", postmanId);
        ClockMessage message = new ClockMessage(postmanId,new Timestamp(new Date().getTime()), clockType);
        postmanClockSource.postmanClockOutput().send(MessageBuilder.withPayload(message).build());
    }

}


