package org.warren.sca.rsc.order.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.warren.sca.rsc.order.pojo.po.OrderMasterPO;

@Mapper
public interface OrderMasterDOMapper  extends BaseMapper<OrderMasterPO> {

}
