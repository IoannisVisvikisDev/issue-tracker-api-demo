package ioannis.visvikis.issuetracker.demo;


import ioannis.visvikis.issuetracker.demo.security.JwtAuthFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class IssueTrackerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueTrackerDemoApplication.class, args);
	}


	//Register custom request filter for JWT and secured endpoints
	@Bean
	public FilterRegistrationBean<JwtAuthFilter> filterRegistrationBean(){
		FilterRegistrationBean<JwtAuthFilter> filterBean = new FilterRegistrationBean<>();
		filterBean.setFilter(new JwtAuthFilter());
		filterBean.addUrlPatterns(new String[] {"/issues/*", "/users"});
		return filterBean;
	}

}
