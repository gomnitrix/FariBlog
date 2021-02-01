package com.gomnitrix.commons.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gomnitrix.commons.configuration.GeneralConfig;
import com.gomnitrix.commons.dto.UserDto;
import com.gomnitrix.commons.entity.User;
import com.gomnitrix.commons.exception.UserNotFoundException;
import com.gomnitrix.commons.mapper.UserConvertMapper;
import com.gomnitrix.commons.mapper.UserMapper;
import com.gomnitrix.commons.service.UuidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UuidService uuidService;

    UserConvertMapper converter = UserConvertMapper.INSTANCE;

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
        User user = converter.fromUserDto(userDto);
        user.setPassWord(encoder.encode(user.getPassword()));
        user.setUid(uuidService.getUid());
        user.setSource(GeneralConfig.FARIBLOG);
        userMapper.insert(user);
    }

    public void updateUserInfo(UserDto userDto){
        if(userDto.getUid() == null){
            throw new UserNotFoundException();
        }
        if(userDto.getPassWord()!=null){
            userDto.setPassWord(encoder.encode(userDto.getPassWord()));
        }
        User user = getById(userDto.getUid());
        //TODO Avatar delete & Avatar type: String -> Long in User.java
        updateById(converter.fromUserDto(userDto));
    }
}
