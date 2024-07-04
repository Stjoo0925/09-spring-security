package com.ohgiraffers.security.user.service;

import com.ohgiraffers.security.user.dao.UserRepository;
import com.ohgiraffers.security.user.model.dto.SignUpDTO;
import com.ohgiraffers.security.user.model.dto.UserRole;
import com.ohgiraffers.security.user.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public Integer regist(SignUpDTO signUpDTO) {
        User user = userRepository.findByUserId(signUpDTO.getUserId());

        if(!Objects.isNull(user)){
            return null;
        }
        user = new User();
        user.setUserId(signUpDTO.getUserId());
        user.setUserName(signUpDTO.getUserName());
        user.setUserRole(UserRole.valueOf(signUpDTO.getRole()));
        user.setUserPass(encoder.encode(signUpDTO.getUserPass()));

        User saveUser = userRepository.save(user);

        if(Objects.isNull(saveUser)){
            return 0;
        } else {
            return 1;
        }
    }

    public User findByUserId(String username) {
        User user = userRepository.findByUserId(username);
        if (Objects.isNull(user)) {
            return null;
        }
        return user;
    }
}
