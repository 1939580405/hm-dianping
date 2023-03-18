package com.hmdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmdp.dto.Result;
import com.hmdp.entity.Blog;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IBlogService extends IService<Blog> {

    Result queryHotBlog(Integer current);

    Result queryBlogById(Long id);

    Result likeBlog(Long id);

    Result queryBlogLikes(Long id);

    /**
     * 保存的笔记的方法，目的有三个
     * 1.将笔记发布者的id放到blog对象中
     * 2.将blog存到数据库中
     * 3.为笔记发布者的粉丝在redis中创建一个sorted set，并将笔记存进去，以后发布笔记也会存进去
     * @param blog 前端用户发过来不带用户id的笔记
     * @return
     */
    Result saveBlog(Blog blog);

    Result queryBlogOfFollow(Long max, Integer offset);



}
