### my-mini-shop-project

# 미니 프로젝트 소개

---

![image](https://user-images.githubusercontent.com/66164361/139701120-86218743-406f-43b0-b7a4-c3d5142e1429.png)


## 사용된 기술 정보

---

- Spring Boot 2.5.4
- Java 11
- Maven
- JQuery
- BootStrap 3
- HTML/ CSS

## 참고 자료

---

- 김영한 Spring Web Core / MVC1 / MVC 2
- 생활코딩 JQuery

## 입력과 동시에 실시간 선택 가능

---

![image](https://user-images.githubusercontent.com/66164361/139701531-ca1bb87c-cd48-40b4-a56f-8c6bbffed17c.png)

- 위(↑), 아래(↓) 방향키를 통해서 노드 선택 가능하고  
엔터키를 통해서 해당 상세 페이지로 이동합니다 (GET)
- 사용 기술: jquery

![Animation](https://user-images.githubusercontent.com/66164361/139701927-bfdc8bdf-27cb-45a9-8418-4f1448749d10.gif)


## 로그인 및 권한

---

- 로그인을 하지 않으면 Item List 제외하고 이동이 불가합니다

![로그인_및_권한](https://user-images.githubusercontent.com/66164361/139840190-d15fa5b0-ec50-4c3c-ae0d-d5c67b726f05.gif)

- 일반 사용자와 다르게 관리자는 Test Page를 조회할 수 있습니다

1. 일반 사용자  
![image](https://user-images.githubusercontent.com/66164361/139840497-3de1a7dc-635a-437d-bfe8-2928fef0a891.png)  

  
2. 관리자  
![image](https://user-images.githubusercontent.com/66164361/139840733-8441ae1e-3ce4-4644-8979-41b9520e1363.png)


## 검증 (Bean Validation)

---

프론트가 아닌 백엔드에서 검증을 진행합니다    
SaveForm과 UpdateForm을 나누어서 Bean Validation을 적용했습니다  
서버에서 검증하기 때문에  
사용자 경험 만족성은 다소 떨어질 수 있지만 보다 안전하게 검증할 수 있습니다

- 필드 예외  
![image](https://user-images.githubusercontent.com/66164361/139842768-1910d554-abf0-45e5-9bb5-c36dfc982180.png)  

![image](https://user-images.githubusercontent.com/66164361/139842834-f51ffe6c-9ff6-43f8-b161-cbe6682ac605.png)  


- 글로벌 예외  
- ![image](https://user-images.githubusercontent.com/66164361/139843112-0e628c53-36af-4616-8e6f-50282ed1b146.png)

## 국제화  

---

모든 화면에서는 아니지만 상품 등록에서 국소적으로 국제화가 됩니다

![image](https://user-images.githubusercontent.com/66164361/139843400-b78d92d9-a640-4366-97ae-60706c61eb99.png)

![image](https://user-images.githubusercontent.com/66164361/139843443-ef17e1c8-d569-4119-955f-f73fc860ce96.png)


## 등록 및 상세

---

![상품 저장](https://user-images.githubusercontent.com/66164361/139845100-267eb555-0e63-4157-acf7-d228e65aa269.gif)

## 수정 및 삭제

---

![상품 수정 삭제](https://user-images.githubusercontent.com/66164361/139845576-80fc70b8-135f-4d84-8095-a34a04ab4a64.gif)

## 로그인 및 로그아웃

---

![로그인_아웃 및 회원가입](https://user-images.githubusercontent.com/66164361/139846592-5396fdc8-a691-49f1-ba98-202de919f328.gif)

## 테스트 페이지

---

- 시스템 관리자는 테스트 페이지에 접속하여 각종 테스트를 진행해 볼 수 있습니다

![테스트_페이지](https://user-images.githubusercontent.com/66164361/139846989-5ad64b0e-6836-4f82-9e3c-14cb1fab6b46.gif)
)



