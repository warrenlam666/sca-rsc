package org.warren.sca.rsc.postmaninfo.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.warren.sca.rsc.common.exception.CheckedException;
import org.warren.sca.rsc.common.postmaninfo.PostmanAccountService;
import org.warren.sca.rsc.postmaninfo.mapper.PostmanMapper;
import org.warren.sca.rsc.postmaninfo.pojo.po.PostmanPO;

@org.apache.dubbo.config.annotation.Service
public class PostmanAccountServiceImpl implements PostmanAccountService {

    @Autowired
    private PostmanMapper postmanMapper;

    private PostmanTokenServiceImpl postmanTokenService = new PostmanTokenServiceImpl();

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String authenticate(String username, String password) throws CheckedException {
        PostmanPO res = postmanMapper.selectOne(new QueryWrapper<PostmanPO>().eq("username", username));
        if (res == null || !bCryptPasswordEncoder.matches(password, res.getPassword()))
            throw new CheckedException("用户名或密码错误");
        return postmanTokenService.createToken(res);
    }

    @Override
    public void register(String username, String password) throws CheckedException {
        try {
            String encryptionPassword = bCryptPasswordEncoder.encode(password);
            //username 建立唯一索引，如果用户名重复将抛出异常
            postmanMapper.insert(new PostmanPO(username, encryptionPassword));
        }catch (Exception e){
            throw new CheckedException("注册失败");
        }
    }
}
