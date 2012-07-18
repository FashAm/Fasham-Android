package com.example.ourfirst;

public class Comment {

	String id;
	int createdAt;
	String user_name;
	String user_text;
	
	
	public Comment(String id, int createdAt, String user_name, String user_text) {
		this.id = id;
		this.createdAt = createdAt;
		this.user_name = user_name;
		this.user_text = user_text;
	}
	
}

class Post {
	int id;
	String user;
	String photolink;
	
}
