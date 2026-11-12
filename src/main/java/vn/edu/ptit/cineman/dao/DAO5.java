package vn.edu.ptit.cineman.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import vn.edu.ptit.cineman.config.Database5;

public abstract class DAO5 {
    private final DataSource dataSource;

    protected DAO5() { this.dataSource = null; }
    protected DAO5(DataSource dataSource) { this.dataSource = dataSource; }

    protected Connection connection() throws SQLException {
        return dataSource == null ? Database5.connect() : dataSource.getConnection();
    }
}
