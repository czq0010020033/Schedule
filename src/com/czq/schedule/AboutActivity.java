
package com.czq.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

/**
 * ����: ���ڽ��棬���ڳ���ļ�˵��<br><br>
 * ���ߣ� ������/850530595@qq.com<br>    
 * ����ʱ�䣺2016��5��8��/����12:24:12<br>    
 * �޸��ˣ�������/850530595@qq.com<br>    
 * �޸�ʱ�䣺2016��5��8��/����12:24:12<br>    
 * �޸ı�ע��<br>
 * �汾��1.0
 */
public class AboutActivity extends Activity
{

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about);
	}


}
