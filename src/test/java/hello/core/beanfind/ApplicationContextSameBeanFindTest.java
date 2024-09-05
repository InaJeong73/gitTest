package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상이면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(DiscountPolicy.class));

    }
    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByName(){
        DiscountPolicy discountPolicy1=ac.getBean("fixDiscountPolicy",DiscountPolicy.class);
        assertThat(discountPolicy1).isInstanceOf(DiscountPolicy.class);


        DiscountPolicy discountPolicy2=ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertThat(discountPolicy2).isInstanceOf(DiscountPolicy.class);

    }
    
    @Test
    @DisplayName("특정 타입을 모두 조회하라")
    void findAllBeanByType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = "+key+", value = "+beansOfType.get(key));
        }
        System.out.println("beansOfType = "+beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }



    @Configuration
    static class SameBeanConfig{
        @Bean
        DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }

        @Bean
        DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
    }
}
