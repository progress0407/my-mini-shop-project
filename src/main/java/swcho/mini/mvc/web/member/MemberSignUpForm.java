package swcho.mini.mvc.web.member;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import swcho.mini.mvc.domain.member.Authority;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class MemberSignUpForm {

    @NotBlank
    @Length(min = 4, max = 12)
    private String id;

    @NotBlank
    @Length(min = 4, max = 12)
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    @Length(min = 4, max = 12)
    private String passwordCheck;

    @NotNull
    private Authority authority;
}
