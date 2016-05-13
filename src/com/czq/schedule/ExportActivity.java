/**      
 * 项目名称：Schedule<br> 
 */
package com.czq.schedule;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.czq.schedule.biz.TaskBiz;
import com.czq.schedule.biz.TaskBizImpl;
import com.czq.schedule.tool.Destination;
import com.czq.schedule.tool.FileTool;
import com.czq.schedule.tool.SwitchActivity;

/**
 * 描述: 导出类<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月10日/下午2:40:55<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月10日/下午2:40:55<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class ExportActivity extends Activity implements OnClickListener
{
	private EditText fileName;
	private Button exportBtn;
	private Button importBtn;
	private Button deleteAllBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 填充标题栏
		setContentView(R.layout.activity_export);

		fileName = (EditText) findViewById(R.id.export_text);
		exportBtn = (Button) findViewById(R.id.export_exportbtn);
		importBtn = (Button) findViewById(R.id.export_importbtn);
		deleteAllBtn = (Button) findViewById(R.id.export_deleteAll);

		exportBtn.setOnClickListener(this);
		importBtn.setOnClickListener(this);
		deleteAllBtn.setOnClickListener(this);
	}

	/**
	 * 描述：
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.export_exportbtn:
			if (fileName.getText().toString() == null
					|| fileName.getText().toString().equals(""))
			{
				Toast.makeText(this, "文件名不能为空", Toast.LENGTH_SHORT).show();
				return;
			}

			FileTool.exportFile(this, fileName.getText().toString());
			Toast.makeText(this, "文件导出成功", Toast.LENGTH_SHORT).show();
			break;

		case R.id.export_importbtn:
			SwitchActivity.switchActivity(ExportActivity.this,
					Destination.ImportActivity, 0);
			break;
		case R.id.export_deleteAll:
			// 创建确认框
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("提示");
			builder.setMessage("是否删除全部？");
			// 取消键的监听
			builder.setNegativeButton("取消",
					new android.content.DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							// 取消对话框
							if (dialog == null)
							{
								System.out.println("dialog is null");
								return;
							}
							dialog.dismiss();
						}

					});
			// 确定键的监听
			builder.setPositiveButton("确定",
					new android.content.DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							TaskBiz taskBiz = new TaskBizImpl(
									ExportActivity.this);
							taskBiz.deleteAll();
							taskBiz.close();
							Toast.makeText(ExportActivity.this, "删除成功",
									Toast.LENGTH_SHORT).show();
						}

					});
			builder.show();

			break;
		}
	}
}
