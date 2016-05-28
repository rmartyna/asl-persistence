package pl.edu.agh.dao;

import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.DbConnection;
import pl.edu.agh.beans.Memory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemoryDAO implements InitializingBean {

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = dbConnection.getConnection();
    }

    public List<Memory> listAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM memory_usage");

        ResultSet result = statement.executeQuery();
        List<Memory> memoryList = new ArrayList<Memory>();
        while(result.next())
            memoryList.add(new Memory(result));

        return memoryList;
    }

    public List<Memory> getByServiceId(int serviceId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM memory_usage where service_id=?");
        statement.setInt(1, serviceId);

        ResultSet result = statement.executeQuery();
        List<Memory> memoryList = new ArrayList<Memory>();
        while(result.next())
            memoryList.add(new Memory(result));

        return memoryList;

    }

    public Memory getNewestByServiceId(int serviceId) throws SQLException {
        List<Memory> memoryList = getByServiceId(serviceId);
        return memoryList.get(memoryList.size() - 1);

    }

    public void insert(Memory memory) throws SQLException {
        PreparedStatement saveMemoryUsage = connection.prepareStatement("INSERT INTO memory_usage(service_id, current, max, date) VALUES(?,?,?,?)");
        saveMemoryUsage.setInt(1, (int)memory.getServiceId());
        saveMemoryUsage.setDouble(2, memory.getCurrent());
        saveMemoryUsage.setDouble(3, memory.getMax());
        saveMemoryUsage.setTimestamp(4, memory.getDate());
        saveMemoryUsage.executeUpdate();
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


}
