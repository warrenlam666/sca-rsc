package org.warren.sca.rsc.distribution.serviceImpl;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.warren.sca.rsc.common.distribution.DistributionOrderPoolService;
import org.warren.sca.rsc.common.order.OrderDetailService;
import org.warren.sca.rsc.distribution.constant.OrderPoolConstant;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@org.apache.dubbo.config.annotation.Service
public class DistributionOrderPoolServiceImpl implements DistributionOrderPoolService {

    @Reference
    private OrderDetailService orderDetailService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public List<Long> getOrders() {
        return Objects.requireNonNull(
                redisTemplate.opsForSet()
                        .members(OrderPoolConstant.REDIS_ORDER_POOL))
                .stream()
                .map(Long::parseLong).collect(Collectors.toList());
    }

    @Override
    public void appendOrder(long id) {
        redisTemplate.opsForSet().add(OrderPoolConstant.REDIS_ORDER_POOL, String.valueOf(id));
    }

    @Override
    public boolean acquireOrder(int postman, long id) {
        /*
        利用redis单线程执行客户端命令的原理，
        如果删除记录数量为1说明当前客户端成功获得了订单，
        如果其他客户端抢先获得了订单（从redis-set里删除了该订单id记录）,
        则该操作将返回0
        在不加锁情况下保证了线程安全
        */
        Long count = redisTemplate.opsForSet().remove(OrderPoolConstant.REDIS_ORDER_POOL, String.valueOf(id));
        if (count != null && count.equals(1L)){
            orderDetailService.updateToDDP(id, postman);
            return true;
        }
        return false;
    }
}
