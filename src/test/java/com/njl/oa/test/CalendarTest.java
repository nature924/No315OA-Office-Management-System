package com.njl.oa.test;

import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarTest {

    public static void main(String[] args) {
        CalendarTest calendarTest = new CalendarTest();
        calendarTest.calendarDay();
    }

    /**
     * 生成日历有多少天表
     */
    public void calendarDay() {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");        //生成格式化实例
        Date date = new Date();
        String format = sdf.format(date);
        Calendar calendar = Calendar.getInstance();                             //获取Calendar对象
        calendar.setTime(date);                                                 //给Calendar对象设置时间
        int actualMaximum = calendar.getActualMaximum(Calendar.DATE);           //获得当月有多少天
        for (int i = 1; i <= actualMaximum; i++) {
            if (i < 10) {
                list.add(format + "-0" + i);
            } else {
                list.add(format + "-" + i);
            }
        }
        System.out.println(list);
    }

    /**
     * 生成日历表
     */
    public void calendar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");        //生成格式化实例
        System.out.print("请输入要查询的月份（yyyy-MM）：");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();        //获取输入字符串
        Date date = new Date();             //要获取的Date对象变量
        try {
            date = sdf.parse(str);        //将字符串转成日期对象，这里会抛出格式转换异常，需要用try..catch进行处理
        } catch (Exception e) {
            System.out.println("时间格式不正确");
        }
        Calendar calendar = Calendar.getInstance();        //获取Calendar对象
        calendar.setTime(date);        //给Calendar对象设置时间
        int year = calendar.get(Calendar.YEAR);        //获取所设置的年份
        int month = calendar.get(Calendar.MONTH);    //获取所设置的月
        System.out.println("====================" + year + "年" + (month + 1) + "月======================");
        calendar.set(Calendar.DATE, 1);
        int[] dayOfWeek = {7, 1, 2, 3, 4, 5, 6};
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        int firstWeekDay = dayOfWeek[weekDay - 1];
        int day = -firstWeekDay + 1;        //第一天
        for (; day <= calendar.getActualMaximum(Calendar.DATE); day++) {
            if (day <= 0) {
                System.out.print("\t");       //day从1开始显示
            } else {
                System.out.print(day + "\t");
            }
            if ((day + firstWeekDay - 1) % 7 == 0) {    //每计7天换行一次
                System.out.println();
            }
        }
    }
}
