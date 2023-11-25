package com.vectorx.crowdfunding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties("short.message")
public class ShortMessageProperties
{
    String url;
    String signName;
    String templateCode;
    String accessKeyId;
    String accessKeySecret;
}
