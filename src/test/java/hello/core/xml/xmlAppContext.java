package hello.core.xml;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class xmlAppContext {


@Test
void xmlAppContext(){
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    MemberService memberService=ac.getBean("memberService", MemberService.class);
    assertThat(memberService).isInstanceOf(MemberService.class);


    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
        BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

        //ROLE_INFRASTRUCTURE: Spring이 내부에서 사용하는 Bean
        // ROLE_APPLICATION: 외부 라이브러리로부터 생성된 Bean이거나 내가 만든 Bean
        if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
            //bean이름으로 bean객체(인스턴스)를 조회한다.
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + ", object = " + bean);
        }
    }


}
}
