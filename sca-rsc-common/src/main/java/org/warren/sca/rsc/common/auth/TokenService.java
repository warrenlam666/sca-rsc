package org.warren.sca.rsc.common.auth;

public interface TokenService {

    boolean verityToken(String token);

    int getIdByToken(String token);

}
