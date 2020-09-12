package jpaboard.jpaboard.domain;


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
    private int read;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardLike> boardLikes = new ArrayList<>();

    public void addReadCount() {
        this.read += 1;
    }

    public void setMember(Member getMember) {
        member = getMember;
        member.getBoards().add(this);
    }

    @Builder(builderClassName = "makeBoard", builderMethodName = "makeBoard")
    public Board(Long id, String title, String content, LocalDateTime createDate, int read) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.read = read;
    }
}
