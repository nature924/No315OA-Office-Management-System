package com.njl.oa.dao;

import com.njl.oa.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentDao {

    List<Department> selectDepartmentAll();

    Integer addDepartment(Department department);

    Integer updateDepartment(Department department);

    Integer deleteDepartment(@Param("departmentId") Integer departmentId);

    Integer verifyDepartmentTitle(Department department);

    /**
     * 查询部门人数
     * @param departmentId
     * @return
     */
    Integer verifyDepartmentByEmployee(@Param("departmentId") Integer departmentId);

    /**
     * 查询部门主要负责人
     * @param employeeId 负责人ID
     * @return
     */
    String selectDepartmentHelmsman(@Param("employeeId") Integer employeeId);

    /**
     * 根据雇员工号查询
     * @param employeeNumber
     * @return
     */
    Integer selectEmployeeByNumber(@Param("employeeNumber") String employeeNumber);

    /**
     * 修改部门负责人
     * @param departmentHead
     * @param departmentId
     * @return
     */
    Integer updateDepartmentHead(@Param("departmentHead") Integer departmentHead,@Param("departmentId") Integer departmentId);
}
