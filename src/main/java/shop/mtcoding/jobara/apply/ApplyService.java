package shop.mtcoding.jobara.apply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import shop.mtcoding.jobara.apply.model.Apply;
import shop.mtcoding.jobara.apply.model.ApplyRepository;
import shop.mtcoding.jobara.common.ex.CustomApiException;

@Service
public class ApplyService {
    @Autowired
    private ApplyRepository applyRepository;

    public void insertApply(Integer boardId, Integer principalId) {
        Apply apply = new Apply(boardId, principalId);
        if (applyRepository.findByUserIdAndBoardId(apply) != null) {
            throw new CustomApiException("이미 지원한 공고입니다.");
        }
        try {
            applyRepository.insert(apply);
        } catch (Exception e) {
            throw new CustomApiException("서버 에러 : 지원 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
