package info.devoooops.common.consts;

import lombok.Getter;

@Getter
public enum BoardStatus {
    Y("사용"),
    N("미사용"),
    D("삭제");

    private String desc;

    BoardStatus(String desc) {
        this.desc = desc;
    }
}