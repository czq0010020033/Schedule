package com.czq.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.czq.schedule.biz.TaskBizImpl;

public class MainActivity extends Activity
{

	//private CalendarView calendarView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ��������
		setContentView(R.layout.activity_main);
		// ����widget
		TaskBizImpl.updateWidget(this);

		/*calendarView = (CalendarView) findViewById(R.id.calendarView);

		calendarView.setOnDateChangeListener(new DateListener());*/

	}

	/*
	 * private void setDefaultFragment() { FragmentManager fm =
	 * getFragmentManager(); FragmentTransaction transaction =
	 * fm.beginTransaction(); mainFragment = new MainFragment();
	 * transaction.replace(R.id.frag_main, mainFragment); transaction.commit();
	 * }
	 */

	// ���������¼�
	/*private class DateListener implements OnDateChangeListener
	{

		@Override
		public void onSelectedDayChange(CalendarView view, int year, int month,
				int dayOfMonth)
		{
			// ��listActivity,������ʾ�����������
			Intent intent = new Intent(MainActivity.this,
					TaskListActivity.class);
			intent.putExtra(TaskListActivity.TASKLIST_TAG,
					TaskListActivity.TAG_DATE);
			intent.putExtra("year", year);
			intent.putExtra("month", month);
			intent.putExtra("dayOfMonth", dayOfMonth);
			startActivity(intent);
		}
	}*/

	
}
