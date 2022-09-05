package edu.school21.repositories;

import edu.school21.models.Product;
import edu.school21.numbers.NumberWorker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

public class ProductsRepositoryJdbcImplTest {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0L, "Still water", 60),
            new Product(1L, "Sparkling water", 65),
            new Product(2L, "Lemonade", 90),
            new Product(3L, "Orange juice", 130),
            new Product(4L, "Green tee", 100),
            new Product(5L, "Black coffee", 120)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(0L, "Still water", 60);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(2L, "Coca cola", 110);
    final Product EXPECTED_SAVED_PRODUCT = new Product(5L, "Black tee", 100);

    ProductsRepository productsRepository;
    EmbeddedDatabase dataSource;

    @BeforeEach
    void init() {
        EmbeddedDatabaseBuilder db = new EmbeddedDatabaseBuilder();
        dataSource = db.setType(EmbeddedDatabaseType.HSQL).addScripts("schema.sql", "data.sql").build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void testConnection() throws SQLException {
        Assertions.assertNotNull(dataSource.getConnection());
    }

    @Test
    void testFindAll() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    void testFindById() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(0L).get());
    }

    @Test
    void testUpdate()  throws SQLException {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(productsRepository.findById(2L).get(), EXPECTED_UPDATED_PRODUCT);
    }

    @Test
    void testSave()  throws SQLException {
        productsRepository.save(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(new Product(5L, "Black tee", 100), EXPECTED_SAVED_PRODUCT);
    }

    @Test
    void testDelete()  throws SQLException {
        productsRepository.delete(1L);
        Assertions.assertThrows(RuntimeException.class, () -> productsRepository.findById(1L));
    }

    @AfterEach
    void close() {
        dataSource.shutdown();
    }
}
