package com.hmdp.controller;


import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.service.IShopTypeService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/shop-type")
public class
ShopTypeController {
    @Resource
    private IShopTypeService typeService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("list")
    public Result queryTypeList() {
        /*List<ShopType> typeList = typeService
                .query().orderByAsc("sort").list();
        return Result.ok(typeList);*/
        //重点是没想到hutool里面的JSONUtil可以将list集合转换成字符串
        String key = "cachShopType";
        // 1.从redis中查询数据
        String typeJson = stringRedisTemplate.opsForValue().get(key);
        // 2.判断是否存在
        if (typeJson != null){
            // 3.存在，返回数据
            List<ShopType> shopTypeList = JSONUtil.toList(typeJson, ShopType.class);
            return Result.ok(shopTypeList);
        }
        // 4.不存在，查询数据库
        List<ShopType> list = typeService.query().orderByAsc("sort").list();
        // 5.不存在，返回错误
        if (list == null) {
            return Result.fail("分类不存在");
        }
        // 6.存在存入redis中
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(list));
        // 7.返回
        return Result.ok(list);
    }
}
