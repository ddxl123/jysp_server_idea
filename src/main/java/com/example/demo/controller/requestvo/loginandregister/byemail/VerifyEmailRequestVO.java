package com.example.demo.controller.requestvo.loginandregister.byemail;

import com.example.demo.validator.annotationgroup.EmailValidAnnotationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 10338
 */
@Data
public class VerifyEmailRequestVO {

    /**
     * 邮箱
     */
    @EmailValidAnnotationGroup
    @ApiModelProperty(value = EmailValidAnnotationGroup.EMAIL_GROUP_MESSAGE)
    private String email;

    /**
     * 邮箱验证码
     */

    @NotNull(message = "5005,验证码不能为空")
    @ApiModelProperty(value = "5005,验证码不能为空")
    private Integer code;
}



