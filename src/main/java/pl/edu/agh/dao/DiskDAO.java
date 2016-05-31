package pl.edu.agh.dao;

import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.DbConnection;
import pl.edu.agh.beans.Disk;
import pl.edu.agh.beans.DiskUsage;
import pl.edu.agh.beans.Partition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This software may be modified and distributed under the terms
 *  of the BSD license.  See the LICENSE.txt file for details.
 */

public class DiskDAO implements InitializingBean {

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = dbConnection.getConnection();
    }

    public List<Disk> listAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM disk");

        ResultSet result = statement.executeQuery();
        List<Disk> diskList = new ArrayList<Disk>();
        while(result.next())
            diskList.add(new Disk(result));

        for(Disk disk : diskList) {
            disk.setDiskUsageList(getUsageListByDiskId((int) disk.getId()));
            disk.setPartitionList(getPartitionListByDiskId((int) disk.getId()));
        }

        return diskList;
    }

    public Disk getByServiceId(int serviceId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM disk where service_id=?");
        statement.setInt(1, serviceId);

        ResultSet result = statement.executeQuery();
        result.next();
        Disk disk = new Disk(result);

        disk.setDiskUsageList(getUsageListByDiskId((int) disk.getId()));
        disk.setPartitionList(getPartitionListByDiskId((int) disk.getId()));

        return disk;

    }

    public List<DiskUsage> getUsageListByDiskId(int diskId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM disk_usage where disk_id=?");
        statement.setInt(1, diskId);

        ResultSet result = statement.executeQuery();
        List<DiskUsage> diskUsageList = new ArrayList<DiskUsage>();
        while(result.next())
            diskUsageList.add(new DiskUsage(result));

        return diskUsageList;
    }

    public List<Partition> getPartitionListByDiskId(int diskId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM partition where disk_id=?");
        statement.setInt(1, diskId);

        ResultSet result = statement.executeQuery();
        List<Partition> partitionList = new ArrayList<Partition>();
        while(result.next())
            partitionList.add(new Partition(result));

        return partitionList;
    }

    public DiskUsage getNewestUsageByServiceId(int serviceId) throws SQLException {
        Disk disk = getByServiceId(serviceId);
        return disk.getDiskUsageList().get(disk.getDiskUsageList().size() - 1);
    }

    public Partition getNewestPartitionByServiceId(int serviceId) throws SQLException {
        Disk disk = getByServiceId(serviceId);
        return disk.getPartitionList().get(disk.getPartitionList().size() - 1);
    }

    public void insertUsage(DiskUsage diskUsage) throws SQLException {
        PreparedStatement saveDiskUsage = connection.prepareStatement("INSERT INTO disk_usage(disk_id, read, write, date) VALUES(?,?,?,?)");

        saveDiskUsage.setInt(1, diskUsage.getDiskId());
        saveDiskUsage.setDouble(2, diskUsage.getRead());
        saveDiskUsage.setDouble(3, diskUsage.getWrite());
        saveDiskUsage.setTimestamp(4, diskUsage.getDate());
        saveDiskUsage.executeUpdate();
    }

    public void insertPartition(Partition partition) throws SQLException {
        PreparedStatement savePartitionUsage = connection.prepareStatement("INSERT INTO partition(disk_id, name, current, max, date) VALUES(?,?,?,?,?)");

        savePartitionUsage.setInt(1, partition.getDiskId());
        savePartitionUsage.setString(2, partition.getName());
        savePartitionUsage.setDouble(3, partition.getCurrent());
        savePartitionUsage.setDouble(4, partition.getMax());
        savePartitionUsage.setTimestamp(5, partition.getDate());
        savePartitionUsage.executeUpdate();
    }

    public void insert(Disk disk) throws SQLException {
        PreparedStatement putDaemonIdStatement = connection.prepareStatement("INSERT INTO disk(service_id, description) VALUES(?, ?)");
        putDaemonIdStatement.setInt(1, (int)disk.getServiceId());
        putDaemonIdStatement.setString(2, disk.getDescription());
        putDaemonIdStatement.executeUpdate();

    }



    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


}
