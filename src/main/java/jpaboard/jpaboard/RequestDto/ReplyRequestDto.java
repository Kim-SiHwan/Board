package jpaboard.jpaboard.RequestDto;

import jpaboard.jpaboard.domain.Reply;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
public class ReplyRequestDto {

    @NotEmpty(message = "내용을 입력해주세요.")
    @Size(min = 1, message = "1자이상 입력해주세요.")
    private String content;

    private LocalDateTime createDate;

    private String userName;

    private Long boardId;

    public Reply toEntity(ReplyRequestDto replyRequestDto) {
        return Reply.createReply()
                .content(replyRequestDto.getContent())
                .createDate(LocalDateTime.now())
                .build();
    }

}
