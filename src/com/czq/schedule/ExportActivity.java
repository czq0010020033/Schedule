/**      
 * ��Ŀ���ƣ�Schedule<br> 
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
 * ����: ������<br>
 * <br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��10��/����2:40:55<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��10��/����2:40:55<br>
 * �޸ı�ע��<br>
 * �汾��1.0
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
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ��������
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
	 * ������
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
				Toast.makeText(this, "�ļ�������Ϊ��", Toast.LENGTH_SHORT).show();
				return;
			}

			FileTool.exportFile(this, fileName.getText().toString());
			Toast.makeText(this, "�ļ������ɹ�", Toast.LENGTH_SHORT).show();
			break;

		case R.id.export_importbtn:
			SwitchActivity.switchActivity(ExportActivity.this,
					Destination.ImportActivity, 0);
			break;
		case R.id.export_deleteAll:
			// ����ȷ�Ͽ�
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("��ʾ");
			builder.setMessage("�Ƿ�ɾ��ȫ����");
			// ȡ�����ļ���
			builder.setNegativeButton("ȡ��",
					new android.content.DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							// ȡ���Ի���
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
							TaskBiz taskBiz = new TaskBizImpl(
									ExportActivity.this);
							taskBiz.deleteAll();
							taskBiz.close();
							Toast.makeText(ExportActivity.this, "ɾ���ɹ�",
									Toast.LENGTH_SHORT).show();
						}

					});
			builder.show();

			break;
		}
	}
}
