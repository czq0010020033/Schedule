package com.czq.schedule.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ����: SQLite���ݿ�Ĺ����࣬ͨ�����������ȡһ�����ݿ�<br>
 * <br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��7��/����7:25:51<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��7��/����7:25:51<br>
 * �޸ı�ע��<br>
 * �汾��1.0
 */
public class DBHelper extends SQLiteOpenHelper
{

	/**
	 * ���ݿ����������ǹ̶��˵ģ����Խ���һ�����캯�����޸����ֵ
	 */
	private static final String DATABASE_NAME = "schedule.db";
	/**
	 * ���ݿ�汾�����ֵ�ı䣬�������onUpgrade��������û�Թ���
	 */
	private static final int DATABASE_VERSION = 1;

	/**
	 * <p>
	 * ����: ����һ��������������һ�����ݿ������
	 * </p>
	 * 
	 * @param context
	 */
	public DBHelper(Context context)
	{
		// CursorFactory����Ϊnull,ʹ��Ĭ��ֵ
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/*
	 * <p>Title: onCreate</p> <p>Description: ���ݿ��һ�α�����ʱonCreate�ᱻ���� </p>
	 * 
	 * @param db
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE IF NOT EXISTS task"
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, date VARCHAR, time VARCHAR, enddate VARCHAR, title VARCHAR, content VARCHAR, remindway INTEGER, ispast INTEGER)");
	}

	/**
	 * ������ ���DATABASE_VERSIONֵ����Ϊ2,ϵͳ�����������ݿ�汾��ͬ,�������onUpgrade
	 * 
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase,
	 *      int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// һ��Ĭ������£������ǲ��� ���ݿ����������
		// �����ݿ���Ҫ������ʱ��Android ϵͳ�������ĵ������������
		// һ������������������ɾ�����ݱ��������µ����ݱ�
		// ��Ȼ�Ƿ���Ҫ�������Ĳ�������ȫȡ������Ϸ����
		System.out.println("upgrade a database");
	}

}
