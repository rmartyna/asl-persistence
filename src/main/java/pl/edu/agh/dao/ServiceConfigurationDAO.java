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

/**
 * This software may be modified and distributed under the terms
 *  of the BSD license.  See the LICENSE.txt file for details.
 */

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

    public void insert(ServiceConfiguration serviceConfiguration) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement("INSERT INTO service_configuration(service_id, mode, poll_rate"
                        + ", cpu_frequency, cpu_enabled, disk_frequency, disk_enabled"
                        + ", memory_frequency, memory_enabled, network_frequency, network_enaled"
                        + ", syslog_frequency, syslog_enabled, syslog_list) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        statement.setInt(1, (int) serviceConfiguration.getServiceId());
        statement.setString(2, serviceConfiguration.getMode());
        statement.setInt(3, serviceConfiguration.getPollRate());
        statement.setInt(4, serviceConfiguration.getCpuFrequency());
        statement.setInt(5, serviceConfiguration.getCpuEnabled());
        statement.setInt(6, serviceConfiguration.getDiskFrequency());
        statement.setInt(7, serviceConfiguration.getDiskEnabled());
        statement.setInt(8, serviceConfiguration.getMemoryFrequency());
        statement.setInt(9, serviceConfiguration.getMemoryEnabled());
        statement.setInt(10, serviceConfiguration.getNetworkFrequency());
        statement.setInt(11, serviceConfiguration.getNetworkEnabled());
        statement.setInt(12, serviceConfiguration.getSyslogFrequency());
        statement.setInt(13, serviceConfiguration.getSyslogEnabled());
        statement.setString(14, serviceConfiguration.getSyslogList());

        statement.executeUpdate();

    }

    public void update(ServiceConfiguration serviceConfiguration) throws  SQLException {
        PreparedStatement statement =
                connection.prepareStatement("UPDATE service_configuration SET "
                + "mode=?,poll_rate=?,cpu_frequency=?,cpu_enabled=?,disk_frequency=?,disk_enabled=?," +
                        "memory_frequency=?,memory_enabled=?,network_frequency=?,network_enaled=?," +
                        "syslog_frequency=?,syslog_enabled=?, syslog_list=? WHERE id=?");

        statement.setString(1, serviceConfiguration.getMode());
        statement.setInt(2, serviceConfiguration.getPollRate());
        statement.setInt(3, serviceConfiguration.getCpuFrequency());
        statement.setInt(4, serviceConfiguration.getCpuEnabled());
        statement.setInt(5, serviceConfiguration.getDiskFrequency());
        statement.setInt(6, serviceConfiguration.getDiskEnabled());
        statement.setInt(7, serviceConfiguration.getMemoryFrequency());
        statement.setInt(8, serviceConfiguration.getMemoryEnabled());
        statement.setInt(9, serviceConfiguration.getNetworkFrequency());
        statement.setInt(10, serviceConfiguration.getNetworkEnabled());
        statement.setInt(11, serviceConfiguration.getSyslogFrequency());
        statement.setInt(12, serviceConfiguration.getSyslogEnabled());
        statement.setString(13, serviceConfiguration.getSyslogList());
        statement.setInt(14, (int) serviceConfiguration.getId());

        statement.executeUpdate();
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

}
