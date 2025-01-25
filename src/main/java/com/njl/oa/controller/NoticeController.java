package com.njl.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/notice")
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 获取全部系统公告
     *
     * @param request
     * @return
     */
    @RequestMapping("/getNoticeAll")
    @ResponseBody
    public JSONObject getNoticeAll(HttpServletRequest request) {
        return noticeService.getNoticeAll(request);
    }

    /**
     * 添加系统公告
     *
     * @param request
     * @return
     */
    @RequestMapping("/addNotice")
    @ResponseBody
    public JSONObject addNotice(HttpServletRequest request) {
        return noticeService.addNotice(request);
    }

    /**
     * 删除系统公告信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteNotice")
    @ResponseBody
    public JSONObject deleteNotice(HttpServletRequest request) {
        return noticeService.deleteNotice(request);
    }

    /**
     * 修改系统公告信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateNotice")
    @ResponseBody
    public JSONObject updateNotice(HttpServletRequest request) {
        return noticeService.updateNotice(request);
    }

    /**
     * 已阅读系统公告
     *
     * @param request
     * @return
     */
    @RequestMapping("/flagNotice")
    @ResponseBody
    public JSONObject flagNotice(HttpServletRequest request) {
        return noticeService.flagNotice(request);
    }

    /**
     * 批量发布系统公告
     *
     * @param request
     * @return
     */
    @RequestMapping("/bulkReleaseNotice")
    @ResponseBody
    public JSONObject bulkReleaseNotice(HttpServletRequest request) {
        return noticeService.bulkReleaseNotice(request);
    }

    /**
     * 查看已发布公告
     * @param request
     * @return
     */
    @RequestMapping("/publishedAnnouncements")
    @ResponseBody
    public JSONObject publishedAnnouncements(HttpServletRequest request){
        return noticeService.publishedAnnouncements(request);
    }

    /**
     * 撤销发布的系统公告
     * @param request
     * @return
     */
    @RequestMapping("/revocation")
    @ResponseBody
    public JSONObject revocation(HttpServletRequest request){
        return noticeService.revocation(request);
    }

    /**
     * 打开系统弹出最新的系统消息
     * @param request
     * @return
     */
    @RequestMapping("/queryingSystemMessages")
    @ResponseBody
    public JSONObject queryingSystemMessages(HttpServletRequest request){
        return noticeService.queryingSystemMessages(request);
    }

    /**
     * 点击已阅读
     * @param request
     * @return
     */
    @RequestMapping("/noticeRead")
    @ResponseBody
    public JSONObject noticeRead(HttpServletRequest request){
        return noticeService.noticeRead(request);
    }
}
