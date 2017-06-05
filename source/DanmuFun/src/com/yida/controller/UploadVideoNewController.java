package com.yida.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yida.common.JSONs;
import com.yida.entity.UserInfo;
import com.yida.service.VideoService;

@Controller
public class UploadVideoNewController {
	

	@Resource
	VideoService service;
	
	ObjectMapper mapper=new ObjectMapper();
	
	//存储视频格式
	public final static ArrayList<String> videoTypes=new ArrayList<String>(){
		{
			add("mov");
			add("avi");
			add("flv");
			add("rmvb");
			add("f4v");
			add("mp4");
			add("wmv");
		}
	};
	
	//存储视频格式
	public final static ArrayList<String> pictureTypes=new ArrayList<String>(){
		{
			add("jpg");
			add("jpeg");
			add("gif");
			add("png");
			add("bmp");
		}
	};
	
	/**
	 * 新版文件上传
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("uploadVideoNewVersion")
	@ResponseBody
	public String uploadVideoNewVersion(HttpServletRequest request) throws IllegalStateException, IOException{
		
		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，重定向
		if(user==null){
			return "false";
		}
		
		//文件名
		String filename=request.getParameter("name");
		
		//块数和当前块数
		int chunk=Integer.valueOf(request.getParameter("chunk"));
		int chunks=Integer.valueOf(request.getParameter("chunks"));
		
		//System.out.println(request.getParameter("id"));
		
		//封面的名称和视频名称
		String coverName = null;
		String videoName = null;

		//获取可用的名称
		int availableId=service.getAvailableVId();
		
		//是否是视频
		boolean isVideo=false;
		
		//是否是最后一个文件
		boolean lastFile=false;
		
		//文件过滤
		String suffix=filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
		
		if(pictureTypes.contains(suffix)){
			
			coverName="cover"+availableId + "." +suffix;
		}
		else if(videoTypes.contains(suffix)){
			
			isVideo=true;
			
			videoName="video"+availableId + "." +suffix;
		}
		else{
			return null;
		}
		
//		//文件唯一ID
//		String fileId=request.getParameter("fileId");

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		MultiValueMap<String, MultipartFile> map = multipartHttpServletRequest.getMultiFileMap();  
		
		File dir = new File(request.getSession().getServletContext().getRealPath("/") + "upload/");
		
		if(map != null) {  

			if (!dir.exists()) dir.mkdirs();    //如果目标文件夹不存在则创建新的文件夹  

			//事实上迭代器中只存在一个值,所以只需要返回一个值即可  
			Iterator<String> iter = map.keySet().iterator();
			
			while(iter.hasNext()) {  

				
				String str = (String) iter.next();  
				List<MultipartFile> fileList =  map.get(str);

				for(MultipartFile multipartFile : fileList) {
					
					//System.out.println("fileType:"+multipartFile);
					
					
					//创建新目标文件，视频或者图像
					File targetFile = null;
					if(isVideo)
						targetFile= new File(dir.getPath()+ "/" + videoName);
					else
						targetFile=new File(dir.getPath()+ "/" + coverName);
					
					
					//去除了文件名的
					String pureName=filename.substring(0, filename.lastIndexOf("."));
					
					//当chunks>1则说明当前传的文件为一块碎片，需要合并  
					if (chunks > 1) { 

						//需要创建临时文件名，最后再更改名称  
						File tempFile = new File(dir.getPath()+ "/" + pureName + ".temp");  
						
						//如果chunk==0,则代表第一块碎片,不需要合并  
						try {
							saveUploadFile(multipartFile.getInputStream(), tempFile, chunk == 0 ? false : true);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  

						//上传并合并完成，则将临时名称更改为指定名称  
						if (chunks - chunk == 1) {
							tempFile.renameTo(targetFile);
							
						}  

					} else {
						
						//否则直接将文件内容拷贝至新文件  
						multipartFile.transferTo(targetFile);
						
					}  
				}  
			}  
		} 

		return "success";
	}
	
	@RequestMapping("checkFinish")
	@ResponseBody
	public String CheckFinish(HttpServletRequest request){
		
		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		
		//没有登录，重定向
		if(user==null){
			return "false";
		}
		
		//视频信息
		String title=request.getParameter("title");
		String type=request.getParameter("type");
		String brief=request.getParameter("brief");
		
		//获取可用的名称
		int availableId=service.getAvailableVId();
		
		String coverNamePre="cover"+availableId;
		String videoNamePre="video"+availableId;
		
		String cover = request.getSession().getServletContext().getRealPath("/") + "upload/"+coverNamePre;
		String video = request.getSession().getServletContext().getRealPath("/") + "upload/"+videoNamePre;
		
		//只要找到一个cover3之类的图片就行
		int flag=-1;
		for(int i=0;i<pictureTypes.size();i++){
			
			File file=new File(cover+"."+pictureTypes.get(i));
			if(file.exists()) flag=i;
		}
		
		int flag2=-1;
		for(int i=0;i<videoTypes.size();i++){
			
			File file2=new File(video+"."+videoTypes.get(i));
			if(file2.exists()) flag2=i;
		}

		//视频图像都在呢
		if(flag>=0 && flag2>=0){
			
			//插入数据库
			service.insertVideo(user.id,title,"upload/"+coverNamePre+"."+pictureTypes.get(flag),
					"upload/"+videoNamePre+"."+videoTypes.get(flag2),type,brief);
			
			return "success";
		}
		
		return "false";
		
	}
	
	@RequestMapping("checkUploaded")
	@ResponseBody
	public String checkUploaded(HttpServletRequest request,@RequestParam String fileId,@RequestParam String fileName,
			@RequestParam int num,@RequestParam int size) throws JsonProcessingException{

		String pureName=fileName.substring(0, fileName.lastIndexOf("."));
		File file=new File(request.getSession().getServletContext().getRealPath("/") + "upload/"+pureName+".temp");
		
		//如果想使用一个类内部的类
		//这个类需要先被实例化
		JSONs js=new JSONs();
		
		//要生成类的名称        写了一个方法用于申请一个CheckJson  
		JSONs.CheckJson cj=js.CreaateCheckJson();
		
		if(file.exists()){
			cj.id=fileId;
			cj.loadedSize=(int) file.length();
			int chunk= (int) Math.ceil(file.length() / (size*1.0));
			cj.percent=chunk*100 / num;
			
		}
		else{
			cj.id=fileId;
			cj.loadedSize=0;
			cj.percent=0;
		}
		String result = mapper.writeValueAsString(cj);

		return result;
		
	}
	
	/**
	 * 保存上传文件，兼合并功能 
	 * @param input
	 * @param targetFile
	 * @param append
	 * @throws IOException
	 */
    private static void saveUploadFile(InputStream input, File targetFile, boolean append) throws IOException {  
        
    	OutputStream out = null;  
        try {  
            if (targetFile.exists() && append) {  
                out = new BufferedOutputStream(new FileOutputStream(targetFile, true), 1024);  
            } else {  
                out = new BufferedOutputStream(new FileOutputStream(targetFile), 1024);  
            }  
              
            byte[] buffer = new byte[1024];  
            int len = 0;  
            //写入文件  
            while ((len = input.read(buffer)) > 0) {  
                out.write(buffer, 0, len);  
            }  
        } catch (IOException e) {  
            throw e;  
        } finally {  
            //关闭输入输出流  
            if (null != input) {  
                try {  
                    input.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (null != out) {  
                try {  
                    out.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
}