package com.mao.kaission.parent_childhandwork.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 教程的需要解析的实体类
 */
public class CourseBean {

    /**
     * id : cate
     * name : 类别
     * child : [{"id":"1","name":"表演艺术","child":[]},{"id":"2","name":"旧物改造","child":[]},{"id":"3","name":"儿童纸艺","child":[]},{"id":"4","name":"粘土/橡皮泥","child":[]},{"id":"5","name":"儿童绘画","child":[]},{"id":"6","name":"儿童美食","child":[]},{"id":"7","name":"玩具DIY","child":[]},{"id":"8","name":"儿童运动","child":[]},{"id":"9","name":"父母必读","child":[]},{"id":"10","name":"儿童心理课堂","child":[]}]
     * ico : http://img1.shougongke.com/Public/images/mobel/cate.png
     */

    private String id;
    private String name;
    private String ico;
    /**
     * id : 1
     * name : 表演艺术
     * child : []
     */

    private List<ChildBean> child;


    public void getJSONObject(JSONObject jsonObject){
        List<ChildBean> list1 = new ArrayList<>();
        this.name = jsonObject.optString("name");
        this.ico = jsonObject.optString("ico");
        JSONArray  jsonArray = jsonObject.optJSONArray("child");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
            ChildBean childBean = new ChildBean();
            childBean.setName(jsonObject1.optString("name"));
            list1.add(childBean);
        }
        this.child=list1;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public List<ChildBean> getChild() {
        return child;
    }

    public void setChild(List<ChildBean> child) {
        this.child = child;
    }

    public static class ChildBean {
        private String id;
        private String name;
        private List<?> child;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<?> getChild() {
            return child;
        }

        public void setChild(List<?> child) {
            this.child = child;
        }
    }
}
