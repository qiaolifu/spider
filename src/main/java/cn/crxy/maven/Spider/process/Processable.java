package cn.crxy.maven.Spider.process;


import cn.crxy.maven.Spider.domain.Page;

public interface Processable {
    void process(Page page);
}
