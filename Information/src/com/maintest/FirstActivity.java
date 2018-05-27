package com.maintest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import java.lang.reflect.Field;
import java.util.ArrayList;


import com.fragment.FirstFragment;
import com.fragment.FourthFragment;
import com.fragment.SecondFragment;
import com.fragment.ThirdFragment;
import com.adapter.MyFragmentPagerAdapter;

import com.fragment.FixedSpeedScroller;
import android.graphics.Color;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class FirstActivity extends FragmentActivity implements  OnPageChangeListener{  
    
    private ViewPager myviewpager;  
    //fragment�ļ��ϣ���Ӧÿ����ҳ��  
    private ArrayList<Fragment> fragments;  
    //ѡ��еİ�ť  
    private RadioButton btn_first;  
    private RadioButton btn_second;  
    private RadioButton btn_third;  
    private RadioButton btn_four;  
   
    //��Ϊָʾ��ǩ�İ�ť  
    private ImageView cursor;  
    //��־ָʾ��ǩ�ĺ�����  
    float cursorX = 0;  
    //���а�ť�Ŀ�ȵļ���  
    private int[] widthArgs;  
    //���а�ť�ļ���  
    private Button[] btnArgs;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.first);  
          
        //initView();  
        
    }

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO �Զ����ɵķ������
		
	}  
      
/*    public void initView(){  
        myviewpager = (ViewPager)this.findViewById(R.id.myviewpager);  
        //ViewPager��setCurrentItem����ת��ViewPager��ָ��ҳ�棬
        //����ʹ�����������ʱ���и����⣬��ת��ʱ���л���Ч����
        //����Ҫ�ӵ�ǰҳ����ת������ҳ��ʱ����תҳ���ȹ���
        //����ViewPagerÿ��ҳ����Ӿ�Ч�����ϴ�ʱ��ͨ�����ַ�ʽʵ��ViewPager��ת�Եúܲ�����
        
        //����취:
        //���ǿ���ȥ����ʹ��ViewPager��setCurrentItem����ʱ�Ļ����ٶ�
        try {  
            Field mScroller = null;  
            mScroller = ViewPager.class.getDeclaredField("mScroller");  
            mScroller.setAccessible(true);   
            FixedSpeedScroller scroller = new FixedSpeedScroller( myviewpager.getContext());  
            mScroller.set( myviewpager, scroller);  
        }catch(NoSuchFieldException e){  
              
        }catch (IllegalArgumentException e){  
              
        }catch (IllegalAccessException e){  
              
        }  
          
        btn_first = (RadioButton)this.findViewById(R.id.btn_first);  
        btn_second = (RadioButton)this.findViewById(R.id.btn_second);  
        btn_third = (RadioButton)this.findViewById(R.id.btn_third);  
        btn_four = (RadioButton)this.findViewById(R.id.btn_four);  
        
       
       
        btnArgs = new Button[]{btn_first,btn_second,btn_third,btn_four};  
          
        cursor = (ImageView)this.findViewById(R.id.cursor_btn);  
        
        cursor.setBackgroundColor(Color.RED);  
       
       
        myviewpager.setOnPageChangeListener(this);  
        btn_first.setOnClickListener(this);  
        btn_second.setOnClickListener(this);  
        btn_third.setOnClickListener(this);  
        btn_four.setOnClickListener(this);  
        
        btn_first.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
		btn_second.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
		btn_third.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
		btn_four.setOnCheckedChangeListener(new InnerOnCheckedChangeListener());
      
          
        fragments = new ArrayList<Fragment>();  
        fragments.add(new FirstFragment());  
        fragments.add(new SecondFragment());  
        fragments.add(new ThirdFragment());  
        fragments.add(new FourthFragment());       
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);  
        myviewpager.setAdapter(adapter);  
        
        //�������а�ť��ɫ
        resetButtonColor();  
        //�ѵ�һ����ť����ɫ����Ϊ��ɫ
        btn_first.setTextColor(Color.RED);
        //Ϊʲô��ֱ��cursor.setWidth()��cursor.setX()
        //��ΪAndroidϵͳ����ԭ����ֻ��ȫ����������֮��Ż᲼�֣�
        //ֻ�����������ֻ�����Ϻ���ͼ���ܵõ�����ĸߺͿ�
        //��������������£���OnCreate()������ֱ�ӻ�ȡ�ؼ��Ŀ�Ⱥ͸߶�ȡ��ֵ��0��
        //�����Ǵ˴�����ָʾ���Ĵ�С��λ�ö���Ҫ�õ���һ����ť�Ĵ�С��Ϊ�ο�ֵ��
        //���Կ���ͨ��post��һ��runnableͶ�ݵ���Ϣ���е�β����Ȼ��ȴ�UI�߳�Looper���ô�runnable��ʱ��viewҲ�Ѿ���ʼ�����ˡ����ʱ����ܳɹ���ȡ�ؼ��Ŀ��
        btn_first.post(new Runnable(){  
            @Override  
            public void run() {  
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)cursor.getLayoutParams();    
                //��ȥ�߾�*2���Զ������������  
                lp.width = btn_first.getWidth()-btn_first.getPaddingLeft()*2;    
                cursor.setLayoutParams(lp);    
                cursor.setX(btn_first.getPaddingLeft());  
            }  
        });  
       
       
    }  
    
    
  //���¼����ڲ��ඨ�����
  	private class InnerOnCheckedChangeListener implements OnCheckedChangeListener{
  		//��ѡ��ťѡ���¼�����
  		//buttonView��ʾ˭��״̬���ı�
  		//isChecked����Ĳ��������״̬�Ƿ�ѡ��
  		public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
  			switch (buttonView.getId()) {
  			case R.id.btn_first:
  				//��ѡ��ťͨ������isCheckedȥ�õ���ǰ������ѡ�л���δѡ��
  				if(isChecked){
  				  myviewpager.setCurrentItem(0);  
                  cursorAnim(0);  
                  
  				}
  				
  				break;
  			case R.id.btn_second:
  				//��ѡ��ťͨ������isCheckedȥ�õ���ǰ������ѡ�л���δѡ��
  				if(isChecked){
  				  myviewpager.setCurrentItem(1);  
                  cursorAnim(1);  
  				}
  				
  				break;
  			case R.id.btn_third:
  				//��ѡ��ťͨ������isCheckedȥ�õ���ǰ������ѡ�л���δѡ��
  				if(isChecked){
  					 myviewpager.setCurrentItem(2);  
  	                cursorAnim(2);  
  				}
  				
  				break;
  			case R.id.btn_four:
  				//��ѡ��ťͨ������isCheckedȥ�õ���ǰ������ѡ�л���δѡ��
  				if(isChecked){
  					myviewpager.setCurrentItem(3);  
  	                cursorAnim(3);  
  				}
  				
  				break;

  			default:
  				break;
  			}
  			
  		}
  			
  		
  		
  	}
      
    //�������а�ť����ɫ  
    public void resetButtonColor(){  
        btn_first.setBackgroundColor(Color.parseColor("#DCDCDC"));  
        btn_second.setBackgroundColor(Color.parseColor("#DCDCDC"));  
        btn_third.setBackgroundColor(Color.parseColor("#DCDCDC"));  
        btn_four.setBackgroundColor(Color.parseColor("#DCDCDC"));  
        
        btn_first.setTextColor(Color.BLACK);  
        btn_second.setTextColor(Color.BLACK);  
        btn_third.setTextColor(Color.BLACK);  
        btn_four.setTextColor(Color.BLACK);  
        
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
        if(widthArgs==null){  
            widthArgs = new int[]{btn_first.getWidth(),  
                                 btn_second.getWidth(),  
                                 btn_third.getWidth(),  
                                 btn_four.getWidth()};  
        }  
        //ÿ�λ��������������а�ť����ɫ  
        resetButtonColor();  
      
        //���������ĵ�ǰ��ť��ɫ����Ϊ��ɫ  
        btnArgs[arg0].setTextColor(Color.RED);  
        
        cursorAnim(arg0); 
        
        //�ѵ�ǰҳ��ĵ�ѡ��ť����Ϊѡ��״̬
        ((CompoundButton) btnArgs[arg0]).setChecked(true);
       
        
    }  
      
  //ָʾ������ת�����뵱ǰ������ҳ����±�  
  	public void cursorAnim(int curItem){  
  	    //ÿ�ε��ã��ͽ�ָʾ���ĺ���������0������ʼ��λ��  
  	    cursorX = 0;  
  	    //�ٸ��ݵ�ǰ��curItem������ָʾ���Ŀ��  
  	    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)cursor.getLayoutParams();    
  	    //���Ȼ�õ�ǰ��ť�Ŀ�ȣ��ټ�ȥ��ť���ұ߾�࣬�Զ���������ı�  
  	    lp.width = widthArgs[curItem]-btnArgs[0].getPaddingLeft()*2; 
  	    //ͨ��ָʾ��ǩ���󣬽���ǩ���õ���������
  	    cursor.setLayoutParams(lp);    
  	    //ѭ����ȡ��ǰҳ֮ǰ������ҳ��Ŀ��  
  	    for(int i=0; i<curItem; i++){  
  	        cursorX = cursorX + btnArgs[i].getWidth();  
  	    }  
  	    //�ټ��ϵ�ǰҳ�����߾࣬��Ϊָʾ����ǰӦ����λ��  
  	    cursor.setX(cursorX+btnArgs[curItem].getPaddingLeft());   
  	}  
  	
  */
}

