package com.czq.schedule.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.czq.schedule.R;
import com.czq.schedule.tool.Destination;
import com.czq.schedule.tool.SwitchActivity;

/**
* 描述: 在界面上方显示的fragmet，现在主要有一个添加task的按钮<br><br>
* 作者： 陈镇钦/850530595@qq.com<br>    
* 创建时间：2016年5月7日/下午8:50:34<br>    
* 修改人：陈镇钦/850530595@qq.com<br>    
* 修改时间：2016年5月7日/下午8:50:34<br>    
* 修改备注：<br>
* 版本：1.0
*/   
public class TopFragment extends Fragment implements OnClickListener,PopupMenu.OnMenuItemClickListener
{
	private ImageButton add;
	private ImageButton menu;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		 View view = inflater.inflate(R.layout.frag_top, container, false);  
	        add = (ImageButton) view.findViewById(R.id.top_add); 
	        menu = (ImageButton) view.findViewById(R.id.top_menu);
	        
	        add.setOnClickListener(this);
	        menu.setOnClickListener(this);
	        return view;  
	}

	/**
	* 描述： 点击不同按钮进入添加页面或者打开菜单
	* 	* @see android.view.View.OnClickListener#onClick(android.view.View)
	*/ 
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.top_add:
			// 转向TaskActivity。并传入TASK_ADD
			SwitchActivity.switchActivity(getActivity(), Destination.TaskActivity, 0);
			break;

		case R.id.top_menu:
			PopupMenu popup = new PopupMenu(TopFragment.this.getActivity(), v);
            //Inflating the Popup using xml file
            popup.getMenuInflater()
                .inflate(R.menu.menu_top, popup.getMenu());

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(TopFragment.this);
            popup.show(); //showing popup menu s
			break;
		}
		
	}

	/**
	* 描述： 
	* @see android.widget.PopupMenu.OnMenuItemClickListener#onMenuItemClick(android.view.MenuItem)
	*/ 
	@Override
	public boolean onMenuItemClick(MenuItem item)
	{
		
		switch (item.getItemId())
		{
		case R.id.menu_top_import:
			SwitchActivity.switchActivity(this.getActivity(), Destination.ExportActivity, 0);
			/*FileTool.exportFile(TopFragment.this.getActivity(), "schedule.txt");
			Toast.makeText(this.getActivity(), "导出文件", Toast.LENGTH_SHORT).show();*/
			break;
		case R.id.menu_top_about:
			SwitchActivity.switchActivity(TopFragment.this.getActivity(), Destination.AboutActivity, 0);
			break;
		}
		return true;
	}
	
}
