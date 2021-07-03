package com.kosta.chap02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.GenericXmlApplicationContext;

@SpringBootApplication
public class Chap02Application {

    public static void main(String[] args) {

        SpringApplication.run(Chap02Application.class, args);

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:config.xml");

        AuthenticationService authSvc = ctx.getBean("authenticationService", AuthenticationService.class);


        runAuthAndCatchAuthEx(authSvc, "jake", "1234");
        runAuthAndCatchAuthEx(authSvc, "jake", "111111");
        runAuthAndCatchAuthEx(authSvc, "jake", "11111111");

        try{
            authSvc.authenticate("jake", "1111");
        }catch(UserNotFoundException ex){

        }

        authSvc.authenticate("bkchoi", "1234");
        PasswordChangeService pwChgSvc = ctx.getBean(PasswordChangeService.class);
        pwChgSvc.changePassword("bkchoi", "1234", "5678");
        runAuthAndCatchAuthEx(authSvc, "bkchoi", "1234");
        authSvc.authenticate("bkchoi", "5678");
        ctx.close();


    }

    private static void runAuthAndCatchAuthEx(AuthenticationService authSvc, String userId, String password){
        try{
            authSvc.authenticate(userId, password);
        }catch(AuthException ex){

        }
    }

}
