package com.czq.schedule.biz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.czq.schedule.TaskWidget;
import com.czq.schedule.bean.Task;
import com.czq.schedule.database.DBManager;

public class TaskBizImpl implements TaskBiz
{
	private DBManager dbManager;
	private Context context;

	public TaskBizImpl(Context context)
	{
		// TODO �Զ����ɵĹ��캯�����
		dbManager = new DBManager(context);
		this.context = context;
	}

	@Override
	public int add(Task task)
	{
		// TODO �Զ����ɵķ������
		int id = dbManager.add(task);
		updateWidget(context);
		return id;
	}

	@Override
	public void update(Task task)
	{
		// TODO �Զ����ɵķ������
		dbManager.update(task);
		updateWidget(context);
	}

	@Override
	public void delete(int id)
	{
		// TODO �Զ����ɵķ������
		dbManager.delete(id);
		updateWidget(context);
	}

	@Override
	public List<Task> queryAll()
	{
		// TODO �Զ����ɵķ������
		return dbManager.queryAll();
	}

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

	@Override
	public List<Task> queryByDate(int year, int month, int dayOfMonth)
	{
		// TODO �Զ����ɵķ������

		return dbManager.queryByDate(year, month, dayOfMonth);
	}

	@Override
	public Task query(int id)
	{
		// TODO �Զ����ɵķ������

		return dbManager.query(id);
	}

	// ��õ���Ĵ������������һ���ַ���������widget��ʾ
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
		return stringBuilder.toString();
	}

	// ����widget
	public static void updateWidget(Context context)
	{
		Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		Bundle extras = new Bundle();
		extras.putIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS,
				TaskWidget.widgetIds);
		intent.putExtras(extras);
		context.sendBroadcast(intent);
	}
	
	//��õ������ڵ��ַ���
	public static String getDateStr()
	{
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}
	
	//�������������ת��Ϊ�̶���ʽ���ַ���
	public static String getDateStr(int year, int monthOfYear,int dayOfMonth)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, monthOfYear, dayOfMonth);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		String dateStr = simpleDateFormat.format(calendar.getTime());
		
		return dateStr;
	}

	@Override
	public void closeDB()
	{
		// TODO �Զ����ɵķ������
		dbManager.closeDB();
	}

}
