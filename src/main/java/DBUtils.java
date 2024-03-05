import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DBUtils {
    private static Properties appProps = new Properties();

    private static BasicDataSource basicDataSource = new BasicDataSource();

    private static String URL = null;

    private static String username = null;
    private static String password = null;

    static{
        try(FileInputStream propertiesFile = new FileInputStream("src/main/resources/app.properties")){
            appProps.load(propertiesFile);
            URL = appProps.getProperty("URL");
            username = appProps.getProperty("username");
            password = appProps.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setDataSourceConfigs();
    }

    private static void setDataSourceConfigs(){
        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }


    /*public Connection getConnection(){
        return DriverManager.getConnection(URL,username,password);
    }*/

}
