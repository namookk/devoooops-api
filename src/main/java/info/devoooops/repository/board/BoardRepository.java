package info.devoooops.repository.board;

import info.devoooops.entity.board.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<Board, String> {

}
