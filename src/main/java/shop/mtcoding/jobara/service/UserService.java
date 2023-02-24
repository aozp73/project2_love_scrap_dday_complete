package shop.mtcoding.jobara.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.jobara.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.jobara.ex.CustomException;
import shop.mtcoding.jobara.model.User;
import shop.mtcoding.jobara.model.UserRepository;
import shop.mtcoding.jobara.util.Verify;

@Service
public class UserService {

      @Autowired
      private UserRepository userRepository;

      public User login(UserLoginReqDto userLoginReqDto) {
            User userPS = userRepository.findByUsernameAndPassword(
                        new User(userLoginReqDto.getUsername(), userLoginReqDto.getPassword()));
            Verify.validateObject(userPS, "유저네임이나 암호를 확인해주세요.");
            return userPS;
      }
}
