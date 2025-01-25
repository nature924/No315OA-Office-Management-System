package com.njl.oa.dao;

import com.njl.oa.entity.Leave;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaveDao {

    /**
     * 添加请假单
     *
     * @param leave 请假
     * @return 大于0添加成功，否则失败
     */
    Integer addLeave(Leave leave);

    /**
     * 校验请假单数据
     *
     * @param leave 请假
     * @return 大于0成功，否则失败
     */
    Integer verifyLeave(Leave leave);

    /**
     * 根据工号查询请假单
     *
     * @param leaveNumber
     * @return
     */
    List<Leave> selectLeaveByLeaveNumber(@Param("leaveNumber") String leaveNumber, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 根据工号查询请假单总数
     *
     * @param leaveNumber
     * @return
     */
    Integer selectLeaveByLeaveNumberCount(@Param("leaveNumber") String leaveNumber);

    /**
     * 修改请假结果
     *
     * @param leaveId
     * @param leaveApproved
     * @return
     */
    Integer updateLeaveApproved(@Param("leaveId") Integer leaveId, @Param("leaveApproved") Integer leaveApproved, @Param("leaveReceipt") String leaveReceipt);


    /**
     * 查询正在审批中的请假单
     *
     * @param approved
     * @param page
     * @param limit
     * @return
     */
    List<Leave> selectLeaveAllApproval(@Param("approved") Integer approved, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 查询正在审批中的请假单总数
     *
     * @param approved
     * @return
     */
    Integer selectLeaveAllApprovalCount(@Param("approved") Integer approved);

    /**
     * 审批请假单
     * @param leave
     * @return
     */
    Integer updateLeaveApproval(Leave leave);

    Leave selectLeaveByProcessInstanceId(String executionId);
}
