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
import com.czq.schedule.tool.StrTool;

/**
 * 描述: 显示对话框，根据传入标志的不同产生不同对话框<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月7日/下午6:59:28<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月7日/下午6:59:28<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class MyDialogFragment extends DialogFragment implements
		OnDateSetListener
{
	private int type = 0;

	/**
	 * 选择开始日期对话框
	 */
	public final static int DATE_DIALOG = 0;
	// public final static int TIME_DIALOG = 1;
	/**
	 * 是否删除对话框
	 */
	public final static int ALERT_DIALOG = 2;
	/**
	 * 选择结束日期对话框
	 */
	public final static int ENDDATE_DIALOG = 3;

	// type值为MyDialogFragment.DATE_DIALOG或TIME_DIALOG
	public MyDialogFragment(int type)
	{
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
		// 选择开始日期对话框
		case DATE_DIALOG:
			Calendar c = Calendar.getInstance();
			// 实例化对话框，并在onDateSet中处理响应
			dialog = new DatePickerDialog(getActivity(), this,
					c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH));
			break;
		// 选择结束日期对话框
		case ENDDATE_DIALOG:
			Calendar ce = Calendar.getInstance();
			// 实例化对话框，并在onDateSet中处理响应
			dialog = new DatePickerDialog(getActivity(), this,
					ce.get(Calendar.YEAR), ce.get(Calendar.MONTH),
					ce.get(Calendar.DAY_OF_MONTH));
			break;
		/*
		 * case TIME_DIALOG: Calendar ct = Calendar.getInstance(); dialog = new
		 * TimePickerDialog(getActivity(), new TimeListener(),
		 * ct.get(Calendar.HOUR_OF_DAY), ct.get(Calendar.MINUTE), true); break;
		 */
		//是否删除对话框，通过builder来建立一个AlertDialog
		case ALERT_DIALOG:
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("提示");
			builder.setMessage("是否删除？");
			//取消键的监听
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
			//确定键的监听
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
					taskBiz.close();
					// 提示
					Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT)
							.show();
					// 结束页面
					/*SwitchActivity.switchActivity(getActivity(),
							Destination.MainActivity, 0);*/
					getActivity().finish();
				}
			});
			dialog = builder.create();
			break;
		}

		return dialog;
	}

	/**
	 * 描述：根据标志不同显示在不同的TextView上
	 * 
	 * @see android.app.DatePickerDialog.OnDateSetListener#onDateSet(android.widget.DatePicker,
	 *      int, int, int)
	 */
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth)
	{
		TextView date = null;
		switch (type)
		{
		case DATE_DIALOG:
			date = (TextView) getActivity().findViewById(R.id.text_date);
			break;
		case ENDDATE_DIALOG:
			date = (TextView) getActivity().findViewById(R.id.text_enddate);
			break;
		}
		// 将日期写成固定格式的字符串
		if (date != null)
			date.setText(StrTool.getDateStr(year, monthOfYear, dayOfMonth));
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
