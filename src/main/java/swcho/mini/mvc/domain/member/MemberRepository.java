package swcho.mini.mvc.domain.member;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemberRepository {

    /**
     * 생성
     * ID로 조회
     * 목록 조회
     */

    private Map<Long, Member> members = new ConcurrentHashMap<>();
    private Long sequence = 0L;

    /**
     * 생성
     * @param member
     * @return true or false
     * 생성될 경우 true
     * 중복 가입 방지로 미생성인 경우 false
     */
    public boolean createMember(Member member) {
        if (existMember(member)) {
            return false;
        }
        members.put(++sequence, member);
        return true;
    }

    private boolean existMember(Member member) {
        return members.values().stream()
                .anyMatch(e -> e.getId() == member.getId());
    }

    /**
     * ID로 조회
     * @param id
     * @return Member
     * 회원이 존재하지 않을 경우 null 반환
     */
    public Member findById(String id) {
        return members.values().stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null);
    } 
}
