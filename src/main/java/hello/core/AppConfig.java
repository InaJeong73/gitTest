package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Bean memberService -> new MemoryMemberRepository()
//@Bean orderService -> new MemoryMemberRepository()


//생성자를 통해서 어떤 구현 객체를 주입할지는 오직 여기서 결정한다.
//application의 구성정보를 담당한다는 애노테이션, 스프링에서는 이러한 설정정보를 관리하는 클래스에 이 어노테이션을 붙임.
@Configuration
public class AppConfig {

    //Bean 애노테이션을 붙이면, 키는 메소드명,밸류는 반환하는 객체 인스턴스로 해서  Spring 컨테이터에 등록됌.
    //꺼낼때는 getBean함수에 메소드명이나 반환타입 줘서 꺼내면 됌.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }


}
