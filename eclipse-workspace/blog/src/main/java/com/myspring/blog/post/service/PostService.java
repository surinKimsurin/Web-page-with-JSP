package com.myspring.blog.post.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.blog.post.vo.PostVO;

public interface PostService {
	public int addPost(Map postMap)throws Exception;
	public List postList()throws Exception;
	public PostVO singlePost(int _articleNO)throws Exception;
	public List searchPost(String _post_theme) throws DataAccessException;
	public void deletePost(int post_articleNO)throws Exception;
	public void modPost(Map postMap)throws Exception;

}
