package jpaboard.jpaboard.domain;


import jpaboard.jpaboard.RequestDto.BoardRequestDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Getter
@ToString(exclude = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private int likes;
    private int read;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board")
    private List<Reply> replies = new ArrayList<>();

    public void changeLikes(int likes){
        this.likes+=likes;
    }
    public void addReadCount() {
        this.read += 1;
    }

    public void setMember(Member getMember) {
        member = getMember;
        member.getBoards().add(this);
    }

    @Builder(builderClassName = "makeBoard", builderMethodName = "makeBoard")
    public Board(Long id, String title, String content, LocalDateTime createDate, int likes, int read, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.likes = likes;
        this.read = read;
        this.member = member;
    }
}
