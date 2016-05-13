package com.czq.schedule.bean;

/**
 * ����: ��������ķ�װ�ࡣ<br>
 * <br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��7��/����3:48:06<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��7��/����3:48:06<br>
 * �޸ı�ע��<br>
 * �汾��1.0
 */
public class Task
{

	/**
	 * task��id��Ψһ��ʶһ����������
	 */
	private int id;
	/**
	 * ��������Ŀ�ʼ����
	 */
	private String date = null;

	/**
	 * ��������Ŀ�ʼʱ�䣬��ʱû�õ�
	 */
	private String time = null;

	/**
	 * ��������Ľ�������
	 */
	private String enddate = null;

	/** 
	 * ��������ı���
	 */ 
	private String title = null;
	
	/** 
	 * ��������ĸ�������
	 */ 
	private String content = null;
	/** 
	 * �������ѷ�ʽ����δ�õ�
	 */ 
	private int remindWay;
	
	/** 
	 * �����Ƿ���ڣ�δ�õ�
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
