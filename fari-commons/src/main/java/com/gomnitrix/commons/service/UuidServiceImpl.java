package com.gomnitrix.commons.service;

import com.github.wujun234.uid.impl.CachedUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UuidServiceImpl implements UuidService{
    private CachedUidGenerator generator;

    @Autowired
    public void setGenerator(CachedUidGenerator cachedUidGenerator) {
        this.generator = cachedUidGenerator;
    }

    @Override
    public String getUid() {
        long uid = this.generator.getUID();
        return String.valueOf(uid);
    }
}
