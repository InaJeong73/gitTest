package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component//memoryMemberRepository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member>store=new HashMap<>();

    @Override
    public void save(Member member) {
    store.put(member.getId(),member);
    }

    @Override
    public Member findbyId(Long memberId) {
       return store.get(memberId);
    }
}


//실무에서는 동시성 이슈 방지를 위해 ConcurrentHashMap을 이용해야 됌. 자세한건 따로 공부해보자.