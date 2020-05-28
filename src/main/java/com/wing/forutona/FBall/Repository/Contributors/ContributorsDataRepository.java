package com.wing.forutona.FBall.Repository.Contributors;

import com.wing.forutona.FBall.Domain.Contributors;
import com.wing.forutona.FBall.Domain.FBall;
import com.wing.forutona.ForutonaUser.Domain.FUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContributorsDataRepository extends JpaRepository<Contributors,Long> {
    List<Contributors> findContributorsByUidIsAndBallUuidIs(FUserInfo uid, FBall ballUuid);
    int deleteContributorsByUidIsAndBallUuidIs(FUserInfo uid, FBall ballUuid);
}
