package com.czq.schedule.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 描述: SQLite数据库的管理类，通过这个类来获取一个数据库<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月7日/下午7:25:51<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月7日/下午7:25:51<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class DBHelper extends SQLiteOpenHelper
{

	/**
	 * 数据库名，这里是固定了的，可以建立一个构造函数来修改这个值
	 */
	private static final String DATABASE_NAME = "schedule.db";
	/**
	 * 数据库版本，如果值改变，将会调用onUpgrade函数（还没试过）
	 */
	private static final int DATABASE_VERSION = 1;

	/**
	 * <p>
	 * 描述: 根据一个上下文来建立一个数据库管理类
	 * </p>
	 * 
	 * @param context
	 */
	public DBHelper(Context context)
	{
		// CursorFactory设置为null,使用默认值
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/*
	 * <p>Title: onCreate</p> <p>Description: 数据库第一次被创建时onCreate会被调用 </p>
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
	 * 描述： 如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
	 * 
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase,
	 *      int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// 一般默认情况下，当我们插入 数据库就立即更新
		// 当数据库需要升级的时候，Android 系统会主动的调用这个方法。
		// 一般我们在这个方法里边删除数据表，并建立新的数据表，
		// 当然是否还需要做其他的操作，完全取决于游戏需求。
		System.out.println("upgrade a database");
	}

}
