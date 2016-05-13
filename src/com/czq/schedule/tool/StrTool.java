package com.czq.schedule.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
* 描述: 包含了Task字段的静态的字符串，以及几个获取日期字符串的静态方法<br><br>
* 作者： 陈镇钦/850530595@qq.com<br>    
* 创建时间：2016年5月7日/下午6:55:02<br>    
* 修改人：陈镇钦/850530595@qq.com<br>    
* 修改时间：2016年5月7日/下午6:55:02<br>    
* 修改备注：<br>
* 版本：1.0
*/   
public class StrTool
{
	//Task相关字段的静态字符串，主要在数据库类中应用
	public static String ID = "id";
	public static String DATE = "date";
	public static String TIME = "time";
	public static String ENDDATE = "enddate";
	public static String TITLE = "title";
	public static String CONTENT = "content";
	public static String REMINDWAY = "remindway";
	public static String ISPAST = "ispast";

	
	/**
	* 描述：获得当天日期的字符串
	* @return String 当天日期的一个字符串，格式为"yyyy-MM-dd"
	*/ 
	public static String getDateStr()
	{
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	/**
	* 描述：将输入的年月日转换为固定格式的字符串
	* @param year
	* @param monthOfYear 月份是从0开始算，0-11。0代表1月份
	* @param dayOfMonth
	* @return String 获得指定日期的字符串，格式为"yyyy-MM-dd"
	*/ 
	public static String getDateStr(int year, int monthOfYear, int dayOfMonth)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, monthOfYear, dayOfMonth);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = simpleDateFormat.format(calendar.getTime());

		return dateStr;
	}
}
