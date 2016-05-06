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
public class MainFragment extends Fragment
{
	private CalendarView calendarView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.frag_main, container, false);
		calendarView = (CalendarView) view.findViewById(R.id.calendarView);
		calendarView.setOnDateChangeListener(new DateListener());
		System.out.println("calendarView childcount: " + calendarView.getChildCount());
		return view;
	}

	// 日历监听事件
	private class DateListener implements OnDateChangeListener
	{

		@Override
		public void onSelectedDayChange(CalendarView view, int year, int month,
				int dayOfMonth)
		{
			
			// 打开listActivity,由其显示待办事项
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
