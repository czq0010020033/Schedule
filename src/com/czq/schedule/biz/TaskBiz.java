package com.czq.schedule.biz;

import java.util.HashMap;
import java.util.List;

import com.czq.schedule.bean.Task;

/**
* 描述: 程序业务类的接口，提供操作接口<br><br>
* 作者： 陈镇钦/850530595@qq.com<br>    
* 创建时间：2016年5月7日/下午3:53:04<br>    
* 修改人：陈镇钦/850530595@qq.com<br>    
* 修改时间：2016年5月7日/下午3:53:04<br>    
* 修改备注：<br>
* 版本：1.0
*/   
public interface TaskBiz
{
	 
	/**
	* 描述：添加一个待办事项
	* @param task
	*  int 成功添加的task的id,失败时返回-1
	*/ 
	public int add(Task task);
	
	/**
	* 描述：添加一个待办事项，但不更新widget，用于导入待办事项时使用
	* @param task
	* @return int
	*/ 
	public int addWithNoUpdate(Task task);
	/**
	* 描述：修改一个待办事项
	* @param task
	* @return boolean true代表修改成功
	*/ 
	public boolean update(Task task);
	/**
	* 描述：删除一个待办事项
	* @param id 待办事项id
	*/ 
	public void delete(int id);
	
	/**
	* 描述： 删除全部待办事项
	*/ 
	public void deleteAll();
	/**
	* 描述：获取所有的待办事项
	* @return List<Task> 所有待办事项的一个集合
	*/ 
	public List<Task> queryAll();
	/**
	* 描述：根据task集合返回相应的task标题的集合
	* @param tasks
	* @return List<String>
	*/ 
	public List<String> getTitles(List<Task> tasks);
	/**
	* 描述：根据待办事项的集合来获取待办事项的标题和日期
	* @param tasks
	* @return List<HashMap<String,String>> 键名为title和date和enddate，值为相应值。返回的是一个集合。
	*/ 
	public List<HashMap<String, String>> getTitleAndDate(List<Task> tasks);
	/**
	* 描述：根据日期来获取待办事项,查询的task为把当天日期包含在起始日期和到期日期之间。
	* @param year
	* @param month
	* @param dayOfMonth
	* @return List<Task>
	*/ 
	public List<Task> queryByDate(int year, int month,
			int dayOfMonth);
	/**
	* 描述：根据待办事项的id来获取待办事项
	* @param id 待办事项的id
	* @return Task
	*/ 
	public Task query(int id);
	/**
	* 描述：关闭一些资源。如数据库等。
	*/ 
	public void close();
}
;