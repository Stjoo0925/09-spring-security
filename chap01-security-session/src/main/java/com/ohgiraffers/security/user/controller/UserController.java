package com.ohgiraffers.security.user.controller;

import com.ohgiraffers.security.user.model.dto.SignUpDTO;
import com.ohgiraffers.security.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public void signup() {

    }

    @PostMapping("/signup")
    public ModelAndView signUp(@ModelAttribute SignUpDTO signUpDTO, ModelAndView mv) {
        Integer result = userService.regist(signUpDTO);
        String message = null;

        if (result == null){
            message = "중복된 회원이 존재합니다";
        } else if(result == 0) {
            message = "등록에 실패했습니다";
            mv.setViewName("user/signup");
        } else if(result >= 1){
            message = "등록에 성공했습니다";
            mv.setViewName("auth/login");
        }

        mv.addObject("message", message);
        return mv;
    }
}
