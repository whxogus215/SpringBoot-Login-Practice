package com.example.springjwtprac.service;

import com.example.springjwtprac.dto.JoinDTO;
import com.example.springjwtprac.entity.UserEntity;
import com.example.springjwtprac.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    /**
     * 1. 생성자 주입 방식 복습 및 정리
      */
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO) {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();
        
        // 요청받은 회원이 DB에 존재하는지 먼저 확인
        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {
            // 이미 존재하는 회원일경우, 회원가입 진행 X

            return;
        }

        UserEntity data = new UserEntity();
        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password)); // 비밀번호는 무조건 암호화하여 저장하기
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}
