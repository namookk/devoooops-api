package info.devoooops.payload.board;

import info.devoooops.common.consts.BoardStatus;
import lombok.*;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class BoardResponse {

    private Collection<Board> boards;

    @Builder
    public BoardResponse(Collection<Board> boards) {
        this.boards = boards;
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @ToString
    public static class Board {
        private Long boardId;

        private String boardName;

        private BoardStatus status;

        @Builder
        public Board(Long boardId, String boardName, BoardStatus status) {
            this.boardId = boardId;
            this.boardName = boardName;
            this.status = status;
        }
    }
}
