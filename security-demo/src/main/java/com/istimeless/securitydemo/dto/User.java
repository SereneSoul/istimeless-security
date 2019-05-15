package com.istimeless.securitydemo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.istimeless.securitydemo.validator.MyConstraint;
import lombok.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    
    public interface UserSimpleView {};
    
    public interface UserDetailView extends UserSimpleView {};
    
    @JsonView(UserSimpleView.class)
    private String id;
    
    @MyConstraint(message = "测试")
    @JsonView(UserSimpleView.class)
    private String username;
    
    @NotBlank(message = "密码不能为空")
    @JsonView(UserDetailView.class)
    private String password;
    
    @Past(message = "生日必须是过去的时间")
    @JsonView(UserSimpleView.class)
    private Date birthday;
}
