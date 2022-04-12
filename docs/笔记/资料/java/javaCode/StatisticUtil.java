/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sudytech.apps.ids.user.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sudytech.audit.api.AuditLog2;
import com.sudytech.audit.om.AuditLog;
import com.sudytech.constraint.om.Constraint;
import com.sudytech.constraint.util.ConstraintParser;
import com.sudytech.data.DBSessionFactory;
import com.sudytech.data.DataRow;
import com.sudytech.data.DataTable;
import com.sudytech.data.HiberSession;
import com.sudytech.data.IDBSession;
import com.sudytech.data.mgr.IManager;
import com.sudytech.ids.core.api.IDSCoreFactory;
import com.sudytech.ids.core.api.IIDSCoreManager;
import com.sudytech.ids.core.api.auth.AppAuth2;
import com.sudytech.ids.core.mongo.MongoLogUtil;
import com.sudytech.ids.core.om.log.LoginMode;
import com.sudytech.log.ILog;
import com.sudytech.log.LogFactory;
import com.sudytech.security.ids.ISSO;
import com.sudytech.security.ids.SSOFactory;
import com.sudytech.security.ids.SSOUser;
import com.sudytech.security.ids.api.om.User2;
import com.sudytech.system.CoreRequestContextUtil;
import com.sudytech.system.env.CoreplusEnv;
import com.sudytech.util.base.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 统计分析工具类
 */
public class StatisticUtil {

    private static ILog _log = LogFactory.getInstance().getLog(StatisticUtil.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static String DATE_FORMAT_MONTH = "%Y%m";
    public static String DATE_FORMAT_DAY = "%Y%m%d";
    public static String DATE_FORMAT_HOUR = "%Y%m%d%H";
    private static String ORACLE_DATE_FORMAT_MONTH = "yyyymm";
    private static String ORACLE_DATE_FORMAT_DAY = "yyyymmdd";
    private static String ORACLE_DATE_FORMAT_HOUR = "yyyymmddhh24";
    public static String ACCOUNT_CREATEACCOUNT = "createAccount";
    public static String ACCOUNT_MODIFYACCOUNT = "modifyAccount";
    public static String ACCOUNT_DELETEACCOUNT = "deleteAccount";
    public static String ACCOUNT_MODIFYPASSWORD  = "modifyPassword";
    
    /**
     * 建立认证统计折线图
     * @param year
     * @param month
     * @param day
     * @return
     */
	public static JSONObject buildAuthenticationStrackedLineChart(String loginName,String year, String month, String day) {
		// TODO Auto-generated method stub
		JSONObject all = new JSONObject();
		JSONObject params = buildStarckedChartDateParams(year,month,day);
		if(params==null || params.isEmpty()) {
			return all;
		}
		String beginTime = params.getString("beginTime");
		String endTime = params.getString("endTime");
		String dateFormat = params.getString("dateFormat");
		JSONObject successObj = getAuthenticationDatas(loginName,beginTime, endTime, dateFormat,1);
		JSONObject failObj = getAuthenticationDatas(loginName,beginTime, endTime, dateFormat,0);
		JSONArray xAxisArr = new JSONArray();
		JSONArray successArr = new JSONArray();
		JSONArray failArr = new JSONArray();
		JSONArray sumArr = new JSONArray();
		if(dateFormat.equals(DATE_FORMAT_MONTH)) {
			String text = "[\"一月\",\"二月\",\"三月\",\"四月\",\"五月\",\"六月\",\"七月\",\"八月\",\"九月\",\"十月\",\"十一月\",\"十二月\"]";
			xAxisArr = JSONArray.fromObject(text);
			successArr = buildMonthJSONArray(successObj,year);
			failArr = buildMonthJSONArray(failObj,year);
		}else if(dateFormat.equals(DATE_FORMAT_DAY)) {
			xAxisArr = buildDayXAxisJSONArray(year,month);
			successArr = buildDayJSONArray(successObj,year,month);
			failArr = buildDayJSONArray(failObj,year,month);
		}else if(dateFormat.equals(DATE_FORMAT_HOUR)) {
			xAxisArr = buildHourXAxisJSONArray();
			successArr = buildHourJSONArray(successObj,year,month,day);
			failArr = buildHourJSONArray(failObj,year,month,day);
		}
		sumArr = buildSumJSONArray(successArr,failArr);
		all.put("xAxis", xAxisArr);
		all.put("sum", sumArr);
		all.put("success", successArr);
		all.put("fail", failArr);
		return all;
	}
	
	private static JSONObject buildStarckedChartDateParams(String year, String month,String day) {
		// TODO Auto-generated method stub
		String beginTime = "";
		String endTime = ""; 
		String dateFormat = "";
		JSONObject obj = new JSONObject();
		if(StringUtil.isEmpty(year) && StringUtil.isEmpty(month) && StringUtil.isEmpty(day)) {
			return obj;
		}else if(!StringUtil.isEmpty(year) && StringUtil.isEmpty(month) && StringUtil.isEmpty(day) ) {
			dateFormat = DATE_FORMAT_MONTH;
			beginTime = year+"-"+"01-01 00:00:00";
			endTime = year+"-"+"12-31 23:59:59";
		}else if(!StringUtil.isEmpty(year) && !StringUtil.isEmpty(month) && StringUtil.isEmpty(day) ) {
			dateFormat = DATE_FORMAT_DAY;
			beginTime = year+"-"+month+"-01 00:00:00";
			endTime = year+"-"+month+"-"+getMonthEndDay(year,month)+" 23:59:59";
		}else if(!StringUtil.isEmpty(year) && !StringUtil.isEmpty(month) && !StringUtil.isEmpty(day) ) {
			dateFormat = DATE_FORMAT_HOUR;
			beginTime = year+"-"+month+"-"+day+" 00:00:00";
			endTime = year+"-"+month+"-"+day+" 23:59:59";
		}
		obj.put("beginTime", beginTime);
		obj.put("endTime", endTime);
		obj.put("dateFormat", dateFormat);
		return obj;
	}

	private static JSONArray buildHourJSONArray(JSONObject obj, String year, String month,String day) {
		// TODO Auto-generated method stub
		JSONArray arr = new JSONArray();
		for(int i=0;i<24 ;i++) {
			String key = year+month+day+(i<10 ? "0"+i : i);
			Object value = obj.get(key);
			arr.add(value==null ? 0 : Integer.parseInt(value.toString()));
		}
		return arr;
	}

	private static JSONArray buildHourXAxisJSONArray() {
		// TODO Auto-generated method stub
		JSONArray arr = new JSONArray();
		for(int i=0;i<24;i++) {
			arr.add(i<10 ? "0"+i : String.valueOf(i));
		}
		return arr;
	}

	private static JSONArray buildDayJSONArray(JSONObject obj, String year, String month) {
		// TODO Auto-generated method stub
		String endDay = getMonthEndDay(year, month);
		int end = StringUtil.isEmpty(endDay) ? 0 : Integer.parseInt(endDay);
		JSONArray arr = new JSONArray();
		for(int i=1;i<=end ;i++) {
			String key = year+month+(i<10 ? "0"+i : i);
			Object value = obj.get(key);
			arr.add(value==null ? 0 : Integer.parseInt(value.toString()));
		}
		return arr;
	}

	private static JSONArray buildDayXAxisJSONArray(String year, String month) {
		// TODO Auto-generated method stub
		String endDay = getMonthEndDay(year, month);
		int end = StringUtil.isEmpty(endDay) ? 0 : Integer.parseInt(endDay);
		JSONArray arr = new JSONArray();
		for(int i=1; i<=end;i++) {
			arr.add(String.valueOf(i));
		}
		return arr;
	}

	private static JSONArray buildSumJSONArray(JSONArray successArr, JSONArray failArr) {
		// TODO Auto-generated method stub
		JSONArray arr = new JSONArray();
		if(successArr==null || failArr==null || successArr.size()!=failArr.size()) return arr;
		for(int i=0; i<successArr.size();i++) {
			Integer success = Integer.parseInt(successArr.getString(i));
			Integer fail = Integer.parseInt(failArr.getString(i));
			arr.add(success+fail);
		}
		return arr;
	}

	private static JSONArray buildMonthJSONArray(JSONObject obj, String year) {
		// TODO Auto-generated method stub
		JSONArray arr = new JSONArray();
		if(obj==null || StringUtil.isEmpty(year)) return arr;
		String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
		for(int i=0;i<months.length ;i++) {
			String key = year+months[i];
			Object value = obj.get(key);
			arr.add(value==null ? 0 : Integer.parseInt(value.toString()));
		}
		return arr;
	}

	private static JSONObject getAuthenticationDatas(String loginName,String beginTime, String endTime, String dateFormat,Integer loginResult) {
		JSONObject obj = new JSONObject();
		if(MongoLogUtil.isMongoLoginLog()) {
			obj = new StatisticMongoUtil().getAuthenticationDatas(loginName,beginTime,endTime,dateFormat,loginResult);
		}else {
			HiberSession session = null;
	    	ISSO sso = null;
	        try {
	        	sso = SSOFactory.getInstance().getSSO();
	            session = (HiberSession) CoreplusEnv.getDBSessionFactory().getDBSession();
	            String sql = "";
	            sql = parseLoginLogSql(loginName, beginTime, endTime, dateFormat, loginResult);
	            DataTable dt = session.query(sql);
	            for (int i = 0; i < dt.rows().count(); i++) {
	                DataRow dr = dt.rows().item(i);
	                String dformat = dr.get("dformat").toString();
	                String count = dr.get("count").toString();
	                obj.put(dformat, count);
	            }
	            
	        } catch (Exception ex) {
	            _log.error("getAuthenticationDatas error！", ex);
	        } finally {
	            session.Close();
	            closeManager(sso, "can not close ISSO!");
	        }
		}
        return obj;
	}

	private static String parseLoginLogSql(String loginName, String beginTime, String endTime, String dateFormat,
			Integer loginResult) {
		String sql;
		if(isOracle()) {
			dateFormat = getOracleDateFormat(dateFormat);
			sql = "select to_char(LoginTime,'"+dateFormat+"') dformat ,count(id) count from ( "
				+ "select * from T_IDS_LOGINLOG where 1=1";
				if(loginResult!=null) {
					sql +=" and loginResult="+loginResult;
				}
		        if(!StringUtil.isEmpty(loginName)){			 
		        	sql += " and LoginName='"+ loginName+"'";
		        }
		        if(!StringUtil.isEmpty(beginTime)){			 
		        	sql += " and LoginTime>=to_date('"+ beginTime+"','yyyy-mm-dd hh24:mi:ss') ";
		        }
		        if(!StringUtil.isEmpty(endTime)){			 
		        	sql += " and LoginTime<=to_date('"+ endTime+"','yyyy-mm-dd hh24:mi:ss') ";
		        }
		    sql += " )a group by to_char(LoginTime,'"+dateFormat+"') order by to_char(LoginTime,'"+dateFormat+"') ";
		}else {
			 sql = "select DATE_FORMAT(LoginTime,'"+dateFormat+"') dformat,count(id) count from "
		 		   + "(select * from T_IDS_LOGINLOG where 1=1";
			 if(loginResult!=null) {
				sql +=" and loginResult="+loginResult;
			 }
		     if(!StringUtil.isEmpty(loginName)){			 
		     	sql += " and LoginName='"+ loginName+"'";
		     }
		     if(!StringUtil.isEmpty(beginTime)){			 
		     	sql += " and LoginTime>='"+ beginTime+"'";
		     }
		     if(!StringUtil.isEmpty(endTime)){			 
		     	sql += " and LoginTime<='"+ endTime+"'";
		     }
		     sql += " )a group by dformat ";
		}
		return sql;
	}
	
	private static String getOracleDateFormat(String mysqlDateFormat) {
		// TODO Auto-generated method stub
		String df = "";
		if(DATE_FORMAT_MONTH.equals(mysqlDateFormat)) {
			df = ORACLE_DATE_FORMAT_MONTH;
		}else if(DATE_FORMAT_DAY.equals(mysqlDateFormat)) {
			df = ORACLE_DATE_FORMAT_DAY;
		}else if(DATE_FORMAT_HOUR.equals(mysqlDateFormat)) {
			df = ORACLE_DATE_FORMAT_HOUR;
		}
		return df;
	}

	private static String getMonthEndDay(String year, String month) {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(year) || StringUtil.isEmpty(month) ) return "";
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(year),Integer.parseInt(month), 0); //输入类型为int类型
		return String.valueOf(c.get(Calendar.DAY_OF_MONTH));
	}
	
	private static void closeManager(IManager manager, String msg) {
        try {
            manager.close();
        } catch (Exception ex) {
            _log.error(msg, ex);
        }
    }

	
	/**
	 * 建立账号概况折线图
	 * @param year
	 * @param month
	 * @param day
	 * @param params
	 * @return
	 */
	public static JSONObject buildAccountStrackedLineChart(String year, String month, String day,
			Map<String, List<Constraint>> map) {
		// TODO Auto-generated method stub
		JSONObject all = new JSONObject();
		JSONObject params = buildStarckedChartDateParams(year,month,day);
		if(params==null || params.isEmpty()) {
			return all;
		}
		String beginTime = params.getString("beginTime");
		String endTime = params.getString("endTime");
		String dateFormat = params.getString("dateFormat");
		
		List<Constraint> consAdd = map.get(ACCOUNT_CREATEACCOUNT);
		JSONObject createObj = getAccountStatisticDatas(null,params, consAdd,AuditLog.OPTYPE_CREATE);
		List<Constraint> updateAdd = map.get(ACCOUNT_MODIFYACCOUNT);
		JSONObject modifyObj = getAccountStatisticDatas(null,params,updateAdd,AuditLog.OPTYPE_UPDATE);
		List<Constraint> deleteAdd = map.get(ACCOUNT_DELETEACCOUNT);
		JSONObject deleteObj = getAccountStatisticDatas(null,params, deleteAdd,AuditLog.OPTYPE_DELETE);
		List<Constraint> modifyPwdAdd = map.get(ACCOUNT_MODIFYPASSWORD);
		JSONObject modifyPwdObj = getAccountStatisticDatas(null,params, modifyPwdAdd,AuditLog.OPTYPE_UNKNOW);
		
		JSONArray xAxisArr = new JSONArray();
		JSONArray createArr = new JSONArray();
		JSONArray modifyArr = new JSONArray();
		JSONArray deleteArr = new JSONArray();
		JSONArray modifyPwdArr = new JSONArray();
		if(dateFormat.equals(DATE_FORMAT_MONTH)) {
			String text = "[\"一月\",\"二月\",\"三月\",\"四月\",\"五月\",\"六月\",\"七月\",\"八月\",\"九月\",\"十月\",\"十一月\",\"十二月\"]";
			xAxisArr = JSONArray.fromObject(text);
			createArr = buildMonthJSONArray(createObj,year);
			modifyArr = buildMonthJSONArray(modifyObj,year);
			deleteArr = buildMonthJSONArray(deleteObj,year);
			modifyPwdArr = buildMonthJSONArray(modifyPwdObj,year);
		}else if(dateFormat.equals(DATE_FORMAT_DAY)) {
			xAxisArr = buildDayXAxisJSONArray(year,month);
			createArr = buildDayJSONArray(createObj,year,month);
			modifyArr = buildDayJSONArray(modifyObj,year,month);
			deleteArr = buildDayJSONArray(deleteObj,year,month);
			modifyPwdArr = buildDayJSONArray(modifyPwdObj,year,month);
		}else if(dateFormat.equals(DATE_FORMAT_HOUR)) {
			xAxisArr = buildHourXAxisJSONArray();
			createArr = buildHourJSONArray(createObj,year,month,day);
			modifyArr = buildHourJSONArray(modifyObj,year,month,day);
			deleteArr = buildHourJSONArray(deleteObj,year,month,day);
			modifyPwdArr = buildHourJSONArray(modifyPwdObj,year,month,day);
		}
		all.put("xAxis", xAxisArr);
		all.put(ACCOUNT_CREATEACCOUNT, createArr);
		all.put(ACCOUNT_MODIFYACCOUNT, modifyArr);
		all.put(ACCOUNT_DELETEACCOUNT, deleteArr);
		all.put(ACCOUNT_MODIFYPASSWORD, modifyPwdArr);
		return all;
	}
	
	
	private static JSONObject getAccountStatisticDatas(Integer userId,JSONObject params,List<Constraint> cons,int type) {
		String beginTime = params.getString("beginTime");
		String endTime = params.getString("endTime");
		String dateFormat = params.getString("dateFormat");
		JSONObject obj = new JSONObject();
		
		if(MongoLogUtil.isMongoAuditLog()) {
			obj = new StatisticMongoUtil().getAccountStatisticDatas(userId,params,cons,type);
		}else {
			HiberSession session = null;
	    	ISSO sso = null;
	        try {
	        	sso = SSOFactory.getInstance().getSSO();
	            session = (HiberSession) CoreplusEnv.getDBSessionFactory().getDBSession();
	            
	            String sql = "";
	            if(isOracle()) {
	            	dateFormat = getOracleDateFormat(dateFormat);
	            	sql = "select to_char(OpTime,'"+dateFormat+"') dformat ,count(id) count from ( "
	            		+ "select * from T_AUDITLOG WHERE 1=1 ";
	            	if(userId!=null) {
	               	   sql += " AND ( OpUserId = "+userId+ " or ( OpObjectId="+userId+" and OpObjectType='com.sudytech.security.ids.impl.rich.local.om.UserPO') ) ";
	            	}
	            	sql = parseAuditLogSql(cons, type, sql);
	                if(!StringUtil.isEmpty(beginTime)){			 
	                	sql += " AND OpTime>=to_date('"+ beginTime+"','yyyy-mm-dd hh24:mi:ss') ";
	                }
	                if(!StringUtil.isEmpty(endTime)){			 
	                	sql += " AND OpTime<=to_date('"+ endTime+"','yyyy-mm-dd hh24:mi:ss') ";
	                }
	                sql += " )a group by to_char(OpTime,'"+dateFormat+"') order by to_char(OpTime,'"+dateFormat+"') ";
	            }else {
	            	 sql = "select DATE_FORMAT(OpTime,'"+dateFormat+"') dformat,count(id) count from "
	             		   + "(select * from T_AUDITLOG where 1=1 ";
		             if(userId!=null) {
		             	 sql += " AND ( OpUserId = "+userId+ " or ( OpObjectId="+userId+" and OpObjectType='com.sudytech.security.ids.impl.rich.local.om.UserPO') ) ";
		             }
		         	 sql = parseAuditLogSql(cons, type, sql);
		             if(!StringUtil.isEmpty(beginTime)){			 
		             	sql += " and OpTime>='"+ beginTime+"'";
		             }
		             if(!StringUtil.isEmpty(endTime)){			 
		             	sql += " and OpTime<='"+ endTime+"'";
		             }
		             sql += " )a group by dformat ";
	            }
	            
	            DataTable dt = session.query(sql);
	            for (int i = 0; i < dt.rows().count(); i++) {
	                DataRow dr = dt.rows().item(i);
	                String dformat = dr.get("dformat").toString();
	                String count = dr.get("count").toString();
	                obj.put(dformat, count);
	            }
	            
	        } catch (Exception ex) {
	            _log.error("getAccountStatisticDatas error！", ex);
	        } finally {
	            session.Close();
	            closeManager(sso, "can not close ISSO!");
	        }
		}
        return obj;
	}

	private static String parseAuditLogSql(List<Constraint> cons, int type, String sql) throws Exception {
		Map<String, String> fields = new HashMap();
		 fields.put(AuditLog2.Domain, "Domain");
		 fields.put(AuditLog2.Module, "AppModule");
		 fields.put(AuditLog2.Operate, "OpType");
		 fields.put(AuditLog2.ClientIp, "OpIp");
		 fields.put(AuditLog2.Content, "OpContent");
		 fields.put(AuditLog2.OperUser, "OpUserName");
		 fields.put(AuditLog2.OperTime, "OpTime");
		 fields.put(AuditLog2.OperUserId, "OpUserId");
		 ConstraintParser parser = new ConstraintParser(cons, fields);
		 List values = parser.getFieldValues();
		 String conSql = parser.getConditionSql();
		 if(!StringUtil.isEmpty(conSql)) {
			 sql += " and ";
		 }
		 for(Object value : values) {
			 conSql = conSql.replaceFirst("\\?", "'"+value.toString()+"'");
		 }
		 sql += conSql;
		if(AuditLog.OPTYPE_UPDATE == type) {
			sql +=" and OpContent not like '%密码%'";
		}
		return sql;
	}

	/**
	 * 应用访问统计
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static JSONObject buildVisitHistogramChart(String year, String month, String day,String loginName) {
		// TODO Auto-generated method stub
		JSONObject params = buildStarckedChartDateParams(year,month,day);
		JSONObject obj = new JSONObject();
		if(params==null || params.isEmpty()) {
			return obj;
		}
		String beginTime = params.getString("beginTime");
		String endTime = params.getString("endTime");
		JSONArray namesArr = new JSONArray();
        JSONArray datasArr = new JSONArray();
		IIDSCoreManager manager = null;
		HiberSession session = null;
    	ISSO sso = null;
        try {
        	sso = SSOFactory.getInstance().getSSO();
            session = (HiberSession) CoreplusEnv.getDBSessionFactory().getDBSession();
        	int casNum = getVisitLogDatas(session,sso,beginTime, endTime,"/cas/login",loginName);
        	if(casNum>0) {
        		namesArr.add("cas");
        		datasArr.add(casNum);
        	}
            manager = IDSCoreFactory.getInstance().getIDSCoreManager();
            List<AppAuth2> appAuths = manager.getAppAuths("", 0, Integer.MAX_VALUE);
           
            if(appAuths!=null && appAuths.size()>0) {
            	for(AppAuth2 app : appAuths) {
            		String name = app.getName();
            		String url = app.getUrl();
            		url = url.replace("/.*", "");
            		url = url.replace("http://", "");
            		url = url.replace("https://", "");
            		int visitNum = getVisitLogDatas(session,sso,beginTime,endTime,url,loginName);
            		if(visitNum>0) {
            			namesArr.add(name);
            			datasArr.add(visitNum);
            		}
            	}
            }
    		obj.put("names", namesArr);
    		obj.put("datas", datasArr);
        } catch (Exception ex) {
            _log.error("查询应用访问统计异常", ex);
        } finally {
            closeManager(manager, "can not close manager!");
            session.Close();
            closeManager(sso, "can not close ISSO!");
        }
		return obj;
	}
	
	private static int getVisitLogDatas(HiberSession session,ISSO sso,String beginTime, String endTime, String url,String loginName) {
		// TODO Auto-generated method stub
		int num = 0;
		if(MongoLogUtil.isMongoVisitLog()) {
			num = new StatisticMongoUtil().getVisitLogDatas(beginTime,endTime,url,loginName);
		}else {
	        try {
	        	String compare = "=";
	        	if(!"/cas/login".equals(url)) {
	        		compare="like";
	        		url = "%"+url+"%";
	        	}
	            String sql = "select count(id) from T_FW_VISITLOG where Url "+compare+" '"+url+"' ";
	            if(!StringUtil.isEmpty(loginName)){			 
	            	sql += " and loginName='"+ loginName+"'";
	            }
	            if(isOracle()) {
	            	if(!StringUtil.isEmpty(beginTime)){			 
	                	sql += "and VisitTime>=to_date('"+ beginTime+"','yyyy-mm-dd hh24:mi:ss') ";
	                }
	                if(!StringUtil.isEmpty(endTime)){			 
	                	sql += "and VisitTime<=to_date('"+ endTime+"','yyyy-mm-dd hh24:mi:ss') ";
	                }
	            }else {
		            if(!StringUtil.isEmpty(beginTime)){			 
		            	sql += " and VisitTime>='"+ beginTime+"'";
		            }
		            if(!StringUtil.isEmpty(endTime)){			 
		            	sql += " and VisitTime<='"+ endTime+"'";
		            }
	            }
	            DataTable dt = session.query(sql);
	            
	            num = Integer.parseInt(dt.rows().item(0).get(0).toString());
	            
	        } catch (Exception ex) {
	            _log.error("getVisitLogDatas error！", ex);
	        } 
		}
        return num;
	}


	/**
	 * 建立个人账号概况折线图
	 * @param year
	 * @param month
	 * @param day
	 * @param params
	 * @return
	 */
	public static JSONObject buildUserAccountStrackedLineChart(String year, String month, String day,
			Map<String, List<Constraint>> map) {
		// TODO Auto-generated method stub
		SSOUser ssoUser = CoreRequestContextUtil.getCoreRequestContext().getCurrentUser();
		JSONObject all = new JSONObject();
		JSONObject params = buildStarckedChartDateParams(year,month,day);
		if(params==null || params.isEmpty()) {
			return all;
		}
		String beginTime = params.getString("beginTime");
		String endTime = params.getString("endTime");
		String dateFormat = params.getString("dateFormat");
		
		List<Constraint> updateAdd = map.get(ACCOUNT_MODIFYACCOUNT);
		int userId = ssoUser.getUser().getId();
		JSONObject modifyObj = getAccountStatisticDatas(userId,params, updateAdd,AuditLog.OPTYPE_UPDATE);
		List<Constraint> modifyPwdAdd = map.get(ACCOUNT_MODIFYPASSWORD);
		JSONObject modifyPwdObj = getAccountStatisticDatas(userId,params, modifyPwdAdd,AuditLog.OPTYPE_UNKNOW);
		
		JSONArray xAxisArr = new JSONArray();
		JSONArray modifyArr = new JSONArray();
		JSONArray modifyPwdArr = new JSONArray();
		if(dateFormat.equals(DATE_FORMAT_MONTH)) {
			String text = "[\"一月\",\"二月\",\"三月\",\"四月\",\"五月\",\"六月\",\"七月\",\"八月\",\"九月\",\"十月\",\"十一月\",\"十二月\"]";
			xAxisArr = JSONArray.fromObject(text);
			modifyArr = buildMonthJSONArray(modifyObj,year);
			modifyPwdArr = buildMonthJSONArray(modifyPwdObj,year);
		}else if(dateFormat.equals(DATE_FORMAT_DAY)) {
			xAxisArr = buildDayXAxisJSONArray(year,month);
			modifyArr = buildDayJSONArray(modifyObj,year,month);
			modifyPwdArr = buildDayJSONArray(modifyPwdObj,year,month);
		}else if(dateFormat.equals(DATE_FORMAT_HOUR)) {
			xAxisArr = buildHourXAxisJSONArray();
			modifyArr = buildHourJSONArray(modifyObj,year,month,day);
			modifyPwdArr = buildHourJSONArray(modifyPwdObj,year,month,day);
		}
		all.put("xAxis", xAxisArr);
		all.put(ACCOUNT_MODIFYACCOUNT, modifyArr);
		all.put(ACCOUNT_MODIFYPASSWORD, modifyPwdArr);
		return all;
	}

	/**
	 * 访客浏览器统计
	 * @return
	 */
	public static JSONArray buildVisitBrowserPieChart(String dateType,Integer userId) {
		// TODO Auto-generated method stub
		JSONArray all = new JSONArray();
		JSONObject dateObject = buildYearMonthWeekFromDateType(dateType);
		if(dateObject==null || dateObject.isEmpty()) {
			return all;
		}
		String beginTime = dateObject.getString("beginTime");
		String endTime = dateObject.getString("endTime");
		HiberSession session = null;
    	ISSO sso = null;
        try {
        	sso = SSOFactory.getInstance().getSSO();
            session = (HiberSession) CoreplusEnv.getDBSessionFactory().getDBSession();
            
            String sql = "SELECT Browser,count(1) Num FROM T_LOGINHISTORY where 1=1 " ;
            if(userId!=null){			 
            	sql += " and UserId="+ userId;
            }
            if(isOracle()) {
            	if(!StringUtil.isEmpty(beginTime)){			 
                	sql += "and LoginDateTime>=to_date('"+ beginTime+"','yyyy-mm-dd hh24:mi:ss') ";
                }
                if(!StringUtil.isEmpty(endTime)){			 
                	sql += "and LoginDateTime<=to_date('"+ endTime+"','yyyy-mm-dd hh24:mi:ss') ";
                }
            }else {
	            if(!StringUtil.isEmpty(beginTime)){			 
	            	sql += " and LoginDateTime>='"+ beginTime+"'";
	            }
	            if(!StringUtil.isEmpty(endTime)){			 
	            	sql += " and LoginDateTime<='"+ endTime+"'";
	            }
            }
            sql += " GROUP BY Browser ";
            
            DataTable dt = session.query(sql);
            for (int i = 0; i < dt.rows().count(); i++) {
                DataRow dr = dt.rows().item(i);
                String name = dr.get("Browser").toString();
                String value = dr.get("Num").toString();
                JSONObject obj = new JSONObject();
                obj.put("name", name);
                obj.put("value", Integer.parseInt(value));
                all.add(obj);
            }
        } catch (Exception ex) {
            _log.error("buildVisitBrowserPieChart error！", ex);
        } finally {
            session.Close();
            closeManager(sso, "can not close ISSO!");
        }
		return all;
	}
	
	private static JSONObject buildYearMonthWeekFromDateType(String dateType) {
		// TODO Auto-generated method stub
		JSONObject dateObject = new JSONObject();
		try {
			Calendar c = Calendar.getInstance();  
			  int year = c.get(Calendar.YEAR);    //获取年
			  int month = c.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
			  int last = c.getActualMaximum(c.DAY_OF_MONTH);    //获取本月最大天数
			  String monthText = month<10 ? "0"+month : String.valueOf(month);
			  String thisWeekMonday = sdf.format(getThisWeekMonday());
			  String thisWeekSunday = sdf.format(getThistWeekSunday());
			  if("week".equals(dateType)) {
				  dateObject.put("beginTime", thisWeekMonday+" 00:00:00");
				  dateObject.put("endTime", thisWeekSunday+" 23:59:59");
			  }else if("month".equals(dateType)) {
				  dateObject.put("beginTime", year+"-"+monthText+"-01 00:00:00");
				  dateObject.put("endTime", year+"-"+monthText+"-"+last+" 23:59:59");
			  }else {
				  dateObject.put("beginTime", year+"-01-01 00:00:00");
				  dateObject.put("endTime", year+"-12-31 23:59:59");
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateObject;
	}

	public static Date getThisWeekMonday() {
		Date date= new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}
 
	public static Date getThistWeekSunday() {
		//Date date= new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday());
		cal.add(Calendar.DATE, 6);
		return cal.getTime();
	}
	
	/**
	 * 首页 认证方式统计
	 * @return
	 */
	public static JSONArray buildLoginModePieChart(String dateType,String loginName) {
		// TODO Auto-generated method stub
		JSONArray all = new JSONArray();
		JSONObject dateObject = buildYearMonthWeekFromDateType(dateType);
		String beginTime = dateObject.getString("beginTime");
		String endTime = dateObject.getString("endTime");
		if(dateObject==null || dateObject.isEmpty()) {
			return all;
		}
		if(MongoLogUtil.isMongoLoginLog()) {
			all = new StatisticMongoUtil().buildLoginModePieChart(beginTime,endTime,loginName);
		}else {
			HiberSession session = null;
	    	ISSO sso = null;
	        try {
	        	sso = SSOFactory.getInstance().getSSO();
	            session = (HiberSession) CoreplusEnv.getDBSessionFactory().getDBSession();
	            
	            String sql = "SELECT LoginMode,count(1) Num FROM T_IDS_LOGINLOG where 1=1 " ;
	            if(!StringUtil.isEmpty(loginName)){			 
	            	sql += " and LoginName='"+ loginName+"'";
	            }
	            if(isOracle()) {
	            	if(!StringUtil.isEmpty(beginTime)){			 
	                	sql += "and LoginTime>=to_date('"+ beginTime+"','yyyy-mm-dd hh24:mi:ss') ";
	                }
	                if(!StringUtil.isEmpty(endTime)){			 
	                	sql += "and LoginTime<=to_date('"+ endTime+"','yyyy-mm-dd hh24:mi:ss') ";
	                }
	            }else {
		            if(!StringUtil.isEmpty(beginTime)){			 
		            	sql += " and LoginTime>='"+ beginTime+"'";
		            }
		            if(!StringUtil.isEmpty(endTime)){			 
		            	sql += " and LoginTime<='"+ endTime+"'";
		            }
	            }
	            sql += " GROUP BY LoginMode ";
	            
	            DataTable dt = session.query(sql);
	            for (int i = 0; i < dt.rows().count(); i++) {
	                DataRow dr = dt.rows().item(i);
	                String mode = dr.get("LoginMode").toString();
	                String value = dr.get("Num").toString();
	                String name = LoginMode.loadModeName(Integer.parseInt(mode));
	                JSONObject obj = new JSONObject();
	                obj.put("name", name);
	                obj.put("value", Integer.parseInt(value));
	                all.add(obj);
	            }
	        } catch (Exception ex) {
	            _log.error("buildLoginModePieChart error！", ex);
	        } finally {
	            session.Close();
	            closeManager(sso, "can not close ISSO!");
	        }
		}
		return all;
	}
	
	/**
	 * 大屏展示 累积认证数量
	 */
	public static JSONObject accumulativeCertificateLS() {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		if(MongoLogUtil.isMongoLoginLog()) {
			obj = new StatisticMongoUtil().accumulativeCertificateLS();
		}else {
			HiberSession session = null;
	    	ISSO sso = null;
	        try {
	        	sso = SSOFactory.getInstance().getSSO();
	            session = (HiberSession) CoreplusEnv.getDBSessionFactory().getDBSession();
	            
	            String sql = "select sum(1) sum,SUM(CASE WHEN LoginResult = 1 THEN 1 ELSE 0 END) success," + 
	            		"SUM(CASE WHEN LoginResult =0 THEN 1 ELSE 0 END) fail from T_IDS_LOGINLOG" ;
	            
	            DataTable dt = session.query(sql);
	            for (int i = 0; i < dt.rows().count(); i++) {
	                DataRow dr = dt.rows().item(i);
	                String sum = dr.get("sum").toString();
	                String success = dr.get("success").toString();
	                String fail = dr.get("fail").toString();
	                obj.put("sum", Integer.parseInt(sum));
	                obj.put("success", Integer.parseInt(success));
	            }
	        } catch (Exception ex) {
	            _log.error("buildLoginModePieChart error！", ex);
	        } finally {
	            session.Close();
	            closeManager(sso, "can not close ISSO!");
	        }
		}
		return obj;
	}
	
	/**
	 * 大屏展示 认证成功率
	 * @param beginTime
	 * @param endTime
	 * @param rowBeginIndex
	 * @param rows
	 * @return
	 */
	public static JSONArray loadCertificateSuccessRateLS(String beginTime,String endTime,int rowBeginIndex,int rows){
		JSONArray arr = new JSONArray();
		if(MongoLogUtil.isMongoLoginLog()) {
			arr = new StatisticMongoUtil().loadCertificateSuccessRateLS(beginTime,endTime,rowBeginIndex,rows);
		}else {
	    	HiberSession session = null;
	    	ISSO sso = null;
	        try {
	        	sso = SSOFactory.getInstance().getSSO();
	            session = (HiberSession) CoreplusEnv.getDBSessionFactory().getDBSession();
	            String sql = "";
	            DataTable dt = null;
	            if(isOracle()) {
	            	sql = "select * from ( select B.*,ROWNUM rn from (select success/sum successRate,loginName,success,sum from (select loginName,sum(1) sum,"
	            		+ "SUM(CASE WHEN LoginResult =1 THEN 1 ELSE 0 END) success,"
	            		+ "SUM(CASE WHEN LoginResult =0 THEN 1 ELSE 0 END) fail from T_IDS_LOGINLOG where 1=1 ";
	            	if(!StringUtil.isEmpty(beginTime)){			 
			        	sql += " and LoginTime>=to_date('"+ beginTime+"','yyyy-mm-dd hh24:mi:ss') ";
			        }
			        if(!StringUtil.isEmpty(endTime)){			 
			        	sql += " and LoginTime<=to_date('"+ endTime+"','yyyy-mm-dd hh24:mi:ss') ";
			        }
			        sql += " group by loginName order by max(LoginTime) desc ) a order by successRate desc ) B WHERE ROWNUM <=?) C WHERE RN>?";
			        dt = session.query(sql, new Integer[]{rowBeginIndex+rows,rowBeginIndex});
	            }else {
	            	sql = "select success/sum successRate,loginName,success,sum from (select loginName,sum(1) sum,SUM(CASE WHEN LoginResult = 1 THEN 1 ELSE 0 END) success,"
	                    + "SUM(CASE WHEN LoginResult =0 THEN 1 ELSE 0 END) fail from T_IDS_LOGINLOG where 1=1 ";
		            if(!StringUtil.isEmpty(beginTime)){			 
		            	sql += " and LoginTime>='"+ beginTime+"'";
		            }
		            if(!StringUtil.isEmpty(endTime)){			 
		            	sql += " and LoginTime<='"+ endTime+"'";
		            }
		            sql += " group by loginName order by max(LoginTime) desc ) a order by successRate desc limit ?,?";
		            dt = session.query(sql, new Integer[]{rowBeginIndex,rows});
	            }
	            
	            for (int i = 0; i < dt.rows().count(); i++) {
	                DataRow dr = dt.rows().item(i);
	                String loginName = dr.get("LoginName").toString();
	                User2 user = sso.findUserByLoginName(loginName);
	                String sum = dr.get("sum").toString();
	                String successRate = dr.get("successRate").toString();
	                JSONObject obj = new JSONObject();
	                obj.put("name", user==null ? "账号不存在" : user.getName());
	                obj.put("sum", sum);
	                obj.put("successRate", parseSuccessRate(successRate));
	                arr.add(obj);
	            }
	            
	        } catch (Exception ex) {
	            _log.error("buildCertTable error！", ex);
	        } finally {
	            session.Close();
	            closeManager(sso, "can not close ISSO!");
	        }
		}
        return arr;
    }

	private static String parseSuccessRate(String successRate) {
		// TODO Auto-generated method stub
		Double rd = Double.parseDouble(successRate);
		int ri = (int) (Math.round(rd * 100));
		return ri+"%";
	}

	public static JSONArray accountStatisticByPieChartLS(String beginTime, String endTime,Map<String, List<Constraint>> params) {
		// TODO Auto-generated method stub
		JSONArray arr = new JSONArray();
		List<Constraint> consAdd = params.get(ACCOUNT_CREATEACCOUNT);
		int createNum = getAccountStatisticDatas(beginTime,endTime,consAdd,AuditLog.OPTYPE_CREATE);
		JSONObject createObj = new JSONObject();
		createObj.put("name", "创建账号");
		createObj.put("value", createNum);
		arr.add(createObj);
		List<Constraint> updateAdd = params.get(ACCOUNT_MODIFYACCOUNT);
		int modifyNum = getAccountStatisticDatas(beginTime,endTime,updateAdd,AuditLog.OPTYPE_UPDATE);
		JSONObject modifyObj = new JSONObject();
		modifyObj.put("name", "修改账号");
		modifyObj.put("value",  modifyNum);
		arr.add(modifyObj);
		List<Constraint> deleteAdd = params.get(ACCOUNT_DELETEACCOUNT);
		int deleteNum = getAccountStatisticDatas(beginTime,endTime, deleteAdd,AuditLog.OPTYPE_DELETE);
		JSONObject deleteObj = new JSONObject();
		deleteObj.put("name", "删除账号");
		deleteObj.put("value",  deleteNum);
		arr.add(deleteObj);
		List<Constraint> modifyPwdAdd = params.get(ACCOUNT_MODIFYPASSWORD);
		int modifyPwdNum = getAccountStatisticDatas(beginTime,endTime, modifyPwdAdd,AuditLog.OPTYPE_UNKNOW);
		JSONObject modifyPwdObj = new JSONObject();
		modifyPwdObj.put("name", "修改密码");
		modifyPwdObj.put("value",  modifyPwdNum);
		arr.add(modifyPwdObj);
		return arr;
	}
	
	private static int getAccountStatisticDatas(String beginTime,String endTime,List<Constraint> cons,int type) {
		int num = 0;
		if(MongoLogUtil.isMongoAuditLog()) {
			num = new StatisticMongoUtil().getAccountStatisticDatas(beginTime,endTime,cons,type);
		}else {
			HiberSession session = null;
	    	ISSO sso = null;
	        try {
	        	sso = SSOFactory.getInstance().getSSO();
	            session = (HiberSession) CoreplusEnv.getDBSessionFactory().getDBSession();
	            
	            String sql = "select count(id) count from T_AUDITLOG where 1=1 ";
	        	sql = parseAuditLogSql(cons, type, sql);
	        	if(isOracle()) {
	        		 if(!StringUtil.isEmpty(beginTime)){			 
	                 	sql += " AND OpTime>=to_date('"+ beginTime+"','yyyy-mm-dd hh24:mi:ss') ";
	                 }
	                 if(!StringUtil.isEmpty(endTime)){			 
	                 	sql += " AND OpTime<=to_date('"+ endTime+"','yyyy-mm-dd hh24:mi:ss') ";
	                 }
	        	 }else {
	        		 if(!StringUtil.isEmpty(beginTime)){			 
	        			 sql += " and OpTime>='"+ beginTime+"'";
	        		 }
	        		 if(!StringUtil.isEmpty(endTime)){			 
	        			 sql += " and OpTime<='"+ endTime+"'";
	        		 }
	        	 }
	            DataTable dt = session.query(sql);
	            return Integer.parseInt(dt.rows().item(0).get("count").toString());
	            
	        } catch (Exception ex) {
	            _log.error("getAccountStatisticDatas error！", ex);
	        } finally {
	            session.Close();
	            closeManager(sso, "can not close ISSO!");
	        }
		}
        return num;
	}

	public static JSONObject accountStatisticYearChangeDatasLS(Map<String, List<Constraint>> params) {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();  
		String thisYear = c.get(Calendar.YEAR)+"-01-01 00:00:00";    //获取年
		String lastYear = (c.get(Calendar.YEAR)-1) +"-01-01 00:00:00";
		String nextYear = (c.get(Calendar.YEAR)+1) +"-01-01 00:00:00";
		JSONObject obj = new JSONObject();
		List<Constraint> consAdd = params.get(ACCOUNT_CREATEACCOUNT);
		int thisYearCreateNum = getAccountStatisticDatas(thisYear,nextYear,consAdd,AuditLog.OPTYPE_CREATE);
		int lastYearCreateNum = getAccountStatisticDatas(lastYear,thisYear,consAdd,AuditLog.OPTYPE_CREATE);
		obj.put("yearCreateAccount", thisYearCreateNum);
		obj.put("yearCreateIncreaseRate", compareLastIncreaseRate(lastYearCreateNum,thisYearCreateNum));
		
		List<Constraint> updateAdd = params.get(ACCOUNT_MODIFYACCOUNT);
		int thisYearModifyNum = getAccountStatisticDatas(thisYear,nextYear,updateAdd,AuditLog.OPTYPE_UPDATE);
		int lastYearModifyNum = getAccountStatisticDatas(lastYear,thisYear,updateAdd,AuditLog.OPTYPE_UPDATE);
		obj.put("yearModifyAccount", thisYearModifyNum);
		obj.put("yearModifyIncreaseRate", compareLastIncreaseRate(lastYearModifyNum,thisYearModifyNum));
		
		List<Constraint> deleteAdd = params.get(ACCOUNT_DELETEACCOUNT);
		int thisYearDeleteNum = getAccountStatisticDatas(thisYear,nextYear, deleteAdd,AuditLog.OPTYPE_DELETE);
		int lastYearDeleteNum = getAccountStatisticDatas(lastYear,thisYear, deleteAdd,AuditLog.OPTYPE_DELETE);
		obj.put("yearDeleteAccount", thisYearDeleteNum);
		obj.put("yearDeleteIncreaseRate", compareLastIncreaseRate(lastYearDeleteNum,thisYearDeleteNum));
		
		List<Constraint> modifyPwdAdd = params.get(ACCOUNT_MODIFYPASSWORD);
		int thisYearModifyPwdNum = getAccountStatisticDatas(thisYear,nextYear, modifyPwdAdd,AuditLog.OPTYPE_UNKNOW);
		int lastYearModifyPwdNum = getAccountStatisticDatas(lastYear,thisYear, modifyPwdAdd,AuditLog.OPTYPE_UNKNOW);
		obj.put("yearModifyPwd", thisYearModifyPwdNum);
		obj.put("yearModifyPwdIncreaseRate", compareLastIncreaseRate(lastYearModifyPwdNum,thisYearModifyPwdNum));
		
		return obj;
	}

	private static String compareLastIncreaseRate(int lastYearNum, int thisYearNum) {
		// TODO Auto-generated method stub
		Double increaseRate = (thisYearNum - lastYearNum) / Double.valueOf(lastYearNum) ; 
		return parseSuccessRate(increaseRate+"");
	}
	public static void main(String[] args) {
		System.out.println(compareLastIncreaseRate(17,12));
	}

	/**
	 * 大屏展示 认证数据 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static JSONObject loadCertificateDatasLS(String beginTime,String endTime){
		JSONObject obj = new JSONObject();
		if(MongoLogUtil.isMongoAuditLog()) {
			obj = new StatisticMongoUtil().loadCertificateDatasLS(beginTime,endTime);
		}else {
	    	HiberSession session = null;
	    	ISSO sso = null;
	        try {
	        	sso = SSOFactory.getInstance().getSSO();
	            session = (HiberSession) CoreplusEnv.getDBSessionFactory().getDBSession();
	            String sql = "select avg,sum,SUCCESS/SUM successRate from (select avg(sum1) avg,sum(sum1) sum,sum(success) success,sum(fail) fail from ( "
	            		   + "SELECT loginName,sum(1) sum1,SUM(CASE WHEN LoginResult = 1 THEN 1 ELSE 0 END) success,SUM(CASE WHEN LoginResult = 0 THEN 1 ELSE 0 END) fail " 
	            		   + "FROM T_IDS_LOGINLOG WHERE 1 = 1 ";
	            
	            if(isOracle()) {
		       		 if(!StringUtil.isEmpty(beginTime)){			 
		              	sql += " AND LoginTime>=to_date('"+ beginTime+"','yyyy-mm-dd hh24:mi:ss') ";
		              }
		              if(!StringUtil.isEmpty(endTime)){			 
		              	sql += " AND LoginTime<=to_date('"+ endTime+"','yyyy-mm-dd hh24:mi:ss') ";
		              }
	     	    }else {
		            if(!StringUtil.isEmpty(beginTime)){			 
		            	sql += "and LoginTime>='"+ beginTime+"'";
		            }
		            if(!StringUtil.isEmpty(endTime)){			 
		            	sql += "and LoginTime<='"+ endTime+"'";
		            }
	     	    }
	            sql += " GROUP BY loginName ORDER BY max(LoginTime) DESC ) a ) b";
	            
	            DataTable dt = session.query(sql);
	            DataRow dr = dt.rows().item(0);
	            String sum = dr.get("sum").toString();
	            String avg = dr.get("avg").toString();
	            String successRate = dr.get("successRate").toString();
	            JSONArray authFastigium = loadCertificateFastigium(beginTime,endTime,session);
	            
	            obj.put("avg", avg.indexOf(".")>-1 ? avg.substring(0, avg.indexOf(".")+3) : avg);
	            obj.put("sum", sum);
	            obj.put("successRate", parseSuccessRate(successRate));
	            obj.put("authFastigium", authFastigium);
	        } catch (Exception ex) {
	            _log.error("buildCertTable error！", ex);
	        } finally {
	            session.Close();
	            closeManager(sso, "can not close ISSO!");
	        }
		}
        return obj;
    }

	/**
	 * 认证高峰期 按小时分组
	 * @param beginTime
	 * @param endTime
	 * @param session
	 * @return
	 */
	private static JSONArray loadCertificateFastigium(String beginTime, String endTime, HiberSession session) {
		// TODO Auto-generated method stub
		JSONArray arr = new JSONArray();
		try {
			session = (HiberSession) CoreplusEnv.getDBSessionFactory().getDBSession();
			String sql = "";
	        if(isOracle()) {
	        	 sql += "select to_char(LoginTime,'hh24') dformat,count(id) num from T_IDS_LOGINLOG where 1=1 ";
	       		 if(!StringUtil.isEmpty(beginTime)){			 
	              	sql += " AND LoginTime>=to_date('"+ beginTime+"','yyyy-mm-dd hh24:mi:ss') ";
	              }
	              if(!StringUtil.isEmpty(endTime)){			 
	              	sql += " AND LoginTime<=to_date('"+ endTime+"','yyyy-mm-dd hh24:mi:ss') ";
	              }
	              sql += "group by to_char(LoginTime,'hh24') order by num desc";
    	    }else {
    	    	sql = "select * from ( SELECT  DATE_FORMAT(LoginTime, '%k') dformat,count(id) count1 " 
    	    		+ "FROM ( SELECT * FROM T_IDS_LOGINLOG WHERE 1 = 1 ";
		        if(!StringUtil.isEmpty(beginTime)){			 
		        	sql += "and LoginTime>='"+ beginTime+"'";
		        }
		        if(!StringUtil.isEmpty(endTime)){			 
		        	sql += "and LoginTime<='"+ endTime+"'";
		        }
		        sql += " ) a GROUP BY dformat) a order by count1 desc ";
    	    }
	        
	        DataTable dt = session.query(sql);
	        
	        int num = dt.rows().count()>3 ? 3 : dt.rows().count();
			for (int i = 0; i < num ; i++) {
                DataRow dr = dt.rows().item(i);
                String dformat = dr.get("dformat").toString();
                dformat = dformat.startsWith("0") ? dformat.substring(1) : dformat;
                String text = dformat+"点~"+(Integer.parseInt(dformat)+1)+"点";
                arr.add(text);
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public static boolean isOracle() {
		IDBSession dbSession = null;

		try {
			dbSession = DBSessionFactory.getInstance().getDBSession();
			if (dbSession != null) {
				int dbType = dbSession.getDatabaseType();
				return dbType==2;
			}
		} catch (Exception var11) {
			_log.error("判断是否是oracle数据库！", var11);
		} finally {
			try {
				if (dbSession != null) {
					dbSession.Close();
				}
			} catch (Exception var10) {
				_log.error("can not close DBSession!", var10);
			}

		}
		return false;
	}
}
