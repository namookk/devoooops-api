package info.devoooops.model.audit;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDateAudit is a Querydsl query type for DateAudit
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QDateAudit extends EntityPathBase<DateAudit> {

    private static final long serialVersionUID = 2120263935L;

    public static final QDateAudit dateAudit = new QDateAudit("dateAudit");

    public final DateTimePath<java.time.Instant> regDate = createDateTime("regDate", java.time.Instant.class);

    public final StringPath regId = createString("regId");

    public final DateTimePath<java.time.Instant> updDate = createDateTime("updDate", java.time.Instant.class);

    public final StringPath updId = createString("updId");

    public QDateAudit(String variable) {
        super(DateAudit.class, forVariable(variable));
    }

    public QDateAudit(Path<? extends DateAudit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDateAudit(PathMetadata metadata) {
        super(DateAudit.class, metadata);
    }

}

