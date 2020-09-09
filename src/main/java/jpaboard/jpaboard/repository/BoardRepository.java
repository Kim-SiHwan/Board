package jpaboard.jpaboard.repository;

import jpaboard.jpaboard.RequestDto.PageRequestDto;
import jpaboard.jpaboard.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    public List<Board> findAll(PageRequestDto pageRequestDto){
        TypedQuery <Board> query = em.createQuery("select b from Board b order by b.id desc",Board.class);
        query.setFirstResult(pageRequestDto.getPage()*10);
        query.setMaxResults(pageRequestDto.getSize());
        return query.getResultList();
    }

    public List<Board> findAllByName(String userName){
        return em.createQuery("select b from Board b where b.member.userName=:userName",Board.class)
                .setParameter("userName",userName)
                .getResultList();
    }
}
