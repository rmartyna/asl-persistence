package pl.edu.agh.dao;

import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.DbConnection;
import pl.edu.agh.beans.Cpu;
import pl.edu.agh.beans.CpuFan;
import pl.edu.agh.beans.CpuTemp;
import pl.edu.agh.beans.CpuUsage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CpuDAO implements InitializingBean {

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = dbConnection.getConnection();
    }

    public List<Cpu> listAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cpu");

        ResultSet result = statement.executeQuery();
        List<Cpu> cpuList = new ArrayList<Cpu>();
        while(result.next())
            cpuList.add(new Cpu(result));

        for(Cpu cpu : cpuList) {
            cpu.setCpuFanList(getFanListByCpuId((int) cpu.getId()));
            cpu.setCpuTempList(getTempListByCpuId((int) cpu.getId()));
            cpu.setCpuUsageList(getUsageListByCpuId((int) cpu.getId()));
        }

        return cpuList;
    }

    public Cpu getByServiceId(int serviceId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cpu where service_id=?");
        statement.setInt(1, serviceId);

        ResultSet result = statement.executeQuery();
        result.next();
        Cpu cpu = new Cpu(result);

        cpu.setCpuFanList(getFanListByCpuId((int) cpu.getId()));
        cpu.setCpuTempList(getTempListByCpuId((int) cpu.getId()));
        cpu.setCpuUsageList(getUsageListByCpuId((int) cpu.getId()));

        return cpu;

    }

    public List<CpuFan> getFanListByCpuId(int cpuId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cpu_fan where cpu_id=?");
        statement.setInt(1, cpuId);

        ResultSet result = statement.executeQuery();
        List<CpuFan> cpuFanList = new ArrayList<CpuFan>();
        while(result.next())
            cpuFanList.add(new CpuFan(result));

        return cpuFanList;
    }

    public List<CpuTemp> getTempListByCpuId(int cpuId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cpu_temp where cpu_id=?");
        statement.setInt(1, cpuId);

        ResultSet result = statement.executeQuery();
        List<CpuTemp> cpuTempList = new ArrayList<CpuTemp>();
        while(result.next())
            cpuTempList.add(new CpuTemp(result));

        return cpuTempList;
    }

    public List<CpuUsage> getUsageListByCpuId(int cpuId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cpu_usage where cpu_id=?");
        statement.setInt(1, cpuId);

        ResultSet result = statement.executeQuery();
        List<CpuUsage> cpuUsageList = new ArrayList<CpuUsage>();
        while(result.next())
            cpuUsageList.add(new CpuUsage(result));

        return cpuUsageList;
    }

    public CpuTemp getNewestTempByServiceId(int serviceId) throws SQLException {
        Cpu cpu = getByServiceId(serviceId);
        return cpu.getCpuTempList().get(cpu.getCpuTempList().size() - 1);
    }

    public CpuUsage getNewestUsageByServiceId(int serviceId) throws SQLException {
        Cpu cpu = getByServiceId(serviceId);
        return cpu.getCpuUsageList().get(cpu.getCpuUsageList().size() - 1);
    }

    public CpuFan getNewestFanByServiceId(int serviceId) throws SQLException {
        Cpu cpu = getByServiceId(serviceId);
        return cpu.getCpuFanList().get(cpu.getCpuFanList().size() - 1);
    }

    public void insert(Cpu cpu) throws SQLException {
        PreparedStatement putDaemonIdStatement = connection.prepareStatement("INSERT INTO cpu(service_id, description) VALUES(?, ?)");
        putDaemonIdStatement.setInt(1, (int)cpu.getServiceId());
        putDaemonIdStatement.setString(2, cpu.getDescription());
        putDaemonIdStatement.executeUpdate();
    }

    public void insertFan(CpuFan cpuFan) throws SQLException {
        PreparedStatement saveFanSpeed = connection.prepareStatement("INSERT INTO cpu_fan(cpu_id, speed, date) VALUES(?,?,?)");
        saveFanSpeed.setInt(1, cpuFan.getCpuId());
        saveFanSpeed.setInt(2, cpuFan.getSpeed());
        saveFanSpeed.setTimestamp(3, cpuFan.getDate());
        saveFanSpeed.executeUpdate();
    }


    public void insertUsage(CpuUsage cpuUsage) throws SQLException {
        PreparedStatement saveUsage = connection.prepareStatement("INSERT INTO cpu_usage(cpu_id, \"user\", system, iowait, date) VALUES(?,?,?,?,?)");
        saveUsage.setInt(1, cpuUsage.getCpuId());
        saveUsage.setDouble(2, cpuUsage.getUser());
        saveUsage.setDouble(3, cpuUsage.getSystem());
        saveUsage.setDouble(4, cpuUsage.getIowait());
        saveUsage.setTimestamp(5, cpuUsage.getDate());
        saveUsage.executeUpdate();
    }


    public void insertTemp(CpuTemp cpuTemp) throws SQLException {
        PreparedStatement saveTemp = connection.prepareStatement("INSERT INTO cpu_temp(cpu_id, core, value, date) VALUES(?,?,?,?)");
        saveTemp.setInt(1, cpuTemp.getCpuId());
        saveTemp.setInt(2, cpuTemp.getCore());
        saveTemp.setDouble(3, cpuTemp.getValue());
        saveTemp.setTimestamp(4, cpuTemp.getDate());
        saveTemp.executeUpdate();
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

}
