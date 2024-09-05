package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired  //ac.getBean(MemberRepository.class), 의존관계 자동주입
    public MemberServiceImpl(MemberRepository memberRepository) {
       this.memberRepository=memberRepository;
    }

    @Override
    public void join(Member member) {
    memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findbyId(memberId);
    }

    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}





//DIP를 위반하고 있다.
//DIP위반: 의존역전법칙, 하나의 클라이언트 클래스가 interface만 이용해야지, 그 구현체까지 이용하면 안된다.
//OCP위반: