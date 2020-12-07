package com.gomnitrix.commons.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gomnitrix.commons.dto.UserDto;
import com.gomnitrix.commons.entity.User;
import com.gomnitrix.commons.exception.UserNotFoundException;
import com.gomnitrix.commons.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UuidService uuidService;

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

    public void updateLoginInfoById(User user){
        userMapper.updateLoginInfo(user);
    }

    public void register(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        user.setPassWord(encoder.encode(userDto.getPassWord()));
        user.setUuid(uuidService.getUid());
        LocalDateTime current = LocalDateTime.now();
        user.setCreateTime(current);
        user.setUpdateTime(current);
        userMapper.insert(user);
    }
}
