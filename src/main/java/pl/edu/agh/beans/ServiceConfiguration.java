package pl.edu.agh.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This software may be modified and distributed under the terms
 *  of the BSD license.  See the LICENSE.txt file for details.
 */

public class ServiceConfiguration {

    private long id;

    private long serviceId;

    private String mode;

    private int pollRate;

    private int cpuFrequency;

    private int cpuEnabled;

    private int diskFrequency;

    private int diskEnabled;

    private int memoryFrequency;

    private int memoryEnabled;

    private int networkFrequency;

    private int networkEnabled;

    private int syslogFrequency;

    private int syslogEnabled;

    private String syslogList;

    public ServiceConfiguration() {

    }

    public ServiceConfiguration(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        serviceId = resultSet.getInt(2);
        mode = resultSet.getString(3);
        pollRate = resultSet.getInt(4);
        cpuFrequency = resultSet.getInt(5);
        cpuEnabled = resultSet.getInt(6);
        diskFrequency = resultSet.getInt(7);
        diskEnabled = resultSet.getInt(8);
        memoryFrequency = resultSet.getInt(9);
        memoryEnabled = resultSet.getInt(10);
        networkFrequency = resultSet.getInt(11);
        networkEnabled = resultSet.getInt(12);
        syslogFrequency = resultSet.getInt(13);
        syslogEnabled = resultSet.getInt(14);
        syslogList = resultSet.getString(15);
    }

    public ServiceConfiguration(long serviceId, String mode, int pollRate) {
        this.serviceId = serviceId;
        this.mode = mode;
        this.pollRate = pollRate;
    }


    @Override
    public String toString() {
        return "ServiceConfiguration{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", mode='" + mode + '\'' +
                ", pollRate=" + pollRate +
                ", cpuFrequency=" + cpuFrequency +
                ", cpuEnabled=" + cpuEnabled +
                ", diskFrequency=" + diskFrequency +
                ", diskEnabled=" + diskEnabled +
                ", memoryFrequency=" + memoryFrequency +
                ", memoryEnabled=" + memoryEnabled +
                ", networkFrequency=" + networkFrequency +
                ", networkEnabled=" + networkEnabled +
                ", syslogFrequency=" + syslogFrequency +
                ", syslogEnabled=" + syslogEnabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceConfiguration)) return false;

        ServiceConfiguration that = (ServiceConfiguration) o;

        if (id != that.id) return false;
        if (serviceId != that.serviceId) return false;
        if (pollRate != that.pollRate) return false;
        if (cpuFrequency != that.cpuFrequency) return false;
        if (cpuEnabled != that.cpuEnabled) return false;
        if (diskFrequency != that.diskFrequency) return false;
        if (diskEnabled != that.diskEnabled) return false;
        if (memoryFrequency != that.memoryFrequency) return false;
        if (memoryEnabled != that.memoryEnabled) return false;
        if (networkFrequency != that.networkFrequency) return false;
        if (networkEnabled != that.networkEnabled) return false;
        if (syslogFrequency != that.syslogFrequency) return false;
        if (syslogEnabled != that.syslogEnabled) return false;
        return mode != null ? mode.equals(that.mode) : that.mode == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (serviceId ^ (serviceId >>> 32));
        result = 31 * result + (mode != null ? mode.hashCode() : 0);
        result = 31 * result + pollRate;
        result = 31 * result + cpuFrequency;
        result = 31 * result + cpuEnabled;
        result = 31 * result + diskFrequency;
        result = 31 * result + diskEnabled;
        result = 31 * result + memoryFrequency;
        result = 31 * result + memoryEnabled;
        result = 31 * result + networkFrequency;
        result = 31 * result + networkEnabled;
        result = 31 * result + syslogFrequency;
        result = 31 * result + syslogEnabled;
        return result;
    }

    public String getSyslogList() {
        return syslogList;
    }

    public void setSyslogList(String syslogList) {
        this.syslogList = syslogList;
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getPollRate() {
        return pollRate;
    }

    public void setPollRate(int pollRate) {
        this.pollRate = pollRate;
    }

    public int getCpuFrequency() {
        return cpuFrequency;
    }

    public void setCpuFrequency(int cpuFrequency) {
        this.cpuFrequency = cpuFrequency;
    }

    public int getCpuEnabled() {
        return cpuEnabled;
    }

    public void setCpuEnabled(int cpuEnabled) {
        this.cpuEnabled = cpuEnabled;
    }

    public int getDiskFrequency() {
        return diskFrequency;
    }

    public void setDiskFrequency(int diskFrequency) {
        this.diskFrequency = diskFrequency;
    }

    public int getDiskEnabled() {
        return diskEnabled;
    }

    public void setDiskEnabled(int diskEnabled) {
        this.diskEnabled = diskEnabled;
    }

    public int getMemoryFrequency() {
        return memoryFrequency;
    }

    public void setMemoryFrequency(int memoryFrequency) {
        this.memoryFrequency = memoryFrequency;
    }

    public int getMemoryEnabled() {
        return memoryEnabled;
    }

    public void setMemoryEnabled(int memoryEnabled) {
        this.memoryEnabled = memoryEnabled;
    }

    public int getNetworkFrequency() {
        return networkFrequency;
    }

    public void setNetworkFrequency(int networkFrequency) {
        this.networkFrequency = networkFrequency;
    }

    public int getNetworkEnabled() {
        return networkEnabled;
    }

    public void setNetworkEnabled(int networkEnabled) {
        this.networkEnabled = networkEnabled;
    }

    public int getSyslogFrequency() {
        return syslogFrequency;
    }

    public void setSyslogFrequency(int syslogFrequency) {
        this.syslogFrequency = syslogFrequency;
    }

    public int getSyslogEnabled() {
        return syslogEnabled;
    }

    public void setSyslogEnabled(int syslogEnabled) {
        this.syslogEnabled = syslogEnabled;
    }
}
