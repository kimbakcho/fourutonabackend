package com.wing.forutona.FcubeDao;

import com.wing.forutona.FcubeDto.Fcubequestsuccesscheck;
import java.util.List;

public interface FcubequestsuccesscheckMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FcubeQuestSuccessCheck
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer idx);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FcubeQuestSuccessCheck
     *
     * @mbg.generated
     */
    int insert(Fcubequestsuccesscheck record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FcubeQuestSuccessCheck
     *
     * @mbg.generated
     */
    Fcubequestsuccesscheck selectByPrimaryKey(Integer idx);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FcubeQuestSuccessCheck
     *
     * @mbg.generated
     */
    List<Fcubequestsuccesscheck> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FcubeQuestSuccessCheck
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Fcubequestsuccesscheck record);
}