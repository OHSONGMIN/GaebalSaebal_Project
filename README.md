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

|메인 페이지|개발새발 커뮤니티|
|---|---|
|<img width="500" src="https://github.com/OHSONGMIN/GaebalSaebal_Project/assets/143185293/36c0fb60-8ec8-493b-b1b7-a7b67ad0d753">|<img width="500" src="https://github.com/OHSONGMIN/GaebalSaebal_Project/assets/143185293/04096cbe-9c8f-4afb-aa22-a1aeb6f3ed33">|


|게시글 작성|게시글 상세|
|---|---|
|<img width="500" src="https://github.com/OHSONGMIN/GaebalSaebal_Project/assets/143185293/ac8ce5ed-0a2e-47f3-b7b3-f7f992d6589e">|<img width="500" src="https://github.com/OHSONGMIN/GaebalSaebal_Project/assets/143185293/66d66f4b-8fcc-403f-b05c-78e20f33fcc0">|



|키워드로 조회 ("스터디"로 제목 검색)|즐겨찾기 게시글만 조회|
|---|---|
|<img width="500" src="https://github.com/OHSONGMIN/GaebalSaebal_Project/assets/143185293/835e5f8b-94c2-4c83-9083-14c6408feee8">|<img width="500" src="https://github.com/OHSONGMIN/GaebalSaebal_Project/assets/143185293/0a234483-1fd1-472f-85dc-9c3fed042e21">|


|상품 조회|상품 주문|
|---|---|
|<img width="500" src="https://github.com/OHSONGMIN/GaebalSaebal_Project/assets/143185293/8e49bd41-3a31-4b88-aba9-4b101184e0a6">|<img width="500" src="https://github.com/OHSONGMIN/GaebalSaebal_Project/assets/143185293/93fb1d6d-2ca1-428c-992a-329411b554a4">|


|퀴즈 게임|문의 관리|
|---|---|
|<img wirdh="500" src="https://github.com/OHSONGMIN/GaebalSaebal_Project/assets/143185293/961c3d09-7063-4e6c-ac3d-b42912143988">|<img width="500" src="https://github.com/OHSONGMIN/GaebalSaebal_Project/assets/143185293/1928d55e-0bff-48ef-b593-55ab2921f6fe">|


스킬북... 가상 아이템
