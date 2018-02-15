package com.czq.schedule.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

import com.czq.schedule.R;
import com.czq.schedule.TaskListActivity;

/**
 * ����: ��ҳ�棨һ����������ͨ��ѡ����������ʾ�����ڵĴ����������������bug����Ҫ����<br>
 * <br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��7��/����8:30:50<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��7��/����8:30:50<br>
 * �޸ı�ע��<br>
 * �汾��1.0
 */
public class MainFragment extends Fragment implements OnDateChangeListener
{
	Long date;
	/**
	 * android�Դ���������
	 */
	private CalendarView calendarView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.frag_main, container, false);
		calendarView = (CalendarView) view.findViewById(R.id.calendarView);
		calendarView.setOnDateChangeListener(this);
/*		System.out.println("calendarView childcount: "
				+ calendarView.getChildCount());*/

		date = calendarView.getDate();
		return view;
	}

	/**
	 * ������ ��ȡѡ������ڣ�Ȼ��ת��TaskListAcitity��ʾ����Ĵ�������
	 * 
	 * @see android.widget.CalendarView.OnDateChangeListener#onSelectedDayChange(android.widget.CalendarView,
	 *      int, int, int)
	 */
	@Override
	public void onSelectedDayChange(CalendarView view, int year, int month,
			int dayOfMonth)
	{
		if (view.getDate() != date)
		{
			date = view.getDate();
			// ��listActivity,������ʾ��������
			Intent intent = new Intent(MainFragment.this.getActivity(),
					TaskListActivity.class);
			intent.putExtra(TaskListActivity.TASKLIST_TAG,
					TaskListActivity.TAG_DATE);
			intent.putExtra("year", year);
			intent.putExtra("month", month);
			intent.putExtra("dayOfMonth", dayOfMonth);
			MainFragment.this.startActivity(intent);
		}
	}
}
