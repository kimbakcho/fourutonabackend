package com.wing.forutona.FcubeDto;

import java.util.Date;

public class Fcubecontent {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FcubeContent.idx
     *
     * @mbg.generated
     */
    private Long idx;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FcubeContent.Cubeuuid
     *
     * @mbg.generated
     */
    private String cubeuuid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FcubeContent.ContentType
     *
     * @mbg.generated
     */
    private String contenttype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FcubeContent.ContentUpdateTime
     *
     * @mbg.generated
     */
    private Date contentupdatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FcubeContent.ContentValue
     *
     * @mbg.generated
     */
    private String contentvalue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FcubeContent.idx
     *
     * @return the value of FcubeContent.idx
     *
     * @mbg.generated
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FcubeContent.idx
     *
     * @param idx the value for FcubeContent.idx
     *
     * @mbg.generated
     */
    public void setIdx(Long idx) {
        this.idx = idx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FcubeContent.Cubeuuid
     *
     * @return the value of FcubeContent.Cubeuuid
     *
     * @mbg.generated
     */
    public String getCubeuuid() {
        return cubeuuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FcubeContent.Cubeuuid
     *
     * @param cubeuuid the value for FcubeContent.Cubeuuid
     *
     * @mbg.generated
     */
    public void setCubeuuid(String cubeuuid) {
        this.cubeuuid = cubeuuid == null ? null : cubeuuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FcubeContent.ContentType
     *
     * @return the value of FcubeContent.ContentType
     *
     * @mbg.generated
     */
    public String getContenttype() {
        return contenttype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FcubeContent.ContentType
     *
     * @param contenttype the value for FcubeContent.ContentType
     *
     * @mbg.generated
     */
    public void setContenttype(String contenttype) {
        this.contenttype = contenttype == null ? null : contenttype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FcubeContent.ContentUpdateTime
     *
     * @return the value of FcubeContent.ContentUpdateTime
     *
     * @mbg.generated
     */
    public Date getContentupdatetime() {
        return contentupdatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FcubeContent.ContentUpdateTime
     *
     * @param contentupdatetime the value for FcubeContent.ContentUpdateTime
     *
     * @mbg.generated
     */
    public void setContentupdatetime(Date contentupdatetime) {
        this.contentupdatetime = contentupdatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FcubeContent.ContentValue
     *
     * @return the value of FcubeContent.ContentValue
     *
     * @mbg.generated
     */
    public String getContentvalue() {
        return contentvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FcubeContent.ContentValue
     *
     * @param contentvalue the value for FcubeContent.ContentValue
     *
     * @mbg.generated
     */
    public void setContentvalue(String contentvalue) {
        this.contentvalue = contentvalue == null ? null : contentvalue.trim();
    }
}