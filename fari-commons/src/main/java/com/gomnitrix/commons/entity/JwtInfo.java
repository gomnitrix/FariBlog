package com.gomnitrix.commons.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class JwtInfo {
    private String userName;
    private String userId;
    private String tokenId;

    public JwtInfo(String userName, String userId, String tokenId) {
        this.userName = userName;
        this.userId = userId;
        this.tokenId = tokenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtInfo jwtInfo = (JwtInfo) o;
        return Objects.equals(userName, jwtInfo.userName) &&
                Objects.equals(userId, jwtInfo.userId) &&
                Objects.equals(tokenId, jwtInfo.tokenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userId, tokenId);
    }
}
