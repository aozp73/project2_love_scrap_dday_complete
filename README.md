# 구인구직 사이트 프로젝트

## 기술스택
- JDK 11
- Springboot 2.7.8
- MyBatis
- 테스트 h2 디비
- JSP
- Tomcat Jasper
- JSTL

## 모델링
### 1단계
- Company
- User
- Board
### 2단계
- Apply
- Tech
### 3단계
- Mark
### 4단계
- Resume

## 기능정리
### 1단계
- 기업로그인
- 기업회원가입
- 기업회원정보수정
- 일반회원로그인
- 일반회원가입
- 일반회원정보수정
- 로그아웃
- 채용공고 목록보기
- 채용공고 상세보기
- 구직자 목록 페이지
- 구직자 상세 페이지
- 자소서 쓰기
- 채용공고 쓰기
### 2단계
- 채용공고 지원하기
- 채용공고 지원자 목록
- 구직자 목록 정렬하기(보유 기술이 유사한 순으로 정렬 기능 추가)
- 채용공고 목록 정렬하기(보유 기술이 유사한 순으로 정렬 기능 추가)
- 프로필 사진 변경
- 아이디 중복 체크
- 비밀번호 확인
### 3단계
- 페이징
- 기업검색기능
- 구직자 저장(좋아요) 기능
- 공고 저장(좋아요) 기능
- 지원하지 않은 구직자 추천
- 비밀번호 SHA-256을 사용하여 해시화
- 주소입력 API 사용하기
### 4단계
- 이력서 테이블 생성
- 자신의 이력서 관리 기능
- 이력서 목록 페이지
- 채용 공고 지원시 이력서 선택 제출