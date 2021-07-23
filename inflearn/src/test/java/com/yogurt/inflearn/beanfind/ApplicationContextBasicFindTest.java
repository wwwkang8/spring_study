package com.yogurt.inflearn.beanfind;

import com.yogurt.inflearn.AppConfig;
import com.yogurt.inflearn.member.MemberService;
import com.yogurt.inflearn.member.MemberServiceImpl;
import com.yogurt.inflearn.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    /** Annotation기반 스프링컨테이너 생성. AppConfig.class에 등록된 빈을 생성 */
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService : "+memberService);
        System.out.println("memberService.getClass() : " + memberService.getClass());

        /** memberService 빈이 MemberServiceImpl.class 타입과 동일한가? */
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입만으로 조회")
    void findBeanByType(){

        /** MemberService.class 타입으로 조회. 하지만 동일한 타입의 빈이 생성되었을 때는 찾기 애매 */
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService : "+memberService);
        System.out.println("memberService.getClass() : " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체타입으로 조회")
    void findBeanByName2(){

        /** MemberService 인터페이스 타입이 아닌 구현체인 MemberServiceImpl로 빈을 검색.
         * 구현체를 기반으로 찾는 것은 바람직하지 않음 */
        MemberService memberService = ac.getBean(MemberServiceImpl.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNamex(){
        /** xxxx로 생성된 bean이 없기 때문에 에러 발생 */
       //MemberService xxxx = ac.getBean("xxxx", MemberService.class);

        /** xxxx 빈을 조회할 때 아래 exception이 발생해야 정상 */
        assertThrows(NoSuchBeanDefinitionException.class,
                ()->ac.getBean("xxxx", MemberService.class));
    }

}
