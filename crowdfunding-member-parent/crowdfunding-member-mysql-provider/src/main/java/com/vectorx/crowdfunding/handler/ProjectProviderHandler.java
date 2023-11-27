package com.vectorx.crowdfunding.handler;

import com.vectorx.crowdfunding.service.api.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectProviderHandler
{
    @Autowired
    private ProjectService projectService;
    
}
