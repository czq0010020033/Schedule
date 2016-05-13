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
 * ����: ��ʾ�Ի��򣬸��ݴ����־�Ĳ�ͬ������ͬ�Ի���<br>
 * <br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��7��/����6:59:28<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��7��/����6:59:28<br>
 * �޸ı�ע��<br>
 * �汾��1.0
 */
public class MyDialogFragment extends DialogFragment implements
		OnDateSetListener
{
	private int type = 0;

	/**
	 * ѡ��ʼ���ڶԻ���
	 */
	public final static int DATE_DIALOG = 0;
	// public final static int TIME_DIALOG = 1;
	/**
	 * �Ƿ�ɾ���Ի���
	 */
	public final static int ALERT_DIALOG = 2;
	/**
	 * ѡ��������ڶԻ���
	 */
	public final static int ENDDATE_DIALOG = 3;

	// typeֵΪMyDialogFragment.DATE_DIALOG��TIME_DIALOG
	public MyDialogFragment(int type)
	{
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
		// ѡ��ʼ���ڶԻ���
		case DATE_DIALOG:
			Calendar c = Calendar.getInstance();
			// ʵ�����Ի��򣬲���onDateSet�д�����Ӧ
			dialog = new DatePickerDialog(getActivity(), this,
					c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH));
			break;
		// ѡ��������ڶԻ���
		case ENDDATE_DIALOG:
			Calendar ce = Calendar.getInstance();
			// ʵ�����Ի��򣬲���onDateSet�д�����Ӧ
			dialog = new DatePickerDialog(getActivity(), this,
					ce.get(Calendar.YEAR), ce.get(Calendar.MONTH),
					ce.get(Calendar.DAY_OF_MONTH));
			break;
		/*
		 * case TIME_DIALOG: Calendar ct = Calendar.getInstance(); dialog = new
		 * TimePickerDialog(getActivity(), new TimeListener(),
		 * ct.get(Calendar.HOUR_OF_DAY), ct.get(Calendar.MINUTE), true); break;
		 */
		//�Ƿ�ɾ���Ի���ͨ��builder������һ��AlertDialog
		case ALERT_DIALOG:
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("��ʾ");
			builder.setMessage("�Ƿ�ɾ����");
			//ȡ�����ļ���
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
			//ȷ�����ļ���
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
					taskBiz.close();
					// ��ʾ
					Toast.makeText(getActivity(), "ɾ���ɹ�", Toast.LENGTH_SHORT)
							.show();
					// ����ҳ��
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
	 * ���������ݱ�־��ͬ��ʾ�ڲ�ͬ��TextView��
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
		// ������д�ɹ̶���ʽ���ַ���
		if (date != null)
			date.setText(StrTool.getDateStr(year, monthOfYear, dayOfMonth));
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
