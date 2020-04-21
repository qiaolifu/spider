package cn.crxy.maven.Spider.utils;

import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class HtmlUtil {

    /**
     * 根据xpath获取对应标签的内容
     * @param tagNode
     * @param xpath
     * @return
     */
    public static String getText(TagNode tagNode,String xpath){
        String content = null;
        Object[] evaluateXPath;
        try {
            evaluateXPath = tagNode.evaluateXPath(xpath);
            if(evaluateXPath!=null && evaluateXPath.length>0){
                TagNode node = (TagNode)evaluateXPath[0];
                content = node.getText().toString();
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 获取对应标签中指定属性的值
     * @param tagNode
     * @param xpath
     * @param attr
     * @return
     */
    public static String getAttributeByName(TagNode tagNode, String xpath, String attr){
        String content = null;
        Object[] evaluateXPath;
        try {
            evaluateXPath = tagNode.evaluateXPath(xpath);
            if(evaluateXPath!=null && evaluateXPath.length>0){
                TagNode node = (TagNode)evaluateXPath[0];
                content = node.getAttributeByName(attr);
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return content;
    }
}
