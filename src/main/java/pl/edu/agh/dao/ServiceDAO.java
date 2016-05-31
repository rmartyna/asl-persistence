package pl.edu.agh.dao;

import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.DbConnection;
import pl.edu.agh.beans.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This software may be modified and distributed under the terms
 *  of the BSD license.  See the LICENSE.txt file for details.
 */

public class ServiceDAO implements InitializingBean {

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = dbConnection.getConnection();
    }

    public List<Service> listAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM service");

        ResultSet result = statement.executeQuery();
        List<Service> serviceList = new ArrayList<Service>();
        while(result.next())
            serviceList.add(new Service(result));

        return serviceList;
    }

    public Service getById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM service WHERE id=?");
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();
        result.next();
        return new Service(result);
    }

    public Service getByHost(String host) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM service WHERE host=?");
        statement.setString(1, host);

        ResultSet result = statement.executeQuery();
        result.next();
        return new Service(result);
    }

    public Service getByHostAndPort(String host, Integer port) throws SQLException {
        PreparedStatement getServiceId = connection.prepareStatement("SELECT * FROM service WHERE host='" + host + "' AND PORT=" + port);

        ResultSet result = getServiceId.executeQuery();
        result.next();

        return new Service(result);
    }

    public void insert(Service service) throws SQLException {
        PreparedStatement putServiceId = connection.prepareStatement("INSERT INTO service(host, port, description) VALUES(?, ?, ?)");
        putServiceId.setString(1, service.getHost());
        putServiceId.setInt(2, service.getPort());
        putServiceId.setString(3, service.getDescription());
        putServiceId.executeUpdate();
    }

    public void delete(Service service) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM service WHERE id=?");
        statement.setInt(1, (int) service.getId());

        statement.executeUpdate();
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


}