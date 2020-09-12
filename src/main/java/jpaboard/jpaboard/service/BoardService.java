package jpaboard.jpaboard.service;

import jpaboard.jpaboard.RequestDto.BoardRequestDto;
import jpaboard.jpaboard.RequestDto.PageRequestDto;
import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.repository.BoardRepository;
import jpaboard.jpaboard.repository.MemberRepository;
import jpaboard.jpaboard.responseDto.BoardResponseDto;
import jpaboard.jpaboard.vo.PageMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional
    public void removeBoard(Long boardId){
        Board board = boardRepository.findOne(boardId);
        boardRepository.remove(board);
    }

    public PageMaker makePage(PageRequestDto pageRequestDto){
        PageMaker pageMaker = new PageMaker(pageRequestDto, boardRepository.countBoard());
        return pageMaker;
    }

    public BoardResponseDto findOne(Long id){
        Board board = boardRepository.findOne(id);
        BoardResponseDto responseDto = new BoardResponseDto(board);
        return responseDto;
    }

    public List<BoardResponseDto> findAll(PageRequestDto pageRequestDto){
        List<Board> boardList = boardRepository.findAll(pageRequestDto);
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();

        boardList.stream().forEach(board -> {
            BoardResponseDto boardResponseDto = new BoardResponseDto(board);
            boardResponseDtoList.add(boardResponseDto);
        });

        return boardResponseDtoList;
    }


    @Transactional
    public void addReadCount(Long id) {
        Board board = boardRepository.findOne(id);
        board.addReadCount();
    }
}
