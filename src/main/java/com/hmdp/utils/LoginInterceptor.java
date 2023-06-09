package com.hmdp.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.hmdp.dto.UserDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/***
 * 登录拦截器
 * 判断用户是否处于登录状态
 */
public class LoginInterceptor implements HandlerInterceptor {
    private StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor() {
    }

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       /* //1. 获取session
        // 1.获取请求头中的token
        //HttpSession session = request.getSession();
        String token = request.getHeader("authorization");
        if(StrUtil.isBlank(token)){
            response.setStatus(401);
            return false;
        }
        //2.获取session中的用户
        // 2.基于TOKEN获取redis中的用户
        //Object user = session.getAttribute("user");
        String key = RedisConstants.LOGIN_USER_KEY+ token;
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);

        //3. 判断用户是否存在
        *//*if (user == null){
            //4. 不存在，拦截
            response.setStatus(401);
            return false;
        }*//*
        if(userMap.isEmpty()){
            response.setStatus(401);
            return false;
        }


        //5. 存在 保存用户信息到ThreadLocal,不同线程
        //5.将查询到的Hash数据转为UserDTO对象
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(),false);
        UserHolder.saveUser(userDTO);
        //6. 放行

        //7.刷新Token有效期
        stringRedisTemplate.expire(key, RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);
        return true;*/
        //1.判断是否需要拦截就行（ThreadLoacl中是否有用户）
        if(UserHolder.getUser() == null){
            //没有用户就拦截
            response.setStatus(401);
            return false;
        }
        //有就放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除用户
        UserHolder.removeUser();
    }
}
