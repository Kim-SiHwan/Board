package jpaboard.jpaboard.repository;

import jpaboard.jpaboard.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {
    private final EntityManager em;

    public void save(Reply reply) {
        em.persist(reply);
    }

    public Reply findOne(Long id) {
        return em.find(Reply.class, id);
    }

    public void remove(Reply reply) {
        em.remove(reply);
    }

    public List<Reply> findAll(Long boardId) {
        return em.createQuery("select r from Reply r where r.board.id=:boardId", Reply.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    public Long findByName(String userName) {
        Query query = em.createQuery("select r.member.id from Reply r where r.member.userName = :userName");
        Long memberId = Long.parseLong(query.getSingleResult().toString());
        return memberId;
    }
}
