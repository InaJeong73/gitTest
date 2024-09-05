package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//Component 애노테이션이 붙은 클래스를 찾아서 다 스프링 빈으로 자동 등록해준다.
//basePackages로 스캔하는 시작하는 위치를 한개 이상 지정할 수 있다.
// basePackageClasses는 ComponentScan을 지정한 클래스가 위치한 패키지를 탐색 시작 위치로 정한다.

//이런거 설치하지 않았을때는 기본적으로 설정클래스가 위치한 패키지가 시작 위치가 된다.
//권장하는 방법은 이런 basePackage 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트의 최상단에 두는 것
@ComponentScan(
           basePackages = "hello.core",
            excludeFilters=@ComponentScan.Filter(type= FilterType.ANNOTATION,classes=Configuration.class)
)
public class AutoAppConfig {

    @Bean(name="memoryMemberRepository")
    MemoryMemberRepository memoryRepository(){
        return new MemoryMemberRepository();
    }

}
