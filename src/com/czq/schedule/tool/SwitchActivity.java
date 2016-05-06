package com.czq.schedule.tool;

import android.content.Context;
import android.content.Intent;

import com.czq.schedule.MainActivity;
import com.czq.schedule.TaskActivity;
import com.czq.schedule.TaskListActivity;
import com.czq.schedule.TaskShowActivity;

public class SwitchActivity
{
	

	// contextΪ�������ڵ�activity,destinationΪҪǰ����activity, idΪtask��id����ЩactivityҪ�õ�
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
			//idΪ0ʱ��Ϊ��Ӵ������id��Ϊ0ʱ��Ϊ�޸ġ�
			intent = new Intent(context, TaskActivity.class);
			if(id == 0)
			{
				intent.putExtra(TaskActivity.TASK_TAG, TaskActivity.TASK_ADD);
			}
			else {
				intent.putExtra("task_id", id);
				intent.putExtra(TaskActivity.TASK_TAG, TaskActivity.TASK_UPDATE);
			}
			break;
		case TaskListActivity:
			//idΪ0ʱ��Ϊ�������id��Ϊ0ʱ��Ϊ�鿴�������
				intent = new Intent(context, TaskListActivity.class);
				intent.putExtra(TaskListActivity.TASKLIST_TAG,
						TaskListActivity.TAG_ALL);
			break;
		case TaskShowActivity:
			intent = new Intent(context, TaskShowActivity.class);
			intent.putExtra("task_id", id);
			break;
		}
		context.startActivity(intent);
	}
}
