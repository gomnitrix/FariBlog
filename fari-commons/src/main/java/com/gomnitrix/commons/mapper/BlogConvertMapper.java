package com.gomnitrix.commons.mapper;

import com.gomnitrix.commons.dto.BlogDto;
import com.gomnitrix.commons.entity.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlogConvertMapper {
    BlogConvertMapper INSTANCE = Mappers.getMapper(BlogConvertMapper.class);

    BlogDto toBlogDto(Blog blog);

    List<BlogDto> toBlogDtos(List<Blog> blogs);

    Blog fromBlogDto(BlogDto blogDto);

    List<Blog> fromBlogDtos(List<BlogDto> blogDtos);
}
