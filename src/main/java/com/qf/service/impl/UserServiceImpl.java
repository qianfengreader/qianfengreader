package com.qf.service.impl;

import com.qf.common.BaseResp;
import com.qf.dao.UserRepository;
import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;//jpa+自定义jpa
    @Autowired
    RedisUtils redisUtils;//自定义redis工具类
    @Autowired
    JavaMailSender javaMailSender;//邮件发送类

    @Value("${spring.mail.username}")//.yml 文件导入发送者邮箱
    private String from;

    //发送邮件
    @Override
    public BaseResp sendMail(String email) {
        BaseResp baseResp = new BaseResp();
        if (email != null){
            User byEmail = userRepository.findByEmail(email);
            if (byEmail != null){//说明邮箱已存在
                baseResp.setCode(201);
                baseResp.setMessage("邮箱已存在");
                return baseResp;
            }
            Random random = new Random();
            StringBuffer code = new StringBuffer();
            for (int i = 0; i < 4; i++) {
                int num = random.nextInt(10);
                code.append(num);
            }
            //得到4位验证码code
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("子龙快看");
            simpleMailMessage.setText(code.toString());
            javaMailSender.send(simpleMailMessage);
            //发送后 存入 redis
            redisUtils.set(email,code.toString());
            //设置过期时间 5分钟
            redisUtils.expire(email,300);

            baseResp.setCode(200);
            baseResp.setMessage("发送成功");
            return baseResp;
        }
        baseResp.setMessage("邮箱不能为空");
        baseResp.setCode(202);
        return baseResp;
    }

    //注册
    @Override
    public BaseResp registy(User user) {
        BaseResp baseResp = new BaseResp();
        String username = user.getUsername();
        User byUsername = userRepository.findByUsername(username);
        if (byUsername != null){
            baseResp.setCode(201);
            baseResp.setMessage("用户名已被注册");
            return baseResp;
        }
        //判断验证
        String code = user.getCode();
        String email = user.getEmail();
        Object o = redisUtils.get(email);
        if (o != null && code.equals(o)){//用户输入 与 库中的相等
            userRepository.saveAndFlush(user);//注册
            baseResp.setMessage("注册成功");
            baseResp.setCode(200);
            return baseResp;
        }
        baseResp.setCode(202);
        baseResp.setMessage("注册失败");
        return baseResp;
    }
    //登陆
    @Override
    public BaseResp login(User user) {
        BaseResp baseResp = new BaseResp();
        String username = user.getUsername();//用户输入账户
        String password = user.getPassword();//用户输入密码
        //从库中查找 用户输入的 name pass
        User byUsername = userRepository.findByUsername(username);
        if (byUsername != null && password.equals(byUsername.getPassword())){
            //用户存在 且密码相等
            //密码比对通过，放行，将用户信息存入到redis中，设置token值
            //生成用户唯一标识符
            UUID uuid = UUID.randomUUID();
            redisUtils.set(uuid.toString(),user);
            baseResp.setData(uuid.toString());
            baseResp.setCode(200);
            baseResp.setMessage("登陆成功");
            return baseResp;
        }
        baseResp.setMessage("信息错误");
        baseResp.setCode(201);
        return baseResp;

    }

    @Override
    public BaseResp findByUsername(String username) {

        User byUsername = userRepository.findByUsername(username);
        BaseResp baseResp = new BaseResp();
        if (byUsername !=null){
            baseResp.setData(byUsername);
            baseResp.setCode(200);
            baseResp.setMessage("查询成功");
        }else {
            baseResp.setCode(201);
            baseResp.setMessage("账号不存在");
        }
        return baseResp;
    }
}
