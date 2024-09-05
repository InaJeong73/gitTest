package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    OrderService orderService;
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig=new AppConfig();
        orderService=appConfig.orderService();
        memberService=appConfig.memberService();
    }
    @Test
    void createOrder(){
        long memberId=1L;
        Member member=new Member(memberId,"정인아", Grade.VIP);
        memberService.join(member);
        Order order=orderService.createOrder(memberId,"모자",20000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(2000);
    }
}
