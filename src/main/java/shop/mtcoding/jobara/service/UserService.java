package shop.mtcoding.jobara.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.jobara.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.jobara.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.jobara.dto.user.UserReq.UserUpdateReqDto;
import shop.mtcoding.jobara.ex.CustomException;
import shop.mtcoding.jobara.model.User;
import shop.mtcoding.jobara.model.UserRepository;
import shop.mtcoding.jobara.util.Verify;

@Service
public class UserService {

      @Autowired
      private UserRepository userRepository;

      @Transactional(readOnly = true)
      public List<User> getUser() {
            List<User> userPS = userRepository.findAll();
            return userPS;
      }

      @Transactional(readOnly = true)
      public User getUser(UserLoginReqDto userLoginReqDto) {
            User userPS = userRepository.findByUsernameAndPassword(
                        new User(userLoginReqDto.getUsername(), userLoginReqDto.getPassword()));
            Verify.validateObject(userPS, "유저네임이나 암호를 확인해주세요.");
            return userPS;
      }

      @Transactional(readOnly = true)
      public User getUser(int id) {
            User userPS = userRepository.findById(id);
            Verify.validateObject(userPS, "서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
            return userPS;
      }

      @Transactional
      public void insertUser(UserJoinReqDto userJoinReqDto) {
            int result = userRepository.insert(new User(userJoinReqDto.getUsername(),
                        userJoinReqDto.getPassword(),
                        userJoinReqDto.getEmail()));
            if (result != 1) {
                  throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
            }
      }

      @Transactional
      public User updateUser(UserUpdateReqDto userUpdateReqDto, Integer id) {
            int result = userRepository.updateById(new User(
                        id,
                        userUpdateReqDto.getUsername(),
                        userUpdateReqDto.getPassword(),
                        userUpdateReqDto.getEmail(),
                        userUpdateReqDto.getAddress(),
                        userUpdateReqDto.getDetailAddress(),
                        userUpdateReqDto.getTel(),
                        userUpdateReqDto.getCareer()));
            if (result != 1) {
                  throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            User userPS = userRepository.findById(id);
            return userPS;
      }
}
