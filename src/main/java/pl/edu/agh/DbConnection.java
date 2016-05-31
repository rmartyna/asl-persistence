package pl.edu.agh;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * This software may be modified and distributed under the terms
 *  of the BSD license.  See the LICENSE.txt file for details.
 */

/**
 * Utility class that returns connection to database
 */
public class DbConnection implements InitializingBean {

    private Connection connection;

    private DataSource dataSource;

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
