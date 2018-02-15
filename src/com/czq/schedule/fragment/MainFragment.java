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
 * 描述: 主页面（一个日历），通过选择日期来显示该日期的待办事项，但该日历有bug，需要更改<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月7日/下午8:30:50<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月7日/下午8:30:50<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class MainFragment extends Fragment implements OnDateChangeListener
{
	Long date;
	/**
	 * android自带的日历类
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
	 * 描述： 获取选择的日期，然后转到TaskListAcitity显示当天的待办事项
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
