package cn.crxy.maven.Spider.domain;

import java.util.HashMap;
import java.util.Map;

public class Page {


    private String goodId;// 商品ID
    private String goodName;//商品名称
    private String dataUrl;//商品URL地址
    private String picUrl;//商品图片URL地址
    private String price;//价格
    private Map<String, String> param = new HashMap<String, String>();//商品参数规格
    private String content;//页面原始源代码内容

    public String getGoodId() {
        return goodId;
    }
    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }
    public String getGoodName() {
        return goodName;
    }
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
    public String getDataUrl() {
        return dataUrl;
    }
    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
    public Map<String, String> getParam() {
        return param;
    }
    public void setParam(String key,String value) {
        this.param.put(key, value);
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

}
