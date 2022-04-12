package com.xjtlu.cpt202.cpt202Project.entity;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {

    //代表这个JWT的接收对象,存入audience
    private String aud;
    private String base64Secret;
    //JWT的签发主体，存入issuer
    private String iss;
    private int expiresSecond;

}
