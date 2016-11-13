package com.bw.test.myapplication.xutils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by a on 2016/11/7.
 */
public class XutilsJson {

    public void xutils(){

        HttpUtils http=new HttpUtils();
        final HttpHandler<String> send = http.send(HttpRequest.HttpMethod.GET, "http://japi.juhe.cn/joke/content/list.from?key= 874ed931559ba07aade103eee279bb37 &page=1&pagesize=10&sort=asc&time=1418745237", new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                String result=msg.toString();
            }
        });

    }


    public String GetHttp(String url){
        String content="";
        try {
            URL u=new URL(url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)u.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String re="";
            while((re=br.readLine())!=null){
                content+=re;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
