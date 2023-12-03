package com.vectorx.crowdfunding.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortalProjectVO
{
    private Integer projectId;

    private String projectName;

    private Integer money;

    private String deploydate;

    private Integer supporter;

    private Integer completion;

    private String headerPicturePath;
}
