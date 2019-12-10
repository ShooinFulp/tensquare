package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/12/11 12:01 上午
 */
@ConfigurationProperties("jwt.config")
public class JwtUtil {
    private String key;

    private long ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    /**
     * 生成Token
     * @param id
     * @param subject
     * @param roles
     * @return
     */
    public String createJwt(String id, String subject, String roles) {
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key)
                .claim("reles", roles);
        if (ttl > 0) {
            builder.setExpiration(new Date(System.currentTimeMillis() + ttl));
        }
        return builder.compact();

    }

    /**
     * 解析JWT
     * @param token
     * @return
     */
    public Claims pareseJwt(String token)
    {
        return Jwts.parser().setSigningKey(key).parseClaimsJwt(token).getBody();
    }
}
