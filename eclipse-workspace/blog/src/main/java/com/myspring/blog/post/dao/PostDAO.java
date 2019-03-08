package com.myspring.blog.post.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.blog.post.vo.PostVO;

public interface PostDAO {
	public int addPost(Map postMap) throws DataAccessException;
	public List postList() throws DataAccessException;
	public PostVO selectPost(int _articleNO) throws DataAccessException;
	public int selectNewPostNO() throws DataAccessException;
	public List searchPost(String _post_theme) throws DataAccessException;
	public void deletePost(int post_articleNO) throws DataAccessException;
	public void modPost(Map postMap)throws DataAccessException;

}
