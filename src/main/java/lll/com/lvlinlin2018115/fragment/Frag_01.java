package lll.com.lvlinlin2018115.fragment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import lll.com.lvlinlin2018115.Net;
import lll.com.lvlinlin2018115.R;
import lll.com.lvlinlin2018115.fragment.bean.Dao;
import lll.com.lvlinlin2018115.fragment.bean.Product;

public class Frag_01 extends Fragment {
    String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    String tag = "lv";
    ArrayList<Product.DataBean> list = new ArrayList<Product.DataBean>();
    private ListView listView;
    private MyAdapter myAdapter;
    private Dao dao;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_01, container, false);
        //找控件
        listView = view.findViewById(R.id.list);
        //自定义Adapter
        myAdapter = new MyAdapter();
        //设置适配器
        listView.setAdapter(myAdapter);
        //异步
        new AysnTack().execute(urlString);
        //设置点击长按监听
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                myAdapter.notifyDataSetChanged();
                return true;
            }
        });
        return view;
    }

    private class AysnTack extends AsyncTask<String, Void, List<Product.DataBean>> {

        @Override
        protected List<Product.DataBean> doInBackground(String... strings) {
            List<Product.DataBean> json = Net.getJson(urlString);
            return json;
        }

        /**
         * news_id : 13811
         * news_summary : 6月17—20日，“2016成都深港澳台嘉年华会”(简称嘉年华会)将在成都世纪城国际会展中心举办。其主办方励展华博借力旗
         * news_title : 深港澳台千里连线，嘉年华会今夏入川
         * pic_url : http://f.expoon.com/sub/news/2016/01/21/887844_230x162_0.jpg
         */
        @SuppressLint("WrongConstant")
        @Override
        protected void onPostExecute(List<Product.DataBean> dataBeans) {
            super.onPostExecute(dataBeans);
            list.addAll(dataBeans);
            //创建Dao层
            dao = new Dao(getActivity());
            ContentValues values = new ContentValues();
            long insert = 0;
            for (int i = 0;i<dataBeans.size();i++) {
                values.put("news_id",dataBeans.get(i).getNews_id());
                values.put("news_summary",dataBeans.get(i).getNews_summary());
                values.put("news_title",dataBeans.get(i).getNews_title());
                values.put("pic_url",dataBeans.get(i).getPic_url());
                insert = dao.insert("news_table", null, values);
            }
            Toast.makeText(getActivity(),""+insert,1).show();
            myAdapter.notifyDataSetChanged();

        }
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh = new ViewHolder();
            if (convertView == null){
                convertView =  View.inflate(getActivity(),R.layout.item,null);
                vh.tv = convertView.findViewById(R.id.tv);
                convertView.setTag(vh);
            }else {
                vh = (ViewHolder) convertView.getTag();
            }
            //绑定数据
            Product.DataBean dataBean = list.get(position);
            vh.tv.setText(dataBean.getNews_summary());
            return convertView;
        }
        class ViewHolder{
            public TextView tv;
        }
    }
}
