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

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest  {
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(TestConfig.class);
    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 빈 이름으로 조회하면 된다.")
    void findBeanByParentTypeBeanName(){

        DiscountPolicy rateDiscountPolicy=ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);

        DiscountPolicy fixDiscountPolicy=ac.getBean("fixDiscountPolicy",DiscountPolicy.class);
        assertThat(fixDiscountPolicy).isInstanceOf(DiscountPolicy.class);

    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 특정 하위 타입으로 조회하면 된다.")
    void findBeanBySubType(){

        DiscountPolicy rateDiscountPolicy=ac.getBean(RateDiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);

        DiscountPolicy fixDiscountPolicy=ac.getBean(FixDiscountPolicy.class);
        assertThat(fixDiscountPolicy).isInstanceOf(FixDiscountPolicy.class);

    }


    @Test
    @DisplayName("부모 타입으로 자식 타입 빈들 전부 다 조회")
    void findBeansByParentType(){

        Map<String,DiscountPolicy> discountPolicies=ac.getBeansOfType(DiscountPolicy.class);
        assertThat(discountPolicies.size()).isEqualTo(2);
        for (String key : discountPolicies.keySet()) {
            System.out.println("key : "+key+" , value : "+discountPolicies.get(key));
        }
    }


    @Test
    @DisplayName("클래스들의 최상위 타입인 Object 타입으로 전부 다 조회")
    void findBeansByObjectType(){
        Map<String,Object> allBeans=ac.getBeansOfType(Object.class);
        for (String key : allBeans.keySet()) {
            System.out.println("key : "+key+" , value : "+allBeans.get(key));
        }
    }

    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
