package com.czq.schedule.biz;

import java.util.HashMap;
import java.util.List;

import com.czq.schedule.bean.Task;

/**
* ����: ����ҵ����Ľӿڣ��ṩ�����ӿ�<br><br>
* ���ߣ� ������/850530595@qq.com<br>    
* ����ʱ�䣺2016��5��7��/����3:53:04<br>    
* �޸��ˣ�������/850530595@qq.com<br>    
* �޸�ʱ�䣺2016��5��7��/����3:53:04<br>    
* �޸ı�ע��<br>
* �汾��1.0
*/   
public interface TaskBiz
{
	 
	/**
	* ���������һ����������
	* @param task
	*  int �ɹ���ӵ�task��id,ʧ��ʱ����-1
	*/ 
	public int add(Task task);
	
	/**
	* ���������һ�����������������widget�����ڵ����������ʱʹ��
	* @param task
	* @return int
	*/ 
	public int addWithNoUpdate(Task task);
	/**
	* �������޸�һ����������
	* @param task
	* @return boolean true�����޸ĳɹ�
	*/ 
	public boolean update(Task task);
	/**
	* ������ɾ��һ����������
	* @param id ��������id
	*/ 
	public void delete(int id);
	
	/**
	* ������ ɾ��ȫ����������
	*/ 
	public void deleteAll();
	/**
	* ��������ȡ���еĴ�������
	* @return List<Task> ���д��������һ������
	*/ 
	public List<Task> queryAll();
	/**
	* ����������task���Ϸ�����Ӧ��task����ļ���
	* @param tasks
	* @return List<String>
	*/ 
	public List<String> getTitles(List<Task> tasks);
	/**
	* ���������ݴ�������ļ�������ȡ��������ı��������
	* @param tasks
	* @return List<HashMap<String,String>> ����Ϊtitle��date��enddate��ֵΪ��Ӧֵ�����ص���һ�����ϡ�
	*/ 
	public List<HashMap<String, String>> getTitleAndDate(List<Task> tasks);
	/**
	* ������������������ȡ��������,��ѯ��taskΪ�ѵ������ڰ�������ʼ���ں͵�������֮�䡣
	* @param year
	* @param month
	* @param dayOfMonth
	* @return List<Task>
	*/ 
	public List<Task> queryByDate(int year, int month,
			int dayOfMonth);
	/**
	* ���������ݴ��������id����ȡ��������
	* @param id ���������id
	* @return Task
	*/ 
	public Task query(int id);
	/**
	* �������ر�һЩ��Դ�������ݿ�ȡ�
	*/ 
	public void close();
}
;