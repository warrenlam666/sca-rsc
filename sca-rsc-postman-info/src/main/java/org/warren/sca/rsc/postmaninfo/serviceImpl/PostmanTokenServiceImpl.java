package org.warren.sca.rsc.postmaninfo.serviceImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.warren.sca.rsc.common.auth.TokenService;
import org.warren.sca.rsc.postmaninfo.pojo.po.PostmanPO;

@Component
@org.apache.dubbo.config.annotation.Service(group = "postman-auth")
public class PostmanTokenServiceImpl implements TokenService {

    private static final String TOKEN_SECRETE_PUBLIC_KEY = "HELLO_WARREN";

    private static Logger logger = LoggerFactory.getLogger(PostmanTokenServiceImpl.class);

    private static Algorithm algorithm = Algorithm.HMAC384(TOKEN_SECRETE_PUBLIC_KEY);

    public String createToken(PostmanPO postmanPO){
        Algorithm algorithm = Algorithm.HMAC384(TOKEN_SECRETE_PUBLIC_KEY);
        return JWT.create().
                withIssuer("WARREN").
                withClaim("id", postmanPO.getId()).
                withClaim("username", postmanPO.getUsername()).sign(algorithm);
    }

    @Override
    public boolean verityToken(String token){
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
