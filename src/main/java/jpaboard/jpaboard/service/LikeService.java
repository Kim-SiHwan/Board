package jpaboard.jpaboard.service;

import jpaboard.jpaboard.domain.*;
import jpaboard.jpaboard.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {
    private final BoardLikeRepository boardLikeRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final ReplyLikeRepository replyLikeRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public String addLike(String userName, Long id, String type) {
        Long memberId = memberRepository.getIdByName(userName);
        Member member = memberRepository.findOne(memberId);
        String msg = "";
        if (!checkBefore(memberId, id, type)) {
            if (type.equals("board")) {
                Board board = boardRepository.findOne(id);
                BoardLike boardLike = new BoardLike();
                boardLike.setMember(member);
                boardLike.setBoard(board);
                boardLikeRepository.save(boardLike);
            }else{
                Reply reply = replyRepository.findOne(id);
                ReplyLike replyLike = new ReplyLike();
                replyLike.setMember(member);
                replyLike.setReply(reply);
                replyLikeRepository.save(replyLike);
            }
            msg = "추천완료";
        } else {
            if(type.equals("board")) {
                BoardLike boardLike = boardLikeRepository.findByAllId(memberId, id);
                boardLikeRepository.remove(boardLike);
            }else{
                ReplyLike replyLike = replyLikeRepository.findByAllId(memberId, id);
                replyLikeRepository.remove(replyLike);
            }
            msg = "추천취소";
        }
        return msg;
    }

    public boolean checkBefore(Long memberId, Long id, String type) {
        if (type.equals("board")) {
            try {
                BoardLike boardLike = boardLikeRepository.findByAllId(memberId, id);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else {
            try {
                ReplyLike replyLike = replyLikeRepository.findByAllId(memberId, id);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
}
