package info.devoooops.user.repository;

import info.devoooops.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUserId(String userId);

    @Query(value = "select fnc_newcid()", nativeQuery = true)
    String getNewCid();
}
