package org.warren.sca.rsc.common.postmaninfo;

import org.warren.sca.rsc.common.exception.CheckedException;

public interface PostmanAccountService {

    String authenticate(String username, String password) throws CheckedException;

    void register(String username, String password) throws CheckedException;

}
