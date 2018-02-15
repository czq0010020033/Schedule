package com.czq.schedule.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.czq.schedule.bean.Task;
import com.czq.schedule.tool.StrTool;

/**
 * 描述: 数据库操作类，实现数据库的各种操作，这个类之前没有写一个规范的接口，如果要扩展，先写一个规范接口会更好<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月7日/下午7:30:00<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月7日/下午7:30:00<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class DBManager
{
	/**
	 * SQLite数据库的管理类
	 */
	private DBHelper helper;
	/**
	 * SQLite数据库类
	 */
	private SQLiteDatabase db;
	
	/*public SQLiteDatabase getDb()
	{
		return db;
	}*/
	/**
	 * <p>
	 * 描述: 实例化DBHelper类，并且同时实例化SQLiteDatabase类
	 * </p>
	 * 
	 * @param context
	 */
	public DBManager(Context context)
	{
		helper = new DBHelper(context);
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
		// mFactory);
		// 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = helper.getWritableDatabase();
		System.out.println(db.getPath());

	}

	/**
	 * 描述：添加待办事项到数据库
	 * 
	 * @param task
	 * @return int 成功添加的task的id
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
	 * 描述：更新一个待办事项
	 * 
	 * @param task
	 *            传入的是一个完整的task，不是一个id
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
	 * 描述：通过task的id来删除待办事项
	 * 
	 * @param id
	 *            task的id
	 */
	public void delete(int id)
	{
		db.delete("task", "id = ?", new String[]
		{ String.valueOf(id) });
	}

	/**
	* 描述： 将待办事项全部删除
	*/ 
	public void deleteAll()
	{
		db.delete("task", "", null);
	}
	
	/**
	 * 描述：根据Id查找一个Task
	 * 
	 * @param id
	 *            一个task的id
	 * @return Task 返回查找到的task，如果没有，则为null
	 */
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
	/**
	 * 描述：查找所有的待办事项
	 * 
	 * @return List<Task> 一个含有所有待办是事项的集合
	 */
	public List<Task> queryAll()
	{

		Cursor c = db.rawQuery("SELECT * FROM task ORDER BY date DESC", null);
		return queryByCursor(c);
	}

	/**
	 * 描述： 根据日期查询待办事项 ..查询的task为把当天日期包含在起始日期和到期日期之间。
	 */
	public List<Task> queryByDate(int year, int month, int dayOfMonth)
	{
		String dateStr = StrTool.getDateStr(year, month, dayOfMonth);

		Cursor c = db.rawQuery(
				"SELECT * FROM task WHERE date<=? AND enddate>=? ORDER BY date DESC", new String[]
				{ dateStr, dateStr });
		System.out.println(dateStr + "总共有" + c.getCount());
		return queryByCursor(c);
	}

	/**
	 * 描述：通过Cursor对象返回该对象含有的所有Task，该方法通常由queryByDate和queryAll等方法调用
	 * 
	 * @param c
	 *            通过查询获得的游标对象
	 * @return List<Task> 返回游标对象含有的task
	 */
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
	* 描述： 关闭数据库
	*/ 
	public void closeDB()
	{
		db.close();
	}
}