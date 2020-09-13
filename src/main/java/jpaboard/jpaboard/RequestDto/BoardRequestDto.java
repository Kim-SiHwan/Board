package jpaboard.jpaboard.RequestDto;

import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.domain.Reply;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardRequestDto {

    private Long boardId;

    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요.")
    @Size(min = 1 , message = "1자이상 입력해주세요.")
    private String content;

    private LocalDateTime createDate;

    private int read;

    private String userName;

    private List<Reply> replies = new ArrayList<>();

    public Board toEntity(BoardRequestDto boardRequestDto){
        return Board.makeBoard()
                .id(boardRequestDto.getBoardId())
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .createDate(LocalDateTime.now())
                .read(0)
                .build();
    }













}
