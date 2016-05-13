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
* 描述: TaskBiz的实现类<br><br>
* 作者： 陈镇钦/850530595@qq.com<br>    
* 创建时间：2016年5月7日/下午6:39:12<br>    
* 修改人：陈镇钦/850530595@qq.com<br>    
* 修改时间：2016年5月7日/下午6:39:12<br>    
* 修改备注：<br>
* 版本：1.0
*/   
public class TaskBizImpl implements TaskBiz
{
	
	/** 
	 *  数据库操作类
	 */ 
	private DBManager dbManager;
	/** 
	 *   上下文，可以是Activity，Fragment等。
	 */ 
	private Context context;

	public TaskBizImpl(Context context)
	{
		dbManager = new DBManager(context);
		this.context = context;
	}

	/**
	* 描述： 
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
		//更新widget状态
		TaskWidget.	updateWidget(context);
		return id;
	}

	/**
	* 描述： 
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
	* 描述： 
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
		//更新widget状态
		TaskWidget.updateWidget(context);
		
		return true;
	}

	/**
	* 描述： 
	* @see com.czq.schedule.biz.TaskBiz#delete(int)
	*/ 
	@Override
	public void delete(int id)
	{
		dbManager.delete(id);
		//更新widget状态
		TaskWidget.updateWidget(context);
	}

	/**
	* 描述： 
	* @see com.czq.schedule.biz.TaskBiz#queryAll()
	*/ 
	@Override
	public List<Task> queryAll()
	{
		return dbManager.queryAll();
	}

	/**
	* 描述： 
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
	* 描述： 
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
	* 描述： 
	* @see com.czq.schedule.biz.TaskBiz#queryByDate(int, int, int)
	*/ 
	@Override
	public List<Task> queryByDate(int year, int month, int dayOfMonth)
	{
		return dbManager.queryByDate(year, month, dayOfMonth);
	}

	/**
	* 描述： 
	* @see com.czq.schedule.biz.TaskBiz#query(int)
	*/ 
	@Override
	public Task query(int id)
	{

		return dbManager.query(id);
	}

	/**
	* 描述：获得当天的待办事项。返回是一个字符串。用于widget显示
	*/ 
	public static String getTodayTitles(Context context)
	{
		// 获得该日期的待办事项。
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
	* 描述： 
	* @see com.czq.schedule.biz.TaskBiz#close()
	*/ 
	@Override
	public void close()
	{
		// TODO 自动生成的方法存根
		dbManager.closeDB();
	}

	/**
	* 描述： 
	* @see com.czq.schedule.biz.TaskBiz#deleteAll()
	*/ 
	@Override
	public void deleteAll()
	{
		dbManager.deleteAll();
	}

	

}
