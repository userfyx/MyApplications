package com.hhzmy.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hhzmy.myapplication.bean.Data;
import com.hhzmy.myapplication.httputil.OkHttp;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private String url="http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17";

    private RecyclerView mRecyclerView;
    private MyAdapter adapter;
    private List<Data.DataBean> list;
//    private ArrayList<String> mDatas;
    //private ArrayList<Data.DataBean> datas=new ArrayList<Data.DataBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderConfiguration builder=new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance().init(builder);
//        ImageLoaderConfiguration.Builder builder=new ImageLoaderConfiguration.Builder(getApplicationContext());
//        builder.build();
//        ImageLoader.getInstance().init(builder);
       // initData();
        OkHttp.getAsync(url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                  Log.e("111111111111111",result);
//                Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();

                Gson gson=new Gson();
                Data data=gson.fromJson(result,Data.class);
                list = data.getData();
                adapter = new MyAdapter(list);
                mRecyclerView.setAdapter(adapter);

//                JSONObject jsonObject=new JSONObject(result);
//                JSONArray jsonArray = jsonObject.getJSONArray("data");
////                Log.e("jsonArray-------------",jsonArray.toString());
//                for (int i=0;i<=jsonArray.length();i++){
//                    JSONObject json=(JSONObject)jsonArray.get(i);
////                    JSONObject json=jsonArray.getJSONObject(i);
//                    Data data=new Data();
//                    String goods_img=json.getString("goods_img");
//                    data.setGoods_img(goods_img);
//                    String goods_name=json.getString("goods_name");
//                    data.setGoods_name(goods_name);
//                    datas.add(data);
//                }
               // Log.e("1234567890",datas.toString());
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
       //mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST));
//       OkHttp.getSync(url);
//        Toast.makeText(MainActivity.this,OkHttp.getSync(url).toString(),Toast.LENGTH_SHORT).show();
    }

//    private void initData() {
//
//        mDatas = new ArrayList<String>();
//        for (int i = 'A'; i < 'z'; i++)
//        {
//            mDatas.add("" + (char) i);
//        }
//    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        //private Context context;
        private ArrayList<Data.DataBean> list;
        public MyAdapter(List<Data.DataBean> list){
            //this.context=context;
            this.list= (ArrayList<Data.DataBean>) list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MainActivity.this).inflate(R.layout.item_home, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            //holder.tv.setText(mDatas.get(position));
            DisplayImageOptions options=new DisplayImageOptions.Builder().build();
            ImageLoader instance = ImageLoader.getInstance();
            holder.tv.setText(list.get(position).getGoods_name());
            instance.displayImage(list.get(position).getGoods_img(),holder.imageview);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tv;
            ImageView imageview;
            public MyViewHolder(View view)
            {
                super(view);

                tv = (TextView) view.findViewById(R.id.id_num);
                imageview=(ImageView) view.findViewById(R.id.imageview);
            }
        }
    }
}
