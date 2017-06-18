package dongting.bwei.com.mvprecyclerokhttp.model;

import java.io.IOException;

import dongting.bwei.com.mvprecyclerokhttp.MyInterface.IModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者:${董婷}
 * 日期:2017/6/14
 * 描述:网络数据的请求
 */

public class MyModel implements IModel{

    @Override
    public void getData() {

        final OkHttpClient okHttpClient =new OkHttpClient();
//http://qhb.2dyt.com/Bwei/login?username=11111111111&passwo
        Request request =new Request.Builder().url("http://ic.snssdk.com/article/category/get/v2/?iid=2939228904").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("okHttpClient = " + okHttpClient);
                listener.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();

                System.out.println("result = " + result);

                listener.success(result);
            }
        });
    }

    Listener listener;

    public MyModel(Listener listener){
        this.listener =listener;
    }

    public  interface  Listener{
        void success(String s);
        void fail();
    }
}
