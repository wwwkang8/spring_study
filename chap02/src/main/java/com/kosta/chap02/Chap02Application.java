package com.kosta.chap02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/** @SpringBootApplication
public class Chap02Application {

    public static void main(String[] args) {

        SpringApplication.run(Chap02Application.class, args);

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
                "classpath:config.xml", "classpath:spring-member.xml", "spring-board.xml", "spring-datasource.xml");

        AuthenticationService authSvc = ctx.getBean("authenticationService", AuthenticationService.class);


        runAuthAndCatchAuthEx(authSvc, "bkchoi", "1111");
        runAuthAndCatchAuthEx(authSvc, "bkchoi", "111111");
        runAuthAndCatchAuthEx(authSvc, "bkchoi", "1111");

//        try{
//            authSvc.authenticate("bkchoi", "1234");
//        }catch(UserNotFoundException ex){
//
//        }

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

} */

@SpringBootApplication
public class Chap02Application {

    public static void main(String[] args) {

        SpringApplication.run(Chap02Application.class, args);

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(Config.class);

        AuthenticationService authSvc
                = ctx.getBean("authenticationService", AuthenticationService.class);
        authSvc.authenticate("bkchoi", "1234");

        PasswordChangeService pwChgSvc = ctx.getBean(PasswordChangeService.class);
        pwChgSvc.changePassword("bkchoi", "1234", "5678");

        ctx.close();

    }
}

