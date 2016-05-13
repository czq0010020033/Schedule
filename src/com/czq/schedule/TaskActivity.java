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
import com.czq.schedule.tool.StrTool;
import com.czq.schedule.tool.SwitchActivity;

/**
 * 描述: 待办事项修改和新建的页面。<br>
 * 进入该Activity时，会传入一个标志字符串（在该类有定义）来确定是要修改或者新建。<br>
 * 当是新建页面时，会获取当天日期来赋值给date和enddate。<br>
 * 当是修改页面时，会传入一个taskid，根据taskid来获取task，并在页面中显示出来。<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月7日/下午3:23:09<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月7日/下午3:23:09<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class TaskActivity extends Activity implements OnClickListener
{
	// 页面内的view
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
	// 业务类
	private TaskBiz taskBiz;

	// 标志字符串用于表明intent的作用。
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

		title = (EditText) findViewById(R.id.task_title);
		content = (EditText) findViewById(R.id.task_content);
		date = (TextView) findViewById(R.id.text_date);
		// time = (TextView) findViewById(R.id.text_time);
		enddate = (TextView) findViewById(R.id.text_enddate);

		// 示例化业务类
		taskBiz = new TaskBizImpl(this);

		// 添加事件监听
		confirm.setOnClickListener(this);
		cancel.setOnClickListener(this);
		date_btn.setOnClickListener(this);
		// time_btn.setOnClickListener(new ButtonListener());
		enddate_btn.setOnClickListener(this);

		// 根据不同功能获得不同的信息
		getMessage();
	}

	/**
	 * 描述： 根据传入的字符标志位来获取要显示的信息
	 */
	private void getMessage()
	{
		Intent intent = getIntent();
		task_tag = intent.getStringExtra(TASK_TAG);

		// 为更新页面
		if (task_tag.equals(TASK_UPDATE))
		{

			id = intent.getIntExtra("task_id", 0);
			// 更新操作。
			Task task = taskBiz.query(id);
			// 设置信息
			title.setText(task.getTitle());
			content.setText(task.getContent());
			date.setText(task.getDate());
			enddate.setText(task.getEnddate());
			// time.setText(task.getTime());
		}
		// 为新建页面
		else
		{

			date.setText(StrTool.getDateStr());
			enddate.setText(StrTool.getDateStr());
		}
	}

	/**
	 * 描述： 按键监听响应函数
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		// 开始日期
		case R.id.task_date:
			// 显示日期对话框
			MyDialogFragment dateDialog = new MyDialogFragment(
					MyDialogFragment.DATE_DIALOG);
			dateDialog.show(TaskActivity.this.getFragmentManager(), "date");
			break;
		// 结束日期
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
			Task taskBean = new Task(id, date.getText().toString(), null,
					enddate.getText().toString(), title.getText().toString(),
					content.getText().toString(), 0, 0);

			// 判断task_tag.
			if (task_tag.equals(TASK_UPDATE))
			{
				if (!taskBiz.update(taskBean))
				{
					Toast.makeText(TaskActivity.this, "修改失败",
							Toast.LENGTH_SHORT).show();
					return;
				}

				Toast.makeText(TaskActivity.this, "修改成功", Toast.LENGTH_SHORT)
						.show();
			} else
			{
				id = taskBiz.add(taskBean);
				// -1表示添加失败
				if (id == -1)
				{
					Toast.makeText(TaskActivity.this, "添加失败",
							Toast.LENGTH_SHORT).show();
					return;
				}
				Toast.makeText(TaskActivity.this, "添加成功", Toast.LENGTH_SHORT)
						.show();
			}
			// 返回到TaskShowActivity，并结束当前Acivity
			SwitchActivity.switchActivity(TaskActivity.this,
					Destination.TaskShowActivity, id);
			TaskActivity.this.finish();
			break;
		// 取消按钮
		case R.id.task_cancel:
			TaskActivity.this.finish();
			break;
		}
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if (taskBiz != null)
			taskBiz.close();
	}

}
