
  package com.demo.spring;
  
  import org.springframework.context.annotation.Configuration; import
  org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.config.annotation.web.configuration.
  WebSecurityConfigurerAdapter;
  
  @SuppressWarnings("deprecation")
  
  @Configuration
  
  public class ProjectConfig extends WebSecurityConfigurerAdapter{
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {

	          http.csrf().disable()     //  <<------- PUT THIS IN YOUR CODE
	          .authorizeRequests()
	              .antMatchers("/css/**", "/primeiro_acesso/**", "/upload", "/testes/upload", "/files/**").permitAll()
	              .and()
	          .formLogin()
	              .loginPage("/login")
	              .permitAll()
	              .and()
	          .logout()
	              .permitAll();
	  }
		/*
		 * @Override protected void configure(HttpSecurity http) throws Exception {
		 * http.antMatcher("/**") .authorizeRequests() .antMatchers("/") .permitAll()
		 * .anyRequest().authenticated().and() .oauth2Login(); }
		 */
  
  
  
  }
 
