package info.devoooops.service.user.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import info.devoooops.common.error.ErrorConst;
import info.devoooops.common.error.exception.DevBadRequestException;
import info.devoooops.entity.user.User;
import info.devoooops.payload.user.UserDto;
import info.devoooops.repository.user.UserRepository;
import info.devoooops.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final PasswordEncoder passwordEncoder;
//    @Override
//    public User findById(String cid) throws Exception{
//
//        QUser quser = QUser.user;
//
//        return jpaQueryFactory.selectFrom(quser)
//                .where(quser.cid.eq(cid))
//                .fetchOne();
//        //return userRepository.findById(cid)
//        //   .orElseThrow(() -> new DevNotFoundException(ErrorConst.UNKNOWN_ERROR));
//    }

    @Override
    public Optional<User> signUpUser(UserDto.SignUpRequest request) throws Exception{
        if(!checkDuplicate(request.getUserId())) throw new DevBadRequestException(ErrorConst.INVALID_PARAMETER_ERROR);
        User user = userRepository.save(this.getUserFromSignUpRequest(request));
        return Optional.of(user);
    }

    private User getUserFromSignUpRequest(UserDto.SignUpRequest request){
        if(request.getProfileImgFile() != null && request.getProfileImgFile().getSize() > 0){
            //TODO: 프로필 이미지 업로드 구현
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setCid(userRepository.getNewCid());

        return User.fromSignUpRequest(request);
    }

    @Override
    public Boolean checkDuplicate(String userId) throws Exception{
        return userRepository.findByUserId(userId).isEmpty();
    }
}
