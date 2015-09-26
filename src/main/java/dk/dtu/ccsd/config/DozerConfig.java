package dk.dtu.ccsd.config;

/**
 * Created by xiuli on 3/2/15.
 */
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;

@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapper getMapper() {
        return new DozerBeanMapper(
                Arrays.asList("dozer-global-configuration.xml", "dozer-mapping.xml")
        );
    }

}