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
    /**
     * 获取项目分页数据总数
     *
     * @param typeId
     * @param status
     * @param sortType
     * @param searchContent
     * @return {@link ResultEntity}<{@link Integer}>
     */
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

    /**
     * 获取项目分页数据
     *
     * @param pageNum
     * @param pageSize
     * @param typeId
     * @param status
     * @param sortType
     * @param searchContent
     * @return {@link ResultEntity}<{@link List}<{@link ProjectPaginationVO}>>
     */
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

    /**
     * 获取项目详情数据
     *
     * @param projectId
     * @return {@link ResultEntity}<{@link DetailProjectVO}>
     */
    @RequestMapping("/get/detail/project/data/remote")
    ResultEntity<DetailProjectVO> getDetailProjectDataRemote(
            @RequestParam("projectId")
                    Integer projectId);

    /**
     * 获取首页项目类型数据
     *
     * @return {@link ResultEntity}<{@link List}<{@link PortalTypeVO}>>
     */
    @RequestMapping("/get/protal/type/project/data/remote")
    ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote();

    /**
     * 根据登录账号获取会员信息
     *
     * @param loginacct
     * @return {@link ResultEntity}<{@link MemberPO}>
     */
    @RequestMapping("/get/memberpo/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(
            @RequestParam("loginacct")
                    String loginacct);

    /**
     * 保存会员信息
     *
     * @param memberPO
     * @return {@link ResultEntity}<{@link String}>
     */
    @RequestMapping("/save/memberpo/remote")
    ResultEntity<String> saveMemberPORemote(
            @RequestBody
                    MemberPO memberPO);

    /**
     * 保存项目信息
     *
     * @param projectVO
     * @param memberId
     * @return {@link ResultEntity}<{@link String}>
     */
    @RequestMapping("/save/projectvo/remote")
    ResultEntity<String> saveProjectVORemote(
            @RequestBody
                    ProjectVO projectVO,
            @RequestParam("memberId")
                    Integer memberId);

    /**
     * 获取订单项目数据
     *
     * @param projectId
     * @param returnId
     * @return {@link ResultEntity}<{@link OrderProjectVO}>
     */
    @RequestMapping("/get/order/project/data/remote")
    ResultEntity<OrderProjectVO> getOrderProjectDataRemote(
            @RequestParam("projectId")
                    Integer projectId,
            @RequestParam("returnId")
                    Integer returnId);

    /**
     * 通过会员ID获取收货地址
     *
     * @param memberId 会员ID
     * @return {@link ResultEntity}<{@link List}<{@link AddressVO}>>
     */
    @RequestMapping("/get/address/info/by/member/id/remote")
    ResultEntity<List<AddressVO>> getAddressInfoByMemberIdRemote(
            @RequestParam("memberId")
                    Integer memberId);

    /**
     * 保存收货地址
     *
     * @param addressVO 收货地址信息
     * @return {@link ResultEntity}<{@link String}>
     */
    @RequestMapping("/save/address/info/remote")
    ResultEntity<String> saveAddressInfoRemote(
            @RequestBody
                    AddressVO addressVO);
}
