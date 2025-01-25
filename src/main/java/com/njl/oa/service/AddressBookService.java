package com.njl.oa.service;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.entity.web.DTree;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AddressBookService {

    /**
     * 获取部门和雇员信息
     * @param request
     * @return
     */
    List<DTree> getDepartmentAndEmployee(HttpServletRequest request);

    /**
     * 获取雇员信息
     * @param request
     * @return
     */
    JSONObject getAddressBookInformation(HttpServletRequest request);

    /**
     * 填写请假单
     * @param request
     * @return
     */
    JSONObject addAddressBook(HttpServletRequest request);
}
