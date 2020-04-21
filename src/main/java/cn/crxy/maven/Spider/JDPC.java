package cn.crxy.maven.Spider;

import cn.crxy.maven.Spider.domain.Page;
import cn.crxy.maven.Spider.download.DownloadImpl;
import cn.crxy.maven.Spider.process.ProcessImpl;
import cn.crxy.maven.Spider.store.StoreImpl;
import cn.crxy.maven.Spider.utils.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class JDPC {

    //创建dao对象
    //static ProductDao productDao = new ProductDao();
    //创建线程池
    static ExecutorService threadPool = Executors.newFixedThreadPool(20);
    //创建原生阻塞队列  队列最大容量为1000
    static BlockingQueue<String> queue=new ArrayBlockingQueue<String>(1000);

    public static void main(String[] args) throws IOException, InterruptedException {
        //监视队列大小的线程
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //获得队列当前的大小
                    int size = queue.size();
                    System.out.println("当前队列中有"+size+"个pid");
                }
            }
        });


        //开启10个线程去解析手机列表页获得的pids
        for (int i = 1; i <=10; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        String pid=null;

                        try {
                            //从队列中取出pid
                            pid = queue.take();
                            Page product = parsePid(pid);


                        } catch (Exception e) {
                            e.printStackTrace();
                            try {
                                //出现异常则放回队列
                                queue.put(pid);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
        for (int i = 1; i <=100 ; i++) {
            //京东分页page为 1 3 5 7 .....
            //         对应第一页 第二页....
            String url="https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&page="+i;
            String html = HttpClientUtils.doGet(url);
            parseIndex(html);
        }

    }
    private  static void parseIndex(String html) throws IOException, InterruptedException {
        Document document = Jsoup.parse(html);
        //手机列表
        Elements elements = document.select("#J_goodsList>ul>li");

        if(elements!=null||elements.size()!=0){
            for (Element element : elements) {
                //获得每个li的pid
                String pid = element.attr("data-pid");
                //将pid放入队列中
                queue.put(pid);
            }
        }
    }

    //解析每个手机的页面 获得某个手机的详细数据
    private static Page parsePid(String pid) throws IOException {
        //拼接url 进入手机详情页
        String productUrl="https://item.jd.com/"+pid+".html";

        Spider spider = new Spider();

        spider.setDownloadable(new DownloadImpl());
        spider.setProcessable(new ProcessImpl());
        spider.setStoreable(new StoreImpl());

        Page page = spider.download(productUrl);
        spider.process(page);
        spider.store(page);

        return page;
    }
}
