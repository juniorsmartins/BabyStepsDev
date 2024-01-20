package microservice.micronoticias.config.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

@Configuration
public class ContentNegotiationYamlConfig extends AbstractJackson2HttpMessageConverter {

    protected ContentNegotiationYamlConfig() {
        super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL),
            MediaType.parseMediaType("application/x-yaml"));
    }
}

