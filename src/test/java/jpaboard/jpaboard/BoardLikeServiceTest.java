package jpaboard.jpaboard;

import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.domain.BoardLike;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.repository.BoardLikeRepository;
import jpaboard.jpaboard.repository.BoardRepository;
import jpaboard.jpaboard.service.BoardLikeService;
import jpaboard.jpaboard.service.BoardService;
import jpaboard.jpaboard.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardLikeServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardLikeRepository boardLikeRepository;
    @Autowired
    private BoardLikeService boardLikeService;

    @Test
    public void addLike (){
    //given
        Member member = Member.createMember()
                .userName("가지")
                .build();
        memberService.join(member);
        Board board = Board.makeBoard()
                .title("가지T")
                .content("가지C")
                .build();
        boardService.upload(board, member.getId());

    //when
        boardLikeService.addLike(member.getId(), board.getId());
    //then
        assertEquals(1,boardLikeRepository.count());
        System.out.println(board.getBoardLikes().size());

    }

    @Test
    public void removeLike (){
    //given
        boardLikeService.addLike(1L,50L);
        BoardLike boardLike = boardLikeRepository.findOne(1L);
    //when
        boardLikeRepository.remove(boardLike);
    //then
        assertEquals(null,boardLike);

    }

}
