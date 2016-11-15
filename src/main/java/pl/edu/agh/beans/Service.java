package pl.edu.agh.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This software may be modified and distributed under the terms
 *  of the BSD license.  See the LICENSE.txt file for details.
 */

public class Service {

    private long id;

    private String host;

    private int port;

    private String description;

    private String password;

    public Service() {

    }

    public Service(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        host = resultSet.getString(2);
        port = resultSet.getInt(3);
        description = resultSet.getString(4);
        password = resultSet.getString(5);
    }

    public Service(String host, int port, String description, String password) {
        this.host = host;
        this.port = port;
        this.description = description;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;

        Service service = (Service) o;

        if (id != service.id) return false;
        if (port != service.port) return false;
        if (host != null ? !host.equals(service.host) : service.host != null) return false;
        if (description != null ? !description.equals(service.description) : service.description != null) return false;
        return password != null ? password.equals(service.password) : service.password == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", description='" + description + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
