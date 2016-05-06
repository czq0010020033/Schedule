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

	// typeֵΪMyDialogFragment.DATE_DIALOG��TIME_DIALOG
	public MyDialogFragment(int type)
	{
		// TODO �Զ����ɵĹ��캯�����
		super();
		this.type = type;

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		Dialog dialog = null;
		// TODO �Զ����ɵķ������
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
			builder.setTitle("��ʾ");
			builder.setMessage("�Ƿ�ɾ����");
			builder.setNegativeButton("ȡ��", new OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// ���ص�TaskShow
					if (dialog == null)
					{
						System.out.println("dialog is null");
					}
					dialog.dismiss();
				}
			});
			builder.setPositiveButton("ȷ��", new OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// ִ��ɾ������
					TaskBiz taskBiz = new TaskBizImpl(getActivity());
					TextView task_id = (TextView) getActivity().findViewById(
							R.id.show_id);
					taskBiz.delete(Integer
							.valueOf(task_id.getText().toString()));
					// ��ʾ
					Toast.makeText(getActivity(), "ɾ���ɹ�", Toast.LENGTH_SHORT)
							.show();
					// ���ص�Tasklist
					SwitchActivity.switchActivity(getActivity(),
							Destination.TaskListActivity, 0);
				}
			});
			dialog = builder.create();
			break;
		}

		return dialog;
	}

	// ���ڸı����������
	private class DateListener implements OnDateSetListener
	{

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth)
		{
			// TODO �Զ����ɵķ������
			TextView date = (TextView) getActivity().findViewById(
					R.id.text_date);
			// ������д�ɹ̶���ʽ���ַ���
			date.setText(TaskBizImpl.getDateStr(year, monthOfYear, dayOfMonth));
		}
	}

	private class EnddateListener implements OnDateSetListener
	{

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth)
		{
			// TODO �Զ����ɵķ������
			TextView enddate = (TextView) getActivity().findViewById(
					R.id.text_enddate);
			// ������д�ɹ̶���ʽ���ַ���
			

			enddate.setText(TaskBizImpl.getDateStr(year, monthOfYear, dayOfMonth));
		}
	}

	/*
	 * // ʱ��ı亯�� private class TimeListener implements OnTimeSetListener {
	 * 
	 * @Override public void onTimeSet(TimePicker view, int hourOfDay, int
	 * minute) { // TODO �Զ����ɵķ������ TextView time = (TextView)
	 * getActivity().findViewById( R.id.text_time); // ��ʱ��д�ɹ̶���ʽ���ַ��� String
	 * timeStr = hourOfDay + ":" + minute + ":00"; time.setText(timeStr); } }
	 */
}
