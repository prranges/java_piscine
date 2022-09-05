package edu.school21.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmbeddedDataSourceTest {
    DataSource dataSource;

    @BeforeEach
    void init() {
        EmbeddedDatabaseBuilder db = new EmbeddedDatabaseBuilder();
        dataSource = db.setType(HSQL).addScripts("schema.sql", "data.sql").build();
    }

    @Test
    void testConnection() throws SQLException {
        Assertions.assertNotNull(dataSource.getConnection());
    }
}
