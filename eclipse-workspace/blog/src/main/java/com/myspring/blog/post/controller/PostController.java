package com.myspring.blog.post.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface PostController {
	public ModelAndView everyPost(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView posting(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity post(MultipartHttpServletRequest multipartRequest, HttpServletResponse  response) throws Exception;
	public ModelAndView singlePost(@PathVariable("num") int _articleNO, HttpServletRequest request, HttpServletResponse response)throws Exception;
	public void download(@RequestParam("imageFileName")String imageFileName, @RequestParam("articleNO")String post_articleNO,HttpServletResponse response) throws Exception;
	public ModelAndView searchPost(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void deletePost(@PathVariable("num")int post_articleNO,HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ModelAndView modifyPost(@PathVariable("num")int post_articleNO,MultipartHttpServletRequest  multipartRequest, HttpServletResponse response)throws Exception;
	public ResponseEntity modPost(@PathVariable("num")int post_articleNO,MultipartHttpServletRequest  multipartRequest, HttpServletResponse response)throws Exception;
}
