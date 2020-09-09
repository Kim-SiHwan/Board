package jpaboard.jpaboard.repository;

import jpaboard.jpaboard.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public void save(Board board){
        em.persist(board);
    }

    public Board findOne(Long id){
        return em.find(Board.class,id);
    }

    public List<Board> findAll(){
        return em.createQuery("select b from Board b",Board.class)
                .getResultList();
    }

    public List<Board> findAllByName(String userName){
        return em.createQuery("select b from Board b where b.member.userName=:userName",Board.class)
                .setParameter("userName",userName)
                .getResultList();
    }
}
