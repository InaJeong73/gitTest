package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    int discount(Member member,int price); //할인금액 반환
}
