package com.hmdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.entity.User;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IUserService extends IService<User> {



    Result login(LoginFormDTO loginForm, HttpSession session);


    /***
     * 生成验证码的方法，会将手机号码加上前缀作为key，验证码本身作为value保存到redis中。
     * 并且将验证码打印到控制台
     * @param phone
     * @param session
     * @return
     */
    Result sendCode(String phone, HttpSession session);

    Result sign();

    Result signCount();
}
