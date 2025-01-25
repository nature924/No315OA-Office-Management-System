package com.njl.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.constant.MessageError;
import com.njl.oa.constant.PunchConstant;
import com.njl.oa.dao.PunchDao;
import com.njl.oa.entity.Punch;
import com.njl.oa.service.PunchService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service("/punchService")
public class PunchServiceImpl implements PunchService {

    @Autowired
    private PunchDao punchDao;


    @Override
    public JSONObject punchTheClock(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int punchClassifyInt = 0;   //打卡类型

            String punchClassify = request.getParameter("punchClassify");   //获得打卡类型，1是上班，2是下班
            String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();   //获得自身工号

            //自定义时间格式
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatLeading = new SimpleDateFormat("yyyy-MM-dd");

            //得到打卡时段
            String currentClock = format.format(new Date());    //得到当前打卡的时间
            String businessHours = formatLeading.format(new Date()) + " " + PunchConstant.BUSINESS_HOURS;   //得到上班的打卡时间段
            String closingTime = formatLeading.format(new Date()) + " " + PunchConstant.CLOSING_TIME;   //得到下班的打卡时间段

            //判断打卡类型和打卡时间是否符合
            if (punchClassify == null) {
                json.put("type", "error");
                json.put("msg", MessageError.SYSTEM_ERROR);
                System.out.println("punchClassify数据为空");
                return json;
            } else if (punchClassify.equals("1")) {
                //如果是上班打卡
                punchClassifyInt = 1;
                Date parse = format.parse(currentClock);    //转为date型
                Date parse1 = format.parse(businessHours);
                if (parse.before(parse1)) {
                    //如果上班时间早于当前时间
                    json.put("type", "error");
                    json.put("msg", "还没到上班打卡时间！");
                    return json;
                }
            } else if (punchClassify.equals("2")) {
                //如果是下班打卡
                punchClassifyInt = 2;
                Date parse = format.parse(currentClock);    //转为date型
                Date parse1 = format.parse(closingTime);
                if (parse.before(parse1)) {
                    //如果下班时间不早于当前时间
                    json.put("type", "error");
                    json.put("msg", "还没到下班打卡时间！");
                    return json;
                }
            }

            //判断今天是否打过卡
            if (punchDao.selectEmployeePunchByNumber(employeeNumber, punchClassifyInt, formatLeading.format(new Date())) != null) {
                //如果打过卡
                String str = "上班签到";
                if (punchClassifyInt == 2) str = "下班签退";
                json.put("type", "error");
                json.put("msg", "今天已经打过" + str + "卡了！");
                return json;
            }

            //保存打卡记录
            Punch punch = new Punch();
            punch.setEmployeeId(punchDao.selectEmployeeIdByNumber(employeeNumber));
            punch.setPunchClassify(punchClassifyInt);
            punch.setPunchStatus(1);
            punch.setPunchDay(formatLeading.format(new Date()));
            if (punchDao.addPunchCard(punch) > 0) {
                //如果保存成功
                json.put("type", "success");
                json.put("msg", "打卡成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "打卡失败！");
        return json;
    }

    @Override
    public JSONObject punchingCardRecord(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int page;
            int limit;

            String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();   //获取自身工号
            String pageStr = request.getParameter("page");
            String limitStr = request.getParameter("limit");

            page = Integer.parseInt(pageStr);   //转成int类型
            limit = Integer.parseInt(limitStr);


            List<Punch> punches = punchDao.selectPunchByEmployeeNumber(employeeNumber, (page - 1) * limit, limit, null, null);


            json.put("type", "success");
            json.put("msg", "获取数据成功");
            json.put("code", 0);
            json.put("count", punchDao.selectPunchCount(employeeNumber,null,null));
            json.put("data", punches);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject initPunch(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();   //得到自身工号
            if(employeeNumber.equals("100001")){
                employeeNumber = "";  //赋予管理员所有权限
            }
            //自定义时间格式
            SimpleDateFormat formatLeading = new SimpleDateFormat("yyyy-MM-dd");

            String punchDay = formatLeading.format(new Date());
            List<Punch> punches = punchDao.selectPunchByPunchDayAndEmployeeNumber(punchDay, employeeNumber);
            if (punches == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            json.put("type", "success");
            json.put("msg", "获取数据成功！");
            json.put("data", punches);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject punchingCardRecordAll(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int page;
            int limit;
            List<Punch> punches;

            String pageStr = request.getParameter("page");
            String limitStr = request.getParameter("limit");
            String employeeNumberStr = request.getParameter("employeeNumber");
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");

            if (pageStr == null || limitStr == null) {
                json.put("type", "error");
                json.put("msg", "获取表格数据失败");
                System.out.println("数据为空！");
                return json;
            }
            page = Integer.parseInt(pageStr);   //转成int类型
            limit = Integer.parseInt(limitStr);

            if ((employeeNumberStr == null || "".equals(employeeNumberStr)) && (startDateStr == null || "".equals(startDateStr)) && (endDateStr == null || "".equals(endDateStr))) {
                punches = punchDao.selectPunchByEmployeeNumber(null, (page - 1) * limit, limit, null, null);
                json.put("count", punchDao.selectPunchCount(null,null,null));
            } else {
                punches = punchDao.selectPunchByEmployeeNumber(employeeNumberStr, (page - 1) * limit, limit, startDateStr, endDateStr);

                json.put("count", punchDao.selectPunchCount(employeeNumberStr,startDateStr,endDateStr));
            }

            json.put("type", "success");
            json.put("msg", "获取数据成功");
            json.put("code", 0);

            json.put("data", punches);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }
}
