package com.njl.oa.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface NoticeService {

    /**
     * 获取全部系统公告
     * @param request
     * @return
     */
    JSONObject getNoticeAll(HttpServletRequest request);

    /**
     * 添加系统公告
     * @param request
     * @return
     */
    JSONObject addNotice(HttpServletRequest request);

    /**
     * 删除系统公告信息
     * @param request
     * @return
     */
    JSONObject deleteNotice(HttpServletRequest request);

    /**
     * 修改系统公告信息
     * @param request
     * @return
     */
    JSONObject updateNotice(HttpServletRequest request);

    /**
     * 已阅读系统公告
     * @param request
     * @return
     */
    JSONObject flagNotice(HttpServletRequest request);

    /**
     * 批量发布系统公告
     * @param request
     * @return
     */
    JSONObject bulkReleaseNotice(HttpServletRequest request);

    /**
     * 查看已发布公告
     * @param request
     * @return
     */
    JSONObject publishedAnnouncements(HttpServletRequest request);

    /**
     * 撤销发布的系统公告
     * @param request
     * @return
     */
    JSONObject revocation(HttpServletRequest request);

    /**
     * 打开系统弹出最新的系统消息
     * @param request
     * @return
     */
    JSONObject queryingSystemMessages(HttpServletRequest request);

    /**
     * 点击已阅读
     * @param request
     * @return
     */
    JSONObject noticeRead(HttpServletRequest request);
}
