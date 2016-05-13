package com.czq.schedule;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.czq.schedule.bean.Task;
import com.czq.schedule.biz.TaskBiz;
import com.czq.schedule.biz.TaskBizImpl;

/**
 * 描述:
 * 显示待办事项的Activity，根据传入的标志的不同显示不同。标志位的名字是TASKLIST_TAG，值为TASKLIST_TAG或TAG_DATE。
 * 现在只传入TAG_DATE
 * ，只用来显示当天待办事项，这个类与TaskListFragment高度耦合，而TaskListFragment用来显示所有待办事项。 这两个类需要整合<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月7日/下午9:01:26<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月7日/下午9:01:26<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class TaskListActivity extends Activity implements OnItemClickListener
{

	private ListView listView;

	// 标志要查询什么内容。在Intent中传输
	/**
	 * 这是标志位的名字
	 */
	public static final String TASKLIST_TAG = "list_tag";
	/** 
	 *   标志位值
	 */
	public static final String TAG_ALL = "tag_all";
	/**  
	 *   标志位值
	 */ 
	public static final String TAG_DATE = "tag_date";

	private List<Task> tasks;
	private List<HashMap<String, String>> tasksStr;
	private TaskBiz taskBiz;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tasklist);

		listView = (ListView) findViewById(R.id.listView);
		taskBiz = new TaskBizImpl(this);
		// 接收Inent，查询并返回数据。
		showList();
	}

	/**
	* 描述： 显示数据
	*/ 
	private void showList()
	{
		Intent intent = getIntent();
		// tag用来判断查询什么内容
		String tag = intent.getStringExtra(TASKLIST_TAG);

		if (tag.equals(TAG_DATE))
		{
			int year = intent.getIntExtra("year", 0);
			int month = intent.getIntExtra("month", 0);
			int dayOfMonth = intent.getIntExtra("dayOfMonth", 0);
			tasks = taskBiz.queryByDate(year, month, dayOfMonth);
		} else
		{
			tasks = taskBiz.queryAll();
		}

		// 查询
		tasksStr = taskBiz.getTitleAndDate(tasks);
		// 判断tasksstr是否为空
		if (tasksStr.isEmpty())
		{
			Toast.makeText(this, "无待办事项", Toast.LENGTH_SHORT).show();
			return;
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(this, tasksStr,
				R.layout.list_item, new String[]
				{ "title", "date" }, new int[]
				{ R.id.item_title, R.id.item_date });
		listView.setAdapter(simpleAdapter);
		listView.setOnItemClickListener(this);

	}

	/**
	* 描述： listView监听事件
	* @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	*/ 
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		// 传入id值，显示待办事项具体内容
					Task task = tasks.get(position);

					Intent intent = new Intent(TaskListActivity.this,
							TaskShowActivity.class);
					intent.putExtra("task_id", task.getId());
					startActivity(intent);
	}

	/**
	* 描述：按返回键则结束该Activity，该方式还不确定是否好用
	*/ 
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		super.onKeyDown(keyCode, event);
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			TaskListActivity.this.finish();
		}

		return true;
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if (taskBiz != null)
			taskBiz.close();
	}

	
}
