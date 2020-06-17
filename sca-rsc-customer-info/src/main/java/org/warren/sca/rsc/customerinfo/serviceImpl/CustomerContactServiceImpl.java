package org.warren.sca.rsc.customerinfo.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.warren.sca.rsc.common.customerinfo.CustomerContactService;
import org.warren.sca.rsc.common.customerinfo.dto.ContactDTO;
import org.warren.sca.rsc.customerinfo.mapper.ContactMapper;
import org.warren.sca.rsc.customerinfo.pojo.po.ContactPO;

import java.util.List;
import java.util.Optional;

@org.apache.dubbo.config.annotation.Service
public class CustomerContactServiceImpl implements CustomerContactService {

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public void addContact(ContactDTO contact) {

    }

    @Override
    public void updateContact(ContactDTO contact) {

    }

    @Override
    public void deleteContactById(int contactId) {

    }

    @Override
    public ContactDTO getContactByContactId(int contactId) {
        ContactPO contactPO = contactMapper.selectById(contactId);
        if (contactPO == null)
            return null;
        ContactDTO contactDTO = new ContactDTO();
        BeanUtils.copyProperties(contactPO, contactDTO);
        return contactDTO;
    }

    @Override
    public List<ContactDTO> getContactsByCustomerId(int customerId) {
        return null;
    }

}
