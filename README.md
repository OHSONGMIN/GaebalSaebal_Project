# GaebalSaebal_Project

## 💁 프로젝트 소개

개발새발은 예비 개발자, 현직 개발자가 함께 이용할 수 있는 사이트입니다. 이용자는 퀴즈 게임을 통해 개발 지식을 학습할 수 있고, 퀴즈 게임을 통해 획득한 포인트로 가상의 상품을 구매할 수 있습니다. 그리고 커뮤니티에 글을 작성해 각종 정보와 일상을 함께 나눌 수 있습니다.


## ⏰ 개발 기간

24.11.16 ~ 24.11.30


## ⚙️ 개발 환경

- 언어 / 서버 : Java8, JavaScript, JSP, HTML, css, Apache Tomcat
- 프레임워크 / 라이브러리 : Spring, MyBatis, JQuery, JSTL, Bootstrap
- 데이터베이스 : Oracle Database
- 도구 : STS3, Eclipse, SQL Developer


## 👥 역할 분담

- KJH : 회원가입, 로그인, 회원탈퇴, 마이페이지
- PHJ : 상품 조회, 관리자의 상품 등록, 수정, 삭제, 조회
- **오송민 : 게시글 및 댓글, 즐겨찾기(북마크), 키워드 검색**
- LMJ : 상품 장바구니, 찜, 결제
- LHY : 퀴즈 게임 관리, 게임 실행
- HYL : 관리자의 회원 관리, 문의글 조회, 답변 작성


## 🔍 ERD

<img width="1013" alt="스크린샷 2024-03-07 134244" src="https://github.com/OHSONGMIN/GaebalSaebal_Project/assets/143185293/0cad44d5-1ead-439f-9b41-82efe945dca0">


## 📋 프로젝트 구조

```
 main
  ├─java
  │  └─com
  │      └─devquiz
  │          └─biz
  │              ├─controller
  │              ├─dao
  │              ├─model
  │              ├─service
  │              └─serviceimpl
  ├─resources
  │  ├─mappings
  │  │  ├─answer
  │  │  ├─board
  │  │  ├─bookmark
  │  │  ├─cart
  │  │  ├─community
  │  │  ├─game
  │  │  ├─like
  │  │  ├─main
  │  │  ├─member
  │  │  ├─myProduct
  │  │  ├─order
  │  │  ├─orderdetail
  │  │  ├─point
  │  │  ├─product
  │  │  └─question
  │  └─META-INF
  └─webapp
      ├─resources
      │  ├─css
      │  │  ├─admin
      │  │  └─gaebal
      │  ├─images
      │  ├─js
      │  └─upload
      └─WEB-INF
          ├─config
          └─jsp
              ├─admin
              │  ├─board
              │  ├─game
              │  ├─main
              │  ├─member
              │  ├─product
              │  └─question
              ├─common
              ├─gaebal
              │  ├─cart
              │  ├─community
              │  ├─game
              │  ├─like
              │  ├─member
              │  ├─myPage
              │  ├─order
              │  ├─product
              │  └─question
              └─include

```

## 📌 주요 기능

# 메인 페이지
최근 일주일 내 가장 조회수가 높은 게시글/ 공지사항/ IT 관련 뉴스를 확인할 수 있습니다.
<img width="500" src="https://github.com/OHSONGMIN/GaebalSaebal_Project/assets/143185293/36c0fb60-8ec8-493b-b1b7-a7b67ad0d753">



스킬북... 가상 아이템
