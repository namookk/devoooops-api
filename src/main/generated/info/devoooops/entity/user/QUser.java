package info.devoooops.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -649387463L;

    public static final QUser user = new QUser("user");

    public final info.devoooops.model.audit.QDateAudit _super = new info.devoooops.model.audit.QDateAudit(this);

    public final StringPath birthDate = createString("birthDate");

    public final NumberPath<Integer> career = createNumber("career", Integer.class);

    public final StringPath cid = createString("cid");

    public final StringPath devField = createString("devField");

    public final StringPath gender = createString("gender");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final DateTimePath<java.time.Instant> passwordDate = createDateTime("passwordDate", java.time.Instant.class);

    public final StringPath profileImgnm = createString("profileImgnm");

    public final StringPath profilePath = createString("profilePath");

    //inherited
    public final DateTimePath<java.time.Instant> regDate = _super.regDate;

    //inherited
    public final StringPath regId = _super.regId;

    public final EnumPath<UserStatus> status = createEnum("status", UserStatus.class);

    public final StringPath tempPasswordFl = createString("tempPasswordFl");

    //inherited
    public final DateTimePath<java.time.Instant> updDate = _super.updDate;

    //inherited
    public final StringPath updId = _super.updId;

    public final StringPath userId = createString("userId");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

