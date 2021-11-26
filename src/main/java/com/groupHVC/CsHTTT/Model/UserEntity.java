package com.groupHVC.CsHTTT.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USERS",
        uniqueConstraints = {
                @UniqueConstraint(name = "User_UK", columnNames = "username")})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USERNAME", nullable = false, unique = true, length = 45)
    private String username;

    @Column(name = "ENCRYPTED_PASSWORD", nullable = false, length = 255)
    private String encryptedPassword;

    @Column(name = "FULL_NAME", nullable = false, length = 45)
    private String fullName;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 45)
    private String phoneNumber;

    @Column(name = "AVATAR", nullable = true, length = 255)
    private String avatar;

    @Column(name = "SEX", nullable = true, length = 45)
    private String sex;

    @Column(name = "BIRTHDAY", nullable = true)
    private Date bithday;

    @Column(name = "EMAIL", nullable = true, length = 255)
    private String email;

    @Column(name = "ADDRESS", nullable = false, length = 255)
    private String address;



    public UserEntity(Long userId, String username, String encryptedPassword, String fullName, String phoneNumber, String avatar, String sex, Date bithday, String email, String address) {
        this.userId = userId;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.sex = sex;
        this.bithday = bithday;
        this.email = email;
        this.address = address;
    }

    public UserEntity() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBithday() {
        return bithday;
    }

    public void setBithday(Date bithday) {
        this.bithday = bithday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

