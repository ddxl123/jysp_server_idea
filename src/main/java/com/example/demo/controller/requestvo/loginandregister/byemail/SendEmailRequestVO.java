package com.example.demo.controller.requestvo.loginandregister.byemail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 类响应码 —— 2010000
 *
 * @author 10338
 */
@Data
public class SendEmailRequestVO {

    /**
     * 方法响应码 —— 2010100
     * <p>
     * 邮箱
     */
    @ApiModelProperty(
            value = "5000,邮箱格式不正确!" + "<br>" +
                    "5001,邮箱不能为空!"
    )
    @Email(message = "5000,邮箱格式不正确!")
    @NotBlank(message = "5001,邮箱不能为空!")
    private String email;
}



