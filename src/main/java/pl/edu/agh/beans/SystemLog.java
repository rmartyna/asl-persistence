package pl.edu.agh.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class SystemLog {

    private long id;

    private long serviceId;

    private String filePath;

    private String log;

    private int fileNumber;

    private Timestamp date;

    public SystemLog() {
    }

    public SystemLog(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        serviceId = resultSet.getInt(2);
        filePath = resultSet.getString(3);
        log = resultSet.getString(4);
        fileNumber = resultSet.getInt(5);
        date = resultSet.getTimestamp(6);
    }

    public SystemLog(long serviceId, String filePath, String log, int fileNumber, Timestamp date) {
        this.serviceId = serviceId;
        this.filePath = filePath;
        this.log = log;
        this.fileNumber = fileNumber;
        this.date = date;
    }

    @Override
    public String toString() {
        return "SystemLog{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", filePath='" + filePath + '\'' +
                ", log='" + log + '\'' +
                ", fileNumber=" + fileNumber +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemLog)) return false;

        SystemLog systemLog = (SystemLog) o;

        if (id != systemLog.id) return false;
        if (serviceId != systemLog.serviceId) return false;
        if (fileNumber != systemLog.fileNumber) return false;
        if (filePath != null ? !filePath.equals(systemLog.filePath) : systemLog.filePath != null) return false;
        if (log != null ? !log.equals(systemLog.log) : systemLog.log != null) return false;
        return date != null ? date.equals(systemLog.date) : systemLog.date == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (serviceId ^ (serviceId >>> 32));
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (log != null ? log.hashCode() : 0);
        result = 31 * result + fileNumber;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public int getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(int fileNumber) {
        this.fileNumber = fileNumber;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
