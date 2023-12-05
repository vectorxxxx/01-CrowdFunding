package com.vectorx.crowdfunding.api;

import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.po.MemberPO;
import com.vectorx.crowdfunding.entity.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("vectorx-crowdfunding-mysql")
public interface MySQLRemoteService
{
    @RequestMapping("/get/project/pagination/total/count/remote")
    ResultEntity<Integer> getProjectPaginationTotalCountRemote(
            @RequestParam(value = "typeId",
                          required = false)
                    Integer typeId,
            @RequestParam(value = "status",
                          required = false)
                    Integer status,
            @RequestParam(value = "sortType",
                          defaultValue = "0")
                    Integer sortType,
            @RequestParam(value = "searchContent",
                          required = false)
                    String searchContent);

    @RequestMapping("/get/project/pagination/data/remote")
    ResultEntity<List<ProjectPaginationVO>> getProjectPaginationDataRemote(
            @RequestParam("pageNum")
                    Integer pageNum,
            @RequestParam(value = "pageSize",
                          defaultValue = "12")
                    Integer pageSize,
            @RequestParam(value = "typeId",
                          required = false)
                    Integer typeId,
            @RequestParam(value = "status",
                          required = false)
                    Integer status,
            @RequestParam(value = "sortType",
                          defaultValue = "0")
                    Integer sortType,
            @RequestParam(value = "searchContent",
                          required = false)
                    String searchContent);

    @RequestMapping("/get/detail/project/data/remote")
    ResultEntity<DetailProjectVO> getDetailProjectDataRemote(
            @RequestParam("projectId")
                    Integer projectId);

    @RequestMapping("/get/protal/type/project/data/remote")
    ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote();

    @RequestMapping("/get/memberpo/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(
            @RequestParam("loginacct")
                    String loginacct);

    @RequestMapping("/save/memberpo/remote")
    ResultEntity<String> saveMemberPORemote(
            @RequestBody
                    MemberPO memberPO);

    @RequestMapping("/save/projectvo/remote")
    ResultEntity<String> saveProjectVORemote(
            @RequestBody
                    ProjectVO projectVO,
            @RequestParam("memberId")
                    Integer memberId);

    @RequestMapping("/get/order/project/data/remote")
    ResultEntity<OrderProjectVO> getOrderProjectDataRemote(
            @RequestParam("projectId")
                    Integer projectId,
            @RequestParam("returnId")
                    Integer returnId);
}
