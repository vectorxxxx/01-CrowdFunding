package com.vectorx.crowdfunding.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegisterVO
{
    private String loginacct;
    private String userpswd;
    private String username;
    private String email;
    private String phoneNum;
    private String captcha;
}
