// Copyright (c) 1998-2018 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2018-03-30, derrick.liang, creation
// ============================================================================
package com.star.demo.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author derrick.liang
 */

@Configuration
@EnableTransactionManagement
//@PropertySource("classpath:/dbConfig.json")
public class BeanConfig {
    //    @Autowired
    //    private Environment environment;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream("dbConfig.json");
        final Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        final String content = scanner.hasNext() ? scanner.next() : "";
        JSONObject jsonObject = new JSONObject(content);
        Properties properties = new Properties();
        properties.putAll(jsonObject.toMap());
        return new HikariDataSource(new HikariConfig(properties));
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
}
