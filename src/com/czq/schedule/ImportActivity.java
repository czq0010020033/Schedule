/**      
 * 项目名称：Schedule<br> 
 */
package com.czq.schedule;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.czq.schedule.tool.FileTool;

/**
 * 描述: 导入类<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月10日/下午2:54:37<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月10日/下午2:54:37<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class ImportActivity extends Activity implements OnItemClickListener
{
	private ListView listView;
	private List<String> fileStr;
	private int position = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 填充标题栏
		setContentView(R.layout.activity_import);

		// 实例化
		listView = (ListView) findViewById(R.id.import_list);
		fileStr = FileTool.getFileInDir();
		if(fileStr == null || fileStr.isEmpty())
		{
			Toast.makeText(this, "文件夹为空", Toast.LENGTH_SHORT).show();
			return;
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item_export, R.id.list_file, fileStr);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	/**
	 * 描述：
	 * 
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		this.position = position;

		// 创建确认框
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("是否导入？");
		// 取消键的监听
		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						// 返回到TaskShow
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
						if (ImportActivity.this.position == -1)
							return;
						String fileName = fileStr
								.get(ImportActivity.this.position);
						FileTool.importFile(ImportActivity.this, fileName);
						Toast.makeText(ImportActivity.this, "导入文件成功",
								Toast.LENGTH_SHORT).show();
					}

				});
		builder.show();
	}

}
