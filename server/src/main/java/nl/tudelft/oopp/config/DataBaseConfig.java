package nl.tudelft.oopp.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableJpaRepositories
@PropertySource("application.properties")
@EnableTransactionManagement

public class DataBaseConfig {

    /**
     * Method to setUp datasource with MySQL database.
     * @return a datasource that can be used by Hibernate.
     */
    @Bean(name = "mySqlDataSource")
    @Primary
    public DataSource mySqlDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        //or projects_OOPPGroup4Test for Test DB        projects_OOPPGroup4 for Real DB
        dataSourceBuilder.url("jdbc:mysql://projects-db.ewi.tudelft.nl:3306/projects_OOPPGroup4Test");

        //pu_ylnfCNSj8WSfx for Test DB      pu_OOPPGroup4 for Real DB
        dataSourceBuilder.username("pu_ylnfCNSj8WSfx");
        //yOzgeAlJDkOE for Test DB       cg5EQkKNHN08 for Real DB
        dataSourceBuilder.password("yOzgeAlJDkOE");
        return dataSourceBuilder.build();
    }

}
