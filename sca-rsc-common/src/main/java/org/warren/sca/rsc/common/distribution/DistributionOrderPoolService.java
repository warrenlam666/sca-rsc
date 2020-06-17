package org.warren.sca.rsc.common.distribution;

import java.util.List;

public interface DistributionOrderPoolService {

    List<Long> getOrders();

    void appendOrder(long id);

    boolean acquireOrder(int postman, long id);

}
