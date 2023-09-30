controller

- 사용 class에 @Controller 선언
- 사용자의 입력을받고 서비스로 전달하는 역할
- View로 부터 오는 요청에 대하여 get, post 등 매칭하여 service로 전달
- 기본적인 계층 구조에서는 비즈니스 계층이 유효성 검사를 하지만 SpringBoot 프로젝트에서는 Validation 라이브러리를 활용해서
요청 정보를 바로 체크할 수 있다
- View

Client -> (DTO) -> Controller(Servlet) -> (DTO) -> Service -> (DTO) -> Repository -> (Domain) -> DB