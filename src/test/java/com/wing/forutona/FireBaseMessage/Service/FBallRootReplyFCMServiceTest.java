package com.wing.forutona.FireBaseMessage.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.wing.forutona.BaseTest;
import com.wing.forutona.FBall.Domain.FBall;
import com.wing.forutona.FBallReply.Domain.FBallReply;
import com.wing.forutona.FBall.Repository.FBallDataRepository;
import com.wing.forutona.ForutonaUser.Domain.FUserInfo;
import com.wing.forutona.ForutonaUser.Repository.FUserInfoDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
class FBallRootReplyFCMServiceTest extends BaseTest {

    @Autowired
    @Qualifier("FBallRootReplyFCMService")
    FBallReplyFCMService fBallReplyFCMService;

    @Autowired
    FBallDataRepository fBallDataRepository;

    @Autowired
    FUserInfoDataRepository fUserInfoDataRepository;




    @Test
    void sendFCM() throws FirebaseMessagingException, JsonProcessingException {
        //given
        FUserInfo testReplyUser = fUserInfoDataRepository.findById("usSMKjNv62eJLkXzFpQux8jWqkT2").get();
        FUserInfo testBallUser = fUserInfoDataRepository.findById("h2q2jl3nRPXZ8809Uvi9KdzSss83").get();
        List<FBall> testFBalls = fBallDataRepository.findByUid(testBallUser);
        FBall testBall = testFBalls.get(0);

        FBallReply fBallReply = FBallReply.builder().
                replyUuid("testUUid")
                .replyBallUuid(testBall)
                .replyDepth(0L)
                .replyText("testUtil")
                .replyUid(testReplyUser)
                .replyUpdateDateTime(LocalDateTime.now())
                .replyNumber(0L)
                .replyUploadDateTime(LocalDateTime.now())
                .replySort(0L)
                .build();

        //when
        fBallReplyFCMService.sendFCM(fBallReply);
        //then


    }
}