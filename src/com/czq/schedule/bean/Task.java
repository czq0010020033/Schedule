package com.czq.schedule.bean;

public class Task
{
	private int id;
	private String date = null;
	private String time = null;
	private String enddate = null;
	public String getEnddate()
	{
		return enddate;
	}

	public void setEnddate(String enddate)
	{
		this.enddate = enddate;
	}
	private String title = null;
	private String content = null;
	private int remindWay;
	private int isPast;
	
	public Task(int id, String date, String time, String enddate, String title, String content, int remindWay, int isPast)
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
	
	
}
