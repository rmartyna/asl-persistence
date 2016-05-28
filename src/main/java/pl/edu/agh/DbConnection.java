package pl.edu.agh;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;
import java.sql.Connection;

public class DbConnection implements InitializingBean {

    private Connection connection;

    private DataSource dataSource;

    private static final Logger LOGGER = Logger.getLogger(DbConnection.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        if(dataSource == null)
            throw new IllegalArgumentException("Data source property cannot be null");

        connection = dataSource.getConnection();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        return connection;
    }

}
