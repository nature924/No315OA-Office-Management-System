package com.njl.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.entity.web.DTree;
import com.njl.oa.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/addressBook")
@Controller
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;


    @RequestMapping("/toPage")
    public String toPage(HttpServletRequest request) {
        String url = "bio/addressBook/addressBook";
        String page = request.getParameter("page");
        if (page != null) {
            url = page;
        }
        return url;
    }

    /**
     * 获取通讯录中的数据
     *
     * @return
     */
    @RequestMapping("/getAddressBookAllDate")
    @ResponseBody
    public List<DTree> getAddressBookAllDate(HttpServletRequest request) {
        return addressBookService.getDepartmentAndEmployee(request);
    }

    /**
     * 获取雇员信息
     * @param request
     * @return
     */
    @RequestMapping("/getAddressBookInformation")
    @ResponseBody
    public JSONObject getAddressBookInformation(HttpServletRequest request){
        return addressBookService.getAddressBookInformation(request);
    }

    /**
     * 填写请假单
     * @param request
     * @return
     */
    @RequestMapping("/addAddressBook")
    @ResponseBody
    public JSONObject addAddressBook(HttpServletRequest request){
        return addressBookService.addAddressBook(request);
    }
}
