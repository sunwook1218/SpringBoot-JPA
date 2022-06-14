package com.example.demo.dailystudy.jpa._0614;

import com.example.demo.dailystudy.jpa.jpabook.start.Member;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootTest
public class JpaMain {

    Logger logger = LoggerFactory.getLogger(JpaMain.class);

    @Test
    void name() {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            logger.error("MSG", e);
            em.close();
        }
        emf.close();

    }

    private void logic(EntityManager em) {

        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUserName("선욱");
        member.setAge(2);

        //등록
        em.persist(member);

        //수정
        member.setAge(20);


        // 한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println(findMember.getUserName() + findMember.getAge());

        List<Member> memberList =
                em.createQuery("select m from Member m", Member.class)
                        .getResultList();

        System.out.println(memberList.size());

        em.remove(member);

    }

}
