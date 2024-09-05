package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderServiceImpl implements OrderService{
//    @Autowired <-필드 주입
    private  MemberRepository memberRepository;
//    @Autowired
    private  DiscountPolicy discountPolicy;


    //필드값을 setter함수를 통해서 보통 수정하는데, 함수에 @Autowired를 붙이면 자동 주입됌.
//    @Autowired <- 수정자 주입
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy2 = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository2 = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }

    //    생성자 주입, 생성자가 하나 있으면 Autowired 애노테이션 없이도 자동으로 주입시켜줌.
//    @Autowired <- 생성자 주입
//    생성자 주입이 선행된 후에 수정자나 함수 주입이 진행됌.
    public OrderServiceImpl(MemberRepository memberRepository,DiscountPolicy discountPolicy){
        System.out.println("memberRepository1 = " + memberRepository);
        System.out.println("discountPolicy1 = " + discountPolicy);
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
    }

    @Autowired//<-일반 메서드 주입
    public void init(MemberRepository memberRepository,DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member=memberRepository.findbyId(memberId);
        int discountPrice=discountPolicy.discount(member,itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}



