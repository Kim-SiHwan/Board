package jpaboard.jpaboard.RequestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PageRequestDto {
    private static final int default_size = 10;
    private static final int default_max_size = 50;

    private int page;
    private int size;


}
