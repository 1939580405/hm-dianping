package com.hmdp.controller;


import com.hmdp.dto.Result;
import com.hmdp.service.IFollowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 */
@RestController
@RequestMapping("/follow")
public class FollowController {
    @Resource
    private IFollowService followService;

    /**
     * 用户主动点击关注或者取消关注的时候发过来的请求
     * @param followUserId 笔记发布者的用户id
     * @param isFollow boolean类型，看是过来关注的还是取消关注的
     * @return
     */
    @PutMapping("/{id}/{isFollow}")
    public Result follow(@PathVariable("id") Long followUserId, @PathVariable("isFollow") Boolean isFollow) {
        return followService.follow(followUserId, isFollow);
    }

    /***
     * 用户进入笔记后会自动发的请求，判断当前用户是否关注了发布这个笔记的用户
     * @param followUserId 这里的id是笔记发布者的用户id
     * @return
     */
    @GetMapping("/or/not/{id}")
    public Result isFollow(@PathVariable("id") Long followUserId) {
        return followService.isFollow(followUserId);
    }


}
