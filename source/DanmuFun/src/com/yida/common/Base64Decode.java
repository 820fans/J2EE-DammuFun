package com.yida.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Base64Decode {
	
	public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
		if(imgFilePath == null || imgFilePath == ""){  
			return "";  
		}  
		File file = new File(imgFilePath);  
		if(!file.exists()){  
			return "";  
		}  
		byte[] data = null;  
		// 读取图片字节数组  
		try {  
			InputStream in = new FileInputStream(imgFilePath);  
			data = new byte[in.available()];  
			in.read(data);  
			in.close();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		// 对字节数组Base64编码  
		BASE64Encoder encoder = new BASE64Encoder();  
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串  
	}  
	public static boolean Decode(String dataUrl,String imgPath,String name){

		BASE64Decoder decoder = new BASE64Decoder();
		try {
			//Base64解码  
			byte[] b = decoder.decodeBuffer(dataUrl);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {//调整异常数据  
					b[i] += 256;
				}
			}

			File file=new File(imgPath);
			if(!file.exists()){ 
				file.mkdirs(); 
			} 
			//生成jpeg图片  
			//  String imgFilePath = "c:/3.jpg";//新生成的图片  
			File temp=new File(imgPath,name);

			OutputStream out= new FileOutputStream(temp);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("解析异常");
			return false;
		}
	}
}
