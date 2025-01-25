package com.njl.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.entity.Employee;
import com.njl.oa.service.EmployeeService;
import com.njl.oa.service.MenuService;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/unLogin")
    public String unLogin(Model model) {
        model.addAttribute("msg", "登录会话超时或还未登录，请重新登录！");
        return "../../login";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {

        return "../../login";
    }

    @RequestMapping("/toMain")
    public String toMain(Model model) {
        //个人信息
        String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();
        Employee employee = employeeService.getEmployeeNumber(employeeNumber);
        model.addAttribute("employee", employee);
        return "index";
    }

    @RequestMapping("/main")
    public String welcome() {
        return "main";
    }

    /**
     * 所有的页面跳转都在这个方法
     *
     * @param request
     * @return
     */
    @RequestMapping("/toPage")
    public String toMenu(@NotNull HttpServletRequest request) {
        return request.getParameter("page");
    }

    /**
     * 登录功能
     *
     * @param employee
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(Employee employee, Model model) {
        Subject subject = SecurityUtils.getSubject();
        //  查看是否又缓存记录
        if (!subject.isAuthenticated()) {
            //  如果没有缓存记录就进行登录
            UsernamePasswordToken token = new UsernamePasswordToken(employee.getEmployeeNumber(), employee.getEmployeePassword());
            //  记住密码？
            token.setRememberMe(false);
            try {
                subject.login(token);   //  进行登录校验
            } catch (Exception e) {
                model.addAttribute("employee", employee);
                model.addAttribute("msg", "用户名或密码错误！");
                return "../../login";   //  转发
            }
        }
        return "redirect:/main/toMain"; //  重定向
    }

    /**
     * 注销功能
     */
    @RequestMapping("/logout")
    @ResponseBody
    public JSONObject logout() {
        JSONObject json = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        json.put("msg", "注销成功！");
        return json;
    }

    /**
     * 获取图像
     *
     * @param request
     */
    @RequestMapping("/accessToImages")
    @ResponseBody
    public void AccessToImages(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String imageUrl = request.getParameter("imageUrl");
        File file = new File(request.getContextPath() + "/" + imageUrl);    //开启IO流
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            long size = file.length();
            byte[] temp = new byte[(int) size];
            fis.read(temp, 0, (int) size);
            fis.close();
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(temp);
            outputStream.flush();
            outputStream.close();
        }
    }

    /**
     * 上传头像
     *
     * @param request
     * @param file
     * @return
     */
    @RequestMapping("/uploadPhoto")
    @ResponseBody
    public JSONObject uploadPhoto(HttpServletRequest request, MultipartFile file) {
        JSONObject json = new JSONObject();
        try {
            if (file.isEmpty()) {
                json.put("type", "error");
                json.put("msg", "上传文件不能为空！");
            } else {
                String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();
                String originalFilename = file.getOriginalFilename();   //文件名
                //创建要上传的路径
                File file1 = new File(request.getContextPath() + "/" + "src/main/webapp/WEB-INF/employee/" + employeeNumber + "/");
                if (!file1.exists()) {
                    file1.mkdirs();
                }
                //文件上传到路径下
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(file1, "photo.png"));
                json.put("type", "success");
                json.put("msg", "上传成功！");
                json.put("value", "src/main/webapp/WEB-INF/employee/" + employeeNumber + "/photo.png");
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", e.getMessage());
        }
        return json;
    }


}
