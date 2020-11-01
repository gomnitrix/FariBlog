package com.gomnitrix.commons.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gomnitrix.commons.entity.User;
import com.gomnitrix.commons.exception.UserNotFoundException;
import com.gomnitrix.commons.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BlogUserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", s);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

//    public List<User> getAllUsers(){};
}
