/**      
 * 项目名称：Schedule<br> 
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
 * 描述: 文件操作类，实现导入导出。。方法不简洁，需要更改<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月8日/下午1:28:49<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月8日/下午1:28:49<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class FileTool
{
	/**
	 * 描述：导出文件到/schedule/文件名。
	 * 
	 * @param context
	 *            调用这个方法时所在的上下文
	 * @param name
	 *            生成文件名
	 */
	public static void exportFile(Context context, String name)
	{
		TaskBiz taskBiz = new TaskBizImpl(context);
		List<Task> list = taskBiz.queryAll();
		File file = getFile(name);
		if (file == null)
		{
			System.out.println("文件获取失败");
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
			// System.out.println("writer的编码：" + writer.getEncoding());
			writer.flush();
		} catch (IOException e)
		{
			System.out.println("导出文件时出错");
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
	 * 描述：导入文件，即导入待办事项
	 * 
	 * @param context
	 *            调用这个方法时所在的上下文
	 * @param name
	 *            生成文件名
	 */
	public static void importFile(Context context, String name)
	{
		TaskBiz taskBiz = new TaskBizImpl(context);

		File file = getFile(name);
		if (file == null)
		{
			System.out.println("文件获取失败");
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
	 * 描述：获得在/shedule文件夹下的文件
	 * 
	 * @param name
	 *            文件名
	 */
	public static File getFile(String name)
	{
		File file = null;
		boolean isFalse = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_UNMOUNTED);
		if (isFalse)
		{
			System.out.println("获取存储卡路径失败");
			return null;
		}
		file = Environment.getExternalStorageDirectory();
		System.out.println(file.getPath());

		// 返回文件
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
				System.out.println("数据库导出文件创建失败");
			}
		}

		return schedule;
	}

	/**
	 * 描述：获得/schedule文件夹中的所有文件名。
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
