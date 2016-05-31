package pl.edu.agh.dao;

import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.DbConnection;
import pl.edu.agh.beans.SystemLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This software may be modified and distributed under the terms
 *  of the BSD license.  See the LICENSE.txt file for details.
 */

public class SystemLogsDAO implements InitializingBean {

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = dbConnection.getConnection();
    }

    public int getMaxFileNumber(int serviceId, String filePath) throws SQLException {
        PreparedStatement getFileNumber = connection.prepareStatement("SELECT MAX(file_number) FROM system_logs WHERE service_id=? AND file_path=?");
        getFileNumber.setInt(1, serviceId);
        getFileNumber.setString(2, filePath);
        ResultSet resultSet = getFileNumber.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public List<SystemLog> getSystemLogList(int serviceId, String filePath, int fileNumber) throws SQLException {
        PreparedStatement getLastFile = connection.prepareStatement("SELECT log FROM system_logs WHERE service_id=? AND file_path=? AND file_number=? ORDER BY id");
        getLastFile.setInt(1, serviceId);
        getLastFile.setString(2, filePath);
        getLastFile.setInt(3, fileNumber);
        ResultSet resultSet = getLastFile.executeQuery();
        List<SystemLog> result = new ArrayList<SystemLog>();
        while(resultSet.next())
            result.add(new SystemLog(resultSet));

        return result;
    }

    public void insert(SystemLog systemLog) throws SQLException {
        PreparedStatement saveSystemLog = connection.prepareStatement("INSERT INTO system_logs(service_id, file_path, log, file_number, date) VALUES(?,?,?,?,?)");
        saveSystemLog.setInt(1, (int) systemLog.getServiceId());
        saveSystemLog.setString(2, systemLog.getFilePath());
        saveSystemLog.setString(3, systemLog.getLog());
        saveSystemLog.setInt(4, systemLog.getFileNumber());
        saveSystemLog.setTimestamp(5, systemLog.getDate());
        saveSystemLog.executeUpdate();
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


}
