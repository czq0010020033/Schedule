package com.czq.schedule.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.czq.schedule.bean.Task;
import com.czq.schedule.biz.TaskBizImpl;
import com.czq.schedule.tool.StrTool;

public class DBManager
{
	private DBHelper helper;
	private SQLiteDatabase db;

	public DBManager(Context context)
	{
		helper = new DBHelper(context);
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
		// mFactory);
		// 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = helper.getWritableDatabase();

	}

	/**
	 * add tasks
	 * 
	 * @param tasks
	 *            返回成功添加的task的id
	 */
	public int add(Task task)
	{
		int id = 0;
		db.execSQL(
				"INSERT INTO task VALUES(null, ?, ?, ?, ?, ?, ?, ?)",
				new Object[]
				{ task.getDate(), task.getTime(), task.getEnddate(),
						task.getTitle(), task.getContent(),
						task.getRemindWay(), task.getIsPast() });

		Cursor cursor = db.rawQuery("select last_insert_rowid() from task",
				null);

		if (cursor.moveToFirst())
			id = cursor.getInt(0);

		return id;
	}

	/**
	 * update task's age
	 * 
	 * @param task
	 */
	public void update(Task task)
	{
		ContentValues cv = new ContentValues();
		cv.put(StrTool.DATE, task.getDate());
		cv.put(StrTool.TIME, task.getTime());
		cv.put(StrTool.ENDDATE, task.getEnddate());
		cv.put(StrTool.TITLE, task.getTitle());
		cv.put(StrTool.CONTENT, task.getContent());
		cv.put(StrTool.REMINDWAY, task.getRemindWay());
		cv.put(StrTool.ISPAST, task.getIsPast());

		db.update("task", cv, "id = ?", new String[]
		{ String.valueOf(task.getId()) });
	}

	/**
	 * delete old task
	 * 
	 * @param task
	 */
	public void delete(int id)
	{
		db.delete("task", "id = ?", new String[]
		{ String.valueOf(id) });
	}

	// 根据Id查找Task
	public Task query(int id)
	{
		Cursor c = db.rawQuery("SELECT * FROM task where id=?", new String[]
		{ String.valueOf(id) });
		List<Task> tasks = queryByCursor(c);
		if (tasks.isEmpty())
		{

			return null;
		}
		Task task = tasks.get(0);
		return task;
	}

	/**
	 * query all tasks, return list
	 * 
	 * @return List<Task> 按日期排序
	 */
	public List<Task> queryAll()
	{

		Cursor c = db.rawQuery("SELECT * FROM task ORDER BY date", null);

		return queryByCursor(c);
	}

	// 根据日期查询待办事项 ..查询的task为把当天日期包含在起始日期和到期日期之间。
	public List<Task> queryByDate(int year, int month, int dayOfMonth)
	{
		String dateStr = TaskBizImpl.getDateStr(year, month, dayOfMonth);
		
		Cursor c = db
				.rawQuery(
						"SELECT * FROM task WHERE date<=? AND enddate>=?",
						new String[]{dateStr, dateStr});
		System.out.println(dateStr + "总共有" + c.getCount());
		return queryByCursor(c);
	}

	// 根据Cursor对象返回Task数组
	public List<Task> queryByCursor(Cursor c)
	{
		List<Task> tasks = new ArrayList<Task>();
		while (c.moveToNext())
		{

			Task task = new Task(c.getInt(c.getColumnIndex(StrTool.ID)),
					c.getString(c.getColumnIndex(StrTool.DATE)), c.getString(c
							.getColumnIndex(StrTool.TIME)), c.getString(c
							.getColumnIndex(StrTool.ENDDATE)), c.getString(c
							.getColumnIndex(StrTool.TITLE)), c.getString(c
							.getColumnIndex(StrTool.CONTENT)), c.getInt(c
							.getColumnIndex(StrTool.REMINDWAY)), c.getInt(c
							.getColumnIndex(StrTool.ISPAST)));

			tasks.add(task);
		}
		c.close();
		return tasks;
	}

	/**
	 * close database
	 */
	public void closeDB()
	{
		db.close();
	}
}