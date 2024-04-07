package com.sonnet.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sonnet.model.domain.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void testFindAll(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users.size());
    }

    @Test
    void testLogicDelete(){
        int i = userMapper.deleteById(2);
        System.out.println(i);
    }

}