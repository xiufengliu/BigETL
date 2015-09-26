package dk.dtu.ccsd.config;

/**
 * Created by xiuli on 3/2/15.
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:config.properties"})
@Import(DatabaseConfig.class)
@ComponentScan(basePackages = {"dk.dtu.ccsd.core.service,dk.dtu.ccsd.core.dao,dk.dtu.ccsd.exception"})
public class AppConfig {


}