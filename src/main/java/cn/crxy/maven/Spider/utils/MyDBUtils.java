package cn.crxy.maven.Spider.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

public class MyDBUtils {
    private static String className = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/spider?"
            + "useUnicode=true&characterEncoding=utf-8";
    private static String user = "root";
    private static String password = "root";
    private static QueryRunner queryRunner = new QueryRunner();

    public static final String INSERT_LOG = "INSERT INTO SPIDER(good_id,"
            + "data_url,pic_url,title,price,param,`current_time`) "
            + "VALUES(?,?,?,?,?,?,?)";

    public static final String INSERT_LOG2 = "INSERT INTO PHONE(name,price,pp,xh,ssnf,ssyf,jsys,jscd,jskd,jshd,jszl,jsczfl,czxt,cpupp,cpuhs,cpuxh,zdzcsim,simklx,rom,ram,cck,zpmcc,fbl,pmczlx,sxt,myjs,zpdzfbl,sxtsl,hzsxt,sgd,pztd,dcrl,cdq,sjcsjk,nfc,ejjklx,cdjklx,zwsb,gps,tly,qt,cygn) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    // 拒绝new一个实例
    private MyDBUtils() {
    };

    static {// 调用该类时既注册驱动
        try {
            Class.forName(className);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //查询
    public static List<String> executeQuerySql(String sql) {
        List<String> result = new ArrayList<String>();
        try {
            List<Object[]> requstList = queryRunner.query(getConnection(), sql,
                    new ArrayListHandler(new BasicRowProcessor() {
                        @Override
                        public <Object> List<Object> toBeanList(ResultSet rs,
                                                                Class<Object> type) throws SQLException {
                            return super.toBeanList(rs, type);
                        }
                    }));
            for (Object[] objects : requstList) {
                result.add(objects[0].toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //这个方法可以执行一些更新或者新增的sql语句或者删除
    public static void update(String sql, Object... params) {
        try {
            Connection connection = getConnection();
            queryRunner.update(connection, sql, params);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取连接
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
