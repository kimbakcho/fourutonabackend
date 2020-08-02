package com.wing.forutona.FBall.Repository.Contributors;

import com.wing.forutona.FBall.Domain.Contributors;
import com.wing.forutona.FBall.Domain.FBall;
import com.wing.forutona.ForutonaUser.Domain.FUserInfo;
import com.wing.forutona.ForutonaUser.Domain.FUserInfoSimple;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContributorsDataRepository extends JpaRepository<Contributors,Long> {
    Optional<Contributors> findContributorsByUidIsAndBallUuidIs(FUserInfoSimple uid, FBall ballUuid);
    int deleteContributorsByUidIsAndBallUuidIs(FUserInfoSimple uid, FBall ballUuid);
}
