package pl.edu.agh.dao;

import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.DbConnection;
import pl.edu.agh.beans.ConsoleConfiguration;

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

    //TODO implement
    public void insert(ConsoleConfiguration consoleConfiguration) throws SQLException {
        PreparedStatement saveConsoleConfiguration =
                connection.prepareStatement("INSERT INTO console_configuration(service_id, current, "
                        + "max, date) VALUES(?,?,?,?)");
        //saveConsoleConfiguration
    }

    //TODO implement
    public void update(ConsoleConfiguration consoleConfiguration) throws SQLException {

    }


    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


}
