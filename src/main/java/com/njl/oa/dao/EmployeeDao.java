package com.njl.oa.dao;

import com.njl.oa.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface EmployeeDao {

    /**
     * 查询全部雇员信息
     *
     * @param page     页数
     * @param limit    每页数量
     * @param employee 雇员
     * @return List<Employee>
     */
    List<Employee> selectEmployeeAll(@Param("page") Integer page, @Param("limit") Integer limit, @Param("employee") Employee employee);

    /**
     * 查询全部雇员信息总数
     */
    Integer selectEmployeeAllCount();

    /**
     * 添加雇员
     *
     * @param employee 雇员
     * @return 大于0成功
     */
    Integer addEmployee(Employee employee);

    /**
     * 删除雇员
     *
     * @param employeeId 雇员ID
     * @return 大于0成功
     */
    Integer deleteEmployee(@Param("employeeId") Integer employeeId);

    /**
     * 修改雇员(管理员)
     *
     * @param employee 雇员
     * @return 大于0成功
     */
    Integer updateEmployeeAdmin(Employee employee);

    /**
     * 修改雇员
     *
     * @param employee 雇员
     * @return 大于0成功
     */
    Integer updateEmployee(Employee employee);

    /**
     * 修改雇员密码
     * @param employeeNumber    雇员工号
     * @param employeePassword  雇员密码
     * @return  大于0成功
     */
    Integer updateEmployeePassword(@Param("employeeNumber") String employeeNumber,@Param("employeePassword") String employeePassword);

    /**
     * 校验工号
     *
     * @param employee 雇员
     * @return 大于0成功
     */
    Integer verifyEmployeeNumber(Employee employee);

    /**
     * 校验手机号
     *
     * @param employee 雇员
     * @return 大于0成功
     */
    Integer verifyEmployeePhone(Employee employee);

    /**
     * 校验密码
     * @param employeeNumber    雇员工号
     * @param employeePassword    雇员密码
     * @return  大于0成功
     */
    Integer verifyEmployeePassword(@Param("employeeNumber") String employeeNumber,@Param("employeePassword") String employeePassword);

    /**
     * 根据工号查询雇员信息
     *
     * @param employeeNumber 工号
     * @return Employee
     */
    Employee selectEmployeeByNumber(@Param("employeeNumber") String employeeNumber);

    /**
     * 根据工号查询有什么资源
     *
     * @param employeeNumber 工号
     * @return Set<String>
     */
    Set<String> selectEmployeeByPermissions(@Param("employeeNumber") String employeeNumber);

    /**
     * 根据雇员ID获取信息
     *
     * @param employeeId 雇员ID
     * @return Employee
     */
    Employee selectEmployeeByEmployeeId(@Param("employeeId") Integer employeeId);

    /**
     * 批量修改雇员角色
     *
     * @param employeeIdList 雇员ID集合
     * @param roleId         角色ID
     * @return 大于0成功，否则失败
     */
    Integer batchUpdateEmployeeByRoleId(@Param("employeeIdList") Integer[] employeeIdList, @Param("roleId") Integer roleId);

    /**
     * 批量修改雇员部门
     *
     * @param employeeIdList 雇员ID集合
     * @param departmentId   部门ID
     * @return 大于0成功，否则失败
     */
    Integer batchUpdateEmployeeByDepartmentId(@Param("employeeIdList") Integer[] employeeIdList, @Param("departmentId") Integer departmentId);

    /**
     * 重置密码
     *
     * @param employeeId 雇员ID
     * @param initPassword   初始密码
     * @return 大于0成功，否则失败
     */
    Integer resetEmployeePassword(@Param("employeeId") Integer employeeId, @Param("initPassword") String initPassword);
}
