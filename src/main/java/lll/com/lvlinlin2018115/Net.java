package lll.com.lvlinlin2018115;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import lll.com.lvlinlin2018115.fragment.bean.Product;

public class Net {
    public static List<Product.DataBean> getJson(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //获取响应状态码
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200){
                //获取文件流
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp = "";
                //拼接字符串
                StringBuilder stringBuilder = new StringBuilder();
                //按行读取
                while ((temp = bufferedReader.readLine())!=null){
                    stringBuilder.append(temp);
                }
                //解析
                Gson gson = new Gson();
                Product product = gson.fromJson(String.valueOf(stringBuilder), Product.class);
                List<Product.DataBean> data = product.getData();
                return data;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
