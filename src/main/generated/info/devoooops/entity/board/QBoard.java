package info.devoooops.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 1731202313L;

    public static final QBoard board = new QBoard("board");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath boardName = createString("boardName");

    public final EnumPath<info.devoooops.common.consts.BoardStatus> status = createEnum("status", info.devoooops.common.consts.BoardStatus.class);

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

