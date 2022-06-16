# 2장 JPA 시작

- JPA와 객체 매핑
- persistence.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" version="2.1">

    <persistence-unit name="jpabook">
        <class>com.example.demo.dailystudy.jpa.jpabook.start.Member</class>
        <properties>

            <!-- 필수 속성 -->
            <property name="javax.persistence.jdbc.driver" value="org.mariadb.jdbc.Driver"/>
            <property name="javax.persistence.jdbc.user" value="trialadm"/>
            <property name="javax.persistence.jdbc.password" value="adm1234"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:mariadb://210.205.15.124:3306/trial"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.MariaDB10Dialect"/>

            <!-- 옵션 -->
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
            <property name="hibernate.use_sql_comments" value="true"/>
            <property name="hibernate.id.new_generator_mappings" value="true"/>

            <!--<property name="hibernate.hbm2ddl.auto" value="create" />-->
        </properties>
    </persistence-unit>
</persistence>
```
추가로 hibername는 특정 DB에 종속되지 않기 위해 방언(Dialect)을 제공함

속성은 `hibername.dialect` 이며 이는 JPA표준이 아니다. 커스텀 쿼리를 사용해야만 한다면 되도록 ANSI표준 SQL을 이용 할 것.

`EntityManagerFactory` 는 `persistence.xml` 정보를 참고하고 있으며 Factory생성 비용은 매우 비싸기 때문에 Bean으로 등록해서 이용할것을 권장.

- Member.java
```java
package com.example.demo.dailystudy.jpa.jpabook.start;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String userName;

    private Integer age;

}
```
매핑 정보가 없는 age 필드의 경우 필드명으로 컬럼을 매핑함.

- JPQL
```java
TypedQuery<Member> query = 
        em.createQuery("select m from Member m", Member.class);
List<Member> members = query.getResultList();
```
JPQL은 엔티티 객체를 대상으로 쿼리한다. (클래스와 필드)

SQL은 데이터베이스 테이블을 대상으로 쿼리한다.