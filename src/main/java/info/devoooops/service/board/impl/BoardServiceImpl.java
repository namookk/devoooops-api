package info.devoooops.service.board.impl;

import info.devoooops.common.error.exception.DevNotFoundException;
import info.devoooops.payload.board.BoardResponse;
import info.devoooops.repository.board.BoardRepository;
import info.devoooops.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public BoardResponse getAllBoards() throws DevNotFoundException {
        return BoardResponse
            .builder()
            .boards(
                    StreamSupport.stream(
                            this.boardRepository.findAll(Sort.by(Sort.Direction.DESC, "boardId"))
                                    .spliterator(),false)
                    .map(b -> BoardResponse
                            .Board
                            .builder()
                            .boardId(b.getBoardId())
                            .boardName(b.getBoardName())
                            .status(b.getStatus())
                            .build())
                        .collect(Collectors.toList())
            ).build();
    }
}
