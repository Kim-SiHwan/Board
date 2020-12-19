package jpaboard.jpaboard;


import jpaboard.jpaboard.RequestDto.BoardRequestDto;
import jpaboard.jpaboard.RequestDto.PageRequestDto;
import jpaboard.jpaboard.domain.Address;
import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.repository.BoardRepository;
import jpaboard.jpaboard.responseDto.BoardResponseDto;
import jpaboard.jpaboard.service.BoardService;
import jpaboard.jpaboard.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void makeBoardTest() {
        //given
        Member member = createMember();
        memberService.join(member);
        //when
        Board board = makeBoard();
        boardService.upload(board, member.getUserName());

        Board getBoard = boardRepository.findOne(board.getId());
        //then

        assertEquals(board, getBoard);
        System.out.println(board.getMember().getUserName());
    }

    @Test
    public void removeBoard() {
        //given
        Member member = createMember();
        memberService.join(member);
        Board board = makeBoard();
        boardService.upload(board, member.getUserName());
        //when
        System.out.println(board.getTitle());
        boardService.removeBoard(board.getId());
        //then
        System.out.println(board.getTitle());

    }

    @Test
    public void pagingTest() {
        //given
        //data.sql 더미 데이터  존재 page 초기 값 0. size 초기 값 10
        PageRequestDto pageRequestDto = new PageRequestDto();
        //when
        List<Board> list = boardRepository.findAll(pageRequestDto);
        int totalPage = boardRepository.countBoard();
        //then
        assertEquals(10, list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTitle());
        }
    }

    @Test
    public void updateBoard() {
        //given
        Board board = boardRepository.findOne(200L);
        //when

        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setBoardId(200L);
        boardRequestDto.setTitle("update");
        boardRequestDto.setContent("updateContent");
        boardService.updateBoard(boardRequestDto);
        //then
        assertEquals("update", board.getTitle());
        assertEquals("updateContent", board.getContent());

    }

    @Test
    public void makeDataSql() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("insert into board (member_id , title, content ,likes , read , create_date ) values (1 , '오이" + i + "T', '오이" + i + "C', 0, 0 ,now() );");
            System.out.println("insert into board (member_id , title, content ,likes , read , create_date ) values (2 , '가지" + i + "T', '가지" + i + "C', 0, 0 ,now() );");
        }
    }

    public Member createMember() {
        return Member.createMember()
                .userName("오잇")
                .address(new Address("감자", "고구마", "가지"))
                .role("USER")
                .build();
    }

    public Board makeBoard() {
        return Board.makeBoard()
                .read(0)
                .title("오이T")
                .content("오이C")
                .createDate(LocalDateTime.now())
                .build();
    }


}
