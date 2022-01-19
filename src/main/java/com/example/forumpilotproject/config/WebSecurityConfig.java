package com.example.forumpilotproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //DataSource генерируется спрингом, нужен, чтобы менеджер мог входить в БД и искать там роли пользователей
    @Autowired
    private DataSource dataSource;


    //для всех, кто заходит требуем авторизацию
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()//включаем форму логина
                .loginPage("/login")//логин находится тут
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)//менеджер мог входить в БД и искать там пользователей и их роли
                .passwordEncoder(NoOpPasswordEncoder.getInstance())//passwordEncoder - делает так, чтобы пароли не хранились в явном виде
                .usersByUsernameQuery("select username, password, true from usr where username=?")//система могла найти по его имени
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");
        //получаем список пользователей с их ролями- т.е. из таблицы usr и присоединенной к ней таблицы user_role соединенный через поля user_id (ur.user) и id(u.id)
       // выбираем поля username и имя roles (ur.roles)
   }
}