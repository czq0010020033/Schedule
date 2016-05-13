package com.czq.schedule.tool;

import android.content.Context;
import android.content.Intent;

import com.czq.schedule.AboutActivity;
import com.czq.schedule.ExportActivity;
import com.czq.schedule.ImportActivity;
import com.czq.schedule.MainActivity;
import com.czq.schedule.TaskActivity;
import com.czq.schedule.TaskListActivity;
import com.czq.schedule.TaskShowActivity;

/**
 * ����: �ṩһ��switchActivity�ľ�̬������������Activity�Ĵ���<br>
 * <br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��7��/����8:54:06<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��7��/����8:54:06<br>
 * �޸ı�ע��<br>
 * �汾��1.0
 */
public class SwitchActivity
{

	/**
	 * ��������ת�����Activity
	 * 
	 * @param context
	 *            �������ڵ�activity
	 * @param destination
	 *            Ҫǰ����activity
	 * @param id  task��id����ЩactivityҪ�õ�����ת��TaskActivityʱ��0Ϊ��Ӵ��������Ϊ0ʱ��Ϊ�޸ġ�
	 * ��ת��TaskShowActivity������Ҫ��Ҫȷ����id��
	 * Ŀǰû�õ���ת��TaskListActivity��
	 * ��ת��MainActivityʱ��idֵ�����ԡ�           
	 */
	public static void switchActivity(Context context, Destination destination,
			int id)
	{
		Intent intent = null;
		switch (destination)
		{
		case MainActivity:
			intent = new Intent(context, MainActivity.class);
			break;
		case TaskActivity:
			// idΪ0ʱ��Ϊ��Ӵ������id��Ϊ0ʱ��Ϊ�޸ġ�
			intent = new Intent(context, TaskActivity.class);
			if (id == 0)
			{
				intent.putExtra(TaskActivity.TASK_TAG, TaskActivity.TASK_ADD);
			} else
			{
				intent.putExtra("task_id", id);
				intent.putExtra(TaskActivity.TASK_TAG, TaskActivity.TASK_UPDATE);
			}
			break;
		case TaskListActivity:
			// idΪ0ʱ��Ϊ�������id��Ϊ0ʱ��Ϊ�鿴�������
			intent = new Intent(context, TaskListActivity.class);
			intent.putExtra(TaskListActivity.TASKLIST_TAG,
					TaskListActivity.TAG_ALL);
			break;
		case TaskShowActivity:
			intent = new Intent(context, TaskShowActivity.class);
			intent.putExtra("task_id", id);
			break;
		case AboutActivity:
			intent = new Intent(context, AboutActivity.class);
			break;
		case ExportActivity:
			intent = new Intent(context, ExportActivity.class);
			break;
		case ImportActivity:
			intent = new Intent(context, ImportActivity.class);
			break;
		
		}
		context.startActivity(intent);
	}
}
