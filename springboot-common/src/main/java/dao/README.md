DAO(or Repository)

- Repository
- 실제로 DB에 접근하는 객체
- Service와 DB를 연결하는 고리 역할
- Data Access Object
- 사용 class에 @Repository 선언
- 보통 실행될 쿼리가 들어있음


* Repository vs DAO
- Repository는 자바 객체를 테이블에 매핑 할 수 있고 매핑 레벨이 객체 수준
- DAO는 매핑 레벨이 SQL 수준

이런 차이는 두 개념이 추상화하는 대상이 다름을 의미한다.

DAO는 SQL 수준의 매핑을 지원하기 때문에 퍼시스턴스 레이어(영속성, 일반 데이터와 다르게 계속 유지되는 데이터)에 속하여 퍼시스턴스 레이어에
대한 추상화이지만 Repository는 도메인 레이어에 객체 컬렉션의 추상화이다.

결과적으로 보면 DAO와 Repository 모두 퍼시스턴스(데이터 처리) 로직을 위해 동작하지만, 따로 놓고보면 명백한 차이점이 있다고 볼 수 있겠다