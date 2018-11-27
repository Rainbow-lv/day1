package lll.com.lvlinlin2018115.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import lll.com.lvlinlin2018115.MFragment;
import lll.com.lvlinlin2018115.R;

public class Frag_03 extends Fragment {

    private DrawerLayout drawar;
    private FrameLayout fragment;
    private ListView listView;
    ArrayList<String> list = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_03, container, false);
        //找控件
        drawar = view.findViewById(R.id.drawar);
        fragment = view.findViewById(R.id.fl);
        listView = view.findViewById(R.id.list);
        initData();
        //设置点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl, MFragment.getIntasc(list.get(position))).commit();
                drawar.closeDrawers();
            }
        });
        drawar.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                //正在拉的过程
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                //打开的时候
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                //关闭的时候
            }

            @Override
            public void onDrawerStateChanged(int i) {
                //状态改变的时候
                Toast.makeText(getActivity(), "我关闭了", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add("DrawerLayout"+i);
        }
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(mAdapter);
    }
}
