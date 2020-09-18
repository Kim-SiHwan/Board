package jpaboard.jpaboard.responseDto;

import jpaboard.jpaboard.domain.Reply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReplyResponseDto {
    private Long id;
    private String userName;
    private String content;
    private LocalDateTime createDate;
    private int replyLikeCount;

    public ReplyResponseDto(Reply reply){
        this.id= reply.getId();
        this.userName=reply.getMember().getUserName();
        this.content= reply.getContent();
        this.createDate=reply.getCreateDate();
        this.replyLikeCount=reply.getReplyLikes().size();
    }

    @Data
    @AllArgsConstructor
    public static class Result <T>{
        private T data;

    }
}
