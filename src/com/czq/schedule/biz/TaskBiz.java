package com.czq.schedule.biz;

import java.util.HashMap;
import java.util.List;

import com.czq.schedule.bean.Task;

public interface TaskBiz
{
	public int add(Task task);
	public void update(Task task);
	public void delete(int id);
	public List<Task> queryAll();
	public List<String> getTitles(List<Task> tasks);
	//获得待办事项的标题和日期
	public List<HashMap<String, String>> getTitleAndDate(List<Task> tasks);
	public List<Task> queryByDate(int year, int month,
			int dayOfMonth);
	public Task query(int id);
	public void closeDB();
}
;