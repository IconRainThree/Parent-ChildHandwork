package com.mao.kaission.parent_childhandwork.fragment.myfragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mao.kaission.parent_childhandwork.R;
import com.mao.kaission.parent_childhandwork.adapter.TopFragmentAdapter;
import com.mao.kaission.parent_childhandwork.bean.TopBean;
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
public class TopFragment extends Fragment {

    //本地图片组
    int[] picture = new int[]{R.mipmap.ranking_colck,R.mipmap.king,R.mipmap.shear,R.mipmap.baby_tree,R.mipmap.ranking_header};

    //picture图片组对应的获取颜色集合
    private List<Integer> listColor;

    //实体类数据集合
    private List<TopBean> list;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private TopFragmentAdapter topFragmentAdapter;

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_top, container, false);
        initPalette();
        initView(inflate);
        getData();
        return inflate;
    }

    //网络下载
    private void getData() {
        list = new ArrayList<>();
        HttpUtil.getStringAsync(APIContent.TOP_URL, new HttpUtil.RequestCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONArray jsonArray1 = jsonArray.optJSONArray(i);
                        for (int j = 0; j <jsonArray1.length() ; j++) {
                        JSONObject jsonObject = jsonArray1.optJSONObject(j);
                            TopBean topBean = new TopBean();
                            topBean.getJSONObject(jsonObject,i);
                            list.add(topBean);
                        }
                    }
                    initAdapter();
                    topFragmentAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    private void initAdapter() {
        topFragmentAdapter = new TopFragmentAdapter(getActivity(),list,picture,listColor);
        recyclerView.setAdapter(topFragmentAdapter);
    }

    private void initView(View inflate) {
        recyclerView = (RecyclerView) inflate.findViewById(R.id.top_fragment_recyclerview );
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    //获取picture图片组的颜色
    private void initPalette() {
        listColor = new ArrayList<>();
        for (int i = 0; i <picture.length ; i++) {
       Bitmap bitmap= BitmapFactory.decodeResource(getResources(),picture[i]);

            //使用Palette.generateAsync会使线程执行顺序变化
//            Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
//                @Override
//                public void onGenerated(Palette palette) {
//                    Palette.Swatch swatch = palette.getVibrantSwatch();
//                    if(swatch!=null){
//                        listColor.add(swatch.getRgb());
//                    }
//                }
//            });

            //Palette.generate没有开线程，执行顺序不会变化
            Palette palette = Palette.generate(bitmap);
            Palette.Swatch swatch = palette.getVibrantSwatch();
            if (swatch != null) {
                listColor.add(swatch.getRgb());
            }
        }

    }

}
