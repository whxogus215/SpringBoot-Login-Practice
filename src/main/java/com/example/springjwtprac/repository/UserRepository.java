package com.example.springjwtprac.repository;

import com.example.springjwtprac.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
/**
 * 1. 데이터베이스 스키마와 커넥션의 차이점 정리
 */
}
