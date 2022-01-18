package dev.dowell.helloredirect.proxy;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class PasswordReset {
    private String oldPassword;
    private String newPassword;
}
