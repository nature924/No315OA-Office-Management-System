package com.njl.oa.dao;

import com.njl.oa.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeDao {

    /**
     * 查询全部系统公告（管理员）
     *
     * @param page  页码
     * @param limit 数量
     * @return List<Notice>
     */
    List<Notice> selectNoticeAll(@Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 添加系统公告
     *
     * @param notice 系统公告类
     * @return 大于0成功
     */
    Integer addNotice(Notice notice);

    /**
     * 修改系统公告
     *
     * @param notice 系统公告类
     * @return 大于0成功
     */
    Integer updateNotice(Notice notice);

    /**
     * 删除系统公告
     *
     * @param noticeId 系统公告类ID
     * @return 大于0成功
     */
    Integer deleteNotice(@Param("noticeId") Integer noticeId);

    /**
     * 已阅读系统公告
     *
     * @param noticeId       系统公告ID
     * @param employeeNumber 雇员工号
     * @return 大于0成功
     */
    Integer flagNotice(@Param("noticeId") Integer noticeId, @Param("employeeNumber") String employeeNumber);

    /**
     * 批量发布系统公告
     *
     * @param noticeId       系统公告ID
     * @param employeeIdList 雇员ID数组
     * @return 大于0成功
     */
    Integer bulkReleaseNotice(@Param("noticeId") Integer noticeId, @Param("employeeIdList") Integer[] employeeIdList);

    /**
     * 查询已发布公告
     * @param page
     * @param limit
     * @return
     */
    List<Notice> selectPublishedAnnouncements(@Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 根据公告ID删除已发布系统公告
     * @param noticeId
     * @return
     */
    Integer revocation(@Param("noticeId") Integer noticeId);

    /**
     * 根据雇员工号查询最新系统公告
     * @param employeeNumber
     * @return
     */
    List<Notice> queryingSystemMessages(@Param("employeeNumber") String employeeNumber);

    /**
     * 修改发布公告阅读状态
     * @param nerId
     */
    Integer noticeRead(@Param("nerId") Integer nerId);
}
