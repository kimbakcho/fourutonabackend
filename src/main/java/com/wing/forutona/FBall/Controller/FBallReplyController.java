package com.wing.forutona.FBall.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.wing.forutona.CustomUtil.AuthFireBaseJwtCheck;
import com.wing.forutona.CustomUtil.FFireBaseToken;
import com.wing.forutona.FBall.Dto.FBallReplyInsertReqDto;
import com.wing.forutona.FBall.Dto.FBallReplyReqDto;
import com.wing.forutona.FBall.Dto.FBallReplyResDto;
import com.wing.forutona.FBall.Dto.FBallReplyUpdateReqDto;
import com.wing.forutona.FBall.Service.FBallReply.FBallReplyInsertServiceFactory;
import com.wing.forutona.FBall.Service.FBallReply.FBallReplyService;
import com.wing.forutona.FireBaseMessage.Service.FBallReplyFCMServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FBallReplyController {

    final FBallReplyService fBallReplyService;

    final FBallReplyInsertServiceFactory fBallReplyInsertServiceFactory;

    final FBallReplyFCMServiceFactory fBallReplyFCMServiceFactory;


    @GetMapping(value = "/v1/FBallReply")
    public Page<FBallReplyResDto> getFBallReply(FBallReplyReqDto reqDto, Pageable pageable) {
        return fBallReplyService.getFBallReply(reqDto, pageable);
    }


    @AuthFireBaseJwtCheck
    @PostMapping(value = "/v1/FBallReply")
    public FBallReplyResDto insertFBallReply(@RequestBody FBallReplyInsertReqDto reqDto, FFireBaseToken fireBaseToken) throws FirebaseMessagingException, JsonProcessingException {
        return fBallReplyService.insertFBallReply(fireBaseToken,
                fBallReplyInsertServiceFactory.getFBallReplyInsertServiceFactory(reqDto),
                fBallReplyFCMServiceFactory.getFBallReplyFCMServiceFactory(reqDto),
                reqDto);
    }


    @AuthFireBaseJwtCheck
    @PutMapping(value = "/v1/FBallReply")
    public FBallReplyResDto updateFBallReply(@RequestBody FBallReplyUpdateReqDto reqDto, FFireBaseToken fireBaseToken) throws Throwable {
        return fBallReplyService.updateFBallReply(fireBaseToken, reqDto);
    }


    @AuthFireBaseJwtCheck
    @DeleteMapping(value = "/v1/FBallReply/{replyUuid}")
    public FBallReplyResDto deleteFBallReply(@PathVariable String replyUuid, FFireBaseToken fireBaseToken) throws Throwable {
        return fBallReplyService.deleteFBallReply(fireBaseToken, replyUuid);
    }

}
