package com.sudytech.core.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;

import com.sudytech.commons.safety.filter.SafetyEnv;
import com.sudytech.commons.safety.validator.IValidator;
import com.sudytech.commons.safety.validator.ValidateUtil;
import com.sudytech.html.HtmlUtil;
import com.sudytech.ids.security.support.login.IdsLoginValidationFilter;
import com.sudytech.log.ILog;
import com.sudytech.log.LogFactory;
import com.sudytech.util.base.StringUtil;

public class ParamTransformFilter implements Filter {
	private static ILog _log = LogFactory.getInstance().getLog(
			ParamTransformFilter.class);

	private List<Map<String,String>> checkList = new ArrayList<Map<String,String>>();
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest servletrequest = (HttpServletRequest) request;
		HttpServletResponse servletresponse = (HttpServletResponse) response;
		String param = "";
		String paramValue = "";
		
		Cookie[] cookies = servletrequest.getCookies();
		String sudyLoginToken = "";
		if(cookies!=null && cookies.length>0){
     		for(Cookie co : cookies){
     			if("sudyLoginToken".equals(co.getName())){
     				sudyLoginToken = co.getValue();break;
     			}
     		}
     	}
		
		String url = servletrequest.getRequestURI();
		System.out.println("url: " + url);
		// 从 HTTP 头中取得 Referer 值
		 String referer= servletrequest.getHeader("Referer");
		 if(url.endsWith(".jsp") || url.endsWith(".rst")){
			 if( (StringUtil.isEmpty(sudyLoginToken) || "expired".equals(sudyLoginToken)) && referer==null){
				 request.getRequestDispatcher("/error.jsp").forward(servletrequest,servletresponse);
				 return;
			 }
			 
		 }
		 
		java.util.Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			param = (String) params.nextElement(); // 获取请求中的参数
			String[] values = servletrequest.getParameterValues(param);// 获得每个参数对应的值
			System.out.println("param: " + param);
			for (int i = 0; i < values.length; i++) {
				System.out.println("value before: " + values[i]);
				checkUrl(url,param,values[i],request,servletrequest,servletresponse);
				paramValue = transform(values[i]).toString();
				values[i] = paramValue;
				System.out.println("value after: " + values[i]);
			}

			request.setAttribute(param, values);
			//request.getParameterMap().put(param, values);
			/*Map<String , String[]> map = getRequestMap(request);
			map.put(param, values);*/
		}
		arg2.doFilter(request, response);

	}
	/*private static Field requestField;
	 
	private static Field parametersParsedField;
	 
	private static Field coyoteRequestField;
	 
	private static Field parametersField;
	 
	private static Field hashTabArrField;
	private Map<String , String[]> getRequestMap(ServletRequest request) {
        try {
        	 Class clazz = Class.forName("org.apache.catalina.connector.RequestFacade");
             requestField = clazz.getDeclaredField("request");
             requestField.setAccessible(true);
  
             parametersParsedField = requestField.getType().getDeclaredField("parametersParsed");
             parametersParsedField.setAccessible(true);
  
             coyoteRequestField = requestField.getType().getDeclaredField("coyoteRequest");
             coyoteRequestField.setAccessible(true);
  
             parametersField = coyoteRequestField.getType().getDeclaredField("parameters");
             parametersField.setAccessible(true);
  
             hashTabArrField = parametersField.getType().getDeclaredField("paramHashStringArray");
             hashTabArrField.setAccessible(true);
             
            Object innerRequest = requestField.get(request);
            parametersParsedField.setBoolean(innerRequest, true);
            Object coyoteRequestObject = coyoteRequestField.get(innerRequest);
            Object parameterObject = parametersField.get(coyoteRequestObject);
            return (Map<String,String[]>)hashTabArrField.get(parameterObject);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }*/

	
	private void checkUrl(String url,String param, String value,ServletRequest request, HttpServletRequest servletrequest,
			HttpServletResponse servletresponse) throws ServletException, IOException {
		// TODO Auto-generated method stub
		for(Map<String,String> map : checkList){
			String url2 = map.get("url");
			String param2 = map.get("param");
			String type = map.get("type");
			try{
				if(url2.equals(url) && param2.equals(param)){
					if("int".equals(type)){
						Integer.parseInt(value);
					}else if("boolean".equals(type)){
						Boolean.parseBoolean(value);
					}
				}
			}catch(Exception e){
				request.getRequestDispatcher("/error.jsp").forward(servletrequest,servletresponse);
			}
		}
	}


	private StringBuffer transform(String value) {
		if (value == null) {
			return new StringBuffer();
		}
		StringBuffer result = new StringBuffer(value.length());
		for (int i = 0; i < value.length(); ++i) {
			switch (value.charAt(i)) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '"':
				result.append("&quot;");
				break;
			case '\'':
				result.append("&#39;");
				break;
			case '%':
				result.append("&#37;");
				break;
			case ';':
				result.append("&#59;");
				break;
			case '(':
				result.append("&#40;");
				break;
			case ')':
				result.append("&#41;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '+':
				result.append("&#43;");
				break;
			default:
				result.append(value.charAt(i));
				break;
			}
		}
		return result;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		map.put("url", "/_web/messageBoxPermit/messageDomainMainMenus.jsp");
		map.put("param", "id");
		map.put("type", "int");
		checkList.add(map);
		
		map = new HashMap<String,String>();
		map.put("url", "/_web/messageBoxPermit/baseEdit.jsp");
		map.put("param", "parentDomainId");
		map.put("type", "int");
		checkList.add(map);
		
		map = new HashMap<String,String>();
		map.put("url", "/_web/messageBoxPermit/baseInfo.jsp");
		map.put("param", "id");
		map.put("type", "int");
		checkList.add(map);
	}

}
