package com.czq.schedule.fragment;

import java.util.HashMap;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.czq.schedule.R;
import com.czq.schedule.TaskShowActivity;
import com.czq.schedule.bean.Task;
import com.czq.schedule.biz.TaskBiz;
import com.czq.schedule.biz.TaskBizImpl;

/**
 * 描述: 显示所有待办事项的Fragment<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月7日/下午8:39:47<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月7日/下午8:39:47<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class TaskListFragment extends Fragment implements OnItemClickListener
{

	private ListView listView;

	// 标志要查询什么内容。在Intent中传输
	/*
	 * public static final String TASKLIST_TAG = "list_tag"; public static final
	 * String TAG_ALL = "tag_all"; public static final String TAG_DATE =
	 * "tag_date";
	 */

	/**
	 * 含有所有待办事项的list
	 */
	private List<Task> tasks;

	/**
	 * 符合listView显示的数据结构，里面含有task的title，date，enddate。
	 */
	private List<HashMap<String, String>> tasksStr;
	/**
	 * 业务类
	 */
	private TaskBiz taskBiz;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.frag_tasklist, container, false);

		listView = (ListView) view.findViewById(R.id.listView);
		taskBiz = new TaskBizImpl(getActivity());

		// 接收Inent，查询并返回数据。
		showList();
		return view;
	}

	/**
	 * 描述： 显示所有待办事项
	 */
	private void showList()
	{
		// Intent intent = getActivity().getIntent();
		// tag用来判断查询什么内容
		// sString tag = intent.getStringExtra(TASKLIST_TAG);

		/*
		 * if (tag.equals(TAG_DATE)) { int year = intent.getIntExtra("year", 0);
		 * int month = intent.getIntExtra("month", 0); int dayOfMonth =
		 * intent.getIntExtra("dayOfMonth", 0); tasks =
		 * taskBiz.queryByDate(year, month, dayOfMonth); } else {
		 */

		tasks = taskBiz.queryAll();

		// 查询
		tasksStr = taskBiz.getTitleAndDate(tasks);
		// 判断tasksstr是否为空
		if (tasksStr.isEmpty())
		{
			System.out.println("it is null");
			return;
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),
				tasksStr, R.layout.list_item, new String[]
				{ "title", "date" }, new int[]
				{ R.id.item_title, R.id.item_date });
		listView.setAdapter(simpleAdapter);
		listView.setOnItemClickListener(this);

	}

	/**
	 * 描述： 对listView的每一项的监听，点击后进入相应的显示页面
	 * 
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		// 传入id值，显示待办事项具体内容
		Task task = tasks.get(position);

		Intent intent = new Intent(TaskListFragment.this.getActivity(),
				TaskShowActivity.class);
		intent.putExtra("task_id", task.getId());
		TaskListFragment.this.startActivity(intent);

	}

	/**
	 * 描述： 在结束时调用taskBiz的close方法，关闭数据库
	 * 
	 * @see android.app.Fragment#onDestroy()
	 */
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		if (taskBiz != null)
			taskBiz.close();
	}

}
