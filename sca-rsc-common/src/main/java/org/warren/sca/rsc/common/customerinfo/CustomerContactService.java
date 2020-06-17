package org.warren.sca.rsc.common.customerinfo;

import org.warren.sca.rsc.common.customerinfo.dto.ContactDTO;

import java.util.List;

public interface CustomerContactService {

    void addContact(ContactDTO contact);

    void updateContact(ContactDTO contact);

    void deleteContactById(int contactId);

    ContactDTO getContactByContactId(int contactId);

    List<ContactDTO> getContactsByCustomerId(int customerId);

}
