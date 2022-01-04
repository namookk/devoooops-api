package info.devoooops.service.user.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import info.devoooops.common.error.ErrorConst;
import info.devoooops.common.error.exception.DevNotFoundException;
import info.devoooops.entity.user.QUser;
import info.devoooops.entity.user.User;
import info.devoooops.repository.user.UserRepository;
import info.devoooops.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public User findById(String cid) throws Exception{

        QUser quser = QUser.user;

        return jpaQueryFactory.selectFrom(quser)
                .where(quser.cid.eq(cid))
                .fetchOne();
        //return userRepository.findById(cid)
        //   .orElseThrow(() -> new DevNotFoundException(ErrorConst.UNKNOWN_ERROR));
    }

}
