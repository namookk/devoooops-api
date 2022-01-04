package info.devoooops.service.user;

import info.devoooops.entity.user.User;

public interface UserService{
    User findById(String cid) throws Exception;
}
