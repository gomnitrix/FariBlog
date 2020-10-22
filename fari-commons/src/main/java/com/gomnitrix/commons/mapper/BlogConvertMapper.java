package com.gomnitrix.commons.mapper;

import com.gomnitrix.commons.dto.BlogDto;
import com.gomnitrix.commons.entity.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BlogConvertMapper {
    BlogConvertMapper INSTANCE = Mappers.getMapper(BlogConvertMapper.class);

    BlogDto toBlogDto(Blog blog);

    Blog fromBlogDto(BlogDto blogDto);
}
