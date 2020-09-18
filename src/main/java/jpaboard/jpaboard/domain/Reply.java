package jpaboard.jpaboard.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(exclude = {"member","board"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    private String content;

    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "reply", cascade = CascadeType.REMOVE)
    private List<ReplyLike> replyLikes = new ArrayList<>();


    @Builder(builderClassName = "createReply", builderMethodName = "createReply")
    public Reply(Long id, String content, LocalDateTime createDate) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
    }

    public void setMember(Member member){
        this.member=member;
        this.member.getReplies().add(this);
    }

    public void setBoard(Board board) {
        this.board = board;
        this.board.getReplies().add(this);
    }


}
