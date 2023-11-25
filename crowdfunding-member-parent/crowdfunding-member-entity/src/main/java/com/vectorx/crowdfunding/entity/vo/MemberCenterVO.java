package com.vectorx.crowdfunding.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCenterVO
{
    private Integer id;
    private String loginacct;
    private String username;
    private String email;
}
