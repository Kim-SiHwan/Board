package jpaboard.jpaboard.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private int likes;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder(builderClassName = "createReply", builderMethodName = "createReply")
    public Reply(Long id, String content, LocalDateTime createDate, int likes, Member member, Board board) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.likes = likes;
        this.member = member;
        this.board = board;
    }

    public void changeLikes(int likes){
        this.likes+=likes;
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
