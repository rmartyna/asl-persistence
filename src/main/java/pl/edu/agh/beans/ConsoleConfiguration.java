package pl.edu.agh.beans;

import java.sql.ResultSet;
import java.sql.SQLException;


//TODO add more parameters
public class ConsoleConfiguration {

    private long id;

    private int serviceId;

    private double cpuTempLowerBound;

    private double cpuTempUpperBound;

    private double cpuLoadLowerBound;

    private double cpuLoadUpperBound;

    private double ramLowerBound;

    private double ramUpperBound;

    private double networkDlLowerBound;

    private double networkDlUpperBound;

    private double networkUlLowerBound;

    private double networkUlUpperBound;

    private double diskReadLowerBound;

    private double diskReadUpperBound;

    private double diskWriteLowerBound;

    private double diskWriteUpperBound;

    private double diskUsageLowerBound;

    private double diskUsageUpperBound;

    public ConsoleConfiguration() {

    }

    public ConsoleConfiguration(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        serviceId = resultSet.getInt(2);
        cpuTempLowerBound = resultSet.getDouble(3);
        cpuTempUpperBound = resultSet.getDouble(4);
        cpuLoadLowerBound = resultSet.getDouble(5);
        cpuLoadUpperBound = resultSet.getDouble(6);
        ramLowerBound = resultSet.getDouble(7);
        ramUpperBound = resultSet.getDouble(8);
        networkDlLowerBound = resultSet.getDouble(9);
        networkDlUpperBound = resultSet.getDouble(10);
        networkUlLowerBound = resultSet.getDouble(11);
        networkUlUpperBound = resultSet.getDouble(12);
        diskReadLowerBound = resultSet.getDouble(13);
        diskReadUpperBound = resultSet.getDouble(14);
        diskWriteLowerBound = resultSet.getDouble(15);
        diskWriteUpperBound = resultSet.getDouble(16);
        diskUsageLowerBound = resultSet.getDouble(17);
        diskUsageUpperBound = resultSet.getDouble(18);
    }

    @Override
    public String toString() {
        return "ConsoleConfiguration{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", cpuTempLowerBound=" + cpuTempLowerBound +
                ", cpuTempUpperBound=" + cpuTempUpperBound +
                ", cpuLoadLowerBound=" + cpuLoadLowerBound +
                ", cpuLoadUpperBound=" + cpuLoadUpperBound +
                ", ramLowerBound=" + ramLowerBound +
                ", ramUpperBound=" + ramUpperBound +
                ", networkDlLowerBound=" + networkDlLowerBound +
                ", networkDlUpperBound=" + networkDlUpperBound +
                ", networkUlLowerBound=" + networkUlLowerBound +
                ", networkUlUpperBound=" + networkUlUpperBound +
                ", diskReadLowerBound=" + diskReadLowerBound +
                ", diskReadUpperBound=" + diskReadUpperBound +
                ", diskWriteLowerBound=" + diskWriteLowerBound +
                ", diskWriteUpperBound=" + diskWriteUpperBound +
                ", diskUsageLowerBound=" + diskUsageLowerBound +
                ", diskUsageUpperBound=" + diskUsageUpperBound +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsoleConfiguration)) return false;

        ConsoleConfiguration that = (ConsoleConfiguration) o;

        if (id != that.id) return false;
        if (serviceId != that.serviceId) return false;
        if (Double.compare(that.cpuTempLowerBound, cpuTempLowerBound) != 0) return false;
        if (Double.compare(that.cpuTempUpperBound, cpuTempUpperBound) != 0) return false;
        if (Double.compare(that.cpuLoadLowerBound, cpuLoadLowerBound) != 0) return false;
        if (Double.compare(that.cpuLoadUpperBound, cpuLoadUpperBound) != 0) return false;
        if (Double.compare(that.ramLowerBound, ramLowerBound) != 0) return false;
        if (Double.compare(that.ramUpperBound, ramUpperBound) != 0) return false;
        if (Double.compare(that.networkDlLowerBound, networkDlLowerBound) != 0) return false;
        if (Double.compare(that.networkDlUpperBound, networkDlUpperBound) != 0) return false;
        if (Double.compare(that.networkUlLowerBound, networkUlLowerBound) != 0) return false;
        if (Double.compare(that.networkUlUpperBound, networkUlUpperBound) != 0) return false;
        if (Double.compare(that.diskReadLowerBound, diskReadLowerBound) != 0) return false;
        if (Double.compare(that.diskReadUpperBound, diskReadUpperBound) != 0) return false;
        if (Double.compare(that.diskWriteLowerBound, diskWriteLowerBound) != 0) return false;
        if (Double.compare(that.diskWriteUpperBound, diskWriteUpperBound) != 0) return false;
        if (Double.compare(that.diskUsageLowerBound, diskUsageLowerBound) != 0) return false;
        return Double.compare(that.diskUsageUpperBound, diskUsageUpperBound) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + serviceId;
        temp = Double.doubleToLongBits(cpuTempLowerBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cpuTempUpperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cpuLoadLowerBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cpuLoadUpperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ramLowerBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ramUpperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(networkDlLowerBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(networkDlUpperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(networkUlLowerBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(networkUlUpperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(diskReadLowerBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(diskReadUpperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(diskWriteLowerBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(diskWriteUpperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(diskUsageLowerBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(diskUsageUpperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public double getCpuTempLowerBound() {
        return cpuTempLowerBound;
    }

    public void setCpuTempLowerBound(double cpuTempLowerBound) {
        this.cpuTempLowerBound = cpuTempLowerBound;
    }

    public double getCpuTempUpperBound() {
        return cpuTempUpperBound;
    }

    public void setCpuTempUpperBound(double cpuTempUpperBound) {
        this.cpuTempUpperBound = cpuTempUpperBound;
    }

    public double getCpuLoadLowerBound() {
        return cpuLoadLowerBound;
    }

    public void setCpuLoadLowerBound(double cpuLoadLowerBound) {
        this.cpuLoadLowerBound = cpuLoadLowerBound;
    }

    public double getCpuLoadUpperBound() {
        return cpuLoadUpperBound;
    }

    public void setCpuLoadUpperBound(double cpuLoadUpperBound) {
        this.cpuLoadUpperBound = cpuLoadUpperBound;
    }

    public double getRamLowerBound() {
        return ramLowerBound;
    }

    public void setRamLowerBound(double ramLowerBound) {
        this.ramLowerBound = ramLowerBound;
    }

    public double getRamUpperBound() {
        return ramUpperBound;
    }

    public void setRamUpperBound(double ramUpperBound) {
        this.ramUpperBound = ramUpperBound;
    }

    public double getNetworkDlLowerBound() {
        return networkDlLowerBound;
    }

    public void setNetworkDlLowerBound(double networkDlLowerBound) {
        this.networkDlLowerBound = networkDlLowerBound;
    }

    public double getNetworkDlUpperBound() {
        return networkDlUpperBound;
    }

    public void setNetworkDlUpperBound(double networkDlUpperBound) {
        this.networkDlUpperBound = networkDlUpperBound;
    }

    public double getNetworkUlLowerBound() {
        return networkUlLowerBound;
    }

    public void setNetworkUlLowerBound(double networkUlLowerBound) {
        this.networkUlLowerBound = networkUlLowerBound;
    }

    public double getNetworkUlUpperBound() {
        return networkUlUpperBound;
    }

    public void setNetworkUlUpperBound(double networkUlUpperBound) {
        this.networkUlUpperBound = networkUlUpperBound;
    }

    public double getDiskReadLowerBound() {
        return diskReadLowerBound;
    }

    public void setDiskReadLowerBound(double diskReadLowerBound) {
        this.diskReadLowerBound = diskReadLowerBound;
    }

    public double getDiskReadUpperBound() {
        return diskReadUpperBound;
    }

    public void setDiskReadUpperBound(double diskReadUpperBound) {
        this.diskReadUpperBound = diskReadUpperBound;
    }

    public double getDiskWriteLowerBound() {
        return diskWriteLowerBound;
    }

    public void setDiskWriteLowerBound(double diskWriteLowerBound) {
        this.diskWriteLowerBound = diskWriteLowerBound;
    }

    public double getDiskWriteUpperBound() {
        return diskWriteUpperBound;
    }

    public void setDiskWriteUpperBound(double diskWriteUpperBound) {
        this.diskWriteUpperBound = diskWriteUpperBound;
    }

    public double getDiskUsageLowerBound() {
        return diskUsageLowerBound;
    }

    public void setDiskUsageLowerBound(double diskUsageLowerBound) {
        this.diskUsageLowerBound = diskUsageLowerBound;
    }

    public double getDiskUsageUpperBound() {
        return diskUsageUpperBound;
    }

    public void setDiskUsageUpperBound(double diskUsageUpperBound) {
        this.diskUsageUpperBound = diskUsageUpperBound;
    }
}