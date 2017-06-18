package dongting.bwei.com.mvprecyclerokhttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import dongting.bwei.com.mvprecyclerokhttp.presenter.MyPresenter;

/**
 * 作者:${董婷}
 * 日期:2017/6/14
 * 描述:
 */
public class MainActivity extends Activity{
//http://www.tngou.net/api/info/list

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    MyPresenter myPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerview.setLayoutManager(linearLayoutManager);

        myPresenter = new MyPresenter(recyclerview,MainActivity.this);

        boolean net = myPresenter.haveNet();

        if(net){
            Toast.makeText(this, "当前是有网状态", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "当前是无网状态", Toast.LENGTH_SHORT).show();

        }

        myPresenter.init();
    }
}
