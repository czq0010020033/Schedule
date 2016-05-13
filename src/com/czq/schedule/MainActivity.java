package com.czq.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * 描述: 程序主页面，使用了fragment，一个日历fragment：MainFragmet，一个显示全部待办事项的fragement：
 * ListFragment。<br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月7日/下午3:25:12<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月7日/下午3:25:12<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class MainActivity extends Activity
{

	// private CalendarView calendarView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 填充标题栏
		setContentView(R.layout.activity_main);
		// 更新widget
		TaskWidget.updateWidget(this);

		/*
		 * calendarView = (CalendarView) findViewById(R.id.calendarView);
		 * 
		 * calendarView.setOnDateChangeListener(new DateListener());
		 */
	}

	/*
	 * private void setDefaultFragment() { FragmentManager fm =
	 * getFragmentManager(); FragmentTransaction transaction =
	 * fm.beginTransaction(); mainFragment = new MainFragment();
	 * transaction.replace(R.id.frag_main, mainFragment); transaction.commit();
	 * }
	 */

	// 日历监听事件
	/*
	 * private class DateListener implements OnDateChangeListener {
	 * 
	 * @Override public void onSelectedDayChange(CalendarView view, int year,
	 * int month, int dayOfMonth) { // 打开listActivity,由其显示当天待办事项 Intent intent =
	 * new Intent(MainActivity.this, TaskListActivity.class);
	 * intent.putExtra(TaskListActivity.TASKLIST_TAG,
	 * TaskListActivity.TAG_DATE); intent.putExtra("year", year);
	 * intent.putExtra("month", month); intent.putExtra("dayOfMonth",
	 * dayOfMonth); startActivity(intent); } }
	 */
}
