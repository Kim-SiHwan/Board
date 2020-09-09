package jpaboard.jpaboard;


import jpaboard.jpaboard.RequestDto.BoardRequestDto;
import jpaboard.jpaboard.domain.Address;
import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.repository.BoardRepository;
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
    public void makeBoardTest (){
    //given
        Member member = createMember();
        memberService.join(member);
    //when
        Board board = makeBoard();
        board.setMember(member);
        boardService.upload(board, member.getId());

        Board getBoard = boardRepository.findOne(board.getId());
    //then

        assertEquals(board,getBoard);
        System.out.println(board.getMember().getUserName());
    }

    @Test
    public void forDataSql (){
    //given
        for(int i=1; i<=50; i++){
            System.out.println("insert into board (member_id , title, content ,likes , read , create_date ) values (1 , '오이"+i+"T', '오이"+i+"C', 0, 0 ,now() );");
            System.out.println("insert into board (member_id , title, content ,likes , read , create_date ) values (2 , '가지"+i+"T', '가지"+i+"C', 0, 0 ,now() );");
        }
    //when

    //then

    }

    public Member createMember(){
        return  Member.createMember()
                .userName("오이")
                .address(new Address("감자","고구마","가지"))
                .build();
    }

    public Board makeBoard(){
        return Board.makeBoard()
                .member(null)
                .likes(0)
                .read(0)
                .title("오이T")
                .content("오이C")
                .createDate(LocalDateTime.now())
                .build();
    }


}
