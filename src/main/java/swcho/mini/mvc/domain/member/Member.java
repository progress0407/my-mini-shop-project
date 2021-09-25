package swcho.mini.mvc.domain.member;

import lombok.*;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String id;
    private String password;
    private String name;
    private Authority authority;
}
