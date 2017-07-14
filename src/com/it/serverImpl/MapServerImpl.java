package com.it.serverImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.it.entity.Bmap;
import com.it.entity.BmapDAO;
import com.it.server.MapServer;
import com.opensymphony.xwork2.ActionContext;

public class MapServerImpl implements MapServer {
	public BmapDAO mapDao;
	
	@Override
	public String getRequestBody(ActionContext ctx) {
		try{		
			HttpServletRequest request=(HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
//			HttpServletRequest request = ServletActionContext.getRequest();
			Map<String, String[]> map = request.getParameterMap();  
	        Set<Entry<String, String[]>> set = map.entrySet();  
	        Iterator<Entry<String, String[]>> it = set.iterator();
	        Bmap bmap=new Bmap();
	        //按顺序来来
	        //String image = httpRequest.getParameter("image");这种方法或许会好一点
	        int count=1;
	        while (it.hasNext()) {  
	            Entry<String, String[]> entry = it.next();  
	            String content="";
	            System.out.println("KEY:"+entry.getKey()); 
	            for (String i : entry.getValue()) {
            		content+=i;
            		}
	            if(count==1){
	            	bmap.setLatitude(Double.valueOf(content));
	            }else if(count==2){
	            	bmap.setPdescribe(content);	            	
	            }else if(count==3){
	            	bmap.setPtitle(content);
	            }else if(count==4){
	            	bmap.setLongitude(Double.valueOf(content));
	            	
	            }
//	            else if(count==5){
//	            	try{
//	                    // 将base64 转 字节数组
//	                    System.out.println("看看");
//	                    byte[] decode = content.getBytes();
//	                    // 图片输出路径
//	                    String imagePath = "ROOT" + "/" + System.currentTimeMillis() + ".png";
//	                    // 定义图片输入流
//	                    InputStream fin = new ByteArrayInputStream(decode);
//	                    // 定义图片输出流
//	                    FileOutputStream fout=new FileOutputStream(imagePath);
//	                    // 写文件
//	                    byte[] b=new byte[1024];
//	                    int length=0;
//	                    while((length=fin.read(b))>0){
//	                        
//	                        fout.write(b, 0, length);
//	                    }
//	                    bmap.setPmphoto(imagePath);
//	                    // 关闭数据流
//	                    fin.close();
//	                    fout.close();
//	                    
//	                }catch(Exception e){
//	                    
//	                    e.printStackTrace();
//	                } 
//	            }
	            count++;
	        }
	       
//	        String image = request.getAttribute("image.jpg").toString();
	        Map params = ctx.getParameters(); 
	        File[] image = (File[])params.get("image"); 
//	        String image = request.getParameter("image");
	        System.out.println("图片"+image);
	        for(int n=0;n<image.length;n++){
	        	 String wjFileName= new java.util.Date().getTime()+"image.jpg";  
	             ServletContext servletContext=ServletActionContext.getServletContext();  
	             String path=servletContext.getRealPath("/Image/"+wjFileName);//文件最终要上传到的路径  
	             FileOutputStream out=new FileOutputStream(path);  
	             FileInputStream in=new FileInputStream(image[n]);  
	             byte[]buffer=new byte[1024];  
	             int len=0;  
	             while((len=in.read(buffer))!=-1){  
	                 out.write(buffer,0,len);  
	                   
	             }
	             bmap.setPmphoto("barber_brush_filled-25.png");
	             out.close();  
	             in.close();  
	        }
	        java.util.Date nowdate = new java.util.Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String t = sdf.format(nowdate);
	        bmap.setPtime(t);
	        bmap.setYh(2);
	        mapDao.save(bmap);
			return "success";
		}catch(Exception e){
			System.out.println(e);
			System.out.println("出错");
			return "failure";
		}
	}

	public BmapDAO getMapDao() {
		return mapDao;
	}

	public void setMapDao(BmapDAO mapDao) {
		this.mapDao = mapDao;
	}
	
	
	
}


