package swcho.mini.mvc.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
public class Member {
    private String id;
    private String password;
    private String name;
}
