package com.yogurt.inflearn.beanDefinition;

import com.yogurt.inflearn.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    GenericXmlApplicationContext ag = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("Annotation기반 빈 설정 메타정보 확인")
    void findApplicationBean(){
        String[] definitionNames = ac.getBeanDefinitionNames();

        for(String name: definitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(name);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinition Name : "+name+ " beanDefinition="+beanDefinition);
            }
        }
    }

    @Test
    @DisplayName("xml기반 빈 설정 메타정보 확인")
    void findApplicationBeanByXml(){
        String[] definitionNames = ag.getBeanDefinitionNames();

        for(String name: definitionNames){
            BeanDefinition beanDefinition = ag.getBeanDefinition(name);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinition Name : "+name+ " beanDefinition="+beanDefinition);
            }
        }
    }

}
