# JPA

## EntityMapping
```java
import java.persistence.*;
import java.util.Date;

@Entity
@Table(name="MEMBER")
public class Member {
    
    @Id
    @Column(name = "ID")
    private String id;
    
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Lob
    private String description;
    
    // Getter Setter ...
}
```

roleType : 자바의 enum을 사용해서 타입을 구분함
createdDate : 자바의 날짜 타입은 Temporal을 사용해서 매핑
description : 길이 제한이 없는 CLOB, BLOB 컬럼을 매핑

### 이름매핑 전략
`<property name="hibernate.ejb.naming_strategy" value="org.hibernate.cfg.ImprovedNamingStrategy"/>`
해당 속성을 이용하여 데이터베이스는 언더바표기법, 자바 필드명은 카멜표기법으로 자동매핑

### 기본 키 매핑
1. 기본 키 직접 할당

em.persist()를 호출하기 전에 어플리케이션에서 직접 식별자 값을 할당해야 함. 식별자 값이 없으면 예외발생
2. IDENTITY

데이터베이스에 엔티티를 저장해서 식별자 값을 획득한 후 영속성 컨텍스트에 저장
3. SEQUENCE

데이터베이스 시퀀스에서 식별자 값을 획득한 후 영속성 컨텍스트에 저장

시퀀스를 지원하는 데이터베이스에서만 사용가능
4. TABLE

데이터베이스 시퀀스 생성용 테이블에서 식별자 값을 획득한 후 영속성 컨텍스트에 저장

모든 데이터베이스에서 사용가능

### 자연키 VS 대리키
JPA는 모든 엔티티에 대리키 사용을 권장한다.

비즈니스 요구사항은 계속해서 변하는데 테이블은 쉽게 변경할 수 없기 때문.

따라서 대리키를 생성하여 테이블인덱스를 관리하는 것이 일반적으로 좋은 선택지라고 할 수 있다.

## 연관관계 매핑 (⭐⭐⭐)
객체의 참조와 테이블의 왜래 키의 매핑. JPA를 제대로 다루는 핵심 기술이다.

