## 정리

### 이름 
설계에서 잘못 지은 이름은 두고두고 개발자를 속이는 말이 정말 맞는 것 같다.

최근 프로젝트에서 대화 창을 구현하는 업무를 맡았는 데, 
채팅방의 채팅을 치는 사람인 송신자의 이름이 필요하겠다고 senderName으로 명명했는 데, 
나중에 확인해 보니 수신자의 이름이 필요하다고 해서 고치지 않고, 
기존에 있던 senderName으로 사용하다가 이게 생각하고 있어도 본인이 지은 명명이지만, 
나 자신을 속이길래 senderName들을 다 receiverName으로 변경했던 적이 있어서 너무 공감이 든다.

그 이후 이름의 중요성을 느끼고 변수명을 고치지 **귀찮다고 냅두는 불상사가 발생하지 않았다.** 


### 기능 명세화
프로젝트의 분석은 미리 하되, TDD 작성은 필요할 것으로 **예측해서 미리 코드를 만들지 않는다.**

```java

LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);

```
처음에는 billingDate, payAmount만 필요 했지만, 차후 조건을 만족 시키기 위해서 PayData안에 firstBillingDate 추가한 모습이다.

```java
PayData payData = PayData.builder()
        .firstBillingDate(LocalDate.of(2019, 1, 31))
        .billingDate(LocalDate.of(2019, 2, 28))
        .payAmount(10_000).build();
LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);

```