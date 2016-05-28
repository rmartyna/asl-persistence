package pl.edu.agh.dao;

import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.DbConnection;
import pl.edu.agh.beans.ServiceConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceConfigurationDAO implements InitializingBean {

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = dbConnection.getConnection();
    }

    public List<ServiceConfiguration> listAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM service_configuration");

        ResultSet result = statement.executeQuery();
        List<ServiceConfiguration> serviceConfigurationList = new ArrayList<ServiceConfiguration>();
        while(result.next())
            serviceConfigurationList.add(new ServiceConfiguration(result));

        return serviceConfigurationList;
    }

    public ServiceConfiguration getByServiceId(int serviceId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM service_configuration where service_id=?");
        statement.setInt(1, serviceId);

        ResultSet result = statement.executeQuery();
        result.next();
        return new ServiceConfiguration(result);
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

}
