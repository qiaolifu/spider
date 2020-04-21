package cn.crxy.maven.Spider;

import org.junit.Test;

import cn.crxy.maven.Spider.domain.Page;
import cn.crxy.maven.Spider.download.DownloadImpl;
import cn.crxy.maven.Spider.process.ProcessImpl;
import cn.crxy.maven.Spider.store.StoreImpl;

public class TestSpider {

    @Test
    public void test1() throws Exception {
        Spider spider = new Spider();

        //给接口注入实现类
        spider.setDownloadable(new DownloadImpl());
        spider.setProcessable(new ProcessImpl());
        spider.setStoreable(new StoreImpl());

        String url = "http://www.manhuacun.com/Mh/inforedit.html&mhid=67&ji_no=19";
        Page page = spider.download(url);
        spider.process(page);
        spider.store(page);

    }
}
