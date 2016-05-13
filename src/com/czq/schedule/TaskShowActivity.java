package com.czq.schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.czq.schedule.bean.Task;
import com.czq.schedule.biz.TaskBiz;
import com.czq.schedule.biz.TaskBizImpl;
import com.czq.schedule.component.MyDialogFragment;
import com.czq.schedule.tool.Destination;
import com.czq.schedule.tool.SwitchActivity;

/**
 * 描述: 用来显示待办事项Task，通过接受一个taskid后再获取task信息并将信息显示出来。<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月7日/下午3:25:58<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月7日/下午3:25:58<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class TaskShowActivity extends Activity implements OnClickListener
{
	// 页面内的view
	private TextView date;
	// private TextView time;
	private TextView enddate;
	private TextView title;
	private TextView content;
	private TextView task_id;
	private Button update;
	private Button delete;

	// 其他activity发来的Task的id
	private int id;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_taskshow);

		// 初始化
		date = (TextView) findViewById(R.id.show_date);
		// time = (TextView) findViewById(R.id.show_time);
		enddate = (TextView) findViewById(R.id.show_enddate);
		title = (TextView) findViewById(R.id.show_title);
		content = (TextView) findViewById(R.id.show_content);
		update = (Button) findViewById(R.id.show_update);
		delete = (Button) findViewById(R.id.show_delete);
		task_id = (TextView) findViewById(R.id.show_id);

		// 显示task
		showTask();

		// 点击修改按键，转到修改页面
		update.setOnClickListener(this);
		delete.setOnClickListener(this);

	}

	/**
	 * 描述：通过inent获取task_id后查询task并显示出task信息。
	 */
	private void showTask()
	{
		Intent intent = getIntent();
		id = intent.getIntExtra("task_id", 0);
		TaskBiz taskBiz = new TaskBizImpl(this);
		Task task = taskBiz.query(id);
		taskBiz.close();
		// 判断task是否为空
		if (task == null)
		{
			Toast.makeText(this, "没有该待办事项", Toast.LENGTH_SHORT).show();
			this.finish();
			return;
		}
		// 显示文本.
		date.setText(task.getDate());
		// time.setText(task.getTime());
		enddate.setText(task.getEnddate());
		title.setText(task.getTitle());
		content.setText(task.getContent());
		task_id.setText(String.valueOf(id));
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		// 修改按键，转向修改页面TaskActivity
		case R.id.show_update:
			SwitchActivity.switchActivity(TaskShowActivity.this,
					Destination.TaskActivity, id);
			this.finish();
			break;
		// 删除按键，显示删除对话框
		case R.id.show_delete:
			MyDialogFragment myDialogFragment = new MyDialogFragment(
					MyDialogFragment.ALERT_DIALOG);
			myDialogFragment.show(getFragmentManager(), "警告");
			break;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		super.onKeyDown(keyCode, event);
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			TaskShowActivity.this.finish();
		}
		return true;
	}

}
