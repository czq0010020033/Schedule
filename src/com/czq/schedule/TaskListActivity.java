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

public class TaskListActivity extends Activity
{

	private ListView listView;
	
	// ��־Ҫ��ѯʲô���ݡ���Intent�д���
	public static final String TASKLIST_TAG = "list_tag";
	public static final String TAG_ALL = "tag_all";
	public static final String TAG_DATE = "tag_date";

	private List<Task> tasks;
	private List<HashMap<String, String>> tasksStr;
	private TaskBiz taskBiz;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tasklist);

		listView = (ListView) findViewById(R.id.listView);
		taskBiz = new TaskBizImpl(this);
		// ����Inent����ѯ���������ݡ�
		showList();
	}

	private void showList()
	{
		Intent intent = getIntent();
		// tag�����жϲ�ѯʲô����
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

		// ��ѯ
		tasksStr = taskBiz.getTitleAndDate(tasks);
		// �ж�tasksstr�Ƿ�Ϊ��
		if (tasksStr.isEmpty())
		{
			Toast.makeText(this, "�޴�������", Toast.LENGTH_SHORT).show();
			return;
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(this, tasksStr,
				R.layout.list_item, new String[]
				{ "title", "date" }, new int[]
				{ R.id.item_title, R.id.item_date });
		listView.setAdapter(simpleAdapter);
		listView.setOnItemClickListener(new ItemListener());

	}

	// listView�����¼�
	private class ItemListener implements OnItemClickListener
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id)
		{
			// ����idֵ����ʾ���������������
			Task task = tasks.get(position);

			Intent intent = new Intent(TaskListActivity.this,
					TaskShowActivity.class);
			intent.putExtra("task_id", task.getId());
			startActivity(intent);
		}
	}

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
			taskBiz.closeDB();
	}
}
