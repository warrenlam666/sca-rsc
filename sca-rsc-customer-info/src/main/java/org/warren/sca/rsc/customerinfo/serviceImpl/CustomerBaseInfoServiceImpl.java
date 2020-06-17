package org.warren.sca.rsc.customerinfo.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.warren.sca.rsc.common.customerinfo.CustomerBaseInfoService;
import org.warren.sca.rsc.common.customerinfo.dto.CustomerDTO;
import org.warren.sca.rsc.customerinfo.pojo.po.CustomerDO;
import org.warren.sca.rsc.customerinfo.mapper.CustomerMapper;
import org.warren.sca.rsc.common.exception.CheckedException;


@org.apache.dubbo.config.annotation.Service
public class CustomerBaseInfoServiceImpl implements CustomerBaseInfoService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private TokenUtils tokenUtils;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String authenticate(String username, String password) throws CheckedException{
        CustomerDO res = customerMapper.selectOne(new QueryWrapper<CustomerDO>().eq("username", username));
        if (res == null || !bCryptPasswordEncoder.matches(password, res.getPassword()))
            throw new CheckedException("用户名或密码错误");
        return tokenUtils.createToken(res);
    }

    @Override
    public void register(String username, String password) throws CheckedException{
        try {
            String encryptionPassword = bCryptPasswordEncoder.encode(password);
            //username 建立唯一索引，如果用户名重复将抛出异常
            customerMapper.insert(new CustomerDO(username, encryptionPassword));
        }catch (Exception e){
            throw new CheckedException("注册失败");
        }

    }

    @Override
    public CustomerDTO getCustomerBaseInfo(int id) {
        CustomerDO customerDO =  customerMapper.selectById(id);
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customerDO, customerDTO);
        return customerDTO;
    }

    @Override
    public void updateCustomerBaseInfo(CustomerDTO customerDTO) {
        CustomerDO customerDO = new CustomerDO();
        BeanUtils.copyProperties(customerDTO, customerDO);
        customerMapper.updateById(customerDO);
    }
}
