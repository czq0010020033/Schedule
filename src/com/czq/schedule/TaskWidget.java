package com.czq.schedule;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.czq.schedule.biz.TaskBizImpl;

/**
 * Ϊ�ֻ��������ؼ������������ؼ�ʱ�����������
 * 
 * AppWidgetProvider���̳���BroadcastRecevier����AppWidgetӦ��update��enable��
 * disable��deleteʱ����֪ͨ�� ���У�onUpdate��onReceive����õ��ķ��������ǽ��ո���֪ͨ
 * 
 * @author jiqinlin
 *
 */
public class TaskWidget extends AppWidgetProvider
{
	public static int widgetIds[];

	/**
	 * ��������ĸ���App
	 * Widget�����ʱ����AppWidgetProviderInfo���updatePeriodMillis���Զ���(��λΪ����)��
	 * ע�⣺SDK1.5֮���android:updatePeriodMillis��ʧЧ�ˣ�Ҫ�Լ�����service���¡� �������Ҳ�����û����App
	 * Widgetʱ�����ã������Ӧ��ִ�л��������ã�����Ϊ��ͼ�����¼�������������һ����ʱ�ķ���Service�������Ҫ�Ļ���
	 * ���ǣ�������Ѿ�������һ�����û������������û����App Widgetʱ�����ᱻ���ã�
	 * ��ֻ�ں�������ʱ�����á����ûӦ�����������ʱ����ִ�е�һ�θ��¡�
	 */

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds)
	{
		System.out.println("onUpdate");
		// ��ֵ
		widgetIds = appWidgetIds;
		// ����������ʱ�������������
		// Intent intent=new Intent(context, MainActivity.class);
		/*
		 * PendingIntent pendingIntent=PendingIntent.getActivity(context, 0,
		 * intent, 0); //RemoteViews��������һ��View�����ܹ���ʾ�����������У������ں�layout��Դ�ļ�ʵ�ֲ��֡�
		 * //��Ȼ������android.widget.RemoteViews������appWidget����,����Android
		 * Widgets�����лᾭ���õ����� //��Ҫ�ǿ��Կ���̵���(appWidget��һ������������ͳһ���е�)�� RemoteViews
		 * myRemoteViews = new RemoteViews(context.getPackageName(),
		 * R.layout.my_layout);
		 * //myRemoteViews.setImageViewResource(R.id.imageView,
		 * R.drawable.png1);//���ò��ֿؼ������ԣ�Ҫ�ر�ע�⣩
		 * myRemoteViews.setOnClickPendingIntent(R.id.btn, pendingIntent);
		 * ComponentName myComponentName = new ComponentName(context,
		 * TestActivity.class);
		 * //�������AppWidget����AppwidgetProvider����֪ͨ���ṩ�˸���AppWidget״̬
		 * ����ȡ�Ѿ���װ��Appwidget�ṩ��Ϣ�����������״̬ AppWidgetManager myAppWidgetManager =
		 * AppWidgetManager.getInstance(context);
		 * myAppWidgetManager.updateAppWidget(myComponentName, myRemoteViews);
		 */
		RemoteViews myRemoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_task);
		
		myRemoteViews.setTextViewText(R.id.widget_date, TaskBizImpl.getDateStr() + " ��������\n");
		myRemoteViews.setTextViewText(R.id.widget_text,
				TaskBizImpl.getTodayTitles(context));
		// ��������
		for (int id : appWidgetIds)
		{
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
	 * Widget�㲥��ǡ���ĵ������������� ע��: ��Android 1.5�У���һ����֪���⣬onDeleted()�����ڵ���ʱ�������á�
	 * Ϊ�˹��������⣬�������Group post������������ʵ��onReceive()���������onDeleted()�ص���
	 */
	@Override
	public void onReceive(Context context, Intent intent)
	{
		System.out.println("onReceive");
		super.onReceive(context, intent);
		System.out.println("next");
		// ���ڸı�ʱ����
		if (intent.getAction().equals(Intent.ACTION_DATE_CHANGED))
		{
			System.out.println("ACTION_DATE_CHANGED");
			System.out.println(" " + widgetIds[0]);
			if (widgetIds != null && widgetIds.length > 0)
			{
				onUpdate(context, AppWidgetManager.getInstance(context), widgetIds);
			}
		}

	}

}