package org.warren.sca.rsc.common.customerinfo;

import org.warren.sca.rsc.common.customerinfo.dto.CustomerDTO;
import org.warren.sca.rsc.common.exception.CheckedException;

public interface CustomerBaseInfoService {

    String authenticate(String username, String password) throws CheckedException;

    void register(String username, String password) throws CheckedException;

    CustomerDTO getCustomerBaseInfo(int id);

    void updateCustomerBaseInfo(CustomerDTO customerDTO);


}
