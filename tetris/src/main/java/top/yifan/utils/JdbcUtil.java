package top.yifan.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 数据库连接工具类
 *
 * @author star
 */
public final class JdbcUtil {

    private JdbcUtil() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }

    /**
     * 获取数据库连接
     *
     * @return connection 对象
     */
    public static Connection getConnection(String url, String user, String password) {
        try {
            // 获得驱动连接
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    /**
     * 关闭资源
     *
     * @throws Exception
     */
    public static void closeResource(Connection connection, Statement statement, ResultSet result) throws Exception {
        if (result != null) {
            result.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

}
