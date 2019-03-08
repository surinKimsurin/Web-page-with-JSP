package com.myspring.blog.post.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.blog.post.dao.PostDAO;
import com.myspring.blog.post.vo.PostVO;

@Service("postService")
public class PostServiceImpl implements PostService{
	@Autowired
	PostDAO postDAO;
	@Autowired
	PostVO postVO;
	
	@Override
	public int addPost(Map postMap)throws Exception{
		return postDAO.addPost(postMap);
	}
	
	@Override
	public List postList()throws Exception{
		return postDAO.postList();
	}
	
	@Override
	public PostVO singlePost(int _articleNO)throws Exception{
		PostVO postVO=postDAO.selectPost(_articleNO);
		return postVO;
	}
	
	@Override
	public List searchPost(String _post_theme) throws DataAccessException{
		return postDAO.searchPost(_post_theme);
	}
	  
	@Override
	public void deletePost(int post_articleNO)throws Exception{
		postDAO.deletePost(post_articleNO);
	}
	
	@Override
	public void modPost(Map postMap)throws Exception{
		System.out.println("service¿« modPost"+postMap);
		postDAO.modPost(postMap);
	}
}
