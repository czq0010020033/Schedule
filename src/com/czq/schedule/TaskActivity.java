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
 * ����: ���������޸ĺ��½���ҳ�档<br>
 * �����Activityʱ���ᴫ��һ����־�ַ������ڸ����ж��壩��ȷ����Ҫ�޸Ļ����½���<br>
 * �����½�ҳ��ʱ�����ȡ������������ֵ��date��enddate��<br>
 * �����޸�ҳ��ʱ���ᴫ��һ��taskid������taskid����ȡtask������ҳ������ʾ������<br>
 * <br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��7��/����3:23:09<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��7��/����3:23:09<br>
 * �޸ı�ע��<br>
 * �汾��1.0
 */
public class TaskActivity extends Activity implements OnClickListener
{
	// ҳ���ڵ�view
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

	// intent�����task_id��task_tag
	private int id;
	private String task_tag;
	// ҵ����
	private TaskBiz taskBiz;

	// ��־�ַ������ڱ���intent�����á�
	public static final String TASK_TAG = "task_tag";
	public static final String TASK_UPDATE = "task_update";
	public static final String TASK_ADD = "task_add";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_task);

		// ʵ����
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

		// ʾ����ҵ����
		taskBiz = new TaskBizImpl(this);

		// ����¼�����
		confirm.setOnClickListener(this);
		cancel.setOnClickListener(this);
		date_btn.setOnClickListener(this);
		// time_btn.setOnClickListener(new ButtonListener());
		enddate_btn.setOnClickListener(this);

		// ���ݲ�ͬ���ܻ�ò�ͬ����Ϣ
		getMessage();
	}

	/**
	 * ������ ���ݴ�����ַ���־λ����ȡҪ��ʾ����Ϣ
	 */
	private void getMessage()
	{
		Intent intent = getIntent();
		task_tag = intent.getStringExtra(TASK_TAG);

		// Ϊ����ҳ��
		if (task_tag.equals(TASK_UPDATE))
		{

			id = intent.getIntExtra("task_id", 0);
			// ���²�����
			Task task = taskBiz.query(id);
			// ������Ϣ
			title.setText(task.getTitle());
			content.setText(task.getContent());
			date.setText(task.getDate());
			enddate.setText(task.getEnddate());
			// time.setText(task.getTime());
		}
		// Ϊ�½�ҳ��
		else
		{

			date.setText(StrTool.getDateStr());
			enddate.setText(StrTool.getDateStr());
		}
	}

	/**
	 * ������ ����������Ӧ����
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		// ��ʼ����
		case R.id.task_date:
			// ��ʾ���ڶԻ���
			MyDialogFragment dateDialog = new MyDialogFragment(
					MyDialogFragment.DATE_DIALOG);
			dateDialog.show(TaskActivity.this.getFragmentManager(), "date");
			break;
		// ��������
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

		// ȷ�ϡ������ݴ���task�����С����������ݿ⡣
		case R.id.task_confirm:
			Task taskBean = new Task(id, date.getText().toString(), null,
					enddate.getText().toString(), title.getText().toString(),
					content.getText().toString(), 0, 0);

			// �ж�task_tag.
			if (task_tag.equals(TASK_UPDATE))
			{
				if (!taskBiz.update(taskBean))
				{
					Toast.makeText(TaskActivity.this, "�޸�ʧ��",
							Toast.LENGTH_SHORT).show();
					return;
				}

				Toast.makeText(TaskActivity.this, "�޸ĳɹ�", Toast.LENGTH_SHORT)
						.show();
			} else
			{
				id = taskBiz.add(taskBean);
				// -1��ʾ���ʧ��
				if (id == -1)
				{
					Toast.makeText(TaskActivity.this, "���ʧ��",
							Toast.LENGTH_SHORT).show();
					return;
				}
				Toast.makeText(TaskActivity.this, "��ӳɹ�", Toast.LENGTH_SHORT)
						.show();
			}
			// ���ص�TaskShowActivity����������ǰAcivity
			SwitchActivity.switchActivity(TaskActivity.this,
					Destination.TaskShowActivity, id);
			TaskActivity.this.finish();
			break;
		// ȡ����ť
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
