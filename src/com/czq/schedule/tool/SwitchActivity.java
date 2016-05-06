package com.czq.schedule.tool;

import android.content.Context;
import android.content.Intent;

import com.czq.schedule.MainActivity;
import com.czq.schedule.TaskActivity;
import com.czq.schedule.TaskListActivity;
import com.czq.schedule.TaskShowActivity;

public class SwitchActivity
{
	

	// context为现在所在的activity,destination为要前往的activity, id为task的id，有些activity要用到
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
			//id为0时，为添加待办事项。id不为0时，为修改。
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
			//id为0时，为所有事项。id不为0时，为查看当天事项。
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
