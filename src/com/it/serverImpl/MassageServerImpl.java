package com.it.serverImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.it.entity.ExtendMassage;
import com.it.entity.Massage;
import com.it.entity.MassageDAO;
import com.it.entity.Msphoto;
import com.it.entity.MsphotoDAO;
import com.it.entity.UserDAO;
import com.it.server.MassageServer;
import com.opensymphony.xwork2.ActionContext;

public class MassageServerImpl implements MassageServer {
	
	public MassageDAO massageDao;
	public UserDAO userDao;
	public MsphotoDAO msphotoDao;
	
	public List<ExtendMassage> listMassage;
	
	
	
	public List<ExtendMassage> getListMassage() {
		return listMassage;
	}

	public void setListMassage(List<ExtendMassage> listMassage) {
		this.listMassage = listMassage;
	}

	public MassageDAO getMassageDao() {
		return massageDao;
	}

	public void setMassageDao(MassageDAO massageDao) {
		this.massageDao = massageDao;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public MsphotoDAO getMsphotoDao() {
		return msphotoDao;
	}
	
	public  String getRequestBody(ActionContext ctx){
		try{		
			HttpServletRequest request=(HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
//			HttpServletRequest request = ServletActionContext.getRequest();
			Map<String, String[]> map = request.getParameterMap();  
	        Set<Entry<String, String[]>> set = map.entrySet();  
	        Iterator<Entry<String, String[]>> it = set.iterator();
	        Massage massage=new Massage();
	        Msphoto photo=new Msphoto();
	        //按顺序来来
	        int count=1;
	        while (it.hasNext()) {  
	            Entry<String, String[]> entry = it.next();  
	            String content="";
	            System.out.println("KEY:"+entry.getKey()); 
	            for (String i : entry.getValue()) {
            		content+=i;
            		}
	            if(count==1){
	            	massage.setMtext(content);
	            }else if(count==2){
	            	massage.setMtitle(content);
	            }else if(count==3){
	            	content=content.trim();
	            	massage.setYh(Integer.valueOf(content));
	            }
	            count++;
	        }
	        java.util.Date nowdate = new java.util.Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String t = sdf.format(nowdate);
	        massage.setMtime(t);
	        massage.setMlike(2);;
	        massage.setMcomment(2);
	        massageDao.save(massage);
	        List msgs=massageDao.findByExample(massage);
	        Massage msg;
	        if(msgs.get(0)!=null){
	        	msg=(Massage) msgs.get(0);
	        	Map params = ctx.getParameters(); 
		        File[] image = (File[])params.get("image"); 
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
		             photo.setMpaddress(path);
		             photo.setDid(msg.getId());
		             String photoPath="http://10.151.254.125:8080/Bian07/"+"/Image/"+wjFileName;
		             photo.setMpaddress(photoPath);
		             msphotoDao.save(photo);
		             out.close();  
		             in.close();  
		        }
	        }
	        
			return "success";
		}catch(Exception e){
			System.out.println(e);
			System.out.println("出错");
			return "failure";
		}
	}	
	public void setMsphotoDao(MsphotoDAO msphotoDao) {
		this.msphotoDao = msphotoDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExtendMassage> getMassage() {
		// TODO Auto-generated method stub
		
		listMassage=massageDao.findExtendMassage();
		
		return listMassage;
	}

	@Override
	public void receiveMassage() {
		// TODO Auto-generated method stub

	}

}
