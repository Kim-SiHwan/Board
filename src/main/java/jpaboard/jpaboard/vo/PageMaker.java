package jpaboard.jpaboard.vo;

import jpaboard.jpaboard.RequestDto.PageRequestDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PageMaker {

    private int currentPage;
    private int totalPage;
    private int prevPage;
    private int nextPage;
    private int tempStartNum;
    private int tempEndNum;
    private List<Integer> pageList;

    public PageMaker(PageRequestDto pageRequestDto, int totalBoard) {
        this.currentPage=pageRequestDto.getPage();
        this.prevPage=0;
        this.nextPage=1;
        this.pageList = new ArrayList<>();
        setTotalPage(totalBoard,pageRequestDto.getSize());
        calcPages();
    }

    public void setTotalPage(int totalBoard, int size) {
        totalPage = totalBoard/size;
        if (totalBoard > size * totalPage) {
            totalPage++;
        }
    }

    public void calcPages(){
        int tempEnd = (int) (Math.ceil(this.currentPage/10.0)*10);
        int startNum = tempEnd-9;
        tempStartNum=startNum;
        for(int i=startNum; i<=this.currentPage; i++){
            tempStartNum-=1;
        }
        this.prevPage = tempStartNum<=0 ? 0 : 1;
        tempStartNum = tempStartNum<=0 ?1 : tempStartNum;
        if(this.totalPage<tempEnd){
            nextPage=0;
            tempEnd=this.totalPage;
        }
        tempEndNum=tempEnd+1;
        for(int i=startNum; i<=tempEnd; i++){
            pageList.add(i);
        }
    }
}
