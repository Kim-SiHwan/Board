package jpaboard.jpaboard.service;

import jpaboard.jpaboard.RequestDto.BoardRequestDto;
import jpaboard.jpaboard.RequestDto.PageRequestDto;
import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.repository.BoardRepository;
import jpaboard.jpaboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long upload(Board board,Long memberId){
        Member member = memberRepository.findOne(memberId);
        board.setMember(member);
        boardRepository.save(board);
        return board.getId();
    }


    public Board findOne(Long id){
        return boardRepository.findOne(id);
    }

    public List<Board> findAll(PageRequestDto pageRequestDto){
        return boardRepository.findAll(pageRequestDto);
    }

    public List<Board> findAllByName(String userName) {
        return boardRepository.findAllByName(userName);
    }

    @Transactional
    public void addReadCount(Long id) {
        Board board = boardRepository.findOne(id);
        board.addReadCount();
    }
}
