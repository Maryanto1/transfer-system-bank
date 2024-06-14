package id.co.com.transfer_system.core.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.sql.DataSource;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class DataSourceConfig {

    @Value("${datasource.username}")
    String USERNAME;

    @Value("${datasource.password}")
    String PASSWORD;

    @Value("${datasource.url}")
    String URL;

    @Value("${datasource.poolsize}")
    int POOLSIZE;

    @Value("${datasource.minimum-idle}")
    int MINIMUMIDLE;

    @Value("${datasource.connection-timeout}")
    Long CONNECTIONTIMEOUT;

    @Bean
    public DataSource getDataSource() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        final String username = USERNAME;
        final String password = PASSWORD;
        final String url = URL;
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setConnectionTimeout(CONNECTIONTIMEOUT);
        config.setMaximumPoolSize(POOLSIZE);
        config.setMinimumIdle(MINIMUMIDLE);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);
        return ds;

    }

}
