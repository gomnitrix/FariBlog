package com.gomnitrix.commons.service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gomnitrix.commons.dto.BlogDto;
import com.gomnitrix.commons.dto.ImageDto;
import com.gomnitrix.commons.entity.Image;
import com.gomnitrix.commons.mapper.ImageConvertMapper;
import com.gomnitrix.commons.mapper.ImageMapper;
import com.gomnitrix.commons.service.ImageService;
import com.gomnitrix.commons.service.UuidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author gomnitrix
 * @since 2021-01-23
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {
    @Value("${qiniu.domain}")
    private String domain;

    ImageConvertMapper converter = ImageConvertMapper.INSTANCE;

    @Autowired
    UuidService uuidService;

    @Override
    public List<String> getCoverUrlByBlogs(List<BlogDto> blogDtos) {
        List<String> coverUrls = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (BlogDto blogDto : blogDtos) {
            String key = getById(blogDto.getCoverUid()).getImgUrl();
            builder.append("http://").append(domain).append("/").append(key);
            coverUrls.add(builder.toString());
            builder.setLength(0);
        }
        return coverUrls;
    }

    @Override
    public void updateCoverBlogUid(BlogDto blogDto) {
        Long coverUid = blogDto.getCoverUid();
        if (coverUid == null || coverUid == 0) return;
        UpdateWrapper<Image> wrapper = new UpdateWrapper<>();
        wrapper.eq("uid", coverUid).set("blog_uid", blogDto.getUid());
        update(wrapper);
    }

    @Override
    public void deprecateImage(Long uid) {
        if (uid == null || uid == 0) return;
        UpdateWrapper<Image> wrapper = new UpdateWrapper<>();
        wrapper.eq("uid", uid).set("status", 0);
        update(wrapper);
    }

    @Override
    public long saveImage(ImageDto imageDto) {
        if (imageDto.getUid() == null) {
            imageDto.setUid(uuidService.getUid());
        }
        Image image = converter.fromImageDto(imageDto);
        this.save(image);
        return image.getUid();
    }

    @Override
    public String getImageUrl(long uid) {
        Image image = getById(uid);
        String key = image.getImgUrl();
        return "http://" + domain + "/" + key;
    }
}
