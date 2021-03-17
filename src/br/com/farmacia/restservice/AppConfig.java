package br.com.farmacia.restservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.MapperFeature;

@Configuration
@ComponentScan("com.journaldev.spring")
public class AppConfig {

	@Bean
	public RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
	    restTemplate.getMessageConverters().add(0, createMappingJacksonHttpMessageConverter());
	    return restTemplate;
	}
	
	private MappingJackson2HttpMessageConverter createMappingJacksonHttpMessageConverter() {
	      
	    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	    converter.setObjectMapper(new ObjectMapper());
	    return converter;
	}
}