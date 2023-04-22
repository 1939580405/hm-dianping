package com.hmdp.utils;

import com.hmdp.dto.UserDTO;

/***
 * UserHolder里有一个静态常量，ThreadLocal，里面存放UserDTO，不同的线程对应不同的
 */
public class UserHolder {
    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();

    public static void saveUser(UserDTO user){
        tl.set(user);
    }

    public static UserDTO getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
