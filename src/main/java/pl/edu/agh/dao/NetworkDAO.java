package pl.edu.agh.dao;

import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.DbConnection;
import pl.edu.agh.beans.Network;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This software may be modified and distributed under the terms
 *  of the BSD license.  See the LICENSE.txt file for details.
 */

public class NetworkDAO implements InitializingBean {

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = dbConnection.getConnection();
    }

    public List<Network> listAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM network_usage");

        ResultSet result = statement.executeQuery();
        List<Network> networkList = new ArrayList<Network>();
        while(result.next())
            networkList.add(new Network(result));

        return networkList;
    }

    public List<Network> getByServiceId(int serviceId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM network_usage where service_id=?");
        statement.setInt(1, serviceId);

        ResultSet result = statement.executeQuery();
        List<Network> networkList = new ArrayList<Network>();
        while(result.next())
            networkList.add(new Network(result));

        return networkList;

    }

    public Network getNewestByServiceId(int serviceId) throws SQLException {
        List<Network> networkList = getByServiceId(serviceId);
        return networkList.get(networkList.size() - 1);
    }

    public void insert(Network network) throws SQLException {
        PreparedStatement saveNetworkUsage = connection.prepareStatement("INSERT INTO network_usage(service_id, download, upload, date) VALUES(?,?,?,?)");
        saveNetworkUsage.setInt(1, (int) network.getServiceId());
        saveNetworkUsage.setDouble(2, network.getDownload());
        saveNetworkUsage.setDouble(3, network.getUpload());
        saveNetworkUsage.setTimestamp(4, network.getDate());
        saveNetworkUsage.executeUpdate();
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


}
