package com.vectorx.crowdfunding.service.impl;

import com.vectorx.crowdfunding.service.api.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class ProjectServiceImpl implements ProjectService
{
}
