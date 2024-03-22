# HSHOP

### Domain Driven Design 과 Microservices 패턴을 적용한 간단한 데모용 eCommerce 웹사이트
#### 기간을 정해 놓지 않고 만들고 있는 개인프로젝트. 전반적인 기능 구현 후 세부적인 부분을 구현
#### Client
 - Vite, React, RTK
#### Server
 - Kotlin, SpringBoot 3


### Microservice 목록
 - User Service - 관리자 계정을 관리하는 서비스
 - Customer Service - 소비자의 계정과 정보를 관리하는 서비스
 - Catalog Service - 브랜드, 카테고리, 제품에 대한 목록 정보를 제공 (Inventory 정보를 같이 제공하지만 차후에 기능이 복잡해 지면 분리)
 - Cart Service - 가입자들의 장바구니 정보를 관리하는 서비스. 소비자 계정이 만들어지면 카프카 메시지를 통해 카트 자동 생성
 - Settings Service - 쇼핑몰의 설정 정보를 관리하는 서비스. 통화, 메일서버 설정, 카피라이트 등 정보 관리

### Infrastructure 서비스
- Discovery Service - 마이크로서비스를 검색하고 상태를 체크하는 서비스
- Configuration Service - 마이크로서비스의 설정을 관리하는 서비스 (개발의 편의성을 위해 현재 file에 저장하는 native 설정)
- Gateway Service - 마이크로서비스의 호출을 통제하고 인증토큰을 검증하는 기능을 가진 gateway 서비스
- Kafka 클러스터 - 이벤트 발행과 구독을 처리하기 위한 메시지 큐
- Rabbit MQ Server - configuration 변화 시에 실시간 데이터 동기화를 위한 메시지 큐
- Elasticsearch 클러스터 - 차후에 화면에 표출된 데이터를 저장하고 CQRS를 패턴을 구현 (현재는 catalog 서비스에서 데이터 수신) 

### 추가할 서비스
 - Order Service - 고객의 주문을 받아서 처리하고 관리하는 서비스
 - Payment Service - 고객의 주문의 결재를 처리하고 상태를 관리하는 서비스 (Paypal 연동)
 - Delivery Service - 고객 주문의 배송정보를 관리하는 서비스
 - Inventory Service - 제품의 재고를 관리하는 서비스
 
