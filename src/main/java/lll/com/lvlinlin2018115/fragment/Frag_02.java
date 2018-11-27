package lll.com.lvlinlin2018115.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.xlistviewlibrary.utils.NetWordUtils;
import com.bwie.xlistviewlibrary.view.XListView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import lll.com.lvlinlin2018115.R;
import lll.com.lvlinlin2018115.fragment.bean.Product;

public class Frag_02 extends Fragment {
    String baseUrl = "http://api.expoon.com/AppNews/getNewsList/type/1/p/";
    int page;
    private XListView xlistView;
     ArrayList<Product.DataBean> list = new ArrayList<>();
    private ImageLoader imageLoader;
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_02,container,false);
        //找控件
        xlistView = view.findViewById(R.id.xlist);
        imageLoader = ImageLoader.getInstance();
        myAdapter = new MyAdapter();
        xlistView.setAdapter(myAdapter);
        initData(page);//第一次加载
        //设置可以加载更多
        xlistView.setPullLoadEnable(true);
        /**
         * 上下拉的监听
         */
        xlistView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                list.clear();
                initData(0);
            }

            @Override
            public void onLoadMore() {
                //上拉加载更多
                page++;
                initData(page);
            }
        });

        return view;
    }

    private void initData(int page) {
        //联网区数据
        String mUrl = baseUrl + page;
        new MAycTask().execute(mUrl);
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getItemViewType(int position) {
            return position % 2;
        }

        @Override
        public int getViewTypeCount() {
            //一共有两种条目类型
            return 2;
        }

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
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int viewType = getItemViewType(position);
            switch (viewType){
                case 0:
                    ViewHolder01 vh01 = new ViewHolder01();
                    if (convertView == null){
                        convertView = View.inflate(getActivity(),R.layout.item01,null);
                        vh01.tv01 = convertView.findViewById(R.id.tv01);
                        vh01.iv = convertView.findViewById(R.id.iv);
                        convertView.setTag(vh01);
                    }else {
                        vh01 = (ViewHolder01) convertView.getTag();
                    }
                    //绑定数据
                    Product.DataBean dataBean = list.get(position);
                    vh01.tv01.setText(dataBean.getNews_summary());
                    imageLoader.displayImage(dataBean.getPic_url(),vh01.iv);
                    break;
                case 1:
                    ViewHolder02 vh02 = new ViewHolder02();
                    if (convertView == null){
                        convertView = View.inflate(getActivity(),R.layout.item02,null);
                        vh02.tv02 = convertView.findViewById(R.id.tv02);
                        convertView.setTag(vh02);
                    }else {
                        vh02 = (ViewHolder02) convertView.getTag();
                    }
                    //绑定数据
                    Product.DataBean dataBean1 = list.get(position);
                    vh02.tv02.setText(dataBean1.getNews_summary());
                    break;
            }
            return convertView;
        }
        class ViewHolder01{
            public TextView tv01;
            public ImageView iv;
        }
        class ViewHolder02{
            public TextView tv02;
        }
    }

    private class MAycTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            String jsonString = NetWordUtils.getNetjson(strings[0]);
            return jsonString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            Product product = gson.fromJson(s, Product.class);
            List<Product.DataBean> data = product.getData();
            list.addAll(data);
            //更新适配器
            myAdapter.notifyDataSetChanged();
            //让刷新头和底部隐藏
            xlistView.setRefreshTime("刚刚");
            xlistView.stopRefresh();//让刷新头消失
            xlistView.stopLoadMore();//让上拉加载的ui消失
        }
    }
}
