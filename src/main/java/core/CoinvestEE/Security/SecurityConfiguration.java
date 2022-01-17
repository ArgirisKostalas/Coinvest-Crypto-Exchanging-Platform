package core.CoinvestEE.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;





//Configure the Security context of our application
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                //store the password as BCrypt
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select client_username, client_password, enabled from client where client_username=?")
                .authoritiesByUsernameQuery("select client_username, role from client where client_username=?");

    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .headers()
                .xssProtection()
                .and()
                .contentSecurityPolicy("script-src 'self'");

        //Configures the request permissions and roles management
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/resources/**", "/static/**").permitAll()
                .antMatchers("/client/**").hasRole("ADMIN")
                .antMatchers("/dashboard", "/dashboard/refresh", "/market", "/dashboard/market/tx").hasAnyRole("ADMIN", "USER")




                //performs the login action
                .and()
                .formLogin().loginPage("/loginform").loginProcessingUrl("/loginform").permitAll()

                .defaultSuccessUrl("/dashboard", true)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .logoutSuccessUrl("/");


    }

}
