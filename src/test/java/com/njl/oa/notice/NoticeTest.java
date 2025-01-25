package com.njl.oa.notice;

import com.njl.oa.dao.NoticeDao;
import com.njl.oa.entity.Notice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class NoticeTest {

    @Autowired
    private NoticeDao noticeDao;

    @Test
    public void test() {
        List<Notice> notices = noticeDao.selectPublishedAnnouncements(0, 99);
        List<Notice> result = new ArrayList<>();
        for (int i = 1; i < notices.size(); i++) {
            if (i == 1) result.add(notices.get(i));
            if (!notices.get(i - 1).getNoticeTime().equals(notices.get(i).getNoticeTime())) {
                result.add(notices.get(i));
            }
        }
        for (Notice notice : result) {
            System.out.println(notice.toString());
        }
    }
}