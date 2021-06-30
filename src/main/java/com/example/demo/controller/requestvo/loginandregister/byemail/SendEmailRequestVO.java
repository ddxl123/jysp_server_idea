package com.example.demo.controller.requestvo.loginandregister.byemail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author 10338
 */
@Data
public class SendEmailRequestVO {

    /**
     * 邮箱
     */
    @Email(message = "5000,不是邮箱类型")
    @NotBlank(message = "5001,邮箱不能为空")
    @ApiModelProperty(
            value = "5000,不是邮箱类型" + "<br>" +
                    "5001,邮箱不能为空")
    private String email;
}



