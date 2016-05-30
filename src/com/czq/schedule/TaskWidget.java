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
 * ����:Ϊ�ֻ��������ؼ������������ؼ�ʱ�������ҳ��MainActivity
 * AppWidgetProvider���̳���BroadcastRecevier
 * ����AppWidgetӦ��update��enable��disable��deleteʱ����֪ͨ��
 * ���У�onUpdate��onReceive����õ��ķ��������ǽ��ո���֪ͨ��<br>
 * <br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��7��/����3:33:33<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��7��/����3:33:33<br>
 * �޸ı�ע��<br>
 * �汾��1.0
 */
public class TaskWidget extends AppWidgetProvider
{
	public static int widgetIds[];

	/**
	 * ��������ĸ���App
	 * Widget�����ʱ����AppWidgetProviderInfo���updatePeriodMillis���Զ���(��λΪ����)��
	 * ע�⣺SDK1.5֮���android:updatePeriodMillis��ʧЧ�ˣ�Ҫ�Լ�����service���¡� �������Ҳ�����û����App
	 * Widgetʱ�����ã������Ӧ��ִ�л��������ã�����Ϊ��ͼ�����¼�������������һ����ʱ�ķ���Service�������Ҫ�Ļ���<br>
	 * �ó������onUpdate�ķ�ʽʱͨ������Inent����������������onreceive�е���onUpdate����widget��
	 */
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds)
	{
		System.out.println("onUpdate");
		// ��ֵ
		widgetIds = appWidgetIds;
		// RemoteViews��������һ��View�����ܹ���ʾ�����������У������ں�layout��Դ�ļ�ʵ�ֲ��֡�widgei����
		RemoteViews myRemoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_task);
		myRemoteViews.setTextViewText(R.id.widget_date, StrTool.getDateStr()
				+ " ��������\n");
		myRemoteViews.setTextViewText(R.id.widget_text,
				TaskBizImpl.getTodayTitles(context));

		// ��������¼�������������ʱ�������������
		Intent intent = new Intent(context, MainActivity.class);
		myRemoteViews.setOnClickPendingIntent(R.id.widget_click,
				PendingIntent.getActivity(context, 0, intent, 0));

		// �������£�TaskWidget��ʵ�����ܲ�ֻһ��
		for (int id : appWidgetIds)
		{
			// appWidgetManager�������AppWidget����AppwidgetProvider����֪ͨ���ṩ�˸���AppWidget״̬
			appWidgetManager.updateAppWidget(id, myRemoteViews);
		}

	}

	/**
	 * ��App Widget��������ɾ��ʱ�����á�
	 */
	@Override
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		System.out.println("onDeleted");
		super.onDeleted(context, appWidgetIds);
	}

	/**
	 * ��һ��App Widgetʵ����һ�δ���ʱ�����á� ���磬����û��������App Widgetʵ����ֻ�ڵ�һ�α����á�
	 * �������Ҫ��һ���µ����ݿ����ִ�������������е�App Widgetʵ��ֻ��Ҫ����һ�ε����ã� ��ô�����������������ĺõط���
	 */
	@Override
	public void onEnabled(Context context)
	{
		System.out.println("onEnabled");
		super.onEnabled(context);
	}

	/**
	 * �����App
	 * Widget�����һ��ʵ������������ɾ��ʱ�����á���Ӧ����onEnabled(Context)����һЩ������������ɾ��һ����ʱ�����ݿ�
	 */
	@Override
	public void onDisabled(Context context)
	{
		System.out.println("onDisabled");
		super.onDisabled(context);
	}

	/**
	 * ���յ�ÿ���㲥ʱ���ᱻ���ã�����������Ļص�����֮ǰ�� ��ͨ������Ҫʵ�������������Ϊȱʡ��AppWidgetProviderʵ�ֹ�������App
	 * Widget�㲥��ǡ���ĵ�������������
	 */
	@Override
	public void onReceive(Context context, Intent intent)
	{
		System.out.println("onReceive");
		super.onReceive(context, intent);

		// ���ڸı�ʱ����
		if (intent.getAction().equals(Intent.ACTION_DATE_CHANGED))
		{
			System.out.println("ACTION_DATE_CHANGED");
			System.out.println(" " + widgetIds[0]);
			if (widgetIds != null && widgetIds.length > 0)
			{
				// ���ø��º���
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
				// ���ø��º���
				onUpdate(context, AppWidgetManager.getInstance(context),
						widgetIds);
				ComponentName componentName = new ComponentName(context,
						TaskWidget.class);
				// ���ø��º���
				onUpdate(
						context,
						AppWidgetManager.getInstance(context),
						AppWidgetManager.getInstance(context).getAppWidgetIds(
								componentName));

			}
		}
	}

	/**
	 * ����������widget������һ��intent��widget���գ�������onUpdate��������һ����̬����
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
		// ����widget��id

		extras.putIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS, id);
		intent.putExtras(extras);
		context.sendBroadcast(intent);

		System.out.println("widgetIds[0]:" + widgetIds[0]);
		System.out.println(AppWidgetManager.getInstance(context)
				.getAppWidgetIds(componentName)[0]);

	}

}