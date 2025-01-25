package com.njl.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.constant.MessageError;
import com.njl.oa.entity.Department;
import com.njl.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取部门全部数据
     * @return
     */
    @RequestMapping("/getDepartmentAll")
    @ResponseBody
    public JSONObject getDepartmentAll(){
        return departmentService.selectDepartmentAll();
    }

    /**
     * 添加部门
     * @param request
     * @return
     */
    @RequestMapping("/addDepartment")
    @ResponseBody
    public JSONObject addDepartment(HttpServletRequest request){
        JSONObject json = new JSONObject();
        try{
            Department department = JSONObject.parseObject(request.getParameter("department"), Department.class);
            if(departmentService.verifyDepartmentTitle(department) > 0){
                json.put("type","error");
                json.put("msg","部门名称重复，请使用别的部门名称");
                return json;
            }
            if(departmentService.addDepartment(department) > 0){
                json.put("type","success");
                json.put("msg","添加成功");
                return json;
            }
        }catch (Exception e){
            json.put("type","success");
            json.put("msg",MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
        }
        return json;
    }

    /**
     * 修改部门
     * @param request
     * @return
     */
    @RequestMapping("/updateDepartment")
    @ResponseBody
    public JSONObject updateDepartment(HttpServletRequest request){
        JSONObject json = new JSONObject();
        try{
            Department department = JSONObject.parseObject(request.getParameter("department"), Department.class);
            if(departmentService.verifyDepartmentTitle(department) > 0){
                json.put("type","error");
                json.put("msg","部门名称重复，请使用别的部门名称");
                return json;
            }
            if(departmentService.updateDepartment(department) > 0){
                json.put("type","success");
                json.put("msg","修改成功");
                return json;
            }
        }catch (Exception e){
            json.put("type","success");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
        }
        return json;
    }

    /**
     * 删除部门
     * @param request
     * @return
     */
    @RequestMapping("/deleteDepartment")
    @ResponseBody
    public JSONObject deleteDepartment(HttpServletRequest request){
        JSONObject json = new JSONObject();
        try{
            String departmentId = request.getParameter("departmentId");
            if(departmentService.verifyDepartmentByEmployee(Integer.parseInt(departmentId)) > 0){
                json.put("type","error");
                json.put("msg","该部门还含有雇员存在，无法删除");
                return json;
            }
            if(departmentService.deleteDepartment(Integer.parseInt(departmentId)) > 0){
                json.put("type","success");
                json.put("msg","删除成功");
                return json;
            }
        }catch (Exception e){
            json.put("type","success");
            json.put("msg",MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
        }
        return json;
    }

    @RequestMapping("/updateDepartmentPrincipal")
    @ResponseBody
    public JSONObject updateDepartmentPrincipal(HttpServletRequest request){
        return departmentService.updateDepartmentPrincipal(request);
    }
}
