package com.wing.forutona.FcubeDao;

import com.wing.forutona.FcubeDto.Fcube;
import java.util.List;

public interface FcubeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fcube
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String cubeuuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fcube
     *
     * @mbg.generated
     */
    int insert(Fcube record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fcube
     *
     * @mbg.generated
     */
    Fcube selectByPrimaryKey(String cubeuuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fcube
     *
     * @mbg.generated
     */
    List<Fcube> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fcube
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Fcube record);
}