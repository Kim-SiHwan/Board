package jpaboard.jpaboard.service;

import jpaboard.jpaboard.requestDto.BoardRequestDto;
import jpaboard.jpaboard.requestDto.PageRequestDto;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long upload(Board board, String userName) {
        Long memberId = memberRepository.getIdByName(userName);
        Member member = memberRepository.findOne(memberId);
        board.setMember(member);
        boardRepository.save(board);
        return board.getId();
    }

    @Transactional
    public void removeBoard(Long boardId) {
        Board board = boardRepository.findOne(boardId);
        boardRepository.remove(board);
    }

    @Transactional
    public void updateBoard(BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findOne(boardRequestDto.getBoardId());
        board.changeText(boardRequestDto.getTitle(), boardRequestDto.getContent());

    }

    public PageMaker makePage(PageRequestDto pageRequestDto) {
        PageMaker pageMaker = new PageMaker(pageRequestDto, boardRepository.countBoard());
        return pageMaker;
    }

    public PageMaker makePageByBest(PageRequestDto pageRequestDto) {
        PageMaker pageMaker = new PageMaker(pageRequestDto, boardRepository.countBestBoard());
        return pageMaker;
    }

    public BoardResponseDto findOne(Long id) {
        Board board = boardRepository.findOne(id);
        BoardResponseDto responseDto = new BoardResponseDto(board);
        return responseDto;
    }

    public List<BoardResponseDto> findAll(PageRequestDto pageRequestDto) {
        List<Board> boardList = boardRepository.findAll(pageRequestDto);

        List<BoardResponseDto> list = boardList.stream()
                .map(m -> new BoardResponseDto(m))
                .collect(Collectors.toList());

        return list;
    }

    public List<BoardResponseDto> findAllByLike(PageRequestDto pageRequestDto) {
        List<Board> boardList = boardRepository.findAllByLike(pageRequestDto);
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
