package org.warren.sca.rsc.customerinfo.serviceImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.warren.sca.rsc.common.auth.TokenService;
import org.warren.sca.rsc.customerinfo.pojo.po.CustomerDO;

@Component
@org.apache.dubbo.config.annotation.Service(group = "customer-auth")
public class TokenUtils implements TokenService {

    private static String TOKEN_SECRETE_PUBLIC_KEY = "HELLO_WARREN";

    private static Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    public String createToken(CustomerDO customerDO){
        Algorithm algorithm = Algorithm.HMAC384(TokenUtils.TOKEN_SECRETE_PUBLIC_KEY);
        return JWT.create().
                withIssuer("WARREN").
                withClaim("id", customerDO.getId()).
                withClaim("username", customerDO.getUsername()).sign(algorithm);
    }

    @Override
    public boolean verityToken(String token){
        Algorithm algorithm = Algorithm.HMAC384(TokenUtils.TOKEN_SECRETE_PUBLIC_KEY);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("WARREN").build();
        try {
            verifier.verify(token);
        }catch (JWTVerificationException e){
            logger.info(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public int getIdByToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("id").asInt();
    }
}
