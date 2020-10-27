package com.gomnitrix.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gomnitrix.commons.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author gomnitrix
 * @since 2020-10-21
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

}
