package com.mao.kaission.parent_childhandwork.fragment.myfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.mao.kaission.parent_childhandwork.R;
import com.mao.kaission.parent_childhandwork.adapter.CourseFragmentAdapter;
import com.mao.kaission.parent_childhandwork.bean.CourseBean;
import com.mao.kaission.parent_childhandwork.comment.APIContent;
import com.rock.teachlibrary.http.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends Fragment {

    private ExpandableListView expandableListView = null;

    private CourseFragmentAdapter courseFragmentAdapter;

    private List<CourseBean> list;
    public CourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_course, container, false);
        initView(inflate);
        getData();
        return inflate;
    }

    private void initAdapter() {
        courseFragmentAdapter = new CourseFragmentAdapter(getActivity(),list);
        expandableListView.setAdapter(courseFragmentAdapter);
    }

    private void getData() {
        list= new ArrayList<>();
        HttpUtil.getStringAsync(APIContent.COURSE_URL, new HttpUtil.RequestCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.optJSONArray("list");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                        CourseBean courseBean = new CourseBean();
                        courseBean.getJSONObject(jsonObject1);
                        list.add(courseBean);
                    }
                    initAdapter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    private void initView(View inflate) {
        expandableListView = (ExpandableListView) inflate.findViewById(R.id.course_fragment_listview);

    }

}
