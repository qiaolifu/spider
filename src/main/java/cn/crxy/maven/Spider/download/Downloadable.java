package cn.crxy.maven.Spider.download;

import cn.crxy.maven.Spider.domain.Page;

public interface Downloadable {
    Page download(String url);
}
