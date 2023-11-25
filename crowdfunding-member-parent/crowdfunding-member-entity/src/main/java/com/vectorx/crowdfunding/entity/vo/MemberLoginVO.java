package com.vectorx.crowdfunding.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginVO
{
    private String loginacct;
    private String userpswd;
}
