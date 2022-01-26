package info.devoooops.controller.board;

import info.devoooops.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public ResponseEntity<?> findAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }
}
