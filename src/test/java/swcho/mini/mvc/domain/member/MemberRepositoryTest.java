package swcho.mini.mvc.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    MemberRepository memberRepository = new MemberRepository();

    @BeforeEach
    public void 회원_초기화() {
        Member testUser1 = new Member("test1", "1234", "테또1", Authority.ADMIN);
        memberRepository.createMember(testUser1);
        Member testUser2 = new Member("test2", "1234", "테또2", Authority.ADMIN);
        memberRepository.createMember(testUser2);
    }

    @Test
    public void 회원조회_테스트() {
        Member foundMember = memberRepository.findById("test1");
        assertThat(foundMember.getName()).isEqualTo("테또1");
    }
    
    @Test
    public void 중복가입_방지_테스트() {
        Member testUserAgain1 = new Member("test1", "12", "테또_또1", Authority.ADMIN);
        boolean result = memberRepository.createMember(testUserAgain1);
        assertThat(result).isEqualTo(false);
        Member foundMember = memberRepository.findById("test1");
        assertThat(foundMember.getName()).isNotEqualTo("테또_또1");
        assertThat(foundMember.getName()).isEqualTo("테또1");
    }

    @Test
    public void 최상위_관리자만_제외하고_출력하기() throws Exception {
      // when
        List<Authority> authorities = Arrays.stream(Authority.values()).filter(e -> e != Authority.ADMIN).collect(Collectors.toList());
        System.out.println("authorities = " + authorities);

        // then

    }



}