/**      
 * ��Ŀ���ƣ�Schedule<br> 
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
 * ����: ������<br>
 * <br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��10��/����2:54:37<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��10��/����2:54:37<br>
 * �޸ı�ע��<br>
 * �汾��1.0
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
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ��������
		setContentView(R.layout.activity_import);

		// ʵ����
		listView = (ListView) findViewById(R.id.import_list);
		fileStr = FileTool.getFileInDir();
		if(fileStr == null || fileStr.isEmpty())
		{
			Toast.makeText(this, "�ļ���Ϊ��", Toast.LENGTH_SHORT).show();
			return;
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item_export, R.id.list_file, fileStr);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	/**
	 * ������
	 * 
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		this.position = position;

		// ����ȷ�Ͽ�
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("��ʾ");
		builder.setMessage("�Ƿ��룿");
		// ȡ�����ļ���
		builder.setNegativeButton("ȡ��",
				new android.content.DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						// ���ص�TaskShow
						if (dialog == null)
						{
							System.out.println("dialog is null");
							return;
						}
						dialog.dismiss();
					}

				});
		// ȷ�����ļ���
		builder.setPositiveButton("ȷ��",
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
						Toast.makeText(ImportActivity.this, "�����ļ��ɹ�",
								Toast.LENGTH_SHORT).show();
					}

				});
		builder.show();
	}

}
