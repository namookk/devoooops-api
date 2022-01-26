package info.devoooops.entity.board;

import info.devoooops.common.consts.BoardStatus;
import info.devoooops.model.audit.DateAudit;

import javax.persistence.Table;

@Table(name="BOARD")
public class Board extends DateAudit {

    private Long boardId;

    private String boardName;

    private BoardStatus status;

}