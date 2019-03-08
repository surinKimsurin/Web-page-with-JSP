package com.myspring.blog.post.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.blog.post.service.PostService;
import com.myspring.blog.post.vo.PostVO;

@Controller("postController")
@RequestMapping("/post")
public class PostControllerImpl implements PostController{
	private static final String ARTICLE_IMAGE_REPO = "C:\\board\\post_image";
	@Autowired
	PostVO postVO;
	@Autowired
	PostService postService;
	
	@Override
	@RequestMapping(value="/everyPost.do", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView everyPost(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav=new ModelAndView();
		List postList=postService.postList();
		System.out.println(postList);
		mav.setViewName("/post/viewPost");
		mav.addObject("postList",postList);
		return mav;
	}
	

	
	@Override
	@RequestMapping(value="/searchPost.do", method= {RequestMethod.GET})
	public ModelAndView searchPost(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String _post_theme=(String)request.getParameter("post_theme");
		String _post_form=(String)request.getParameter("post_form");
		String album="album";
		System.out.println("post_form��"+_post_form);
		if(_post_form.equals(album)) {//��...
			System.out.println(_post_theme);
			ModelAndView mav=new ModelAndView();
			List searchPost=postService.searchPost(_post_theme);
			System.out.println(searchPost);
			mav.setViewName("/post/albumPost");
			mav.addObject("postList",searchPost);
			return mav;
		}else {
		System.out.println(_post_theme);
		ModelAndView mav=new ModelAndView();
		List searchPost=postService.searchPost(_post_theme);
		System.out.println(searchPost);
		mav.setViewName("/post/viewPost");
		mav.addObject("postList",searchPost);
		return mav;
		}
	}

	
	
	
	@Override
	@RequestMapping(value="/singlePost.do/{num}", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ModelAndView singlePost(@PathVariable("num") int _articleNO, HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView mav=new ModelAndView();
		//���⿡ select from articleNO =num���� �ϰ�..  !! 
		postVO=postService.singlePost(_articleNO);
		mav.setViewName("/post/singlePost");
		mav.addObject("post",postVO);
		mav.addObject("post_id",postVO.getPost_id());
		mav.addObject("post_title",postVO.getPost_title());
		mav.addObject("post_content",postVO.getPost_content());
		mav.addObject("post_theme",postVO.getPost_theme());
		mav.addObject("post_imageFileName",postVO.getPost_imageFileName());
		mav.addObject("post_articleNO",postVO.getPost_articleNO());
		
		System.out.println(postVO.getPost_id()+postVO.getPost_articleNO());
		return mav;
	}

	@Override
	@RequestMapping(value="/posting.do", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView posting(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav=new ModelAndView();
		//���� redirect�� HttpSession ����ϴ°� ������ �� ����غ���
		HttpSession session=request.getSession();
		mav.setViewName("/post/postForm");
		return mav;
	}

	@Override
	@RequestMapping(value="/modifyPost.do/{num}",method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView modifyPost(@PathVariable("num")int post_articleNO,MultipartHttpServletRequest  multipartRequest, HttpServletResponse response)throws Exception{
		ModelAndView mav=new ModelAndView();
		//���⿡ select from articleNO =num���� �ϰ�..  !! 
		postVO=postService.singlePost(post_articleNO);
		mav.setViewName("/post/modForm");
		mav.addObject("post",postVO);
		mav.addObject("post_id",postVO.getPost_id());
		mav.addObject("post_title",postVO.getPost_title());
		mav.addObject("post_content",postVO.getPost_content());
		mav.addObject("post_theme",postVO.getPost_theme());
		mav.addObject("post_imageFileName",postVO.getPost_imageFileName());
		mav.addObject("post_articleNO",post_articleNO);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/modPost.do/{num}",method= {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity modPost(@PathVariable("num")int post_articleNO,MultipartHttpServletRequest  multipartRequest, HttpServletResponse response)throws Exception{

		response.setContentType("text/html;charset=UTF-8");
		multipartRequest.setCharacterEncoding("utf-8");
		
		Map<String, Object> postMap=new HashMap<String, Object>();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			postMap.put(name,value);
		}
		
		String imageFileName=upload(multipartRequest);
		HttpSession session=multipartRequest.getSession();
		postMap.put("post_articleNO",post_articleNO);
		postMap.put("post_imageFileName",imageFileName);
		System.out.println("��Ʈ�ѷ��� ����Ʈ��:"+postMap);

		String message=null;
		ResponseEntity resEntity=null;
		HttpHeaders responseHeaders=new HttpHeaders();
		responseHeaders.add("Content-Type","text/html;charset=utf-8");
		
		try {
			postService.modPost(postMap);
			if(imageFileName!=null&&imageFileName.length()!=0) {
			File srcFile=new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			File destDir=new File(ARTICLE_IMAGE_REPO+"\\"+"post_articleNO");
			FileUtils.moveFileToDirectory(srcFile, destDir,true);
			message="<script>";
			message+="alert('������ �Ϸ�Ǿ����ϴ�.');";
			message+="location.href='"+multipartRequest.getContextPath()+"/post/everyPost.do';";
			message+="</script>";
//			String originalFileName=(String)postMap.get("originalFileName");
	//		File oldFile=new File(ARTICLE_IMAGE_REPO+"\\"+"originalFileName");
		//	oldFile.delete();

			}

		}catch(Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			srcFile.delete();
			message="<script>";
			message+="alert('���� �� ������ �߻��߽��ϴ�. �ٽ� �õ����ּ���');";
			message+="location.href='"+multipartRequest.getContextPath()+"/post/everyPost.do';";
			message+="</script>";
			e.printStackTrace();
			resEntity=new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);

		}
		return resEntity;
	}
		
	@Override
	@RequestMapping(value="/deletePost.do/{num}",method= {RequestMethod.POST,RequestMethod.GET})
	public void deletePost(@PathVariable("num")int post_articleNO,HttpServletRequest request, HttpServletResponse response)throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter script=response.getWriter();
		try {
			
			postService.deletePost(post_articleNO);
			script.println("<script>");
			script.println("alert('������ �Ϸ�Ǿ����ϴ�.');");
			script.println("location.href='"+request.getContextPath()+"/post/everyPost.do';");
			script.println("</script>");
			
		}catch(Exception e) {
			script.println("<Script>");
			script.println("alert('���� ������ �߻��߽��ϴ�. �ٽ� �õ����ּ���');");
			script.println("location.href='"+request.getContextPath()+"/post/everyPost.do';");
			script.println("</script>");
			e.printStackTrace();

		}
	}
	
	@RequestMapping("/download.do")
	public void download(@RequestParam("imageFileName")String imageFileName, @RequestParam("articleNO")String post_articleNO,
						HttpServletResponse response) throws Exception{
		OutputStream out=response.getOutputStream();
		String downFile=ARTICLE_IMAGE_REPO+"\\"+"post_articleNO"+"\\"+imageFileName;
		File file=new File(downFile);
		
		response.setHeader("Cache-Control","no-cache");
		response.addHeader("Content-disposition","attachment;fileName="+imageFileName);
		FileInputStream in=new FileInputStream(file);
		byte[] buffer=new byte[1024*8];
		while(true) {
			int count=in.read(buffer);
			if(count==-1)
				break;
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
 	}
	

	
	//�� �� �̹��� �۾���
	@Override
	@RequestMapping(value="/addPost.do", method= {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity post(MultipartHttpServletRequest multipartRequest, HttpServletResponse  response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		multipartRequest.setCharacterEncoding("utf-8");
		
		//�̰� ���� ���� �� Map���ٰ� �־��ִ� ������.
		Map<String,Object> postMap=new HashMap<String,Object>();
		Enumeration enu=multipartRequest.getParameterNames();

		while(enu.hasMoreElements()) {
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			postMap.put(name,value);
			System.out.println(postMap);
		}
		String imageFileName=upload(multipartRequest);
		HttpSession session=multipartRequest.getSession();
		postMap.put("post_imageFileName",imageFileName);

		System.out.println(postMap);

		String message=null;
		ResponseEntity resEntity=null;
		HttpHeaders responseHeaders=new HttpHeaders();
		responseHeaders.add("Content-Type","text/html;charset=utf-8");
		
		try {
			int post_aritlcleNO=postService.addPost(postMap);
			if(imageFileName!=null&&imageFileName.length()!=0) {
			File srcFile=new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			File destDir=new File(ARTICLE_IMAGE_REPO+"\\"+"post_articleNO");
			FileUtils.moveFileToDirectory(srcFile, destDir,true);
						
			}
			message="<script>";
			message+="alert('������ �Ϸ�Ǿ����ϴ�.');";
			message+="location.href='"+multipartRequest.getContextPath()+"/post/everyPost.do';";
			message+="</script>";
		}catch(Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			srcFile.delete();
			message="<script>";
			message+="alert('������ ������ �߻��߽��ϴ�. �ٽ� �õ����ּ���');";
			message+="location.href='"+multipartRequest.getContextPath()+"/post/posting.do';";
			message+="</script>";
			e.printStackTrace();
		}
		resEntity=new ResponseEntity(message,responseHeaders,HttpStatus.OK);
		return resEntity;
	}
	
	//�Ѱ� �̹��� ���ε��ϱ�
		private String upload(MultipartHttpServletRequest multipartRequest) throws Exception{
			String imageFileName= null;
			Map<String, String> postMap = new HashMap<String, String>();
			Iterator<String> fileNames = multipartRequest.getFileNames();
			
			while(fileNames.hasNext()){
				String fileName = fileNames.next();
				MultipartFile mFile = multipartRequest.getFile(fileName);
				imageFileName=mFile.getOriginalFilename();
				File file = new File(ARTICLE_IMAGE_REPO +"\\"+ fileName);
				if(mFile.getSize()!=0){ //File Null Check
					if(! file.exists()){ //��λ� ������ �������� ���� ���
						if(file.getParentFile().mkdirs()){ //��ο� �ش��ϴ� ���丮���� ����
								file.createNewFile(); //���� ���� ����
						}
					}
					mFile.transferTo(new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+ "\\"+imageFileName)); //�ӽ÷� ����� multipartFile�� ���� ���Ϸ� ����
				}
			}
			return imageFileName;
		}
}
