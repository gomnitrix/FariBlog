package com.gomnitrix.commons.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gomnitrix.commons.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Map;

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

    @Update("update t_user set browser = #{browser}, os = #{os}, last_login_ip = #{last_login_ip}, last_login_time = #{last_login_time}, login_count = login_count + 1} where uid = #{uid}")
    public void updateLoginInfo(User user);

}
