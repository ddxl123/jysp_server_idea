package com.example.demo.controller.requestvo.loginandregister.byemail;

import com.example.demo.validator.annotationgroup.EmailValidAnnotationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 10338
 */
@Data
public class SendEmailRequestVO {

    /**
     * 邮箱
     */
    @EmailValidAnnotationGroup()
    @ApiModelProperty(value = EmailValidAnnotationGroup.EMAIL_GROUP_MESSAGE)
    private String email;
}



