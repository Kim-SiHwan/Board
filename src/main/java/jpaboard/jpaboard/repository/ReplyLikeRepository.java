package jpaboard.jpaboard.repository;

import jpaboard.jpaboard.domain.ReplyLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ReplyLikeRepository {

    private final EntityManager em;

    public void save(ReplyLike replyLike) {
        em.persist(replyLike);
    }

    public void remove(ReplyLike replyLike) {
        em.remove(replyLike);
    }

    public ReplyLike findByAllId(Long memberId, Long replyId) {
        ReplyLike replyLike = em.createQuery("select rl from ReplyLike rl where rl.member.id = :memberId and rl.reply.id = :replyId", ReplyLike.class)
                .setParameter("memberId", memberId)
                .setParameter("replyId", replyId)
                .getSingleResult();
        return replyLike;
    }

    public ReplyLike findOne(Long id) {
        return em.find(ReplyLike.class, id);
    }
}
