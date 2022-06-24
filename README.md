# 키친포스

## 요구 사항

---

### step1
1. 기능정리
 - 테이블
   - 테이블 생성
   - 테이블 리스트 조회 
   - 빈 테이블 설정 
     - 제약사항 
       - 테이블 그룹이 설정되어있는 테이블은 빈테이블 설정 불가 
       - 주문 상태가 먹고있거나(MEAL), 요리중일때(COOKING) 빈테이블 설정 불가
   - 테이블에 손님 수 등록 
     - 제약사항
       - 테이블의 손님수를 음수로 설정은 불가하다 
       - 테이블이 이미 빈테이블이면 설정 불가
 - 단체 지정
   - 단체를 위한 테이블 그룹 등록
     - 제약사항
       - 등록할 테이블중 비어있는 테이블이 있으면 설정 불가 
       - 등록할 테이블이 1개 이하라면 설정 불가
       - 등록할 테이블이 저장되어있지 않다면 설정 불가
   - 단체 계산 후 테이블 그룹 삭제 
     - 제약사항 
       - 그룹의 테이블중 주문상태가 먹고있거나(MEAL), 요리중일때(COOKING) 그룹 테이블 삭제 불가
 - 상품 
   - 상품 등록
     - 제약사항
       - 등록할 상품의 가격이 자연수가 아니면 등록 불가 
   - 상픔 조회 
 - 주문
   - 주문 등록 
     - 제약사항
       - 주문 정보가 없으면 등록 불가 
     - 제약사항
       - 주문할 메뉴가 등록이 안되있으면 주문 불가
     - 제약사항
       - 테이블이 없으면 주문 불가 
     - 제약사항
       - 테이블이 비어있으면 주문 불가
   - 주문 목록 조회 
   - 주문 상태 변경 
 - 메뉴 
   - 메뉴 저장 
     - 제약사항
       - 등록할 메뉴의 가격이 자연수가 아니면 등록 불가 
       - 등록할 메뉴의 가격과 메뉴 제품들의 가격의 합이 다르다면 등록 불가
   - 메뉴 목록 조회 
 - 메뉴그룹 
   - 메뉴 그룹 생성 
   - 메뉴 그룹 리스트 조회

2. 구현 예정 기능
 - 모든 기능에 대한 인수테스트 작성(MOCKING 사용하지않음)
 - 모든 서비스 로직에 대한 테스트 작성 (연관 객체 MOCKING 사용)

---

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |
