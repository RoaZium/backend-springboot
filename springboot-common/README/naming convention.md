com.example 명명은 회사 or 제품의 역순 도메인 이름
> #### 규칙
- 패키지 이름은 모두 소문자로 작성
- 패키지 이름은 회사의 역순 도메인 이름으로 시작(예. com.example.mypackage)
- 패키지 이름이 키워드와 충돌하는 경우, 하이픈(-)을 사용하여 충돌을 방지합니다.
- 패키지 구조는 계층적으로 구성하여 코드의 가독성과 유지보수성을 향상시킵니다.(예.com.example.mypackage.domain.user)

> #### 항목
-controller
-dao

> #### 예시
```
com.example.mypackage
├── domain
│   ├── user
│   │   └── User.java
│   └── product
│       └── Product.java
├── controller
│   ├── user
│   │   └── UserController.java
│   └── product
│       └── ProductController.java
└── service
├── user
│   └── UserService.java
└── product
└── ProductService.java
```