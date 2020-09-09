package jpaboard.jpaboard.service;

import jpaboard.jpaboard.domain.Reply;
import jpaboard.jpaboard.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {
    private final ReplyRepository replyRepository;

    public Long addReply(Reply reply){
        replyRepository.save(reply);
        return reply.getId();
    }

    public Reply findOne(Long id){
        return replyRepository.findOne(id);
    }

    public List<Reply> findAll(){
        return replyRepository.findALl();
    }
}
