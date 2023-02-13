package com.ssafy.ws.dto;

public class User {
    private String id;
    private String name;
    private String pass;
    private String recId;

    public User() {}

    public User(String id, String name, String pass, String recId) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.recId = recId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pass=" + pass + ", recId=" + recId + "]";
	}


}
