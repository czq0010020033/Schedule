package com.czq.schedule.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
  
public class DBHelper extends SQLiteOpenHelper {  
  
    private static final String DATABASE_NAME = "schedule.db";  
    private static final int DATABASE_VERSION = 1;  
      
    public DBHelper(Context context) {  
        //CursorFactory设置为null,使用默认值  
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }  
  
    //数据库第一次被创建时onCreate会被调用  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        db.execSQL("CREATE TABLE IF NOT EXISTS task" +  
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, date VARCHAR, time VARCHAR, enddate VARCHAR, title VARCHAR, content VARCHAR, remindway INTEGER, ispast INTEGER)");  
    }  
  
    //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
    	System.out.println("upgrade a database"); 
    } 
    
    
}  
