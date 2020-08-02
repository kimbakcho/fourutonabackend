package com.wing.forutona.FBall.Domain;

import com.wing.forutona.ForutonaUser.Domain.FUserInfo;
import com.wing.forutona.ForutonaUser.Domain.FUserInfoSimple;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FBallValuation {
    @Id
    String valueUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ballUuid")
    FBall ballUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    FUserInfoSimple uid;

    Long ballLike = 0L;
    Long ballDislike = 0L;
    Long point = 0L;

    @Builder
    public FBallValuation(String valueUuid,FBall ballUuid,FUserInfoSimple uid,Long point){
        this.valueUuid = valueUuid;
        this.ballUuid = ballUuid;
        this.uid =uid;
        this.point = point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public void setBallLike(Long like) {
        this.ballLike = like;
    }

    public void setBallDislike(Long dislike) {
        this.ballDislike = dislike;
    }


}
