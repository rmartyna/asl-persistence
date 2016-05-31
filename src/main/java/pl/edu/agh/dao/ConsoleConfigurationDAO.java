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
                connection.prepareStatement("INSERT INTO console_configuration(service_id, cpu_temp_lower_color," +
                        "cpu_temp_upper_color,cpu_load_lower_color,cpu_load_upper_color,ram_load_lower_color," +
                        "ram_load_upper_color,network_dl_lower_color,network_dl_upper_color," +
                        "network_ul_lower_color,network_ul_upper_color,disk_read_lower_color," +
                        "disk_read_upper_color,disk_write_lower_color,disk_write_upper_color," +
                        "disk_usage_lower_color,disk_usage_upper_color) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        statement.setInt(1, (int) consoleConfiguration.getServiceId());
        statement.setDouble(2, consoleConfiguration.getCpuTempLowerBound());
        statement.setDouble(3, consoleConfiguration.getCpuTempUpperBound());
        statement.setDouble(4, consoleConfiguration.getCpuLoadLowerBound());
        statement.setDouble(5, consoleConfiguration.getCpuLoadUpperBound());
        statement.setDouble(6, consoleConfiguration.getRamLowerBound());
        statement.setDouble(7, consoleConfiguration.getRamUpperBound());
        statement.setDouble(8, consoleConfiguration.getNetworkDlLowerBound());
        statement.setDouble(9, consoleConfiguration.getNetworkDlUpperBound());
        statement.setDouble(10, consoleConfiguration.getNetworkUlLowerBound());
        statement.setDouble(11, consoleConfiguration.getNetworkUlUpperBound());
        statement.setDouble(12, consoleConfiguration.getDiskReadLowerBound());
        statement.setDouble(13, consoleConfiguration.getDiskReadUpperBound());
        statement.setDouble(14, consoleConfiguration.getDiskWriteLowerBound());
        statement.setDouble(15, consoleConfiguration.getDiskWriteUpperBound());
        statement.setDouble(16, consoleConfiguration.getDiskUsageLowerBound());
        statement.setDouble(17, consoleConfiguration.getDiskUsageUpperBound());

        statement.executeUpdate();

    }

    public void update(ConsoleConfiguration consoleConfiguration) throws  SQLException {
        PreparedStatement statement =
                connection.prepareStatement("UPDATE console_configuration SET " +
                        "cpu_temp_lower_color=?,cpu_temp_upper_color=?," +
                        "cpu_load_lower_color=?,cpu_load_upper_color=?," +
                        "ram_load_lower_color=?,ram_load_upper_color=?," +
                        "network_dl_lower_color=?,network_dl_upper_color=?," +
                        "network_ul_lower_color=?,network_ul_upper_color=?," +
                        "disk_read_lower_color=?,disk_read_upper_color=?," +
                        "disk_write_lower_color=?,disk_write_upper_color=?," +
                        "disk_usage_lower_color=?,disk_usage_upper_color=? WHERE id=?");

        statement.setDouble(1, consoleConfiguration.getCpuTempLowerBound());
        statement.setDouble(2, consoleConfiguration.getCpuTempUpperBound());
        statement.setDouble(3, consoleConfiguration.getCpuLoadLowerBound());
        statement.setDouble(4, consoleConfiguration.getCpuLoadUpperBound());
        statement.setDouble(5, consoleConfiguration.getRamLowerBound());
        statement.setDouble(6, consoleConfiguration.getRamUpperBound());
        statement.setDouble(7, consoleConfiguration.getNetworkDlLowerBound());
        statement.setDouble(8, consoleConfiguration.getNetworkDlUpperBound());
        statement.setDouble(9, consoleConfiguration.getNetworkUlLowerBound());
        statement.setDouble(10, consoleConfiguration.getNetworkUlUpperBound());
        statement.setDouble(11, consoleConfiguration.getDiskReadLowerBound());
        statement.setDouble(12, consoleConfiguration.getDiskReadUpperBound());
        statement.setDouble(13, consoleConfiguration.getDiskWriteLowerBound());
        statement.setDouble(14, consoleConfiguration.getDiskWriteUpperBound());
        statement.setDouble(15, consoleConfiguration.getDiskUsageLowerBound());
        statement.setDouble(16, consoleConfiguration.getDiskUsageUpperBound());
        statement.setInt(17, (int) consoleConfiguration.getId());

        statement.executeUpdate();
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


}
