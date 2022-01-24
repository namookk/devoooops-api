package info.devoooops.service.user.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import info.devoooops.common.error.ErrorConst;
import info.devoooops.common.error.exception.*;
import info.devoooops.entity.user.User;
import info.devoooops.payload.auth.UserPrincipal;
import info.devoooops.payload.user.UserDto;
import info.devoooops.repository.user.UserRepository;
import info.devoooops.service.user.UserService;
import info.devoooops.util.MailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final PasswordEncoder passwordEncoder;
    private final MailUtil mailUtil;

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
    public Optional<User> findById(String cid) throws Exception{
        return userRepository.findById(cid);
    }

    private void uploadProfileImg(User user, MultipartFile file) throws Exception{
        if(file != null && file.getSize() > 0){
            String imgPath = null;
            String imgName = null;
            //TODO: 프로필 이미지 업로드 구현
            user.updateProfileImg(imgName, imgPath);
        }
    }

    @Override
    public Optional<User> signUpUser(UserDto.SignUpRequest request) throws Exception{
        checkDuplicate(request.getUserId());

        User user = userRepository.save(this.getUserFromSignUpRequest(request));
        return Optional.of(user);
    }

    @Override
    public Boolean checkDuplicate(String userId) throws DevRuntimeException {
        userRepository.findByUserId(userId)
                .ifPresent(user -> {
                    throw new DevRuntimeException(ErrorConst.DUPLICATE_ID);
                });
        return true;
    }

    private User getUserFromSignUpRequest(UserDto.SignUpRequest request) throws Exception{
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setCid(userRepository.getNewCid());

        User user = User.fromSignUpRequest(request);
        this.uploadProfileImg(user, request.getProfileImgFile());

        return user;
    }

    @Override
    public Optional<User> getMyInfo() throws Exception {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(userPrincipal.getCid());
    }

    @Override
    public void checkPassword(String password) throws Exception {
        User user = this.getMyInfo()
                .orElseThrow(() -> new DevInternalServerErrorException(ErrorConst.UNKNOWN_ERROR));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Override
    public void findPassword(String userId, String name) throws Exception {
        User user = userRepository.findByUserIdAndName(userId, name)
                .orElseThrow(() -> new UsernameNotFoundException("유저 정보를 찾을 수 없습니다."));

        String tempPassword = RandomStringUtils.randomAlphanumeric(8);

        mailUtil.sendMail(userId, "임시 비밀번호입니다.", "임시 비밀번호 : " + tempPassword);

        String encodePassword = passwordEncoder.encode(tempPassword);
        user.findPassword(encodePassword);

        userRepository.save(user);
    }

    @Override
    public void changePassword(String password) throws Exception {
        User user = this.getMyInfo()
                .orElseThrow(() -> new DevInternalServerErrorException(ErrorConst.UNKNOWN_ERROR));

        String encodePassword = passwordEncoder.encode(password);
        user.updatePassword(encodePassword);

        userRepository.save(user);
    }

    @Override
    public void changeMyInfo(UserDto.ChangeInfoRequest request) throws Exception{
        User user = this.getMyInfo()
                .orElseThrow(() -> new DevInternalServerErrorException(ErrorConst.UNKNOWN_ERROR));
        user.update(request);
        this.uploadProfileImg(user, request.getProfileImgFile());

        userRepository.save(user);
    }

    @Override
    public User withdrawUser() throws Exception{
        User user = this.getMyInfo()
                .orElseThrow(() -> new DevInternalServerErrorException(ErrorConst.UNKNOWN_ERROR));

        user.withdraw();

        return userRepository.save(user);
    }
}
