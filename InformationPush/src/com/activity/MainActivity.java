package com.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.activity.LectureFragment;
import com.activity.MainFragment;
import com.activity.NewsFragment;
import com.activity.NoticeFragment;
import com.activity.OrganizationFragment;
import com.activity.OtherFragment;
import com.adapter.MyFragmentPagerAdapter;
import com.page.R;

import android.app.Activity;
import android.content.IntentFilter;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity implements OnPageChangeListener {


	private ViewPager myviewpager;
	// fragment�ļ��ϣ���Ӧÿ����ҳ��
	private ArrayList<Fragment> fragments;
	// ѡ��еİ�ť
	private RadioButton main;
	private RadioButton news;
	private RadioButton lecture;
	private RadioButton notice;
	private RadioButton organization;
	private RadioButton other;

	// ��Ϊָʾ��ǩ�İ�ť
	private ImageView cursor;
	// ��־ָʾ��ǩ�ĺ�����
	float cursorX = 0;
	// ���а�ť�Ŀ�ȵļ���
	private int[] widthArgs;
	// ���а�ť�ļ���
	private Button[] btnArgs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();


	}

	public void initView() {
		myviewpager = (ViewPager) this.findViewById(R.id.viewpager);

		// ViewPager��setCurrentItem����ת��ViewPager��ָ��ҳ�棬
		// ����ʹ�����������ʱ���и����⣬��ת��ʱ���л���Ч����
		// ����Ҫ�ӵ�ǰҳ����ת������ҳ��ʱ����תҳ���ȹ���
		// ����ViewPagerÿ��ҳ����Ӿ�Ч�����ϴ�ʱ��ͨ�����ַ�ʽʵ��ViewPager��ת�Եúܲ�����

		// ����취:
		// ���ǿ���ȥ����ʹ��ViewPager��setCurrentItem����ʱ�Ļ����ٶ�
		try {
			Field mScroller = null;
			mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			FixedSpeedScroller scroller = new FixedSpeedScroller(myviewpager.getContext());
			mScroller.set(myviewpager, scroller);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		main = (RadioButton) this.findViewById(R.id.main);
		news = (RadioButton) this.findViewById(R.id.news);
		lecture = (RadioButton) this.findViewById(R.id.lecture);
		notice = (RadioButton) this.findViewById(R.id.notice);
		organization = (RadioButton) this.findViewById(R.id.organization);
		other = (RadioButton) this.findViewById(R.id.other);

		btnArgs = new Button[] { main, news, lecture, notice, organization, other };

		cursor = (ImageView) this.findViewById(R.id.cursor_btn);

		cursor.setBackgroundColor(Color.RED);

		myviewpager.setOnPageChangeListener(this);
		/*
		 * btn_first.setOnClickListener(this); btn_second.setOnClickListener(this);
		 * btn_third.setOnClickListener(this); btn_four.setOnClickListener(this);
		 */

		main.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
		news.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
		lecture.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
		notice.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
		organization.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
		other.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());

		fragments = new ArrayList<Fragment>();
		fragments.add(new MainFragment());
		fragments.add(new NewsFragment());
		fragments.add(new LectureFragment());
		fragments.add(new NoticeFragment());
		fragments.add(new OrganizationFragment());
		fragments.add(new OtherFragment());
		MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
		myviewpager.setAdapter(adapter);

		// �������а�ť��ɫ
		resetButtonColor();
		// �ѵ�һ����ť����ɫ����Ϊ��ɫ
		main.setTextColor(Color.RED);
		// Ϊʲô��ֱ��cursor.setWidth()��cursor.setX()
		// ��ΪAndroidϵͳ����ԭ����ֻ��ȫ����������֮��Ż᲼�֣�
		// ֻ�����������ֻ�����Ϻ���ͼ���ܵõ�����ĸߺͿ�
		// ��������������£���OnCreate()������ֱ�ӻ�ȡ�ؼ��Ŀ�Ⱥ͸߶�ȡ��ֵ��0��
		// �����Ǵ˴�����ָʾ���Ĵ�С��λ�ö���Ҫ�õ���һ����ť�Ĵ�С��Ϊ�ο�ֵ��
		// ���Կ���ͨ��post��һ��runnableͶ�ݵ���Ϣ���е�β����Ȼ��ȴ�UI�߳�Looper���ô�runnable��ʱ��viewҲ�Ѿ���ʼ�����ˡ����ʱ����ܳɹ���ȡ�ؼ��Ŀ��
		main.post(new Runnable() {
			@Override
			public void run() {
				LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
				// ��ȥ�߾�*2���Զ������������
				lp.width = main.getWidth() - main.getPaddingLeft() * 2;
				cursor.setLayoutParams(lp);
				cursor.setX(main.getPaddingLeft());
			}
		});

	}

	// ���¼����ڲ��ඨ�����
	private class InnerOnCheckedChangeListener implements OnCheckedChangeListener {
		// ��ѡ��ťѡ���¼�����
		// buttonView��ʾ˭��״̬���ı�
		// isChecked����Ĳ��������״̬�Ƿ�ѡ��
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			switch (buttonView.getId()) {
			case R.id.main:
				// ��ѡ��ťͨ������isCheckedȥ�õ���ǰ������ѡ�л���δѡ��
				if (isChecked) {
					myviewpager.setCurrentItem(0);
					cursorAnim(0);
				}
				break;
			case R.id.news:
				// ��ѡ��ťͨ������isCheckedȥ�õ���ǰ������ѡ�л���δѡ��
				if (isChecked) {
					myviewpager.setCurrentItem(1);
					cursorAnim(1);
				}
				break;
			case R.id.lecture:
				// ��ѡ��ťͨ������isCheckedȥ�õ���ǰ������ѡ�л���δѡ��
				if (isChecked) {
					myviewpager.setCurrentItem(2);
					cursorAnim(2);
				}
				break;
			case R.id.notice:
				// ��ѡ��ťͨ������isCheckedȥ�õ���ǰ������ѡ�л���δѡ��
				if (isChecked) {
					myviewpager.setCurrentItem(3);
					cursorAnim(3);
				}
				break;
			case R.id.organization:
				// ��ѡ��ťͨ������isCheckedȥ�õ���ǰ������ѡ�л���δѡ��
				if (isChecked) {
					myviewpager.setCurrentItem(4);
					cursorAnim(4);
				}
				break;
			case R.id.other:
				// ��ѡ��ťͨ������isCheckedȥ�õ���ǰ������ѡ�л���δѡ��
				if (isChecked) {
					myviewpager.setCurrentItem(5);
					cursorAnim(5);
				}
				break;
			default:
				break;
			}

		}

	}

	// �������а�ť����ɫ
	public void resetButtonColor() {
		main.setBackgroundColor(Color.parseColor("#DCDCDC"));
		news.setBackgroundColor(Color.parseColor("#DCDCDC"));
		lecture.setBackgroundColor(Color.parseColor("#DCDCDC"));
		notice.setBackgroundColor(Color.parseColor("#DCDCDC"));
		organization.setBackgroundColor(Color.parseColor("#DCDCDC"));
		other.setBackgroundColor(Color.parseColor("#DCDCDC"));

		main.setTextColor(Color.BLACK);
		news.setTextColor(Color.BLACK);
		lecture.setTextColor(Color.BLACK);
		notice.setTextColor(Color.BLACK);
		organization.setTextColor(Color.BLACK);
		other.setTextColor(Color.BLACK);

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		if (widthArgs == null) {
			widthArgs = new int[] { main.getWidth(), news.getWidth(), lecture.getWidth(), notice.getWidth(),
					organization.getWidth(), other.getWidth() };
		}
		// ÿ�λ��������������а�ť����ɫ
		resetButtonColor();

		// ���������ĵ�ǰ��ť��ɫ����Ϊ��ɫ
		btnArgs[arg0].setTextColor(Color.RED);

		cursorAnim(arg0);

		// �ѵ�ǰҳ��ĵ�ѡ��ť����Ϊѡ��״̬
		((CompoundButton) btnArgs[arg0]).setChecked(true);

	}

	// ָʾ������ת�����뵱ǰ������ҳ����±�
	public void cursorAnim(int curItem) {
		// ÿ�ε��ã��ͽ�ָʾ���ĺ���������0������ʼ��λ��
		cursorX = 0;
		// �ٸ��ݵ�ǰ��curItem������ָʾ���Ŀ��
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
		// ���Ȼ�õ�ǰ��ť�Ŀ�ȣ��ټ�ȥ��ť���ұ߾�࣬�Զ���������ı�
		lp.width = widthArgs[curItem] - btnArgs[0].getPaddingLeft() * 2;
		// ͨ��ָʾ��ǩ���󣬽���ǩ���õ���������
		cursor.setLayoutParams(lp);
		// ѭ����ȡ��ǰҳ֮ǰ������ҳ��Ŀ��
		for (int i = 0; i < curItem; i++) {
			cursorX = cursorX + btnArgs[i].getWidth();
		}
		// �ټ��ϵ�ǰҳ�����߾࣬��Ϊָʾ����ǰӦ����λ��
		cursor.setX(cursorX + btnArgs[curItem].getPaddingLeft());
	}

}
