package swcho.mini.mvc.web.member;

import org.junit.jupiter.api.Test;
import swcho.mini.mvc.domain.member.Member;
import swcho.mini.mvc.domain.member.MemberRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MemberControllerTest {

    MemberRepository memberRepository = new MemberRepository();
    MemberController memberController = new MemberController(memberRepository);
    
    @Test
    public void 도대체_왜_검증이_랜더링_안돼는고얌() throws Exception {
      // given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        MemberSignUpForm form = new MemberSignUpForm();
        form.setId("");
        form.setPassword("123");
        form.setPasswordCheck("1");
        form.setName("성성");
        form.setAuthority(null);

        // when
        Set<ConstraintViolation<MemberSignUpForm>> constraintViolations = validator.validate(form);

        // then
        for (ConstraintViolation<MemberSignUpForm> constraintViolation : constraintViolations) {
            System.out.println("constraintViolation = " + constraintViolation);
            System.out.println("constraintViolation.getMessage() = " + constraintViolation.getMessage());
        }
      
    }

}