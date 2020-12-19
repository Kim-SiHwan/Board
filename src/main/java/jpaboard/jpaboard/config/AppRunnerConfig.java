package jpaboard.jpaboard.config;

import jpaboard.jpaboard.domain.Address;
import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.domain.Reply;
import jpaboard.jpaboard.service.BoardService;
import jpaboard.jpaboard.service.LikeService;
import jpaboard.jpaboard.service.MemberService;
import jpaboard.jpaboard.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppRunnerConfig implements ApplicationRunner {

    private final PasswordEncoder pwEncoder;
    private final MemberService memberService;
    private final BoardService boardService;
    private final ReplyService replyService;
    private final LikeService likeService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member member1 = Member.createMember()
                .userName("오이")
                .password(pwEncoder.encode("123"))
                .address(new Address("감자", "토마토", "양배추"))
                .joinDate(LocalDateTime.now())
                .role("USER")
                .build();
        memberService.join(member1);

        Member member2 = Member.createMember()
                .userName("배추")
                .password(pwEncoder.encode("123"))
                .address(new Address("감자", "양상추", "고구마"))
                .joinDate(LocalDateTime.now())
                .role("USER")
                .build();
        memberService.join(member2);

        Member member = Member.createMember()
                .userName("관리자")
                .password(pwEncoder.encode("123"))
                .address(new Address("인천", "남동", "아파트"))
                .joinDate(LocalDateTime.now())
                .role("ADMIN")
                .build();
        memberService.join(member);
        Long memberId = 1L;

        for (int i = 1; i <= 200; i++) {
            memberId = memberId == 1L ? 2L : 1L;
            String userName = memberId == 1L ? "오이" : "배추";
            Board board = Board.makeBoard()
                    .title("Title" + i)
                    .content("Content" + i)
                    .createDate(LocalDateTime.now())
                    .read(i % 7)
                    .build();
            boardService.upload(board, userName);

            for (int j = 1; j <= 10; j++) {
                memberId = memberId == 1L ? 2L : 1L;
                userName = memberId == 1L ? "오이" : "배추";
                Reply reply = Reply.createReply()
                        .content("Reply" + j)
                        .createDate(LocalDateTime.now())
                        .build();
                replyService.addReply(reply, userName, board.getId());
            }

        }
        likeService.addLike("오이", 200L, "board");
        likeService.addLike("배추", 200L, "board");
        likeService.addLike("관리자", 200L, "board");

    }
}
