package info.devoooops.service.board;

import info.devoooops.common.error.exception.DevNotFoundException;
import info.devoooops.payload.board.BoardResponse;

public interface BoardService {
    BoardResponse getAllBoards() throws DevNotFoundException;
}
