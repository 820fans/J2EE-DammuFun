package com.yida.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yida.common.CommentItem;
import com.yida.entity.CommentZans;

@Repository
public interface ICommentDao {

	public void addComment(@Param("user_id")int userId, @Param("video_id")int videoId,@Param("content") String content);

	public List<CommentItem> getCommentsById(@Param("video_id")String videoId);

	public void zanComment(@Param("user_id")int user_id, @Param("comment_id")int comment_id);

	public CommentZans getCommentZanInfo(@Param("user_id")int user_id, @Param("comment_id")int comment_id);

	public void unzanComment(@Param("user_id")int user_id, @Param("comment_id")int comment_id);

	public CommentItem getMyLatestComment(@Param("user_id")int user_id, @Param("video_id")int videoId);

}
