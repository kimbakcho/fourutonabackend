package com.wing.forutona;

import com.grum.geocalc.BoundingArea;
import com.grum.geocalc.EarthCalc;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.vividsolutions.jts.geom.*;

import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import com.wing.forutona.FBall.Dto.FBallResDto;
import com.wing.forutona.FBall.Dto.NearBallFindDistanceReqDto;
import com.wing.forutona.FBall.Repository.FBall.FBallDataRepository;
import com.wing.forutona.FBall.Repository.FBall.FBallQueryRepository;
import com.wing.forutona.FBall.Service.FBallService;
import com.wing.forutona.FTag.Dto.TagRankingReqDto;
import com.wing.forutona.FTag.Dto.TagRankingWrapdto;
import com.wing.forutona.FTag.Repository.FBallTagQueryRepository;
import com.wing.forutona.FTag.Service.FTagService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@ActiveProfiles(profiles = "local")
@Transactional
class ForutonaApplicationTests {

    @Autowired
    FBallDataRepository fBallDataRepository;

    @Autowired
    FBallQueryRepository fBallQueryRepository;

    @Autowired
    FBallTagQueryRepository fBallTagQueryRepository;

    @Autowired
    FBallService fBallService;

    @Autowired
    FTagService fTagService;

    @PersistenceContext
    EntityManager em;

    //https://www.baeldung.com/hibernate-spatial
    @Test
    public void getFindBallCountInDistanceForJpaData() {
        NearBallFindDistanceReqDto reqDto = new NearBallFindDistanceReqDto();
        reqDto.setLatitude(37.51368824280154);
        reqDto.setLongitude(126.8985465914011);
        reqDto.setDistance(100);
        Long count = fBallDataRepository.getFindLocationWithin(createRect(reqDto.getLatitude(), reqDto.getLongitude(), 1000));
    }

    //지도 중심 위치로 부터 주번 볼 검색
    @Test
    public void getFindBallCountInDistanceForQueryDsl() throws ParseException {
        NearBallFindDistanceReqDto reqDto = new NearBallFindDistanceReqDto();
        reqDto.setLatitude(37.51368824280154);
        reqDto.setLongitude(126.8985465914011);
        reqDto.setDistance(1000);
        com.grum.geocalc.Coordinate lat = com.grum.geocalc.Coordinate.fromDegrees(reqDto.getLatitude());
        com.grum.geocalc.Coordinate lng = com.grum.geocalc.Coordinate.fromDegrees(reqDto.getLongitude());
        com.grum.geocalc.Point findPosition = com.grum.geocalc.Point.at(lat, lng);
        BoundingArea area = EarthCalc.around(findPosition, reqDto.getDistance() / 2);
        Coordinate southWest = new Coordinate(area.southWest.longitude, area.southWest.latitude);
        Coordinate northEast = new Coordinate(area.northEast.longitude, area.northEast.latitude);

        Geometry rect = new WKTReader().read(fBallService.createRectPOLYGONStr(area.southWest.longitude,area.southWest.latitude,area.northEast.longitude,area.northEast.latitude));
        rect.setSRID(4326);


        Long count = fBallQueryRepository.getFindBallCountInDistance(rect);
		System.out.println("count = "+ count);
    }



    //일정 범위내에 Ball들의 거리 계산
    @Test
    @Disabled
    public void getFindBallInDistanceForQueryDsl() throws ParseException {
        NearBallFindDistanceReqDto reqDto = new NearBallFindDistanceReqDto();
        reqDto.setLatitude(37.51368824280154);
        reqDto.setLongitude(126.8985465914011);
        reqDto.setDistance(1000);
        Geometry mapCenterCircle = makeCenterPoint(reqDto);
        List<Tuple> balls = fBallQueryRepository.getFindBallInDistanceForQueryDsl(
                fBallService.createCenterPoint(37.4402052,126.79369789999998),
                fBallService.createRect(37.4402052,126.79369789999998,1000)
        );
        for (Tuple ball : balls) {
            System.out.println(ball.get(1,Double.class));
        }
        //아래는 혹시 모를 Geometry 조건등에서 Expressions 을 생성하는 방법 예시이다.
        //Distance Spear 같은 곳에서 QueryDSL 이 요구 한다.
        Expression<Geometry> expressions = Expressions.constant(mapCenterCircle);
    }

    @Test
    public void findTagRankingInDistanceOfInfluencePower() {
        NearBallFindDistanceReqDto reqDto = new NearBallFindDistanceReqDto();
        reqDto.setLatitude(37.51368824280154);
        reqDto.setLongitude(126.8985465914011);
        reqDto.setDistance(10000);
        Geometry mapCenterCircle = makeCenterPoint(reqDto);
        TagRankingWrapdto findTagRankingInDistanceOfInfluencePower = fBallTagQueryRepository.getFindTagRankingInDistanceOfInfluencePower(mapCenterCircle,
                createRect(reqDto.getLatitude(), reqDto.getLongitude(), reqDto.getDistance()),
                10);

    }



    @Test
    public void diatanceOfBallCountToLimit() throws ParseException {
        NearBallFindDistanceReqDto reqDto = new NearBallFindDistanceReqDto();
        reqDto.setLatitude(37.51368824280154);
        reqDto.setLongitude(126.8985465914011);
        reqDto.setDistance(10000);

        int i = fBallService.diatanceOfBallCountToLimit(reqDto.getLatitude(), reqDto.getLongitude(), 30);
        System.out.println("distance  = " + i);
    }

    @Test
    public void getTagTagRanking(){
        TagRankingReqDto tagRankingReqDto = new TagRankingReqDto();
        tagRankingReqDto.setLatitude(37.51368824280154);
        tagRankingReqDto.setLongitude(126.8985465914011);
        tagRankingReqDto.setLimit(10);
//        List<TagRankingDto> fTagRanking = fTagService.getFTagRanking(tagRankingReqDto);
//        for (TagRankingDto tagRankingDto : fTagRanking) {
//            System.out.println( "tag ranking = " + tagRankingDto.getRanking());
//            System.out.println( "tag name = " + tagRankingDto.getTagName());
//            System.out.println( "tag power = " + tagRankingDto.getTagPower());
//            System.out.println( "--------------------------------------------");
//        }
    }




    private Geometry makeCenterPoint(NearBallFindDistanceReqDto reqDto) {
        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        Coordinate centerPoint1 = new Coordinate(reqDto.getLongitude(),reqDto.getLatitude());
        shapeFactory.setCentre(centerPoint1);
        shapeFactory.setSize(0.00000001);
        Geometry mapCenterCircle = shapeFactory.createCircle();
        mapCenterCircle.setSRID(4326);
        return mapCenterCircle;
    }



    public Geometry createCircle(double x, double y, double radius) {
        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setNumPoints(32);
        shapeFactory.setCentre(new Coordinate(x, y));
        shapeFactory.setSize(radius * 2);
        return shapeFactory.createCircle();
    }

    public Geometry createRect(double latitude, double longitude, double distance) {
        com.grum.geocalc.Coordinate lat = com.grum.geocalc.Coordinate.fromDegrees(latitude);
        com.grum.geocalc.Coordinate lng = com.grum.geocalc.Coordinate.fromDegrees(longitude);
        com.grum.geocalc.Point findPosition = com.grum.geocalc.Point.at(lat, lng);
        BoundingArea area = EarthCalc.around(findPosition, distance / 2);
        Coordinate southWest = new Coordinate(area.southWest.longitude, area.southWest.latitude);
        Coordinate northEast = new Coordinate(area.northEast.longitude, area.northEast.latitude);
        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        Envelope envelope = new Envelope(southWest, northEast);
        shapeFactory.setEnvelope(envelope);
        Geometry rectangle = shapeFactory.createRectangle();
        rectangle.setSRID(4326);
        return rectangle;
    }

}
