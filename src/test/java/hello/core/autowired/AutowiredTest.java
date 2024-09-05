package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{
        //메소드 자체가 호출되지 않음
    @Autowired(required = false)
    public void setNoBean1(Member member1){
        System.out.println("member1 = " + member1);
    }
    //아하 다른 브랜치에서 변경 내용을 반영하지 못하도록 하려면 commit을 해줘야하는거구나..! 한번 해볼겡

    @Autowired
    public void setNoBean2(@Nullable Member member2){
        System.out.println("member2 = " + member2);
    }

    @Autowired
    public void setNoBean3(Optional<Member> member3){
        System.out.println("member3 = " + member3);
    }
    }
}
