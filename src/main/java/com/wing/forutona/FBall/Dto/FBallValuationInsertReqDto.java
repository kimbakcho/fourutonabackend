package com.wing.forutona.FBall.Dto;

import lombok.Data;

@Data
public class FBallValuationInsertReqDto {
    String valueUuid;
    String ballUuid;
    String uid;
    Long upAndDown;
}
