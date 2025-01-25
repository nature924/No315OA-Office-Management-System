package com.njl.oa.dao;

import com.njl.oa.entity.Punch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PunchDao {

    /**
     * 根据工号来查询自身的打卡记录
     *
     * @param employeeNumber 雇员工号
     * @param limit          每页数据量
     * @param page           当前页码
     * @return List<Punch>
     */
    List<Punch> selectPunchByEmployeeNumber(@Param("employeeNumber") String employeeNumber, @Param("page") Integer page, @Param("limit") Integer limit, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 查询雇员打卡记录总数
     *
     * @param employeeNumber 雇员工号
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    Integer selectPunchCount(@Param("employeeNumber") String employeeNumber, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 根据工号查询今日有没有打卡
     *
     * @param employeeNumber 雇员工号
     * @param punchClassify  打卡分类
     * @param punchDay       日期
     * @return
     */
    String selectEmployeePunchByNumber(@Param("employeeNumber") String employeeNumber, @Param("punchClassify") Integer punchClassify, @Param("punchDay") String punchDay);

    /**
     * 根据工号查询雇员ID
     * @param employeeNumber    雇员工号
     * @return
     */
    Integer selectEmployeeIdByNumber(@Param("employeeNumber") String employeeNumber);

    /**
     * 打卡
     *
     * @param punch 打卡
     * @return 大于0成功
     */
    Integer addPunchCard(Punch punch);

    /**
     * 查询今天有没有打过卡
     *
     * @param punchDay       今天日期
     * @param employeeNumber 工号
     * @return List<Punch>
     */
    List<Punch> selectPunchByPunchDayAndEmployeeNumber(@Param("punchDay") String punchDay, @Param("employeeNumber") String employeeNumber);
}
