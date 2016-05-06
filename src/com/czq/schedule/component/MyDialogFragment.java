package com.czq.schedule.component;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.czq.schedule.R;
import com.czq.schedule.biz.TaskBiz;
import com.czq.schedule.biz.TaskBizImpl;
import com.czq.schedule.tool.Destination;
import com.czq.schedule.tool.SwitchActivity;

public class MyDialogFragment extends DialogFragment
{
	private int type;

	public final static int DATE_DIALOG = 0;
	// public final static int TIME_DIALOG = 1;
	public final static int ALERT_DIALOG = 2;
	public final static int ENDDATE_DIALOG = 3;

	// type值为MyDialogFragment.DATE_DIALOG或TIME_DIALOG
	public MyDialogFragment(int type)
	{
		// TODO 自动生成的构造函数存根
		super();
		this.type = type;

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		Dialog dialog = null;
		// TODO 自动生成的方法存根
		switch (type)
		{
		case DATE_DIALOG:

			Calendar c = Calendar.getInstance();

			dialog = new DatePickerDialog(getActivity(), new DateListener(),
					c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH));
			break;
		case ENDDATE_DIALOG:
			Calendar ce = Calendar.getInstance();

			dialog = new DatePickerDialog(getActivity(), new EnddateListener(),
					ce.get(Calendar.YEAR), ce.get(Calendar.MONTH),
					ce.get(Calendar.DAY_OF_MONTH));
			break;
		/*
		 * case TIME_DIALOG: Calendar ct = Calendar.getInstance(); dialog = new
		 * TimePickerDialog(getActivity(), new TimeListener(),
		 * ct.get(Calendar.HOUR_OF_DAY), ct.get(Calendar.MINUTE), true); break;
		 */
		case ALERT_DIALOG:
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("提示");
			builder.setMessage("是否删除？");
			builder.setNegativeButton("取消", new OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// 返回到TaskShow
					if (dialog == null)
					{
						System.out.println("dialog is null");
					}
					dialog.dismiss();
				}
			});
			builder.setPositiveButton("确定", new OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// 执行删除操作
					TaskBiz taskBiz = new TaskBizImpl(getActivity());
					TextView task_id = (TextView) getActivity().findViewById(
							R.id.show_id);
					taskBiz.delete(Integer
							.valueOf(task_id.getText().toString()));
					// 提示
					Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT)
							.show();
					// 返回到Tasklist
					SwitchActivity.switchActivity(getActivity(),
							Destination.TaskListActivity, 0);
				}
			});
			dialog = builder.create();
			break;
		}

		return dialog;
	}

	// 日期改变监听函数。
	private class DateListener implements OnDateSetListener
	{

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth)
		{
			// TODO 自动生成的方法存根
			TextView date = (TextView) getActivity().findViewById(
					R.id.text_date);
			// 将日期写成固定格式的字符串
			date.setText(TaskBizImpl.getDateStr(year, monthOfYear, dayOfMonth));
		}
	}

	private class EnddateListener implements OnDateSetListener
	{

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth)
		{
			// TODO 自动生成的方法存根
			TextView enddate = (TextView) getActivity().findViewById(
					R.id.text_enddate);
			// 将日期写成固定格式的字符串
			

			enddate.setText(TaskBizImpl.getDateStr(year, monthOfYear, dayOfMonth));
		}
	}

	/*
	 * // 时间改变函数 private class TimeListener implements OnTimeSetListener {
	 * 
	 * @Override public void onTimeSet(TimePicker view, int hourOfDay, int
	 * minute) { // TODO 自动生成的方法存根 TextView time = (TextView)
	 * getActivity().findViewById( R.id.text_time); // 将时间写成固定格式的字符串 String
	 * timeStr = hourOfDay + ":" + minute + ":00"; time.setText(timeStr); } }
	 */
}
