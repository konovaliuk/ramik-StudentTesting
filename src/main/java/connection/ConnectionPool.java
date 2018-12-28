package connection;


import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public final class ConnectionPool {


    private final static String DB_USERNAME = "username";
    private final static String DB_PASSWORD = "password";
    private final static String DB_URL = "url";
    private final static String DB_DRIVER_CLASS = "driver.class.name";
    private final static String MIN_IDLE = "minIdle";
    private final static String MAX_IDLE = "maxIdle";
    private final static String MAX_WAIT = "maxWaitMillis";
    private final static String MAX_TOTAL = "maxTotal";
    private final static String TRANSACTION_ISOLATION = "transactionIsolation";

    private static Properties properties = null;


    private static BasicDataSource dataSource = null;

    static {

           /* classLoader = Thread.currentThread().getContextClassLoader();
            inputStream = classLoader.getResourceAsStream("database.properties");*/
            try {
                properties = new Properties();
                properties.load(new FileInputStream("D:\\JAVA\\Проекты\\IdeaProjects\\WebStudentTesting\\src\\properties\\database.properties"));
                dataSource = new BasicDataSource();
                dataSource.setDriverClassName(properties.getProperty(DB_DRIVER_CLASS));
                dataSource.setUrl(properties.getProperty(DB_URL));
                dataSource.setUsername(properties.getProperty(DB_USERNAME));
                dataSource.setPassword(properties.getProperty(DB_PASSWORD));
                dataSource.setMinIdle(Integer.valueOf(properties.getProperty(MIN_IDLE)));
                dataSource.setMaxIdle(Integer.valueOf(properties.getProperty(MAX_IDLE)));
                dataSource.setMaxWaitMillis(Long.valueOf(properties.getProperty(MAX_WAIT)));
                dataSource.setMaxTotal(Integer.valueOf(properties.getProperty(MAX_TOTAL)));
                dataSource.setDefaultTransactionIsolation(Integer.valueOf(properties.getProperty(TRANSACTION_ISOLATION)));
            } catch (IOException e) {
                e.printStackTrace();
            }

    }




    public static Connection getConnection(boolean autocommit) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(autocommit);
            return connection;
        } catch (SQLException e) {
        }
        return connection;
    }

    public static void commitTransaction(Connection connection) {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
            }
        }
    }


    public static void transactionRollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
            }
        }

    }
}
