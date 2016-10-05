package com.mao.kaission.parent_childhandwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mao.kaission.parent_childhandwork.R;
import com.mao.kaission.parent_childhandwork.bean.CourseBean;
import com.rock.teachlibrary.ImageLoader;

import java.util.List;

/**
 * 教程中的listView所需要的自定义适配器
 */
public class CourseFragmentAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<CourseBean> list;
    public CourseFragmentAdapter(Context context,List<CourseBean> list){
        this.context = context;
        this.list = list;
        ImageLoader.init(context);
    }

    //获取的群体数量，得到groups里元素的个数
    @Override
    public int getGroupCount() {
        return list.size();
    }

    //取得指定组中的children个数，就是groups中每一个条目中的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChild().size();
    }

    //获取与给定的组相关的数据，得到数组groups中元素的数据
    @Override
    public CourseBean getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    //获取与孩子在给定的组相关的数据,得到数组children中元素的数据
    @Override
    public CourseBean.ChildBean getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getChild().get(childPosition);
    }

    //获取组在给定的位置编号，即groups中元素的ID
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //获取在给定的组的children的ID，也就是children中元素的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //表示孩子是否和组ID是跨基础数据的更改稳定
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //获取一个视图显示给定组，存放groups
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        MyGroupViewHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_course,parent,false);
            holder= new MyGroupViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.item_course_img);
            holder.txt1 = (TextView) convertView.findViewById(R.id.item_course_txt1);
            holder.txt2= (TextView) convertView.findViewById(R.id.item_course_txt2);
            convertView.setTag(holder);
        }else{
            holder = (MyGroupViewHolder) convertView.getTag();
        }
        ImageLoader.display(holder.img,list.get(groupPosition).getIco());
        holder.txt1.setText(list.get(groupPosition).getName());
        holder.txt2.setText("");
        for (int i = 0; i <list.get(groupPosition).getChild().size() ; i++) {
            if (i < list.get(groupPosition).getChild().size() - 1) {
                holder.txt2.append(list.get(groupPosition).getChild().get(i).getName() + ",");
            } else {
                holder.txt2.append(list.get(groupPosition).getChild().get(i).getName());
            }
        }
        return convertView;
    }

    //获取一个视图显示在给定的组 的儿童的数据，就是存放children
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        List<CourseBean.ChildBean> listChild=list.get(groupPosition).getChild();
        MyChildViewHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_course_expandchild,parent,false);
            holder=new MyChildViewHolder();
            holder.txt= (TextView) convertView.findViewById(R.id.item_course_expand_txt);
            convertView.setTag(holder);
        }else{
            holder= (MyChildViewHolder) convertView.getTag();
        }

        holder.txt.setText(listChild.get(childPosition).getName());
        return convertView;
    }

    //孩子在指定的位置是可选的，即：children中的元素是可点击的
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class MyGroupViewHolder{
        ImageView  img;
        TextView txt1,txt2;
    }

    class MyChildViewHolder{
        TextView txt;
    }
}
