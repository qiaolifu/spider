package cn.crxy.maven.Spider.process;

import cn.crxy.maven.Spider.domain.ComicPage;
import org.htmlcleaner.XPatherException;

public interface Comic {
    void process(ComicPage ComicPage) throws XPatherException;
}
