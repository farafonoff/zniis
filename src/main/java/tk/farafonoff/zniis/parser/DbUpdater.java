package tk.farafonoff.zniis.parser;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Artem on 17.04.2015.
 */
public class DbUpdater {
    String propPath = "numbers.properties";
    Properties propFile = new Properties();
    JdbcTemplate jt = new JdbcTemplate();
    BasicDataSource dataSource = new BasicDataSource();

    public void init() throws IOException {
        propFile.clear();
        propFile.load(new FileInputStream(propPath));
        dataSource.setDriverClassName(propFile.getProperty("driver", "org.firebirdsql.jdbc.FBDriver"));
        dataSource.setUrl(propFile.getProperty("database", "jdbc:firebirdsql:localhost/3050:adminpanel"));
        dataSource.setUsername(propFile.getProperty("username"));
        dataSource.setPassword(propFile.getProperty("password"));
        jt = new JdbcTemplate(dataSource);
    }

    public String update(String number, Operator op) {
        return jt.queryForObject("select logline from UPDATEPORTNUM(?,?)", String.class, number, op.name);
    }
}
