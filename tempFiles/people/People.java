

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 */
public class People implements Serializable{

    /**
     * 
     */
    private String insuranceCardNo;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String genderCode;

    /**
     * 
     */
    private String birthday;

    /**
     * 
     */
    private String nationRaw;

    /**
     * 
     */
    private String idCardNo;

    /**
     * 
     */
    private String maritalStatusRaw;

    /**
     * 
     */
    private String telephone;

    /**
     * 
     */
    private String address;

    /**
     * 
     */
    private String contacts;

    /**
     * 
     */
    private String relationshipRaw;

    /**
     * 
     */
    private String unitName;

    /**
     * 
     */
    private String occupationRaw;

    /**
     *  取值方法get
     * @return insuranceCardNo
     */
    public String getInsuranceCardNo() {
        return insuranceCardNo;
    }

    /**
     *  賦值方法set
     * @param insuranceCardNo
     */
    public void setInsuranceCardNo(String insuranceCardNo) {
        this.insuranceCardNo = insuranceCardNo;
    }

    /**
     *  取值方法get
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *  賦值方法set
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  取值方法get
     * @return genderCode
     */
    public String getGenderCode() {
        return genderCode;
    }

    /**
     *  賦值方法set
     * @param genderCode
     */
    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    /**
     *  取值方法get
     * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     *  賦值方法set
     * @param birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     *  取值方法get
     * @return nationRaw
     */
    public String getNationRaw() {
        return nationRaw;
    }

    /**
     *  賦值方法set
     * @param nationRaw
     */
    public void setNationRaw(String nationRaw) {
        this.nationRaw = nationRaw;
    }

    /**
     *  取值方法get
     * @return idCardNo
     */
    public String getIdCardNo() {
        return idCardNo;
    }

    /**
     *  賦值方法set
     * @param idCardNo
     */
    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    /**
     *  取值方法get
     * @return maritalStatusRaw
     */
    public String getMaritalStatusRaw() {
        return maritalStatusRaw;
    }

    /**
     *  賦值方法set
     * @param maritalStatusRaw
     */
    public void setMaritalStatusRaw(String maritalStatusRaw) {
        this.maritalStatusRaw = maritalStatusRaw;
    }

    /**
     *  取值方法get
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     *  賦值方法set
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     *  取值方法get
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     *  賦值方法set
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *  取值方法get
     * @return contacts
     */
    public String getContacts() {
        return contacts;
    }

    /**
     *  賦值方法set
     * @param contacts
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     *  取值方法get
     * @return relationshipRaw
     */
    public String getRelationshipRaw() {
        return relationshipRaw;
    }

    /**
     *  賦值方法set
     * @param relationshipRaw
     */
    public void setRelationshipRaw(String relationshipRaw) {
        this.relationshipRaw = relationshipRaw;
    }

    /**
     *  取值方法get
     * @return unitName
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     *  賦值方法set
     * @param unitName
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     *  取值方法get
     * @return occupationRaw
     */
    public String getOccupationRaw() {
        return occupationRaw;
    }

    /**
     *  賦值方法set
     * @param occupationRaw
     */
    public void setOccupationRaw(String occupationRaw) {
        this.occupationRaw = occupationRaw;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}