package com.kosta.chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Config {

    @Bean(name="user1") //Bean 객체 생성시 식별 이름을 이렇게 설정가능
    public User user1(){
        return new User("bkchoi", "1234");
    }

    @Bean(name="user2") //Bean 객체 생성시 식별 이름을 이렇게 설정가능
    public User user(){
        return new User("madvirus", "qwer");
    }

    @Bean
    public UserRepository userRepository(){
        UserRepository userRepo = new UserRepository();
        userRepo.setUsers(Arrays.asList(user1(), user()));
        return userRepo;
    }

    @Bean
    public PasswordChangeService pwChangeSvc(){
        return new PasswordChangeService(userRepository());
    }

    @Bean
    public AuthFailLogger authFailLogger(){
        AuthFailLogger logger = new AuthFailLogger();
        logger.setThreshold(2);
        return logger;
    }

    @Bean
    public AuthenticationService authenticationService(){
        AuthenticationService authSvc = new AuthenticationService();
        authSvc.setFailLogger(authFailLogger());
        authSvc.setUserRepository(userRepository());
        return authSvc;
    }




}
