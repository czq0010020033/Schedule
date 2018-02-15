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
 * ����: ���ݿ�����࣬ʵ�����ݿ�ĸ��ֲ����������֮ǰû��дһ���淶�Ľӿڣ����Ҫ��չ����дһ���淶�ӿڻ����<br>
 * <br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��7��/����7:30:00<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��7��/����7:30:00<br>
 * �޸ı�ע��<br>
 * �汾��1.0
 */
public class DBManager
{
	/**
	 * SQLite���ݿ�Ĺ�����
	 */
	private DBHelper helper;
	/**
	 * SQLite���ݿ���
	 */
	private SQLiteDatabase db;
	
	/*public SQLiteDatabase getDb()
	{
		return db;
	}*/
	/**
	 * <p>
	 * ����: ʵ����DBHelper�࣬����ͬʱʵ����SQLiteDatabase��
	 * </p>
	 * 
	 * @param context
	 */
	public DBManager(Context context)
	{
		helper = new DBHelper(context);
		// ��ΪgetWritableDatabase�ڲ�������mContext.openOrCreateDatabase(mName, 0,
		// mFactory);
		// ����Ҫȷ��context�ѳ�ʼ��,���ǿ��԰�ʵ����DBManager�Ĳ������Activity��onCreate��
		db = helper.getWritableDatabase();
		System.out.println(db.getPath());

	}

	/**
	 * ��������Ӵ���������ݿ�
	 * 
	 * @param task
	 * @return int �ɹ���ӵ�task��id
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
	 * ����������һ����������
	 * 
	 * @param task
	 *            �������һ��������task������һ��id
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
	 * ������ͨ��task��id��ɾ����������
	 * 
	 * @param id
	 *            task��id
	 */
	public void delete(int id)
	{
		db.delete("task", "id = ?", new String[]
		{ String.valueOf(id) });
	}

	/**
	* ������ ����������ȫ��ɾ��
	*/ 
	public void deleteAll()
	{
		db.delete("task", "", null);
	}
	
	/**
	 * ����������Id����һ��Task
	 * 
	 * @param id
	 *            һ��task��id
	 * @return Task ���ز��ҵ���task�����û�У���Ϊnull
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
	 * @return List<Task> ����������
	 */
	/**
	 * �������������еĴ�������
	 * 
	 * @return List<Task> һ���������д���������ļ���
	 */
	public List<Task> queryAll()
	{

		Cursor c = db.rawQuery("SELECT * FROM task ORDER BY date DESC", null);
		return queryByCursor(c);
	}

	/**
	 * ������ �������ڲ�ѯ�������� ..��ѯ��taskΪ�ѵ������ڰ�������ʼ���ں͵�������֮�䡣
	 */
	public List<Task> queryByDate(int year, int month, int dayOfMonth)
	{
		String dateStr = StrTool.getDateStr(year, month, dayOfMonth);

		Cursor c = db.rawQuery(
				"SELECT * FROM task WHERE date<=? AND enddate>=? ORDER BY date DESC", new String[]
				{ dateStr, dateStr });
		System.out.println(dateStr + "�ܹ���" + c.getCount());
		return queryByCursor(c);
	}

	/**
	 * ������ͨ��Cursor���󷵻ظö����е�����Task���÷���ͨ����queryByDate��queryAll�ȷ�������
	 * 
	 * @param c
	 *            ͨ����ѯ��õ��α����
	 * @return List<Task> �����α�����е�task
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
	* ������ �ر����ݿ�
	*/ 
	public void closeDB()
	{
		db.close();
	}
}