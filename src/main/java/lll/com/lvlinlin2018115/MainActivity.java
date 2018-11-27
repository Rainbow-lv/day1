package lll.com.lvlinlin2018115;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.ArrayList;

import lll.com.lvlinlin2018115.fragment.Frag_01;
import lll.com.lvlinlin2018115.fragment.Frag_02;
import lll.com.lvlinlin2018115.fragment.Frag_03;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private RadioGroup radioGroup;
    private Frag_01 frag_01;
    private Frag_02 frag_02;
    private Frag_03 frag_03;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        pager = findViewById(R.id.pager);
        radioGroup = findViewById(R.id.radioGroup);

        Button radio01 = findViewById(R.id.radio01);
        Button radio02 = findViewById(R.id.radio02);
        Button radio03 = findViewById(R.id.radio03);
        //创建集合
        final ArrayList<Fragment> list = new ArrayList<Fragment>();
        //创建Fragmnet
        //添加Fragmnet
        frag_01 = new Frag_01();
        frag_02 = new Frag_02();
        frag_03 = new Frag_03();
        list.add(frag_01);
        list.add(frag_02);
        list.add(frag_03);
        //设置适配器
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        //滑动页面改变按钮状态
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radioGroup.check(radioGroup.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //切换按钮改变页面状态
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio01:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.radio02:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.radio03:
                        pager.setCurrentItem(2);
                        break;
                }
            }
        });
    }
}
