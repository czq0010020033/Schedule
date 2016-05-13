package com.czq.schedule.bean;

/**
 * 描述: 待办事项的封装类。<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月7日/下午3:48:06<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月7日/下午3:48:06<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class Task
{

	/**
	 * task的id，唯一标识一个待办事项
	 */
	private int id;
	/**
	 * 待办事项的开始日期
	 */
	private String date = null;

	/**
	 * 待办事项的开始时间，暂时没用到
	 */
	private String time = null;

	/**
	 * 待办事项的结束日期
	 */
	private String enddate = null;

	/** 
	 * 待办事项的标题
	 */ 
	private String title = null;
	
	/** 
	 * 待办事项的附加内容
	 */ 
	private String content = null;
	/** 
	 * 设置提醒方式，暂未用到
	 */ 
	private int remindWay;
	
	/** 
	 * 事项是否过期，未用到
	 */ 
	private int isPast;

	public Task(int id, String date, String time, String enddate, String title,
			String content, int remindWay, int isPast)
	{
		this.id = id;
		this.date = date;
		this.time = time;
		this.enddate = enddate;
		this.title = title;
		this.content = content;
		this.remindWay = remindWay;
		this.isPast = isPast;

	}

	public Task()
	{

	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public int getRemindWay()
	{
		return remindWay;
	}

	public void setRemindWay(int remindWay)
	{
		this.remindWay = remindWay;
	}

	public int getIsPast()
	{
		return isPast;
	}

	public void setIsPast(int isPast)
	{
		this.isPast = isPast;
	}

	public String getEnddate()
	{
		return enddate;
	}

	public void setEnddate(String enddate)
	{
		this.enddate = enddate;
	}

}
