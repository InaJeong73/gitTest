package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    //iter,souv
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //SpringContainer 내에 내가 수동으로 등록한 Bean뿐만 아니라 Spring을 확장할 때 자동으로 생성된 Bean이나 AppConfig Bean도 같이 들어가있다.
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }


    //스프링이 내부에서 사용하는 빈은 제외하고, 내가 등록한 빈만 출력해보자.
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        //스프링에 등록된 모든 빈 이름을 조회한다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //ROLE_INFRASTRUCTURE: Spring이 내부에서 사용하는 Bean
            // ROLE_APPLICATION: 외부 라이브러리로부터 생성된 Bean이거나 내가 만든 Bean
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                //bean이름으로 bean객체(인스턴스)를 조회한다.
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}