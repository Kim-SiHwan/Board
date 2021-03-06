package jpaboard.jpaboard.repository;

import jpaboard.jpaboard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.userName = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Long getIdByName(String userName) {
        Query query = em.createQuery("select m.id from Member m where m.userName = :userName")
                .setParameter("userName", userName);
        Long memberId = Long.parseLong(query.getSingleResult().toString());
        return memberId;
    }

}
