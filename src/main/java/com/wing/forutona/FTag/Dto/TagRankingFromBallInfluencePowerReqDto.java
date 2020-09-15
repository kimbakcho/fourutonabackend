package com.wing.forutona.FTag.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagRankingFromBallInfluencePowerReqDto {
    double userLatitude;
    double userLongitude;
    double mapCenterLatitude;
    double mapCenterLongitude;
}
