package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        MemberService memberService=new MemberServiceImpl(null);//회원가입, 조회
//        OrderService orderService=new OrderServiceImpl(null,null);//상품 주문등록
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService=appConfig.memberService();
//        OrderService orderService=appConfig.orderService();







        ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);//Appconfig에 있는 설정들을 컨테이너에 등록
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);


        Long memberId=1L;
        Member member=new Member(memberId,"정인아", Grade.VIP);
        memberService.join(member);
        Order order=orderService.createOrder(memberId,"모자",12000);
        System.out.println("order = "+order);
        System.out.println("order.calculatePrice() = "+order.calculatePrice());
    }
}
