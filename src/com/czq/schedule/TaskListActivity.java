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
 * ����:
 * ��ʾ���������Activity�����ݴ���ı�־�Ĳ�ͬ��ʾ��ͬ����־λ��������TASKLIST_TAG��ֵΪTASKLIST_TAG��TAG_DATE��
 * ����ֻ����TAG_DATE
 * ��ֻ������ʾ�����������������TaskListFragment�߶���ϣ���TaskListFragment������ʾ���д������ ����������Ҫ����<br>
 * <br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��7��/����9:01:26<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��7��/����9:01:26<br>
 * �޸ı�ע��<br>
 * �汾��1.0
 */
public class TaskListActivity extends Activity implements OnItemClickListener
{

	private ListView listView;

	// ��־Ҫ��ѯʲô���ݡ���Intent�д���
	/**
	 * ���Ǳ�־λ������
	 */
	public static final String TASKLIST_TAG = "list_tag";
	/** 
	 *   ��־λֵ
	 */
	public static final String TAG_ALL = "tag_all";
	/**  
	 *   ��־λֵ
	 */ 
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

	/**
	* ������ ��ʾ����
	*/ 
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
		listView.setOnItemClickListener(this);

	}

	/**
	* ������ listView�����¼�
	* @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	*/ 
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

	/**
	* �����������ؼ��������Activity���÷�ʽ����ȷ���Ƿ����
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
