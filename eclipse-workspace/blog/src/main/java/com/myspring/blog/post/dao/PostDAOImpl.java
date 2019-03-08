package com.myspring.blog.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.blog.post.vo.PostVO;

@Repository("postDA0")
public class PostDAOImpl implements PostDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int addPost(Map postMap) throws DataAccessException{
		int post_aritlcleNO=selectNewPostNO();
		
		System.out.println("articleNO´Â"+post_aritlcleNO);
		postMap.put("post_articleNO",post_aritlcleNO);
		sqlSession.insert("mapper.post.addPost",postMap);
		return post_aritlcleNO;
	}
	
	public List searchPost(String _post_theme) throws DataAccessException
	{
		if(sqlSession.selectList("mapper.post.searchPost",_post_theme)==null) {
			return null;
		}else return sqlSession.selectList("mapper.post.searchPost",_post_theme);	
	}
	
	@Override
	public int selectNewPostNO() throws DataAccessException{

		if(sqlSession.selectOne("mapper.post.selectNewPostNO")==null) {
			return 1;
		}else return sqlSession.selectOne("mapper.post.selectNewPostNO");
	}
	
	@Override
	public void deletePost(int post_articleNO) throws DataAccessException{
		sqlSession.delete("mapper.post.deletePost",post_articleNO);
	}
	
	@Override
	public void modPost(Map postMap)throws DataAccessException{
		System.out.println("daoÀÇ modPost"+postMap);
		sqlSession.update("mapper.post.modPost",postMap);
	}
	
	@Override
	public List postList() throws DataAccessException{
		if(sqlSession.selectList("mapper.post.postList")==null) {
			return null;
		}else return sqlSession.selectList("mapper.post.postList");	
	}
	
	@Override
	public PostVO selectPost(int _articleNO) throws DataAccessException{
		if(sqlSession.selectOne("mapper.post.selectPost", _articleNO)==null) {
			return null;
		}else return sqlSession.selectOne("mapper.post.selectPost", _articleNO);
	}
}
