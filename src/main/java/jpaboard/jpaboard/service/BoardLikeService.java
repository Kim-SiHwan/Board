package jpaboard.jpaboard.service;

import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.domain.BoardLike;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.repository.BoardLikeRepository;
import jpaboard.jpaboard.repository.BoardRepository;
import jpaboard.jpaboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardLikeService {
    private final BoardLikeRepository boardLikeRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public String addLike(Long memberId, Long boardId){
        Member member = memberRepository.findOne(memberId);
        Board board = boardRepository.findOne(boardId);
        String msg = "";
        if(!checkBefore(memberId,boardId)){
            BoardLike boardLike = new BoardLike();
            boardLike.setMember(member);
            boardLike.setBoard(board);
            boardLikeRepository.save(boardLike);
            msg="추천완료";
        }else{
            BoardLike boardLike = boardLikeRepository.findByAllId(memberId,boardId);
            boardLikeRepository.remove(boardLike);
            msg="추천취소";
        }
        return msg;
    }

    public boolean checkBefore(Long memberId, Long boardId){
        try {
            BoardLike boardLike = boardLikeRepository.findByAllId(memberId, boardId);
        }catch (Exception e){
            System.out.println(e.getCause()+" "+e.getMessage());
            return false;
        }

        return true;
    }
}
