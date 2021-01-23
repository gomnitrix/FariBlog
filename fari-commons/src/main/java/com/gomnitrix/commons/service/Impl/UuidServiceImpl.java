package com.gomnitrix.commons.service.Impl;

import com.github.wujun234.uid.impl.CachedUidGenerator;
import com.gomnitrix.commons.service.UuidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UuidServiceImpl implements UuidService {
    private CachedUidGenerator generator;

    @Autowired
    public void setGenerator(CachedUidGenerator cachedUidGenerator) {
        this.generator = cachedUidGenerator;
    }

    @Override
    public long getUid() {
        return this.generator.getUID();
    }
}
