package jpaboard.jpaboard.responseDto;

import jpaboard.jpaboard.domain.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyResponseDto {
    private Long id;
    private String userName;
    private String content;
    private int likes;
    private LocalDateTime createDate;

    public ReplyResponseDto(Reply reply){
        this.id= reply.getId();
        this.userName=reply.getMember().getUserName();
        this.content= reply.getContent();
        this.likes= reply.getLikes();
        this.createDate=reply.getCreateDate();
    }
}
