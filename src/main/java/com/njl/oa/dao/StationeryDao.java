package com.njl.oa.dao;

import com.njl.oa.entity.Stationery;
import com.njl.oa.entity.StationeryAdd;
import com.njl.oa.entity.StationeryProposer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StationeryDao {

    /**
     * 查看全部办公用品
     *
     * @param page  页码
     * @param limit 每页数量
     * @return List<Stationery>
     */
    List<Stationery> selectStationeryAll(@Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 查看全部办公用品总数
     *
     * @return
     */
    Integer selectStationeryAllCount();

    /**
     * 根据雇员工号查看申请办公用品记录
     *
     * @param employeeNumber 雇员工号
     * @param page           页码
     * @param limit          每页数量
     * @return List<Stationery>
     */
    List<StationeryProposer> selectStationeryByEmployeeNumber(@Param("employeeNumber") String employeeNumber, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 查询全部办公用品添加记录
     *
     * @param page  页码
     * @param limit 每页数量
     * @return List<StationeryAdd>
     */
    List<StationeryAdd> selectStationeryAddAll(@Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 查询全部办公用品添加记录总数
     *
     * @return
     */
    Integer selectStationeryAddAllCount();

    /**
     * 添加新办公用品
     *
     * @param stationery 办公用品
     * @return 大于0成功
     */
    Integer addStationery(Stationery stationery);

    /**
     * 修改新办公用品信息
     *
     * @param stationery 办公用品
     * @return 大于0成功
     */
    Integer updateStationery(Stationery stationery);

    /**
     * 删除办公用品
     *
     * @param stationeryId 办公用品ID
     * @return 大于0成功
     */
    Integer deleteStationery(@Param("stationeryId") Integer stationeryId);

    /**
     * 申请办公用品
     *
     * @param stationeryProposer 申请办公用品实体类
     * @param employeeNumber     雇员工号
     * @return 大于0创建成功
     */
    Integer applicationForOfficeSupplies(@Param("stationeryProposer") StationeryProposer stationeryProposer, @Param("employeeNumber") String employeeNumber);

    /**
     * 办公用品仓库添加
     *
     * @param stationeryAdd 办公用品添加实体类
     * @return 大于0成功
     */
    Integer officeSuppliesWarehouseAdded(StationeryAdd stationeryAdd);

    /**
     * 办公用品数量的修改
     *
     * @param stationeryId    办公用品ID
     * @param stationeryCount 办公用品数量
     * @return 大于0成功
     */
    Integer updateStationeryCount(@Param("stationeryId") Integer stationeryId, @Param("stationeryCount") Integer stationeryCount);

    /**
     * 根据DepartmentHeadId查询雇员Id
     *
     * @param departmentHeadId
     * @return
     */
    Integer selectEmployeeIdByDepartmentHeadId(@Param("departmentHeadId") Integer departmentHeadId);

    /**
     * 根据自身工号查询正在申请办公用品
     * @param page
     * @param limit
     * @return
     */
    List<StationeryProposer> selectStationeryApprovalInformation(@Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 根据自身工号查询正在申请办公用品总数
     * @return
     */
    List<StationeryProposer> selectStationeryApprovalInformationCount();

    /**
     * 修改申请办公用品审批状态
     * @param stationeryEmployeeRelId
     * @param approve
     * @param receipt
     * @return
     */
    Integer updateStationeryApprovalApprove(@Param("stationeryEmployeeRelId") Integer stationeryEmployeeRelId,@Param("approve") Integer approve,@Param("receipt") String receipt);

    /**
     * 查询申请办公用品审批历史
     * @param page
     * @param limit
     * @return
     */
    List<StationeryProposer> selectStationeryApproverMarkHistory(@Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 查询申请办公用品审批历史总数
     * @return
     */
    Integer selectStationeryApproverMarkHistoryCount();

}
