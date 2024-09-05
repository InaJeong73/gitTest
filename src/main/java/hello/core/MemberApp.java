package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService=applicationContext.getBean("MemberService",MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember=memberService.findMember(member.getId());
        System.out.println("member = " + member.getName());
        System.out.println("findMember="+findMember.getName());

//        MemberService memberService=new MemberServiceImpl();//직접 찾아오는 방식

        //Spring 에서는 모든게 ApplicationContext에서 시작됌. SpringContatiner라고 보면 됌. 이 친구가 @Bean이라는 것을 관리해줌.
        //AppConfig에 있는 환경설정 정보를 가지고 Spring이 각각의 Bean을 Spring 컨테이너에 넣고 객체 생성하여 관리해줌.
        //Spring을 이용하여 설정클래스 찾아오고 Spring 컨테이너에서 관리하도록 하기
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        //이제 내가 등록한 Bean을 꺼내줄건데, 꺼낼 Bean의 메서드명과 어떤 타입을 꺼낼것인지 명시하자.
//        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
//        Member member = new Member(1L, "memberA", Grade.VIP);
//        memberService.join(member);
//
//        Member findMember=memberService.findMember(member.getId());
//
//        System.out.println("member = " + member.getName());
//        System.out.println("findMember="+findMember.getName());

    }
}
