package com.it.serverImpl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.it.entity.User;
import com.it.entity.UserDAO;
import com.it.server.UserServer;
import com.opensymphony.xwork2.ActionContext;

public class UserServerImpl implements UserServer {
	UserDAO userDao;
	
	@Override
	public boolean login(ActionContext act) {
		// TODO Auto-generated method stub
		HttpServletRequest request =(HttpServletRequest)act.get(ServletActionContext.HTTP_REQUEST);
		Map<String, String[]>maps =request.getParameterMap();
		System.out.println(request.getParameter("password"));
		Set<Entry<String,String[]>>set=maps.entrySet();
		Iterator<Entry<String, String[]>>it=set.iterator();
		String password="";
		String account="";
		int n=0;
		User user=new User();
		while(it.hasNext()){
			Entry<String,String[]>entry=it.next();
            
            System.out.println("KEY:"+entry.getKey());
            
            
            if(n==0){
            	for (String i : entry.getValue()) {
                	account+=i;
            	}
            }else{
            	for (String i : entry.getValue()) {
            		password+=i;
            	}
            }
            
		}
    	user=(User) userDao.findByUaccount(account).get(0);
		if(user.getUpassword().equals(password)){            		
    		return true;
    	}else{
    		return false;
    	}
	}
	public UserDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
}
