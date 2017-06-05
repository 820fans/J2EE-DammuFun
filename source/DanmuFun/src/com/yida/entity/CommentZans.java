package com.yida.entity;

public class CommentZans {
	
	private int id;
	
	private int user_id;//谁赞的
	
	private int comment_id;//赞哪一条评论

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	
	
}
