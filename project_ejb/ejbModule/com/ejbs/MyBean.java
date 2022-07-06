package com.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class MyBean
 */
@Stateless
@LocalBean
public class MyBean {

    String msg;
    public String getMsg()
    {
    	msg = "Hello from bean";
    	return msg;
    }

}
