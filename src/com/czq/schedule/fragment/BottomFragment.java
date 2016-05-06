package com.czq.schedule.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.czq.schedule.R;

public class BottomFragment extends Fragment implements OnClickListener
{
	private TextView main;
	private TextView all;
	private TextView exit;
	
	private MainFragment mainFragment;
	private TaskListFragment taskListFragment;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.frag_bottom, container, false);

		main = (TextView) view.findViewById(R.id.frag_bottom_main);
		all = (TextView) view.findViewById(R.id.frag_bottom_all);
		exit = (TextView) view.findViewById(R.id.frag_bottom_exit);
		main.setOnClickListener(this);
		all.setOnClickListener(this);
		exit.setOnClickListener(this);
		
		 // 设置默认的Fragment  
        setDefaultFragment();  
		return view;
		
	}
	 private void setDefaultFragment()  
	    {  
	        FragmentManager fm = getActivity().getFragmentManager();  
	        FragmentTransaction transaction = fm.beginTransaction();  
	        mainFragment = new MainFragment();  
	        transaction.replace(R.id.main_content, mainFragment);  
	        transaction.commit();  
	    }  

	@Override
	public void onClick(View v)
	{
		FragmentManager fm = BottomFragment.this.getActivity().getFragmentManager();  
        FragmentTransaction transaction = fm.beginTransaction();
		switch (v.getId())
		{
		case R.id.frag_bottom_main:
			if (mainFragment == null)
			{
				mainFragment = new MainFragment();
			}
			transaction.replace(R.id.main_content, mainFragment);
			break;
		case R.id.frag_bottom_all:
			if (taskListFragment == null)
			{
				taskListFragment = new TaskListFragment();
			}
			transaction.replace(R.id.main_content, taskListFragment);
			break;

		case R.id.frag_bottom_exit:
			System.exit(0);
			//android.os.Process.killProcess(android.os.Process.myPid());
			break;
		}
		 // transaction.addToBackStack();  
        // 事务提交  
        transaction.commit();  
	}
}
