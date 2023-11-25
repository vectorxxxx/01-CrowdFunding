package com.vectorx.crowdfunding.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCenterVO implements Serializable
{
    private Integer id;
    private String loginacct;
    private String username;
    private String email;
}
