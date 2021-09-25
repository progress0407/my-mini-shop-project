package swcho.mini.mvc.domain.member;

import lombok.Getter;

@Getter
public enum Authority {

    NORMAL("일반 사용자"), MANAGER("상품관리자"), ADMIN("시스템 운영자");

    private final String name;

    Authority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
