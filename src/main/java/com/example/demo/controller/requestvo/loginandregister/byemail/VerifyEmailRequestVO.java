package com.example.demo.controller.requestvo.loginandregister.byemail;

import com.example.demo.constant.code.Code2;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 10338
 */
@Data
public class VerifyEmailRequestVO {

    /**
     * 邮箱
     */
    @ApiModelProperty(
            value = Code2.C2_01_01 + ",邮箱格式不正确!" + "<br>" +
                    Code2.C2_01_02 + ",邮箱不能为空!"
    )
    @Email(message = Code2.C2_01_01 + ",邮箱格式不正确!")
    @NotBlank(message = Code2.C2_01_02 + ",邮箱不能为空!")
    private String email;

    /**
     * 邮箱验证码
     */
    @ApiModelProperty(
            value = Code2.C2_02_01 + ",验证码不能为空"
    )
    @NotNull(message = Code2.C2_02_01 + ",验证码不能为空")
    private Integer code;
}



