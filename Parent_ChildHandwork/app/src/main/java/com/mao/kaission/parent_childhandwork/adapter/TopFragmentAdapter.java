package com.mao.kaission.parent_childhandwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mao.kaission.parent_childhandwork.R;
import com.mao.kaission.parent_childhandwork.bean.TopBean;

import java.util.List;

/**
 * RecyclerView的适配器
 */
public class TopFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<TopBean> list;
    private int[] picture;
    private List<Integer> listColor;

    //判断布局时使用
    private final int TYPE_ONE = 0;
    private final int TYPE_TWO=1;

    public TopFragmentAdapter(Context context,List<TopBean> list,int[] picture,List<Integer> listColor){
        this.context = context;
        this.list = list;
        this.picture=picture;
        this.listColor=listColor;
    }

    @Override
    public int getItemViewType(int position) {
        if(position<2){
            return TYPE_ONE;
        }else{
            return TYPE_TWO;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case TYPE_ONE:
                View view1 = LayoutInflater.from(context).inflate(R.layout.item_top_1,parent,false);
                holder=new MyHolder1(view1);
                break;
            case TYPE_TWO:
                View view2 = LayoutInflater.from(context).inflate(R.layout.item_top_2,parent,false);
                holder = new MyHolder2(view2);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case TYPE_ONE:
                    ((MyHolder1) holder).txt.setText(list.get(position).getTitle());
                    ((MyHolder1) holder).img.setImageResource(picture[position]);
                    ((MyHolder1) holder).view.setBackgroundColor(listColor.get(position));
                break;
            case TYPE_TWO:
                    ((MyHolder2) holder).view.setBackgroundColor(listColor.get(position));
                    ((MyHolder2) holder).img.setImageResource(picture[position]);
                if(list.get(position * 2 - 2).getNum()==list.get(position * 2 - 1).getNum()) {
                    ((MyHolder2) holder).txt1.setText(list.get(position * 2 - 2).getTitle());
                    ((MyHolder2) holder).txt2.setText(list.get(position * 2 - 1).getTitle());
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size()/2+1;
    }

    //布局一
    class MyHolder1 extends RecyclerView.ViewHolder{
        TextView txt;
        ImageView img;
        View view;
        public MyHolder1(View itemView) {
            super(itemView);
            txt= (TextView) itemView.findViewById(R.id.item_top_1_txt);
            img= (ImageView) itemView.findViewById(R.id.item_top_1_img);
            view=itemView.findViewById(R.id.item_top_1_view);
        }
    }

    //布局二
    class MyHolder2 extends RecyclerView.ViewHolder{
        TextView txt1,txt2;
        ImageView img;
        View view;
        public MyHolder2(View itemView) {
            super(itemView);
            txt1= (TextView) itemView.findViewById(R.id.item_top_2_txt1);
            txt2= (TextView) itemView.findViewById(R.id.item_top_2_txt2);
            img= (ImageView) itemView.findViewById(R.id.item_top_2_img);
            view=itemView.findViewById(R.id.item_top_2_view);
        }
    }
}
