package shop.mtcoding.jobara.apply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.jobara.apply.model.Apply;
import shop.mtcoding.jobara.apply.model.ApplyRepository;
import shop.mtcoding.jobara.board.model.BoardRepository;
import shop.mtcoding.jobara.common.ex.CustomApiException;
import shop.mtcoding.jobara.common.util.Verify;

@Service
public class ApplyService {
    
    @Autowired
    ApplyRepository applyRepository;

    @Autowired
    BoardRepository boardRepository;

    @Transactional
    public void insertApply(Integer id, Integer usPrincipalId) {
        Verify.validateObject(boardRepository.findById(id), "존재하지 않는 공고입니다.");
        Apply applyTemp = new Apply(usPrincipalId, id);
        Verify.validateApiObject(applyRepository.findByUserIdAndBoardId(applyTemp), "이미 지원한 공고입니다.");
        try {
            applyRepository.insert(applyTemp);
        } catch (Exception e) {
            throw new CustomApiException("서버 오류로 인한 지원 실패",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
