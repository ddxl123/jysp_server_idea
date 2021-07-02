package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.EmailVerify;
import com.example.demo.mapper.EmailVerifyMapper;
import com.example.demo.service.EmailVerifyService;
import org.springframework.stereotype.Service;


/**
 * @author 10338
 */
@Service
public class EmailVerifyServiceImpl extends ServiceImpl<EmailVerifyMapper, EmailVerify> implements EmailVerifyService {
}
