package cn.crxy.maven.Spider.process;

import cn.crxy.maven.Spider.domain.Page;
import cn.crxy.maven.Spider.utils.HtmlUtil;
import cn.crxy.maven.Spider.utils.PageUtil;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.json.JSONArray;
import org.json.JSONObject;


public class ProcessImpl implements Processable{


    public void process(Page page) {

        HtmlCleaner htmlCleaner = new HtmlCleaner();
        TagNode rootNode = htmlCleaner.clean(page.getContent());
        try {
            String str = page.getDataUrl();
            String str1=str.substring(0, str.indexOf(".html"));
            String[]  strs=str1.split("/");
            String goodid = strs[3];

            // 获取商品价格
            // 得到价格的json格式[{"id":"J_1593512","p":"17988.00","m":"17989.00"}]
            String pricejson = PageUtil
                    .getContent("http://p.3.cn/prices/get?skuid=J_" + goodid);
            JSONArray jsonArray = new JSONArray(pricejson);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String price = jsonObject.getString("p");
            if (!"-1.00".equals(price)) {

                page.setPrice(price);

                String goodName = HtmlUtil.getText(rootNode, "//*[@id=\"crumb-wrap\"]/div/div[1]/div[9]");// 得到商品名称
                boolean flag = goodName.contains("华为");
                boolean flag2 = goodName.contains("米");
                boolean flag3 = goodName.contains("OPPO");
                boolean flag4 = goodName.contains("oppo");
                if (flag || flag2 || flag3 || flag4) {
                page.setGoodName(goodName);

                String picUrl = HtmlUtil.getAttributeByName(rootNode, "//*[@id='spec-n1']/img", "src");// 获取商品图片url
                page.setPicUrl("http:" + picUrl);

                // 获取规格参数
                // *[@id="product-detail-2"]
                // *[@id="product-detail-2"]/table/tbody/tr[1]/th
                Object[] evaluateXPath = rootNode
                        .evaluateXPath("//*[@id=\"detail\"]/div[2]/div[2]/div[1]/div");
                //*[@id="detail"]

                JSONArray jsonArray2 = new JSONArray();
                if (evaluateXPath != null && evaluateXPath.length > 0) {
                    for (Object object : evaluateXPath) {
                        TagNode tagnode = (TagNode) object;
                        if (!"".equals(tagnode.getText().toString().trim())) {//有数据
                            Object[] evaluateXPath2 = tagnode.evaluateXPath("/dl");

                            if (evaluateXPath2.length > 0) {
                                for (Object obj : evaluateXPath2) {
                                    TagNode tt = (TagNode) obj;
                                    if (!"".equals(tt.getText().toString().trim())) {
                                        JSONObject jsonObject2 = new JSONObject();
                                        Object[] dtV = tt.evaluateXPath("/dt");
                                        TagNode dtn = (TagNode) dtV[0];
                                        jsonObject2.put("name", dtn.getText().toString());
                                        Object[] ddV = tt.evaluateXPath("/dd");
                                        TagNode ddn = (TagNode) ddV[ddV.length - 1];
                                        jsonObject2.put("value", ddn.getText().toString());
                                        jsonArray2.put(jsonObject2);
                                    }
                                }
                            }
                        }
                    }
                }
                page.setParam("spec", jsonArray2.toString());
            }
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
    }
}
