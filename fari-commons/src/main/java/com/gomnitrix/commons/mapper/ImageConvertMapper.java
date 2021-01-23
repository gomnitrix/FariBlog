package com.gomnitrix.commons.mapper;

import com.gomnitrix.commons.dto.ImageDto;
import com.gomnitrix.commons.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageConvertMapper {
    ImageConvertMapper INSTANCE = Mappers.getMapper(ImageConvertMapper.class);

    ImageDto toImageDto(Image image);

    List<ImageDto> toImageDtos(List<Image> images);

    Image fromImageDto(ImageDto imageDto);

    List<Image> fromImageDtos(List<ImageDto> imageDtos);
}
