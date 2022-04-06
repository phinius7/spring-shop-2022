package ru.senchenko.dao;

import ru.senchenko.entities.Role;
import ru.senchenko.entities.User;

import java.util.Date;
import java.util.Set;

public class UserDao {

    private Integer id;
    private Date createDate;
    private Date modifyDate;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private Set<Role> roles;

    public UserDao() {}

    public UserDao(User user) {
        this.id = user.getId();
        this.createDate = user.getCreateDate();
        this.modifyDate = user.getModifyDate();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDao : {" +
                "id=" + id + ", " +
                "userName=" + userName + ", " +
                "password=" + password +
                "}";
    }
}
