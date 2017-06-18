package dongting.bwei.com.mvprecyclerokhttp.presenter;

import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import dongting.bwei.com.mvprecyclerokhttp.MainActivity;
import dongting.bwei.com.mvprecyclerokhttp.NetUtils;
import dongting.bwei.com.mvprecyclerokhttp.adapter.MyAdapter;
import dongting.bwei.com.mvprecyclerokhttp.bean.News;
import dongting.bwei.com.mvprecyclerokhttp.model.MyModel;

/**
 * 作者:${董婷}
 * 日期:2017/6/14
 * 描述:逻辑处理
 */

public class MyPresenter implements MyModel.Listener{

    List<News.DataBeanX.DataBean> list = new ArrayList<>();

    MainActivity context;

    MyModel myModel;

    RecyclerView recyclerView;

    public MyPresenter(RecyclerView recyclerView, MainActivity context){
        this.context=context;
        this.recyclerView=recyclerView;
        this.myModel=new MyModel(this);
    }

    public boolean haveNet(){
        boolean b = NetUtils.isNetworkAvailable(context);
        return b;
    }

    public void init(){
        myModel.getData();
    }


//成功返回数据结果
    @Override
    public void success(String result) {
        System.out.println("result = " + result);

        Gson gson=new Gson();
        News news = gson.fromJson(result, News.class);

        List<News.DataBeanX.DataBean> data = news.getData().getData();
        list.addAll(data);

        final MyAdapter myAdapter =new MyAdapter(context,list);

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(myAdapter);

                myAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void fail() {

    }
}
