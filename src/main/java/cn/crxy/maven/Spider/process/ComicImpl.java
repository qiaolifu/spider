package cn.crxy.maven.Spider.process;

import cn.crxy.maven.Spider.domain.ComicPage;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class ComicImpl implements Comic{
    @Override
    public void process(ComicPage comicPage) throws XPatherException {

        HtmlCleaner htmlCleaner = new HtmlCleaner();
        TagNode rootNode = htmlCleaner.clean(comicPage.getContent());

        Object[] evaluateXPath = rootNode
                .evaluateXPath("/html/body/article");
    }
}
