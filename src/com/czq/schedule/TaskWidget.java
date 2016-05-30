package com.czq.schedule;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.czq.schedule.biz.TaskBizImpl;
import com.czq.schedule.tool.StrTool;

/**
 * 描述:为手机添加桌面控件，当点击桌面控件时则进入主页面MainActivity
 * AppWidgetProvider：继承自BroadcastRecevier
 * ，在AppWidget应用update、enable、disable和delete时接收通知。
 * 其中，onUpdate、onReceive是最常用到的方法，它们接收更新通知。<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月7日/下午3:33:33<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月7日/下午3:33:33<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class TaskWidget extends AppWidgetProvider
{
	public static int widgetIds[];

	/**
	 * 用来间隔的更新App
	 * Widget，间隔时间用AppWidgetProviderInfo里的updatePeriodMillis属性定义(单位为毫秒)。
	 * 注意：SDK1.5之后此android:updatePeriodMillis就失效了，要自己创建service更新。 这个方法也会在用户添加App
	 * Widget时被调用，因此它应该执行基础的设置，比如为视图定义事件处理器并启动一个临时的服务Service，如果需要的话。<br>
	 * 该程序调用onUpdate的方式时通过发送Inent让这个类结束，并在onreceive中调用onUpdate更新widget。
	 */
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds)
	{
		System.out.println("onUpdate");
		// 赋值
		widgetIds = appWidgetIds;
		// RemoteViews类描述了一个View对象能够显示在其他进程中，可以融合layout资源文件实现布局。widgei对象
		RemoteViews myRemoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_task);
		myRemoteViews.setTextViewText(R.id.widget_date, StrTool.getDateStr()
				+ " 今日事项\n");
		myRemoteViews.setTextViewText(R.id.widget_text,
				TaskBizImpl.getTodayTitles(context));

		// 创建点击事件，点击桌面组件时进入主程序入口
		Intent intent = new Intent(context, MainActivity.class);
		myRemoteViews.setOnClickPendingIntent(R.id.widget_click,
				PendingIntent.getActivity(context, 0, intent, 0));

		// 遍历更新，TaskWidget的实例可能不只一个
		for (int id : appWidgetIds)
		{
			// appWidgetManager负责管理AppWidget，向AppwidgetProvider发送通知。提供了更新AppWidget状态
			appWidgetManager.updateAppWidget(id, myRemoteViews);
		}

	}

	/**
	 * 当App Widget从宿主中删除时被调用。
	 */
	@Override
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		System.out.println("onDeleted");
		super.onDeleted(context, appWidgetIds);
	}

	/**
	 * 当一个App Widget实例第一次创建时被调用。 比如，如果用户添加两个App Widget实例，只在第一次被调用。
	 * 如果你需要打开一个新的数据库或者执行其他对于所有的App Widget实例只需要发生一次的设置， 那么这里是完成这个工作的好地方。
	 */
	@Override
	public void onEnabled(Context context)
	{
		System.out.println("onEnabled");
		super.onEnabled(context);
	}

	/**
	 * 当你的App
	 * Widget的最后一个实例被从宿主中删除时被调用。你应该在onEnabled(Context)中做一些清理工作，比如删除一个临时的数据库
	 */
	@Override
	public void onDisabled(Context context)
	{
		System.out.println("onDisabled");
		super.onDisabled(context);
	}

	/**
	 * 接收到每个广播时都会被调用，而且在上面的回调函数之前。 你通常不需要实现这个方法，因为缺省的AppWidgetProvider实现过滤所有App
	 * Widget广播并恰当的调用上述方法。
	 */
	@Override
	public void onReceive(Context context, Intent intent)
	{
		System.out.println("onReceive");
		super.onReceive(context, intent);

		// 日期改变时调用
		if (intent.getAction().equals(Intent.ACTION_DATE_CHANGED))
		{
			System.out.println("ACTION_DATE_CHANGED");
			System.out.println(" " + widgetIds[0]);
			if (widgetIds != null && widgetIds.length > 0)
			{
				// 调用更新函数
				onUpdate(context, AppWidgetManager.getInstance(context),
						widgetIds);
			}
		}

		// update
		if (intent.getAction().equals("com.czq.action.APPWIDGET_UPDATE"))
		{
			System.out.println(" " + widgetIds[0]);
			if (widgetIds != null && widgetIds.length > 0)
			{
				System.out.println("update");
				// 调用更新函数
				onUpdate(context, AppWidgetManager.getInstance(context),
						widgetIds);
				ComponentName componentName = new ComponentName(context,
						TaskWidget.class);
				// 调用更新函数
				onUpdate(
						context,
						AppWidgetManager.getInstance(context),
						AppWidgetManager.getInstance(context).getAppWidgetIds(
								componentName));

			}
		}
	}

	/**
	 * 描述：更新widget，发送一个intent让widget接收，并调用onUpdate函数，是一个静态方法
	 * 
	 * @param context
	 *            void
	 */
	public static void updateWidget(Context context)
	{
		ComponentName componentName = new ComponentName(context,
				TaskWidget.class);
		int id[] = AppWidgetManager.getInstance(context).getAppWidgetIds(
				componentName);
		if (id == null || id.length == 0)
			return;

		Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		Bundle extras = new Bundle();
		// 放入widget的id

		extras.putIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS, id);
		intent.putExtras(extras);
		context.sendBroadcast(intent);

		System.out.println("widgetIds[0]:" + widgetIds[0]);
		System.out.println(AppWidgetManager.getInstance(context)
				.getAppWidgetIds(componentName)[0]);

	}

}