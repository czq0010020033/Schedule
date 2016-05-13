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
 * ����: �ڽ����·���ʾ��fragment����Ҫ����ҳ�棨����������������˳�����ѡ��<br>
 * <br>
 * ���ߣ� ������/850530595@qq.com<br>
 * ����ʱ�䣺2016��5��7��/����8:23:41<br>
 * �޸��ˣ�������/850530595@qq.com<br>
 * �޸�ʱ�䣺2016��5��7��/����8:23:41<br>
 * �޸ı�ע��<br>
 * �汾��1.0
 */
public class BottomFragment extends Fragment implements OnClickListener
{
	private TextView main;
	private TextView all;
	private TextView exit;

	/**
	 * ��ʾ������mainFragment
	 */
	private MainFragment mainFragment;

	/**
	 * ��ʾ���������taskListFragment
	 */
	private TaskListFragment taskListFragment;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// fragment��ʼ��
		View view = inflater.inflate(R.layout.frag_bottom, container, false);

		// ��ʼ��
		main = (TextView) view.findViewById(R.id.frag_bottom_main);
		all = (TextView) view.findViewById(R.id.frag_bottom_all);
		exit = (TextView) view.findViewById(R.id.frag_bottom_exit);
		// ����¼�����
		main.setOnClickListener(this);
		all.setOnClickListener(this);
		exit.setOnClickListener(this);

		// ����Ĭ�ϵ�Fragment
		setDefaultFragment();
		return view;

	}

	/**
	 * ������ �������Ҫ��ʾ��fragment��Ĭ����mainFragment
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
	 * ������ �Ե���¼�������Ӧ����Ҫ����ҳ�棨����������������˳�����ѡ��
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
		// �����ύ
		transaction.commit();
	}
}
