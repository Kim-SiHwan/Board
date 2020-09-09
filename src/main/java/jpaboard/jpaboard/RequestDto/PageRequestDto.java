package jpaboard.jpaboard.RequestDto;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
public class PageRequestDto {
    private static final int default_size = 10;
    private static final int default_max_size = 50;

    private int page;
    private int size;

    public PageRequestDto(){
        page=0;
        size=default_size;
    }

    public void setPage ( int page ){
        this.page = page<0 ? 0 : page;
    }

    public void setSize( int size ){
        this.size = size<default_size || size>default_max_size ? default_size : size;
    }




}
