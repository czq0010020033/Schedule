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

import com.czq.schedule.bean.Task;
import com.czq.schedule.biz.TaskBiz;
import com.czq.schedule.biz.TaskBizImpl;
import com.czq.schedule.component.MyDialogFragment;
import com.czq.schedule.tool.Destination;
import com.czq.schedule.tool.SwitchActivity;

public class TaskShowActivity extends Activity implements OnClickListener
{
	private TextView date;
	//private TextView time;
	private TextView enddate;
	private TextView title;
	private TextView content;
	private TextView task_id;
	private Button update;
	private Button delete;
	
	// ����activity������Task��id
	private int id;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_taskshow);

		date = (TextView) findViewById(R.id.show_date);
	//	time = (TextView) findViewById(R.id.show_time);
		enddate = (TextView) findViewById(R.id.show_enddate);
		title = (TextView) findViewById(R.id.show_title);
		content = (TextView) findViewById(R.id.show_content);
		update = (Button) findViewById(R.id.show_update);
		delete = (Button) findViewById(R.id.show_delete);
		task_id = (TextView) findViewById(R.id.show_id);
		// ��ʾtask
		showTask();

		// ����޸İ�����ת���޸�ҳ��
		update.setOnClickListener(this);
		delete.setOnClickListener(this);

	}

	private void showTask()
	{
		Intent intent = getIntent();
		id = intent.getIntExtra("task_id", 0);
		TaskBiz taskBiz = new TaskBizImpl(this);
		Task task = taskBiz.query(id);
		// �ж�task�Ƿ�Ϊ��
		if (task == null)
		{
			title.setText("�ı�����");
			return;
		}
		// ��ʾ�ı�.
		date.setText(task.getDate());
	//	time.setText(task.getTime());
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
		case R.id.show_update:
			//ת��TaskActivity
			SwitchActivity.switchActivity(TaskShowActivity.this,
					Destination.TaskActivity, id);
			break;
		case R.id.show_delete:
			MyDialogFragment myDialogFragment = new MyDialogFragment(MyDialogFragment.ALERT_DIALOG);
			myDialogFragment.show(getFragmentManager(), "����");
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
