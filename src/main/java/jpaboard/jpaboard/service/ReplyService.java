package jpaboard.jpaboard.service;

import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.domain.Reply;
import jpaboard.jpaboard.repository.BoardRepository;
import jpaboard.jpaboard.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long addReply(Reply reply,Long memberId,Long boardId){
        Member member= memberRepository.findOne(memberId);
        Board board= boardRepository.findOne(boardId);
        reply.setMember(member);
        reply.setBoard(board);
        replyRepository.save(reply);
        return reply.getId();
    }

    public Reply findOne(Long id){
        return replyRepository.findOne(id);
    }

    public List<Reply> findAll(Long boardId){
        return replyRepository.findAll(boardId);
    }
}
