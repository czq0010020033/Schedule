package com.czq.schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.czq.schedule.bean.Task;
import com.czq.schedule.biz.TaskBiz;
import com.czq.schedule.biz.TaskBizImpl;
import com.czq.schedule.component.MyDialogFragment;
import com.czq.schedule.tool.Destination;
import com.czq.schedule.tool.SwitchActivity;

public class TaskActivity extends Activity
{

	private Button confirm;
	private Button cancel;
	private Button date_btn;
	// private Button time_btn;
	private Button enddate_btn;

	private EditText title;
	private EditText content;
	private TextView date;
	// private TextView time;
	private TextView enddate;

	// intent传入的task_id和task_tag
	private int id;
	private String task_tag;

	private TaskBiz taskBiz;
	// 用于表明intent的作用。
	public static final String TASK_TAG = "task_tag";
	public static final String TASK_UPDATE = "task_update";
	public static final String TASK_ADD = "task_add";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_task);

		// 实例化
		confirm = (Button) findViewById(R.id.task_confirm);
		cancel = (Button) findViewById(R.id.task_cancel);
		date_btn = (Button) findViewById(R.id.task_date);
		// time_btn = (Button) findViewById(R.id.task_time);
		enddate_btn = (Button) findViewById(R.id.task_enddate);

		// 添加事件监听
		confirm.setOnClickListener(new ButtonListener());
		cancel.setOnClickListener(new ButtonListener());
		date_btn.setOnClickListener(new ButtonListener());
		// time_btn.setOnClickListener(new ButtonListener());
		enddate_btn.setOnClickListener(new ButtonListener());

		title = (EditText) findViewById(R.id.task_title);
		content = (EditText) findViewById(R.id.task_content);
		date = (TextView) findViewById(R.id.text_date);
		// time = (TextView) findViewById(R.id.text_time);
		enddate = (TextView) findViewById(R.id.text_enddate);

		taskBiz = new TaskBizImpl(this);

		// 获得一个id和task_tag
		getMessage();
	}

	private void getMessage()
	{
		Intent intent = getIntent();
		task_tag = intent.getStringExtra(TASK_TAG);

		// 判断task_tag.
		if (task_tag.equals(TASK_UPDATE))
		{

			id = intent.getIntExtra("task_id", 0);
			// 更新操作。
			Task task = taskBiz.query(id);
			// 设置信息
			title.setText(task.getTitle());
			content.setText(task.getContent());
			date.setText(task.getDate());
			// time.setText(task.getTime());
		} else
		{

			date.setText(TaskBizImpl.getDateStr());
			enddate.setText(TaskBizImpl.getDateStr());
		}
	}

	private class ButtonListener implements OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			// task页面的按钮
			case R.id.task_date:

				MyDialogFragment dateDialog = new MyDialogFragment(
						MyDialogFragment.DATE_DIALOG);
				dateDialog.show(TaskActivity.this.getFragmentManager(), "date");
				break;
			case R.id.task_enddate:
				MyDialogFragment enddateDialog = new MyDialogFragment(
						MyDialogFragment.ENDDATE_DIALOG);
				enddateDialog.show(TaskActivity.this.getFragmentManager(),
						"enddate");
				break;
			/*
			 * case R.id.task_time: MyDialogFragment timeDialog = new
			 * MyDialogFragment( MyDialogFragment.TIME_DIALOG);
			 * timeDialog.show(TaskActivity.this.getFragmentManager(), "time");
			 * break;
			 */

			// 确认。将数据传到task对象中。并传到数据库。
			case R.id.task_confirm:
				// 判断文本是否正确

				//
				Task taskBean = new Task(id, date.getText().toString(), null,
						enddate.getText().toString(), title.getText()
								.toString(), content.getText().toString(), 0, 0);

				// 判断task_tag.
				if (task_tag.equals(TASK_UPDATE))
				{
					taskBiz.update(taskBean);
					Toast.makeText(TaskActivity.this, "修改成功",
							Toast.LENGTH_SHORT).show();
				} else
				{
					id = taskBiz.add(taskBean);
					Toast.makeText(TaskActivity.this, "添加成功",
							Toast.LENGTH_SHORT).show();
				}
				// 返回到TaskShowActivity
				
				SwitchActivity.switchActivity(TaskActivity.this,
						Destination.TaskShowActivity, id);
				TaskActivity.this.finish();
				break;
			case R.id.task_cancel:
				TaskActivity.this.finish();
				break;
			}
		}

	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if (taskBiz != null)
			taskBiz.closeDB();
	}

}
