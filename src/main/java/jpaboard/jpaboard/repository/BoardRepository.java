package jpaboard.jpaboard.repository;

import jpaboard.jpaboard.RequestDto.BoardRequestDto;
import jpaboard.jpaboard.RequestDto.PageRequestDto;
import jpaboard.jpaboard.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public void save(Board board){
        em.persist(board);
    }

    public void remove(Board board){
        em.remove(board);
    }

    public Board findOne(Long id){
        return em.find(Board.class,id);
    }

    public List<Board> findAll(PageRequestDto pageRequestDto){
        TypedQuery <Board> query = em.createQuery("select b from Board b order by b.id desc",Board.class);
        query.setFirstResult((pageRequestDto.getPage()-1)*10);
        query.setMaxResults(pageRequestDto.getSize());
        return query.getResultList();
    }

    public List<Board> findAllByLike(PageRequestDto pageRequestDto){
        TypedQuery <Board> query = em.createQuery("select b from Board b where b.boardLikes.size>1 order by b.id desc",Board.class);
        query.setFirstResult((pageRequestDto.getPage()-1)*10);
        query.setMaxResults(pageRequestDto.getSize());
        return query.getResultList();
    }


    public int countBoard(){
        Query query = em.createQuery("select count(b.id) as cnt from Board b");
        int count = Integer.parseInt(query.getSingleResult().toString());
        return count;
    }


    public int countBestBoard(){
        Query query = em.createQuery("select count(b.id) as cnt from Board b where b.boardLikes.size>1");
        int count = Integer.parseInt(query.getSingleResult().toString());
        return count;
    }

}
