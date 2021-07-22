package com.yogurt.inflearn.order;

import com.yogurt.inflearn.discount.DiscountPolicy;
import com.yogurt.inflearn.discount.FixDiscountPolicy;
import com.yogurt.inflearn.discount.RateDiscountPolicy;
import com.yogurt.inflearn.member.Member;
import com.yogurt.inflearn.member.MemberRepository;
import com.yogurt.inflearn.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private  DiscountPolicy discountPolicy;


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}
