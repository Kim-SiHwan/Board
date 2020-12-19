package jpaboard.jpaboard.repository;

import jpaboard.jpaboard.domain.BoardLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BoardLikeRepository {
    private final EntityManager em;

    public void save(BoardLike boardLike) {
        em.persist(boardLike);
    }

    public void remove(BoardLike boardLike) {
        em.remove(boardLike);
    }

    public BoardLike findByAllId(Long memberId, Long boardId) {
        BoardLike boardLike = em.createQuery("select bl from BoardLike bl where bl.board.id = :boardId and bl.member.id = :memberId", BoardLike.class)
                .setParameter("memberId", memberId)
                .setParameter("boardId", boardId)
                .getSingleResult();
        return boardLike;
    }

    public BoardLike findOne(Long id) {
        return em.find(BoardLike.class, id);
    }
}
