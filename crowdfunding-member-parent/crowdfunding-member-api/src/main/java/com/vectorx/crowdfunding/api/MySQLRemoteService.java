package com.vectorx.crowdfunding.api;

import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.po.MemberPO;
import com.vectorx.crowdfunding.entity.vo.DetailProjectVO;
import com.vectorx.crowdfunding.entity.vo.PortalTypeVO;
import com.vectorx.crowdfunding.entity.vo.ProjectVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("vectorx-crowdfunding-mysql")
public interface MySQLRemoteService
{
    @RequestMapping("/get/detail/project/data/remote")
    ResultEntity<DetailProjectVO> getDetailProjectDataRemote(@RequestParam("projectId") Integer projectId);

    @RequestMapping("/get/protal/type/project/data/remote")
    ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote();

    @RequestMapping("/get/memberpo/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct);

    @RequestMapping("/save/memberpo/remote")
    ResultEntity<String> saveMemberPORemote(@RequestBody MemberPO memberPO);

    @RequestMapping("/save/projectvo/remote")
    ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId);
}
