package com.gomnitrix.commons.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gomnitrix.commons.dto.BlogDto;
import com.gomnitrix.commons.dto.ImageDto;
import com.gomnitrix.commons.entity.Image;

import java.util.List;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author gomnitrix
 * @since 2021-01-23
 */
public interface ImageService extends IService<Image> {
    List<String> getCoverUrlByBlogs(List<BlogDto> blogDtos);
    void updateCoverBlogUid(BlogDto blogDto);
    void deprecateImage(Long uid);
    long saveImage(ImageDto imageDto);
    String getImageUrl(long uid);
}
