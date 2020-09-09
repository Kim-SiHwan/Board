package jpaboard.jpaboard.repository;

import jpaboard.jpaboard.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {
    private final EntityManager em;

    public void save(Reply reply){
        em.persist(reply);
    }

    public Reply findOne(Long id){
        return em.find(Reply.class,id);
    }

    public List<Reply> findALl(){
        return em.createQuery("select r from Reply r",Reply.class)
                .getResultList();
    }
}