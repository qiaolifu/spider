package cn.crxy.maven.Spider;

import cn.crxy.maven.Spider.domain.Page;
import cn.crxy.maven.Spider.download.Downloadable;
import cn.crxy.maven.Spider.process.Processable;
import cn.crxy.maven.Spider.store.Storeable;

public class Spider {


    private Downloadable downloadable;
    private Processable processable;
    private Storeable storeable;

    //下载页面源代码
    public Page download(String url){
        return downloadable.download(url);
    }

    //解析页面源代码
    public void process(Page page){
        processable.process(page);
    }

    //将解析后的数据保存到数据库
    public void store(Page page){
        storeable.store(page);
    }

    public Downloadable getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(Downloadable downloadable) {
        this.downloadable = downloadable;
    }

    public Processable getProcessable() {
        return processable;
    }

    public void setProcessable(Processable processable) {
        this.processable = processable;
    }

    public Storeable getStoreable() {
        return storeable;
    }

    public void setStoreable(Storeable storeable) {
        this.storeable = storeable;
    }
}
