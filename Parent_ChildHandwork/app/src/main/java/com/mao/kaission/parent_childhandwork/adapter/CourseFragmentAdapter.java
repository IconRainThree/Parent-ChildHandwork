package com.mao.kaission.parent_childhandwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mao.kaission.parent_childhandwork.R;
import com.mao.kaission.parent_childhandwork.bean.CourseBean;
import com.rock.teachlibrary.ImageLoader;

import java.util.List;

/**
 * 教程中的listView所需要的自定义适配器
 */
public class CourseFragmentAdapter extends BaseAdapter {
    private Context context;
    private List<CourseBean> list;
    public CourseFragmentAdapter(Context context,List<CourseBean> list){
        this.context = context;
        this.list = list;
        ImageLoader.init(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CourseBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_course,parent,false);
            holder= new MyViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.item_course_img);
            holder.txt1 = (TextView) convertView.findViewById(R.id.item_course_txt1);
            holder.txt2= (TextView) convertView.findViewById(R.id.item_course_txt2);
            convertView.setTag(holder);
        }else{
            holder = (MyViewHolder) convertView.getTag();
        }
        ImageLoader.display(holder.img,list.get(position).getIco());
        holder.txt1.setText(list.get(position).getName());
        holder.txt2.setText("");
        for (int i = 0; i <list.get(position).getChild().size() ; i++) {
            if (i < list.get(position).getChild().size() - 1) {
                holder.txt2.append(list.get(position).getChild().get(i).getName() + ",");
            } else {
                holder.txt2.append(list.get(position).getChild().get(i).getName());
            }
        }
        return convertView;
    }

    class MyViewHolder{
        ImageView  img;
        TextView txt1,txt2;
    }
}
