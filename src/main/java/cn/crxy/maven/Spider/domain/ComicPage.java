package cn.crxy.maven.Spider.domain;

public class ComicPage {
    private String url;//url
    private String content;//页面原始源代码内容

    public ComicPage() {
    }

    public ComicPage(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
