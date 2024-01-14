package com.example.springjwtprac.repository;

import com.example.springjwtprac.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
/**
 * 1. 데이터베이스 스키마와 커넥션의 차이점 정리
 */
    Boolean existsByUsername(String username); // existsBy+? : 해당 ?가 DB에 존재하는지 여부를 반환하는 JPA 쿼리 메소드

    UserEntity findByUsername(String username);
}
