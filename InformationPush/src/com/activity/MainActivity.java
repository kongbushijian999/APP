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
	// fragment的集合，对应每个子页面
	private ArrayList<Fragment> fragments;
	// 选项卡中的按钮
	private RadioButton main;
	private RadioButton news;
	private RadioButton lecture;
	private RadioButton notice;
	private RadioButton organization;
	private RadioButton other;

	// 作为指示标签的按钮
	private ImageView cursor;
	// 标志指示标签的横坐标
	float cursorX = 0;
	// 所有按钮的宽度的集合
	private int[] widthArgs;
	// 所有按钮的集合
	private Button[] btnArgs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();


	}

	public void initView() {
		myviewpager = (ViewPager) this.findViewById(R.id.viewpager);

		// ViewPager的setCurrentItem是跳转到ViewPager的指定页面，
		// 但在使用这个方法的时候有个问题，跳转的时候有滑动效果，
		// 当需要从当前页面跳转到其它页面时，跳转页面跨度过大、
		// 或者ViewPager每个页面的视觉效果相差较大时，通过这种方式实现ViewPager跳转显得很不美观

		// 解决办法:
		// 我们可以去掉在使用ViewPager的setCurrentItem方法时的滑屏速度
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

		// 重置所有按钮颜色
		resetButtonColor();
		// 把第一个按钮的颜色设置为红色
		main.setTextColor(Color.RED);
		// 为什么不直接cursor.setWidth()和cursor.setX()
		// 因为Android系统绘制原理是只有全部遍历测量之后才会布局，
		// 只有在整个布局绘制完毕后，视图才能得到自身的高和宽。
		// 所以在正常情况下，在OnCreate()方法中直接获取控件的宽度和高度取得值是0。
		// 而我们此处设置指示器的大小和位置都需要用到第一个按钮的大小作为参考值，
		// 所以可以通过post将一个runnable投递到消息队列的尾部，然后等待UI线程Looper调用此runnable的时候，view也已经初始化好了。这个时候就能成功获取控件的宽高
		main.post(new Runnable() {
			@Override
			public void run() {
				LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
				// 减去边距*2，以对齐标题栏文字
				lp.width = main.getWidth() - main.getPaddingLeft() * 2;
				cursor.setLayoutParams(lp);
				cursor.setX(main.getPaddingLeft());
			}
		});

	}

	// 把事件的内部类定义出来
	private class InnerOnCheckedChangeListener implements OnCheckedChangeListener {
		// 单选按钮选中事件方法
		// buttonView表示谁的状态被改变
		// isChecked上面的参数代表的状态是否选中
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			switch (buttonView.getId()) {
			case R.id.main:
				// 单选按钮通过参数isChecked去得到当前到底是选中还是未选中
				if (isChecked) {
					myviewpager.setCurrentItem(0);
					cursorAnim(0);
				}
				break;
			case R.id.news:
				// 单选按钮通过参数isChecked去得到当前到底是选中还是未选中
				if (isChecked) {
					myviewpager.setCurrentItem(1);
					cursorAnim(1);
				}
				break;
			case R.id.lecture:
				// 单选按钮通过参数isChecked去得到当前到底是选中还是未选中
				if (isChecked) {
					myviewpager.setCurrentItem(2);
					cursorAnim(2);
				}
				break;
			case R.id.notice:
				// 单选按钮通过参数isChecked去得到当前到底是选中还是未选中
				if (isChecked) {
					myviewpager.setCurrentItem(3);
					cursorAnim(3);
				}
				break;
			case R.id.organization:
				// 单选按钮通过参数isChecked去得到当前到底是选中还是未选中
				if (isChecked) {
					myviewpager.setCurrentItem(4);
					cursorAnim(4);
				}
				break;
			case R.id.other:
				// 单选按钮通过参数isChecked去得到当前到底是选中还是未选中
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

	// 重置所有按钮的颜色
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
		// 每次滑动首先重置所有按钮的颜色
		resetButtonColor();

		// 将滑动到的当前按钮颜色设置为红色
		btnArgs[arg0].setTextColor(Color.RED);

		cursorAnim(arg0);

		// 把当前页面的单选按钮设置为选中状态
		((CompoundButton) btnArgs[arg0]).setChecked(true);

	}

	// 指示器的跳转，传入当前所处的页面的下标
	public void cursorAnim(int curItem) {
		// 每次调用，就将指示器的横坐标设置0，即开始的位置
		cursorX = 0;
		// 再根据当前的curItem来设置指示器的宽度
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
		// 首先获得当前按钮的宽度，再减去按钮左右边距距，以对齐标题栏文本
		lp.width = widthArgs[curItem] - btnArgs[0].getPaddingLeft() * 2;
		// 通过指示标签对象，将标签设置到父容器中
		cursor.setLayoutParams(lp);
		// 循环获取当前页之前的所有页面的宽度
		for (int i = 0; i < curItem; i++) {
			cursorX = cursorX + btnArgs[i].getWidth();
		}
		// 再加上当前页面的左边距，即为指示器当前应处的位置
		cursor.setX(cursorX + btnArgs[curItem].getPaddingLeft());
	}

}
