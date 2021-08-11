package com.yogurt.inflearn.beanfind;

import com.yogurt.inflearn.AppConfig;
import com.yogurt.inflearn.discount.DiscountPolicy;
import com.yogurt.inflearn.member.MemberRepository;
import com.yogurt.inflearn.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상이면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate(){

        assertThrows(NoUniqueBeanDefinitionException.class, ()->ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상이면, 빈 이름을 지정하면 된다")
    void findByBeanName(){
        MemberRepository memory = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memory).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 조회하기")
    void findAllBeanByType(){

        /** MemberRepository 클래스에 대한 것 모두 가져오는 함수 */
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for(String key: beansOfType.keySet()){
            System.out.printf("key = " + key + " value = " + beansOfType.get(key)+"\n");
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);


    }


    @Configuration
    static class SameBeanConfig{

        /** 메서드 이름이 다르고 반환되는 객체타입은 동일 */

        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }


    }

}
