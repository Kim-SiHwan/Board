package jpaboard.jpaboard.responseDto;

import jpaboard.jpaboard.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private String userName;
    private int read;
    private int likes;
    private int replyCount;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createDate = board.getCreateDate();
        this.userName = board.getMember().getUserName();
        this.read= board.getRead();
        this.likes= board.getLikes();
        this.replyCount=board.getReplies().size();
    }
}
