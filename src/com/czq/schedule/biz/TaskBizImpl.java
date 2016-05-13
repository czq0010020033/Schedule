package com.czq.schedule.biz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.content.Context;

import com.czq.schedule.TaskWidget;
import com.czq.schedule.bean.Task;
import com.czq.schedule.database.DBManager;

/**
 * 
* ����: TaskBiz��ʵ����<br><br>
* ���ߣ� ������/850530595@qq.com<br>    
* ����ʱ�䣺2016��5��7��/����6:39:12<br>    
* �޸��ˣ�������/850530595@qq.com<br>    
* �޸�ʱ�䣺2016��5��7��/����6:39:12<br>    
* �޸ı�ע��<br>
* �汾��1.0
*/   
public class TaskBizImpl implements TaskBiz
{
	
	/** 
	 *  ���ݿ������
	 */ 
	private DBManager dbManager;
	/** 
	 *   �����ģ�������Activity��Fragment�ȡ�
	 */ 
	private Context context;

	public TaskBizImpl(Context context)
	{
		dbManager = new DBManager(context);
		this.context = context;
	}

	/**
	* ������ 
	* @see com.czq.schedule.biz.TaskBiz#add(com.czq.schedule.bean.Task)
	*/ 
	@Override
	public int add(Task task)
	{
		int id = -1;
		if ("".equals(task.getTitle()) || "".equals(task.getDate()) || "".equals(task.getEnddate()))
		{
			return id;
		}
		id = dbManager.add(task);
		//����widget״̬
		TaskWidget.	updateWidget(context);
		return id;
	}

	/**
	* ������ 
	* @see com.czq.schedule.biz.TaskBiz#addWithNoUpdate(com.czq.schedule.bean.Task)
	*/ 
	@Override
	public int addWithNoUpdate(Task task)
	{
		int id = -1;
		if ("".equals(task.getTitle()) || "".equals(task.getDate()) || "".equals(task.getEnddate()))
		{
			return id;
		}
		id = dbManager.add(task);
		return id;
	}
	
	/**
	* ������ 
	* @see com.czq.schedule.biz.TaskBiz#update(com.czq.schedule.bean.Task)
	*/ 
	@Override
	public boolean update(Task task)
	{
		if ("".equals(task.getTitle()) || "".equals(task.getDate()) || "".equals(task.getEnddate()))
		{
			return false;
		}
		dbManager.update(task);
		//����widget״̬
		TaskWidget.updateWidget(context);
		
		return true;
	}

	/**
	* ������ 
	* @see com.czq.schedule.biz.TaskBiz#delete(int)
	*/ 
	@Override
	public void delete(int id)
	{
		dbManager.delete(id);
		//����widget״̬
		TaskWidget.updateWidget(context);
	}

	/**
	* ������ 
	* @see com.czq.schedule.biz.TaskBiz#queryAll()
	*/ 
	@Override
	public List<Task> queryAll()
	{
		return dbManager.queryAll();
	}

	/**
	* ������ 
	* @see com.czq.schedule.biz.TaskBiz#getTitles(java.util.List)
	*/ 
	@Override
	public List<String> getTitles(List<Task> tasks)
	{

		List<String> tasksStr = new ArrayList<String>();
		for (Task task : tasks)
		{
			tasksStr.add(task.getTitle());
		}
		return tasksStr;
	}

	/**
	* ������ 
	* @see com.czq.schedule.biz.TaskBiz#getTitleAndDate(java.util.List)
	*/ 
	@Override
	public List<HashMap<String, String>> getTitleAndDate(List<Task> tasks)
	{
		List<HashMap<String, String>> tasksStr = new ArrayList<HashMap<String, String>>();
		for (Task task : tasks)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("title", task.getTitle());
			map.put("date", task.getDate() + "\n" +task.getEnddate());
			tasksStr.add(map);
		}
		return tasksStr;
	}

	/**
	* ������ 
	* @see com.czq.schedule.biz.TaskBiz#queryByDate(int, int, int)
	*/ 
	@Override
	public List<Task> queryByDate(int year, int month, int dayOfMonth)
	{
		return dbManager.queryByDate(year, month, dayOfMonth);
	}

	/**
	* ������ 
	* @see com.czq.schedule.biz.TaskBiz#query(int)
	*/ 
	@Override
	public Task query(int id)
	{

		return dbManager.query(id);
	}

	/**
	* ��������õ���Ĵ������������һ���ַ���������widget��ʾ
	*/ 
	public static String getTodayTitles(Context context)
	{
		// ��ø����ڵĴ������
		TaskBizImpl taskBiz = new TaskBizImpl(context);
		Calendar calendar = Calendar.getInstance();

		List<String> taskStr = taskBiz.getTitles(taskBiz.queryByDate(
				calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH)));
		StringBuilder stringBuilder = new StringBuilder();
		for (String s : taskStr)
		{
			stringBuilder.append(s + "\n");
		}
		
		taskBiz.close();
		return stringBuilder.toString();
	}

	
	
	

	/**
	* ������ 
	* @see com.czq.schedule.biz.TaskBiz#close()
	*/ 
	@Override
	public void close()
	{
		// TODO �Զ����ɵķ������
		dbManager.closeDB();
	}

	/**
	* ������ 
	* @see com.czq.schedule.biz.TaskBiz#deleteAll()
	*/ 
	@Override
	public void deleteAll()
	{
		dbManager.deleteAll();
	}

	

}
