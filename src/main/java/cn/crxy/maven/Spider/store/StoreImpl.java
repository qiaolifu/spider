package cn.crxy.maven.Spider.store;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import cn.crxy.maven.Spider.domain.Page;
import cn.crxy.maven.Spider.utils.MyDBUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


public class StoreImpl implements Storeable {

    public void store(Page page) {
        String dataUrl = page.getDataUrl();
        String goodid = page.getGoodId();
        String tilte = page.getGoodName();
        String picUrl = page.getPicUrl();
        String price  = page.getPrice();


        if (tilte != null) {
            if (price != null) {
                Map<String, String> values = page.getParam();
                String param = values.get("spec");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currtime = sdf.format(new Date());
                MyDBUtils.update(MyDBUtils.INSERT_LOG, goodid, dataUrl, picUrl, tilte, price, param, currtime);
            }
        }
    }

//    public static Map<String, Object> json2map(String str_json) {
//        Map<String, Object> res = null;
//        try {
//            Gson gson = new Gson();
//            res = gson.fromJson(str_json, new TypeToken<Map<String, Object>>() {
//            }.getType());
//        } catch (JsonSyntaxException e) {
//        }
//        return res;
//    }
//    //                MyDBUtils.update(MyDBUtils.INSERT_LOG,name,price,pp,xh,ssnf,ssyf,jsys,jscd,jskd,jshd,
////                        jszl,jsczfl,czxt,cpupp,cpuhs,cpuxh,zdzcsim,simklx,rom,ram,cck,zpmcc,fbl,pmczlx,
////                        sxt,myjs,zpdzfbl,sxtsl,hzsxt,sgd,pztd,dcrl,cdq,sjcsjk,nfc,ejjklx,cdjklx,zwsb,gps,tly,qt,cygn);

}

