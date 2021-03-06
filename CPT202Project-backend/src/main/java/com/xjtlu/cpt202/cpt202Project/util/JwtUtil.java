package com.xjtlu.cpt202.cpt202Project.util;

import com.xjtlu.cpt202.cpt202Project.exceptions.UserException;
import com.xjtlu.cpt202.cpt202Project.entity.Audience;
import io.jsonwebtoken.*;

import java.security.Key;

import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import com.xjtlu.cpt202.cpt202Project.exceptions.UserException.ResultCode;

@Slf4j
public class JwtUtil {

    public static final String AUTH_HEADER_KEY = "Authorization";

    public static final Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * 构建jwt
     *
     * @param userId
     * @param username
     * @param role
     * @param audience
     * @return
     */
    public static String createToken(String userId, String username, String role, Audience audience) throws UserException {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            //生成签名密钥
//            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
//            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                    // 可以将基本不重要的对象信息放到claims
                    .claim("role", role)
                    .claim("userId", userId)
                    .setSubject(username)           // 代表这个JWT的主体，即它的所有人
                    .setIssuer(audience.getIss())              // 代表这个JWT的签发主体；
                    .setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                    .setAudience(audience.getAud())          // 代表这个JWT的接收对象；
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            int TTLMillis = audience.getExpiresSecond();
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                        .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
            }

            //生成JWT
            return builder.compact();
        } catch (Exception e) {
            log.error("签名失败", e);
            throw new UserException(ResultCode.PERMISSION_SIGNATURE_ERROR);
        }


    }

    /**
     * 解析jwt
     *
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, Key key) throws UserException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (ExpiredJwtException eje) {
            log.error("===== Token过期 =====", eje);
            throw new UserException(ResultCode.PERMISSION_TOKEN_EXPIRED);
        } catch (Exception e) {
            log.error("===== token解析异常 =====", e);
            throw new UserException(ResultCode.PERMISSION_TOKEN_INVALID);
        }
    }

    /**
     * 从token中获取用户名
     *
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUsername(String token, Key base64Security) throws UserException {
        return parseJWT(token, base64Security).getSubject();
    }

    /**
     * 从token中获取用户ID
     *
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUserId(String token, Key base64Security) throws UserException {
        String userId = parseJWT(token, base64Security).get("userId", String.class);
        return Base64Util.decode(userId);
    }

    /**
     * 是否已过期
     *
     * @param token
     * @param base64Security
     * @return
     */
    public static boolean isExpiration(String token, Key base64Security) throws UserException {
        return parseJWT(token, base64Security).getExpiration().before(new Date());
    }









}
