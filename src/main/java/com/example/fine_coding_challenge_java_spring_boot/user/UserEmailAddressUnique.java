package com.example.fine_coding_challenge_java_spring_boot.user;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the emailAddress value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = UserEmailAddressUnique.UserEmailAddressUniqueValidator.class
)
public @interface UserEmailAddressUnique {

    String message() default "{Exists.user.emailAddress}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class UserEmailAddressUniqueValidator implements ConstraintValidator<UserEmailAddressUnique, String> {

        private final UserService userService;
        private final HttpServletRequest request;

        public UserEmailAddressUniqueValidator(final UserService userService,
                final HttpServletRequest request) {
            this.userService = userService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(userService.get(Integer.parseInt(currentId)).getEmailAddress())) {
                // value hasn't changed
                return true;
            }
            return !userService.emailAddressExists(value);
        }

    }

}
