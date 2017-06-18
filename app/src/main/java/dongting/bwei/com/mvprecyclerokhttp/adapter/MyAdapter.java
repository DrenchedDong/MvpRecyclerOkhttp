package dongting.bwei.com.mvprecyclerokhttp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dongting.bwei.com.mvprecyclerokhttp.MainActivity;
import dongting.bwei.com.mvprecyclerokhttp.R;
import dongting.bwei.com.mvprecyclerokhttp.bean.News;

/**
 * 作者:${董婷}
 * 日期:2017/6/14
 * 描述:
 */

public class MyAdapter extends RecyclerView.Adapter {

    int content =1;

    List<News.DataBeanX.DataBean> list ;
    MainActivity context;
    LayoutInflater inflater;

    public MyAdapter(MainActivity context,List<News.DataBeanX.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if(viewType==1){
            View view = inflater.inflate(R.layout.item, parent, false);

            OneViewHolder one=new OneViewHolder(view);

            return one;

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof OneViewHolder){
            OneViewHolder oneViewHolder = (OneViewHolder) holder ;
            oneViewHolder.title.setText(list.get(position).getName());

           /* Glide.with(context).load(newsList.get(position).getImg())
                    .error(R.mipmap.ic_launcher)
                    .into(oneViewHolder.iv);*/
        }
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    @Override
    public int getItemViewType(int position) {

        return content;
    }

    class OneViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView iv;

        public OneViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
