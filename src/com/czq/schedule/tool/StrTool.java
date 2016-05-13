package com.czq.schedule.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
* ����: ������Task�ֶεľ�̬���ַ������Լ�������ȡ�����ַ����ľ�̬����<br><br>
* ���ߣ� ������/850530595@qq.com<br>    
* ����ʱ�䣺2016��5��7��/����6:55:02<br>    
* �޸��ˣ�������/850530595@qq.com<br>    
* �޸�ʱ�䣺2016��5��7��/����6:55:02<br>    
* �޸ı�ע��<br>
* �汾��1.0
*/   
public class StrTool
{
	//Task����ֶεľ�̬�ַ�������Ҫ�����ݿ�����Ӧ��
	public static String ID = "id";
	public static String DATE = "date";
	public static String TIME = "time";
	public static String ENDDATE = "enddate";
	public static String TITLE = "title";
	public static String CONTENT = "content";
	public static String REMINDWAY = "remindway";
	public static String ISPAST = "ispast";

	
	/**
	* ��������õ������ڵ��ַ���
	* @return String �������ڵ�һ���ַ�������ʽΪ"yyyy-MM-dd"
	*/ 
	public static String getDateStr()
	{
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	/**
	* �������������������ת��Ϊ�̶���ʽ���ַ���
	* @param year
	* @param monthOfYear �·��Ǵ�0��ʼ�㣬0-11��0����1�·�
	* @param dayOfMonth
	* @return String ���ָ�����ڵ��ַ�������ʽΪ"yyyy-MM-dd"
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
