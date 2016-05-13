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
 * 描述: 提供一个switchActivity的静态方法来简化启动Activity的代码<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月7日/下午8:54:06<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月7日/下午8:54:06<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class SwitchActivity
{

	/**
	 * 描述：跳转到别的Activity
	 * 
	 * @param context
	 *            现在所在的activity
	 * @param destination
	 *            要前往的activity
	 * @param id  task的id，有些activity要用到。跳转到TaskActivity时，0为添加待办事项，不为0时则为修改。
	 * 跳转到TaskShowActivity，必须要需要确定的id。
	 * 目前没用到跳转到TaskListActivity。
	 * 跳转到MainActivity时，id值被忽略。           
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
			// id为0时，为添加待办事项。id不为0时，为修改。
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
			// id为0时，为所有事项。id不为0时，为查看当天事项。
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
