package scan;


import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService=ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl orderService=ac.getBean("orderServiceImpl", OrderServiceImpl.class);
        System.out.println("orderService.getMemberRepository() : "+orderService.getMemberRepository());
    }
}
