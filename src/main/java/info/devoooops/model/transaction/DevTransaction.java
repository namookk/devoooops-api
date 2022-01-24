package info.devoooops.model.transaction;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//TODO : RollbackFor관련 추후 논의 필요
@Transactional
public @interface DevTransaction {
}
