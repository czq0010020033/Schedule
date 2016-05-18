/**      
 * ��Ŀ���ƣ�Schedule<br> 
 */
package com.czq.schedule.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.os.Environment;

import com.czq.schedule.bean.Task;
import com.czq.schedule.biz.TaskBiz;
import com.czq.schedule.biz.TaskBizImpl;

/**
 * ����: �ļ������࣬ʵ�ֵ��뵼��������������࣬��Ҫ����<br>
 * <br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��8��/����1:28:49<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��8��/����1:28:49<br>
 * �޸ı�ע��<br>
 * �汾��1.0
 */
public class FileTool
{
	/**
	 * �����������ļ���/schedule/�ļ�����
	 * 
	 * @param context
	 *            �����������ʱ���ڵ�������
	 * @param name
	 *            �����ļ���
	 */
	public static void exportFile(Context context, String name)
	{
		TaskBiz taskBiz = new TaskBizImpl(context);
		List<Task> list = taskBiz.queryAll();
		File file = getFile(name);
		if (file == null)
		{
			System.out.println("�ļ���ȡʧ��");
			return;
		}

		OutputStreamWriter writer = null;
		String taskStr = null;
		try
		{
			writer = new OutputStreamWriter(new FileOutputStream(file),
					"unicode");

			for (Task task : list)
			{
				taskStr = task.getDate() + " " + task.getEnddate() + " "
						+ task.getTitle() + "\n";
				writer.append(taskStr);

			}
			// System.out.println("writer�ı��룺" + writer.getEncoding());
			writer.flush();
		} catch (IOException e)
		{
			System.out.println("�����ļ�ʱ����");
		} finally
		{
			try
			{
				if (writer != null)
					writer.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		taskBiz.close();
	}

	/**
	 * �����������ļ����������������
	 * 
	 * @param context
	 *            �����������ʱ���ڵ�������
	 * @param name
	 *            �����ļ���
	 */
	public static void importFile(Context context, String name)
	{
		TaskBiz taskBiz = new TaskBizImpl(context);

		File file = getFile(name);
		if (file == null)
		{
			System.out.println("�ļ���ȡʧ��");
			return;
		}
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "unicode"));
			String taskStr = reader.readLine();
			while (taskStr != null && !taskStr.equals(""))
			{
				// System.out.println(taskStr);
				String[] str = taskStr.split(" ", 3);
				if (str.length == 3)
				{
					Task task = new Task();
					task.setDate(str[0]);
					task.setEnddate(str[1]);
					task.setTitle(str[2]);
					taskBiz.addWithNoUpdate(task);
				}
				taskStr = reader.readLine();
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (reader != null)
				try
				{
					reader.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
		}

		taskBiz.close();
	}

	/**
	 * �����������/shedule�ļ����µ��ļ�
	 * 
	 * @param name
	 *            �ļ���
	 */
	public static File getFile(String name)
	{
		File file = null;
		boolean isFalse = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_UNMOUNTED);
		if (isFalse)
		{
			System.out.println("��ȡ�洢��·��ʧ��");
			return null;
		}
		file = Environment.getExternalStorageDirectory();
		System.out.println(file.getPath());

		// �����ļ�
		File path = new File(file.getPath() + "/schedule");
		File schedule = new File(file.getPath() + "/schedule/" + name);
		if (!path.exists())
		{
			path.mkdirs();
		}
		if (!schedule.exists())
		{
			try
			{
				schedule.createNewFile();
			} catch (IOException e)
			{
				System.out.println("���ݿ⵼���ļ�����ʧ��");
			}
		}

		return schedule;
	}

	/**
	 * ���������/schedule�ļ����е������ļ�����
	 */
	public static List<String> getFileInDir()
	{
		File sdPath = null;
		boolean isFalse = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_UNMOUNTED);
		if (isFalse)
		{
			return null;
		}
		sdPath = Environment.getExternalStorageDirectory();

		File path = new File(sdPath.getPath() + "/schedule");
		if (!path.exists())
		{
			path.mkdirs();
		}
		
		String[] fileStrList = path.list();
		List<String> list = Arrays.asList(fileStrList);

		return list;
	}
}
