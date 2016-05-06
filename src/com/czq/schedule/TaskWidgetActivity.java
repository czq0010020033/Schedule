package com.czq.schedule;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class TaskWidgetActivity extends Activity
{
	 int mAppWidgetId;  
     
	    @Override  
	    protected void onCreate(Bundle savedInstanceState) {  
	        // TODO Auto-generated method stub  
	        super.onCreate(savedInstanceState);  
	          
	        Log.i("myLog"," on WidgetConf ... ");  
	          
	        setResult(RESULT_CANCELED);  
	          
	        // Find the widget id from the intent.  
	        Intent intent = getIntent();  
	        Bundle extras = intent.getExtras();  
	        if (extras != null) {  
	            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,  
	                    AppWidgetManager.INVALID_APPWIDGET_ID);  
	        }  
	  
	        // If they gave us an intent without the widget id, just bail.  
	        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {  
	            finish();  
	        }  
	          
	        // return OK  
	        Intent resultValue = new Intent();  
	        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,  
	                mAppWidgetId);  
	          
	        setResult(RESULT_OK, resultValue);  
	        finish();  
	    } 
}
