package shop.mtcoding.jobara.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.jobara.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.jobara.ex.CustomException;
import shop.mtcoding.jobara.model.User;
import shop.mtcoding.jobara.model.UserRepository;

@Service
public class UserService {

      @Autowired
      private UserRepository userRepository;

      public User login(UserLoginReqDto userLoginReqDto) {
            User userPS = userRepository.findByUsernameAndPassword(
                        new User(userLoginReqDto.getUsername(), userLoginReqDto.getPassword()));
            if (userPS == null) {
                  throw new CustomException("username이나 password가 잘못되었습니다.");
            }
            return userPS;
      }
}
