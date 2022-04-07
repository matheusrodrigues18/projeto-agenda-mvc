package br.com.cotiinformatica.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.cotiinformatica.interfaces.IScheduleRepository;
import br.com.cotiinformatica.interfaces.IUsuarioRepository;
import br.com.cotiinformatica.repositories.ScheduleRepository;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Configuration
@ComponentScan(basePackages="br.com.cotiinformatica")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/COTIDB05?useTimezone=true"
				+ "&serverTimezone=UTC&useSSL=false");	
		dataSource.setUsername("root");
		dataSource.setPassword("coti");
		return dataSource;
	}
	
	@Bean
	public IUsuarioRepository getUsuarioRepository() {
		return new UsuarioRepository(getDataSource());
	}
	
	@Bean
	public IScheduleRepository getTarefaRepository() {
		return new ScheduleRepository(getDataSource());
	}
}
