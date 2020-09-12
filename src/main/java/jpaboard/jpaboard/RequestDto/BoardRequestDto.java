package jpaboard.jpaboard.RequestDto;

import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.domain.Reply;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardRequestDto {
    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요.")
    @Size(min = 1 , message = "1자이상 입력해주세요.")
    private String content;

    private LocalDateTime createDate;

    private int read;

    private Long memberId;

    private List<Reply> replies = new ArrayList<>();

    public Board toEntity(BoardRequestDto boardRequestDto){
        return Board.makeBoard()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .createDate(LocalDateTime.now())
                .read(0)
                .build();
    }













}
