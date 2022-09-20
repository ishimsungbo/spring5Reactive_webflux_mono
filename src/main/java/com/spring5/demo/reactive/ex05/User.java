package com.spring5.demo.reactive.ex05;

public class User {
    private String userName;
    private String empNo;

    public User(String userName, String empNo) {
        this.userName = userName;
        this.empNo = empNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", empNo='" + empNo + '\'' +
                '}';
    }
}
