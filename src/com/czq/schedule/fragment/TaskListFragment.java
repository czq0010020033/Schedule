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

public class TaskListFragment extends Fragment
{
	private ListView listView;

	// ��־Ҫ��ѯʲô���ݡ���Intent�д���
	public static final String TASKLIST_TAG = "list_tag";
	public static final String TAG_ALL = "tag_all";
	public static final String TAG_DATE = "tag_date";

	private List<Task> tasks;
	private List<HashMap<String, String>> tasksStr;
	private TaskBiz taskBiz;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.frag_tasklist, container, false);

		listView = (ListView) view.findViewById(R.id.listView);
		taskBiz = new TaskBizImpl(getActivity());

		// ����Inent����ѯ���������ݡ�
		showList();
		return view;
	}

	private void showList()
	{
		// Intent intent = getActivity().getIntent();
		// tag�����жϲ�ѯʲô����
		// sString tag = intent.getStringExtra(TASKLIST_TAG);

		/*
		 * if (tag.equals(TAG_DATE)) { int year = intent.getIntExtra("year", 0);
		 * int month = intent.getIntExtra("month", 0); int dayOfMonth =
		 * intent.getIntExtra("dayOfMonth", 0); tasks =
		 * taskBiz.queryByDate(year, month, dayOfMonth); } else {
		 */

		tasks = taskBiz.queryAll();

		// ��ѯ
		tasksStr = taskBiz.getTitleAndDate(tasks);
		// �ж�tasksstr�Ƿ�Ϊ��
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

			Intent intent = new Intent(TaskListFragment.this.getActivity(),
					TaskShowActivity.class);
			intent.putExtra("task_id", task.getId());
			TaskListFragment.this.startActivity(intent);
		}
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		if (taskBiz != null)
			taskBiz.closeDB();
	}

}
