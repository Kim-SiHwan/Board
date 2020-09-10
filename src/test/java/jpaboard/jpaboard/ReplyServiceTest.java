package jpaboard.jpaboard;

import jpaboard.jpaboard.RequestDto.ReplyRequestDto;
import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.domain.Reply;
import jpaboard.jpaboard.service.BoardService;
import jpaboard.jpaboard.service.MemberService;
import jpaboard.jpaboard.service.ReplyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReplyServiceTest {
    @Autowired
    public BoardService boardService;
    @Autowired
    public MemberService memberService;
    @Autowired
    public ReplyService replyService;

    @Test
    public void makeReply (){
    //given
        Reply reply = Reply.createReply()
                .board(null)
                .member(null)
                .content("리플")
                .createDate(LocalDateTime.now())
                .likes(0)
                .build();
    //when
        Long replyId = replyService.addReply(reply,1L,200L);
        Reply getReply = replyService.findOne(replyId);
    //then
        assertEquals(reply,getReply);
        System.out.println(getReply.getContent());
        System.out.println(getReply.getMember().getUserName());
        System.out.println(getReply.getBoard().getTitle());

    }

    @Test
    public void replyList (){
    //given
        for (int i = 0; i < 5; i++) {
            Reply reply = Reply.createReply()
                    .board(null)
                    .member(null)
                    .content("리플"+i)
                    .createDate(LocalDateTime.now())
                    .likes(0)
                    .build();
            replyService.addReply(reply,1L,200L);
        }
    //when
        List<Reply> list = replyService.findAll(200L);
    //then
        assertEquals(5,list.size());

    }
}
