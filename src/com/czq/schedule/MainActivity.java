package com.czq.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * ����: ������ҳ�棬ʹ����fragment��һ������fragment��MainFragmet��һ����ʾȫ�����������fragement��
 * ListFragment��<br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��7��/����3:25:12<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��7��/����3:25:12<br>
 * �޸ı�ע��<br>
 * �汾��1.0
 */
public class MainActivity extends Activity
{

	// private CalendarView calendarView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ��������
		setContentView(R.layout.activity_main);
		// ����widget
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

	// ���������¼�
	/*
	 * private class DateListener implements OnDateChangeListener {
	 * 
	 * @Override public void onSelectedDayChange(CalendarView view, int year,
	 * int month, int dayOfMonth) { // ��listActivity,������ʾ����������� Intent intent =
	 * new Intent(MainActivity.this, TaskListActivity.class);
	 * intent.putExtra(TaskListActivity.TASKLIST_TAG,
	 * TaskListActivity.TAG_DATE); intent.putExtra("year", year);
	 * intent.putExtra("month", month); intent.putExtra("dayOfMonth",
	 * dayOfMonth); startActivity(intent); } }
	 */
}
