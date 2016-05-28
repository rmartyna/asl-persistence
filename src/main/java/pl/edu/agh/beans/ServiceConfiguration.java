package pl.edu.agh.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

//TODO more parameters
public class ServiceConfiguration {

    private long id;

    private long serviceId;

    private String mode;

    private int pollRate;

    public ServiceConfiguration() {

    }

    public ServiceConfiguration(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        serviceId = resultSet.getInt(2);
        mode = resultSet.getString(3);
        pollRate = resultSet.getInt(4);
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
        return mode != null ? mode.equals(that.mode) : that.mode == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (serviceId ^ (serviceId >>> 32));
        result = 31 * result + (mode != null ? mode.hashCode() : 0);
        result = 31 * result + pollRate;
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
}
