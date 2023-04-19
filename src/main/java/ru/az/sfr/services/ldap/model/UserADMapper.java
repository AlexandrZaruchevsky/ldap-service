package ru.az.sfr.services.ldap.model;

import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

public class UserADMapper implements AttributesMapper<UserAD> {

    @Override
    public UserAD mapFromAttributes(Attributes attributes) throws NamingException {
        UserAD userAD = new UserAD();
        userAD.setObjectCategory((String) getAttribute(attributes.get("objectCategory")));
        userAD.setUserParameters((String) getAttribute(attributes.get("userParameters")));
        userAD.setWhenCreated((String) getAttribute(attributes.get("whenCreated")));
        userAD.setBadPwdCount((String) getAttribute(attributes.get("badPwdCount")));
        userAD.setOtherTelephone((String) getAttribute(attributes.get("otherTelephone")));
        userAD.setCodePage((String) getAttribute(attributes.get("codePage")));
        userAD.setPager((String) getAttribute(attributes.get("pager")));
        userAD.setScriptPath((String) getAttribute(attributes.get("scriptPath")));
        userAD.setMail((String) getAttribute(attributes.get("mail")));
        userAD.setObjectGUID((String) getAttribute(attributes.get("objectGUID")));
        userAD.setMemberOf((String) getAttribute(attributes.get("memberOf")));
//        userAD.setUserCertificate((String) getAttribute(attributes.get("userCertificate")));
        userAD.setInstanceType((String) getAttribute(attributes.get("instanceType")));
        userAD.setObjectSid((String) getAttribute(attributes.get("objectSid")));
        userAD.setBadPasswordTime((String) getAttribute(attributes.get("badPasswordTime")));
        userAD.setDSCorePropagationData((String) getAttribute(attributes.get("dSCorePropagationData")));
        userAD.setObjectClass((String) getAttribute(attributes.get("objectClass")));
        userAD.setCompany((String) getAttribute(attributes.get("company")));
        userAD.setName((String) getAttribute(attributes.get("name")));
        userAD.setDescription((String) getAttribute(attributes.get("description")));
        userAD.setSn((String) getAttribute(attributes.get("sn")));
        userAD.setInitials((String) getAttribute(attributes.get("initials")));
        userAD.setTelephoneNumber((String) getAttribute(attributes.get("telephoneNumber")));
        userAD.setUserAccountControl((String) getAttribute(attributes.get("userAccountControl")));
        userAD.setPrimaryGroupID((String) getAttribute(attributes.get("primaryGroupID")));
        userAD.setAccountExpires((String) getAttribute(attributes.get("accountExpires")));
        userAD.setLastLogon((String) getAttribute(attributes.get("lastLogon")));
        userAD.setLastLogoff((String) getAttribute(attributes.get("lastLogoff")));
        userAD.setUSNChanged((String) getAttribute(attributes.get("uSNChanged")));
        userAD.setPhysicalDeliveryOfficeName((String) getAttribute(attributes.get("physicalDeliveryOfficeName")));
        userAD.setCn((String) getAttribute(attributes.get("cn")));
        userAD.setTitle((String) getAttribute(attributes.get("title")));
        userAD.setLogonCount((String) getAttribute(attributes.get("logonCount")));
        userAD.setMobile((String) getAttribute(attributes.get("mobile")));
        userAD.setSAMAccountType((String) getAttribute(attributes.get("sAMAccountType")));
        userAD.setSIDHistory((String) getAttribute(attributes.get("sIDHistory")));
        userAD.setGivenName((String) getAttribute(attributes.get("givenName")));
        userAD.setUSNCreated((String) getAttribute(attributes.get("uSNCreated")));
        userAD.setDisplayName((String) getAttribute(attributes.get("displayName")));
        userAD.setUserPrincipalName((String) getAttribute(attributes.get("userPrincipalName")));
        userAD.setPwdLastSet((String) getAttribute(attributes.get("pwdLastSet")));
        userAD.setWhenChanged((String) getAttribute(attributes.get("whenChanged")));
        userAD.setLastLogonTimestamp((String) getAttribute(attributes.get("lastLogonTimestamp")));
        userAD.setMiddleName((String) getAttribute(attributes.get("middleName")));
        userAD.setDepartment((String) getAttribute(attributes.get("department")));
        userAD.setStreetAddress((String) getAttribute(attributes.get("streetAddress")));
        userAD.setCountryCode((String) getAttribute(attributes.get("countryCode")));
        userAD.setL((String) getAttribute(attributes.get("l")));
        userAD.setDistinguishedName((String) getAttribute(attributes.get("distinguishedName")));
        userAD.setInfo((String) getAttribute(attributes.get("info")));
        userAD.setC((String) getAttribute(attributes.get("c")));
        userAD.setLogonHours((String) getAttribute(attributes.get("logonHours")));
        userAD.setSAMAccountName((String) getAttribute(attributes.get("sAMAccountName")));
        return userAD;
    }

    private Object getAttribute(Attribute attribute) throws NamingException {
        return attribute != null ? attribute.get() : null;
    }

}
