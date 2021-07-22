package com.yogurt.inflearn;

import com.yogurt.inflearn.discount.DiscountPolicy;
import com.yogurt.inflearn.discount.RateDiscountPolicy;
import com.yogurt.inflearn.member.MemberRepository;
import com.yogurt.inflearn.member.MemberService;
import com.yogurt.inflearn.member.MemberServiceImpl;
import com.yogurt.inflearn.member.MemoryMemberRepository;
import com.yogurt.inflearn.order.OrderService;
import com.yogurt.inflearn.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl();
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl();
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
