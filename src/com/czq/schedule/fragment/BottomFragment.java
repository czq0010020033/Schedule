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

/**
 * 描述: 在界面下方显示的fragment，主要有主页面（日历），所有事项，退出三个选项<br>
 * <br>
 * 作者： 陈镇钦/850530595@qq.com<br>
 * 创建时间：2016年5月7日/下午8:23:41<br>
 * 修改人：陈镇钦/850530595@qq.com<br>
 * 修改时间：2016年5月7日/下午8:23:41<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class BottomFragment extends Fragment implements OnClickListener
{
	private TextView main;
	private TextView all;
	private TextView exit;

	/**
	 * 显示日历的mainFragment
	 */
	private MainFragment mainFragment;

	/**
	 * 显示所有事项的taskListFragment
	 */
	private TaskListFragment taskListFragment;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// fragment初始化
		View view = inflater.inflate(R.layout.frag_bottom, container, false);

		// 初始化
		main = (TextView) view.findViewById(R.id.frag_bottom_main);
		all = (TextView) view.findViewById(R.id.frag_bottom_all);
		exit = (TextView) view.findViewById(R.id.frag_bottom_exit);
		// 添加事件监听
		main.setOnClickListener(this);
		all.setOnClickListener(this);
		exit.setOnClickListener(this);

		// 设置默认的Fragment
		setDefaultFragment();
		return view;

	}

	/**
	 * 描述： 设置最初要显示的fragment，默认是mainFragment
	 */
	private void setDefaultFragment()
	{
		FragmentManager fm = getActivity().getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		mainFragment = new MainFragment();
		transaction.replace(R.id.main_content, mainFragment);
		transaction.commit();
	}

	/**
	 * 描述： 对点击事件进行相应，主要有主页面（日历），所有事项，退出三个选项
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{
		FragmentManager fm = BottomFragment.this.getActivity()
				.getFragmentManager();
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
			BottomFragment.this.getActivity().finish();
			// android.os.Process.killProcess(android.os.Process.myPid());
			break;
		}
		// transaction.addToBackStack();
		// 事务提交
		transaction.commit();
	}
}
