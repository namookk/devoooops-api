package info.devoooops.entity.board;

import info.devoooops.common.consts.BoardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
@Table(name="BOARD")
public class Board {

    @Id
    @Column(name="board_id", nullable = false)
    private Long boardId;

    @Column(name="board_name", nullable = false)
    private String boardName;

    @Enumerated(value = EnumType.STRING)
    @Column(name="status", nullable = false)
    private BoardStatus status;

    @Builder
    public Board(Long boardId, String boardName, BoardStatus status) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.status = status;
    }
}