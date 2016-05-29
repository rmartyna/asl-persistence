package pl.edu.agh.dao;

import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.DbConnection;
import pl.edu.agh.beans.ConsoleConfiguration;
import pl.edu.agh.beans.ServiceConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleConfigurationDAO implements InitializingBean {

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = dbConnection.getConnection();
    }

    public List<ConsoleConfiguration> listAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM console_configuration");

        ResultSet result = statement.executeQuery();
        List<ConsoleConfiguration> consoleConfigurationList = new ArrayList<ConsoleConfiguration>();
        while(result.next())
            consoleConfigurationList.add(new ConsoleConfiguration(result));

        return consoleConfigurationList;
    }

    public ConsoleConfiguration getByServiceId(int serviceId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM console_configuration where service_id=?");
        statement.setInt(1, serviceId);

        ResultSet result = statement.executeQuery();
        result.next();
        return new ConsoleConfiguration(result);

    }

    public void insert(ConsoleConfiguration consoleConfiguration) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement("INSERT INTO console_configuration(service_id, cpu_temp_temp_lower_color," +
                        "cpu_temp_upper_color,cpu_load_lower_color,cpu_load_upper_color,bandwidth_lower_color," +
                        "bandwidth_uper_color,io_lower_color,io_upper_color,ram_load_lower_color," +
                        "ram_load_upper_color) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

        statement.setInt(1, (int) consoleConfiguration.getServiceId());
        statement.setDouble(2, consoleConfiguration.getCpuTempLowerBound());
        statement.setDouble(3, consoleConfiguration.getCpuTempUpperBound());
        statement.setDouble(4, consoleConfiguration.getCpuLoadLowerBound());
        statement.setDouble(5, consoleConfiguration.getCpuLoadUpperBound());
        statement.setDouble(6, consoleConfiguration.getBandwidthLowerBound());
        statement.setDouble(7, consoleConfiguration.getBandwidthUpperBound());
        statement.setDouble(8, consoleConfiguration.getIoLowerBound());
        statement.setDouble(9, consoleConfiguration.getIoUpperBound());
        statement.setDouble(10, consoleConfiguration.getRamLowerBound());
        statement.setDouble(11, consoleConfiguration.getRamUpperBound());

        statement.executeUpdate();

    }

    public void update(ConsoleConfiguration consoleConfiguration) throws  SQLException {
        PreparedStatement statement =
                connection.prepareStatement("UPDATE console_configuration SET " +
                        "cpu_temp_lower_bound=?,cpu_temp_upper_bound=?," +
                        "cpu_load_lower_bound=?,cpu_load_upper_bound=?," +
                        "bandwidth_lower_bound=?,bandwidth_upper_bound=?," +
                        "io_lower_bound=?,io_upper_bound=?," +
                        "ram_load_lower_bound=?,ram_load_upper_bound=? WHERE id=?");

        statement.setDouble(1, consoleConfiguration.getCpuTempLowerBound());
        statement.setDouble(2, consoleConfiguration.getCpuTempUpperBound());
        statement.setDouble(3, consoleConfiguration.getCpuLoadLowerBound());
        statement.setDouble(4, consoleConfiguration.getCpuLoadUpperBound());
        statement.setDouble(5, consoleConfiguration.getBandwidthLowerBound());
        statement.setDouble(6, consoleConfiguration.getBandwidthUpperBound());
        statement.setDouble(7, consoleConfiguration.getIoLowerBound());
        statement.setDouble(8, consoleConfiguration.getIoUpperBound());
        statement.setDouble(9, consoleConfiguration.getRamLowerBound());
        statement.setDouble(10, consoleConfiguration.getRamUpperBound());
        statement.setInt(11, (int) consoleConfiguration.getId());

        statement.executeUpdate();
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


}
