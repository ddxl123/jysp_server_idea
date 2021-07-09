package com.example.demo.controller.requestvo.loginandregister.byemail;

import com.example.demo.constant.code.Code2;
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
    @ApiModelProperty(
            value = Code2.C1_01_01 + ",邮箱格式不正确!" + "<br>" +
                    Code2.C1_01_02 + ",邮箱不能为空!"
    )
    @Email(message = Code2.C1_01_01 + ",邮箱格式不正确!")
    @NotBlank(message = Code2.C1_01_02 + ",邮箱不能为空!")
    private String email;
}



