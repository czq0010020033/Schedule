package com.czq.schedule.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.czq.schedule.R;
import com.czq.schedule.tool.Destination;
import com.czq.schedule.tool.SwitchActivity;

public class TopFragment extends Fragment implements OnClickListener
{
	private ImageButton add;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		 View view = inflater.inflate(R.layout.frag_top, container, false);  
	        add = (ImageButton) view.findViewById(R.id.top_add);  
	        add.setOnClickListener(this);
	        return view;  
	}

	@Override
	public void onClick(View v)
	{
		// TODO 自动生成的方法存根
		// 转向TaskActivity。并传入TASK_ADD
		SwitchActivity.switchActivity(getActivity(), Destination.TaskActivity, 0);
	}
	
}
