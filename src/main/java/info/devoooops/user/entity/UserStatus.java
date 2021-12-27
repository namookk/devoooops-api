package info.devoooops.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    Y("정상"),
    N("탈퇴"),
    D("휴면");

    private final String desc;
}
