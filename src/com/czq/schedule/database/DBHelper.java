package com.czq.schedule.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
  
public class DBHelper extends SQLiteOpenHelper {  
  
    private static final String DATABASE_NAME = "schedule.db";  
    private static final int DATABASE_VERSION = 1;  
      
    public DBHelper(Context context) {  
        //CursorFactory����Ϊnull,ʹ��Ĭ��ֵ  
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }  
  
    //���ݿ��һ�α�����ʱonCreate�ᱻ����  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        db.execSQL("CREATE TABLE IF NOT EXISTS task" +  
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, date VARCHAR, time VARCHAR, enddate VARCHAR, title VARCHAR, content VARCHAR, remindway INTEGER, ispast INTEGER)");  
    }  
  
    //���DATABASE_VERSIONֵ����Ϊ2,ϵͳ�����������ݿ�汾��ͬ,�������onUpgrade  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
    	System.out.println("upgrade a database"); 
    } 
    
    
}  
