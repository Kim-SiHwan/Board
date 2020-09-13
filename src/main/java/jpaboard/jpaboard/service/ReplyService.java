package jpaboard.jpaboard.service;

import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.domain.Reply;
import jpaboard.jpaboard.repository.BoardRepository;
import jpaboard.jpaboard.repository.MemberRepository;
import jpaboard.jpaboard.repository.ReplyRepository;
import jpaboard.jpaboard.responseDto.ReplyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long addReply(Reply reply,String userName,Long boardId){
        Long memberId = memberRepository.getIdByName(userName);
        Member member= memberRepository.findOne(memberId);
        Board board= boardRepository.findOne(boardId);
        reply.setMember(member);
        reply.setBoard(board);
        replyRepository.save(reply);
        return reply.getId();
    }

    @Transactional
    public void removeReply(Long replyId){
        Reply reply = replyRepository.findOne(replyId);
        replyRepository.remove(reply);
    }

    public ReplyResponseDto findOne(Long id){
        Reply reply = replyRepository.findOne(id);
        ReplyResponseDto replyResponseDto = new ReplyResponseDto(reply);
        return replyResponseDto;
    }

    public List<ReplyResponseDto> findAll(Long boardId){
        List<Reply> replyList = replyRepository.findAll(boardId);
        List<ReplyResponseDto> replyResponseDtoList = new ArrayList<>();

        replyList.stream().forEach(reply -> {
            ReplyResponseDto replyResponseDto = new ReplyResponseDto(reply);
            replyResponseDtoList.add(replyResponseDto);
        });

        return replyResponseDtoList;
    }
}
