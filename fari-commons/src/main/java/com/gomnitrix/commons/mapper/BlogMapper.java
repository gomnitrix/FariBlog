package com.gomnitrix.commons.mapper;

import com.gomnitrix.commons.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 博客表 Mapper 接口
 * </p>
 *
 * @author gomnitrix
 * @since 2020-10-21
 */
@Mapper
@Component
public interface BlogMapper extends BaseMapper<Blog> {

}
