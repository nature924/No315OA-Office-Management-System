package com.njl.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.constant.MessageError;
import com.njl.oa.dao.NoticeDao;
import com.njl.oa.entity.Notice;
import com.njl.oa.service.NoticeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public JSONObject getNoticeAll(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int page;   //页码
            int limit;  //每页数据量

            //获取数据
            String pageStr = request.getParameter("page");
            String limitStr = request.getParameter("limit");
            String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();   //得到自身工号

            //校验数据是否为空
            if (pageStr == null || limitStr == null) {
                //为空
                json.put("type", "error");
                json.put("msg", "数据获取错误！");
                return json;
            }

            //转成int型
            page = Integer.parseInt(pageStr);
            limit = Integer.parseInt(limitStr);

            //查询
            List<Notice> notices = noticeDao.selectNoticeAll((page - 1) * limit, limit);
            json.put("type", "success");
            json.put("msg", "获取数据成功！");
            json.put("code", 0);
            json.put("count", notices.size());
            json.put("data", notices);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject addNotice(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            //从前端接收数据
            String noticeStr = request.getParameter("notice");
            if (noticeStr == null) {
                json.put("type", "error");
                json.put("msg", "接收数据为空！");
                return json;
            }
            Notice notice = JSONObject.parseObject(noticeStr, Notice.class);

            //添加进数据库
            if (noticeDao.addNotice(notice) > 0) {
                json.put("type", "success");
                json.put("msg", "添加公告成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "添加公告失败！");
        return json;
    }

    @Override
    public JSONObject deleteNotice(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String noticeIdStr = request.getParameter("noticeId");
            if (noticeIdStr == null) {
                json.put("type", "error");
                json.put("msg", "接收数据为空！");
                return json;
            }
            int noticeId = Integer.parseInt(noticeIdStr);
            if (noticeDao.deleteNotice(noticeId) > 0) {
                json.put("type", "success");
                json.put("msg", "删除公告成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "删除公告失败！");
        return json;
    }

    @Override
    public JSONObject updateNotice(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String noticeStr = request.getParameter("notice");
            if (noticeStr == null) {
                json.put("type", "error");
                json.put("msg", "接收数据为空！");
                return json;
            }
            Notice notice = JSONObject.parseObject(noticeStr, Notice.class);
            if (noticeDao.updateNotice(notice) > 0) {
                json.put("type", "success");
                json.put("msg", "修改公告信息成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "修改公告信息失败！");
        return json;
    }

    @Override
    public JSONObject flagNotice(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();   //得到自身工号
            String noticeIdStr = request.getParameter("noticeId");
            if (noticeIdStr == null || employeeNumber == null) {
                json.put("type", "error");
                json.put("msg", "接收数据为空！");
                return json;
            }
            int noticeId = Integer.parseInt(noticeIdStr);
            if (noticeDao.flagNotice(noticeId, employeeNumber) > 0) {
                json.put("type", "success");
                json.put("msg", "已阅成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "已阅失败！");
        return json;
    }

    @Override
    public JSONObject bulkReleaseNotice(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String employeeIdListStr = request.getParameter("employeeIdList");
            String noticeIdStr = request.getParameter("noticeId");
            if (noticeIdStr == null || employeeIdListStr == null) {
                json.put("type", "error");
                json.put("msg", "接收数据为空！");
                return json;
            }
            Integer[] employeeIdList = JSONObject.parseObject(employeeIdListStr, Integer[].class);
            int noticeId = Integer.parseInt(noticeIdStr);
            if (noticeDao.bulkReleaseNotice(noticeId, employeeIdList) > 0) {
                json.put("type", "success");
                json.put("msg", "批量发布系统公告成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "批量发布系统公告失败！");
        return json;
    }

    @Override
    public JSONObject publishedAnnouncements(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int page = 0;
            int limit = 999;
            String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();   //得到自身工号
            String pageStr = request.getParameter("page");
            String limitStr = request.getParameter("limit");
            if (employeeNumber == null || pageStr == null || limitStr == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
//            page = Integer.parseInt(pageStr);
//            limit = Integer.parseInt(limitStr);
            List<Notice> notices = noticeDao.selectPublishedAnnouncements((page - 1) * limit, limit);
            List<Notice> result = new ArrayList<>();
            for (int i = 1; i < notices.size(); i++) {
                if (i == 1) result.add(notices.get(i));
                if (!notices.get(i - 1).getNoticeTime().equals(notices.get(i).getNoticeTime())) {
                    result.add(notices.get(i));
                }
            }
            json.put("type", "success");
            json.put("msg", "获取数据成功！");
            json.put("code", 0);
            json.put("count", result.size());
            json.put("data", result);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject revocation(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int noticeId;
            String noticeIdStr = request.getParameter("noticeId");
            if (noticeIdStr == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            noticeId = Integer.parseInt(noticeIdStr);
            if (noticeDao.revocation(noticeId) > 0) {
                json.put("type", "success");
                json.put("msg", "撤销系统公告成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "撤销系统公告失败！");
        return json;
    }

    @Override
    public JSONObject queryingSystemMessages(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();   //得到自身工号
            if (employeeNumber == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            List<Notice> notices = noticeDao.queryingSystemMessages(employeeNumber);
            json.put("type", "success");
            json.put("msg", "获取数据成功！");
            json.put("code", 0);
            json.put("count", notices.size());
            json.put("data", notices);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject noticeRead(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try{
            int nerId;
            String nerIdStr = request.getParameter("nerId");
            if(nerIdStr == null){
                json.put("type","error");
                json.put("msg","获取数据失败！");
                return json;
            }
            nerId = Integer.parseInt(nerIdStr);
            if( noticeDao.noticeRead(nerId) > 0){
                json.put("type","success");
                json.put("msg","阅读成功！");
                return json;
            }
        }catch (Exception e){
            json.put("type","error");
            json.put("msg",MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type","error");
        json.put("msg","阅读失败！");
        return json;
    }
}
