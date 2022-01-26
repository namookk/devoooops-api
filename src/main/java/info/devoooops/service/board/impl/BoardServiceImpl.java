package info.devoooops.service.board.impl;

import info.devoooops.entity.board.Board;
import info.devoooops.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final Board
    @Override
    public List<Board> getAllBoards() {
        return null;
    }
}
