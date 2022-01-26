package info.devoooops.repository.user;

import info.devoooops.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId);

    Optional<User> findByUserIdAndName(String userId, String name);

    @Query(value = "select fnc_newcid()", nativeQuery = true)
    String getNewCid();
}
