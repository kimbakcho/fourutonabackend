package com.wing.forutona.FBall.Service;

import com.google.type.LatLng;
import com.vividsolutions.jts.io.ParseException;
import com.wing.forutona.CustomUtil.GisGeometryUtil;
import com.wing.forutona.FBall.Dto.*;
import com.wing.forutona.FBall.Repository.FBallQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface BallListUpService {
    Page<FBallResDto> searchBallListUpFromMapArea(BallFromMapAreaReqDto reqDto, Pageable pageable) throws ParseException;

    Page<FBallResDto> searchBallListUpFromSearchTitle(FBallListUpFromSearchTitleReqDto reqDto, Pageable pageable) throws ParseException;

    Page<FBallResDto> searchBallListUpFromTagName(FBallListUpFromTagReqDto reqDto, Pageable pageable) throws ParseException;

    Page<FBallResDto> searchBallListUpInfluencePower(FBallListUpFromBallInfluencePowerReqDto reqDto, Pageable pageable) throws ParseException;

    Page<FBallResDto> searchBallListUpUserMakerBall(String ballUuid,Pageable pageable) throws ParseException;
}

@Service
@Transactional
@RequiredArgsConstructor
class BallListUpServiceImpl implements BallListUpService {

    final FBallQueryRepository fBallQueryRepository;
    final DistanceOfBallCountToLimitService distanceOfBallCountToLimitService;

    @Override
    public Page<FBallResDto> searchBallListUpFromMapArea(BallFromMapAreaReqDto reqDto, Pageable pageable) throws ParseException {
        return fBallQueryRepository.getBallListUpFromMapArea((BallFromMapAreaReqDto) reqDto, pageable);
    }

    @Override
    public Page<FBallResDto> searchBallListUpFromSearchTitle(FBallListUpFromSearchTitleReqDto reqDto, Pageable pageable) throws ParseException {
        return fBallQueryRepository.getBallListUpFromSearchTitle((FBallListUpFromSearchTitleReqDto) reqDto, pageable);
    }

    @Override
    public Page<FBallResDto> searchBallListUpFromTagName(FBallListUpFromTagReqDto reqDto, Pageable pageable) throws ParseException {
        return fBallQueryRepository.ListUpFromTagName((FBallListUpFromTagReqDto) reqDto, pageable);
    }

    @Override
    public Page<FBallResDto> searchBallListUpInfluencePower(FBallListUpFromBallInfluencePowerReqDto reqDto, Pageable pageable) throws ParseException {
        FBallListUpFromBallInfluencePowerReqDto acceptReqDto = (FBallListUpFromBallInfluencePowerReqDto) reqDto;
        int findDistanceRangeLimit = distanceOfBallCountToLimitService.distanceOfBallCountToLimit(
                LatLng.newBuilder().setLatitude(reqDto.getLatitude()).setLongitude(reqDto.getLongitude()).build());

        return fBallQueryRepository.getBallListUpFromBallInfluencePower(
                GisGeometryUtil.createPoint(acceptReqDto.getLatitude(), acceptReqDto.getLongitude())
                , GisGeometryUtil.createSquareFromCenterPosition(
                        LatLng.newBuilder().setLatitude(acceptReqDto.getLatitude()).setLongitude(acceptReqDto.getLongitude()).build(), findDistanceRangeLimit)
                , pageable);
    }

    @Override
    public Page<FBallResDto> searchBallListUpUserMakerBall(String ballUuid, Pageable pageable) throws ParseException {
        return fBallQueryRepository.getUserToMakerBalls(ballUuid, pageable);
    }


}