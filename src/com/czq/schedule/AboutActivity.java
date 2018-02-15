
package com.czq.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

/**
 * 描述: 关于界面，关于程序的简单说明<br><br>
 * 作者： 陈镇钦/850530595@qq.com<br>    
 * 创建时间：2016年5月8日/下午12:24:12<br>    
 * 修改人：陈镇钦/850530595@qq.com<br>    
 * 修改时间：2016年5月8日/下午12:24:12<br>    
 * 修改备注：<br>
 * 版本：1.0
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
