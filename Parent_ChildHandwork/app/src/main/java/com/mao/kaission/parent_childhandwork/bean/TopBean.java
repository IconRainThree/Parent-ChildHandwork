package com.mao.kaission.parent_childhandwork.bean;

import org.json.JSONObject;

/**
 * 排行榜Top实体类
 */
public class TopBean {


    /**
     * id : n
     * action : tophot
     * title : 最新教程 TOP100
     */

    private String id;
    private String action;
    private String title;
    private int num;


    public void getJSONObject(JSONObject jsonObject, int num){
        this.title=jsonObject.optString("title");

        //num用来判断数据的组别
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
