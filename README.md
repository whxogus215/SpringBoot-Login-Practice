# SpringBoot-Login-Practice
스프링부트 시큐리티 기반 회원가입 서비스 연습 저장소

## 구현 내용
스프링 부트에 **JWT 방식의 스프링 시큐리티를 적용하고 인증/인가를 구현**하는 연습을 한다.

> 자료 출처 : https://www.youtube.com/watch?v=NPRh2v7PTZg

## 공부할 내용

**스프링 시큐리티 공식문서를 통해 공부하고 이해하기
https://www.youtube.com/watch?v=3Ff7UHGG3t8 댓글 참고**

### 스프링 시큐리티
1. csrf가 무엇인가?
2. 세션방식에서는 왜 csrf에 대한 설정을 해야 하는가
3. 왜 JWT 방식에서는 세션이 stateless이어야 하는가
4. 빌더 패턴
5. 스프링 시큐리티 필터 동작 원리
6. 빈 등록 관련 내용 복습 및 정리
7. 로그인 인증 전달 시 토큰을 사용해야 하는 이유(`UsernamePasswordAuthenticationToken`)
8. `UserDetailsSerivice` 인터페이스의 역할 및 기능
9. `UserDetails`의 역할(DTO) 및 기능
10. JWT 암호화 방식 - `HS256`

### 스프링 원리
1. `HttpServletRequest`, `HttpServletResponse` 객체 복습 및 정리
2. @ModelAttribute 방식 복습 및 정리 (입력 방식이 formdata인 이유)
2. 생성자 주입 방식 복습 및 정리
3. 스프링 필터 구조

### 데이터베이스
1. 데이터베이스 스키마와 커넥션의 차이점 정리
