package com.vectorx.crowdfunding.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressVO implements Serializable
{
    private static final long serialVersionUID = 4780880362141479971L;
    
    private Integer id;

    private String consigneeName;

    private String phoneNum;

    private String address;

    private Integer memberId;
}
