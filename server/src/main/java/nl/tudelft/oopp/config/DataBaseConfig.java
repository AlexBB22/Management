package nl.tudelft.oopp.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableJpaRepositories
@PropertySource("application.properties")
@EnableTransactionManagement

public class DataBaseConfig {

//    @Bean
//    public DataSource getDataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
//        ds.setUrl("jdbc:mysql://projects-db.ewi.tudelft.nl:3306/projects_OOPPGroup4");
//        ds.setUsername("pu_OOPPGroup4");
//        ds.setPassword("cg5EQkKNHN08");
//        return ds;
//    }

    @Bean(name = "mySqlDataSource")
    @Primary
    public DataSource mySqlDataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://projects-db.ewi.tudelft.nl:3306/projects_OOPPGroup4");
        dataSourceBuilder.username("pu_OOPPGroup4");
        dataSourceBuilder.password("cg5EQkKNHN08");
        return dataSourceBuilder.build();
    }

}
