package com.sudytech.apps.ids.user.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sudytech.apps.ids.util.IdsQueryManager;
import com.sudytech.audit.api.AuditLog2;
import com.sudytech.audit.om.AuditLog;
import com.sudytech.constraint.om.Constraint;
import com.sudytech.constraint.om.OrConstraint;
import com.sudytech.constraint.om.Order;
import com.sudytech.constraint.om.RelationConstraint;
import com.sudytech.constraint.om.TypeConstraint;
import com.sudytech.data.mgr.IManager;
import com.sudytech.firewall.api.om.VisitLog2;
import com.sudytech.firewall.om.VisitLog;
import com.sudytech.ids.core.api.log.LoginLog2;
import com.sudytech.ids.core.api.sso.IdsConstraint;
import com.sudytech.ids.core.mongo.MongoManager;
import com.sudytech.ids.core.om.log.LoginLog;
import com.sudytech.ids.core.om.log.LoginMode;
import com.sudytech.log.ILog;
import com.sudytech.log.LogFactory;
import com.sudytech.mongo.api.IMongoDBManager;
import com.sudytech.mongo.api.MongoDBMgrFactory;
import com.sudytech.mongo.util.MongoMapperUtil;
import com.sudytech.security.ids.ISSO;
import com.sudytech.security.ids.SSOFactory;
import com.sudytech.security.ids.api.om.User2;
import com.sudytech.system.DateTime;
import com.sudytech.util.base.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * mongo统计分析工具类
 * 
 * @author sudy
 *
 */
public class StatisticMongoUtil {

	private static ILog _log = LogFactory.getInstance().getLog(StatisticMongoUtil.class);

	/**
	 * mongo查询账号概况
	 * 
	 * @param userId
	 * @param params
	 * @param cons
	 * @param type
	 * @return
	 */
	public JSONObject getAccountStatisticDatas(Integer userId, JSONObject params, List<Constraint> cons, int type) {
		// TODO Auto-generated method stub
		IMongoDBManager manager = null;
		JSONObject obj = new JSONObject();
		String beginTime = params.getString("beginTime");
		String endTime = params.getString("endTime");
		String dateFormat = params.getString("dateFormat");
		try {
			manager = MongoDBMgrFactory.getInstance().getManager();
			DB db = manager.getDB();
			BasicDBObject matchObj = new BasicDBObject();
			// BasicDBObject orderObj = new BasicDBObject();
			if (!StringUtil.isEmpty(beginTime)) {
				cons.add(AuditLog2.buildOperTimeConstraint(DateTime.parseDateTime(beginTime).toDate(),
						Constraint.Compare_GreaterThanOrEqual));
			}
			if (!StringUtil.isEmpty(endTime)) {
				cons.add(AuditLog2.buildOperTimeConstraint(DateTime.parseDateTime(endTime).toDate(),
						Constraint.Compare_LessThanOrEqual));
			}
			if (userId != null) {
				cons.add(AuditLog2.buildOperUserIdConstraint(userId));
			}
			generAuditLogWhere(matchObj, cons);

			String table = MongoMapperUtil.getCollectionName(AuditLog.class);
			DBCollection collection = db.getCollection(table);
			obj = selectGroupByTime(dateFormat, collection, matchObj, "$opTime");
		} catch (Exception ex) {
			_log.error("查询MONGO数据时异常：" + ex);
		} finally {
			closeMongo(manager, "can not close IMongoDBManager!");
		}
		return obj;
	}

	private JSONObject selectGroupByTime(String dateFormat, DBCollection collection, BasicDBObject matchObj,
			String timeCol) {
		JSONObject obj = new JSONObject();
		try {
			BasicDBObject groupObj = new BasicDBObject();
			BasicDBObject dataAddObj = new BasicDBObject();
			JSONArray arr = new JSONArray();
			arr.add(timeCol);
			arr.add(28800000);// mongo少八小时
			dataAddObj.put("$add", arr);
			BasicDBObject dateObj = new BasicDBObject("date", dataAddObj);
			dateObj.put("format", dateFormat);
			groupObj.put("_id", new BasicDBObject("$dateToString", dateObj));
			groupObj.put("count", new BasicDBObject("$sum", 1));

			Map<String, Object> projectMap = new HashMap<String, Object>();
			projectMap.put("dformat", "$_id");
			projectMap.put("count", "$count");
			projectMap.put("_id", 0);
			BasicDBObject projectObj = new BasicDBObject(projectMap);

			BasicDBObject match = new BasicDBObject("$match", matchObj);
			BasicDBObject group = new BasicDBObject("$group", groupObj);
			BasicDBObject project = new BasicDBObject("$project", projectObj);
			BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("_id", 1));

			List<BasicDBObject> aggregateList = new ArrayList<BasicDBObject>();
			aggregateList.add(match);
			aggregateList.add(group);
			aggregateList.add(project);
			aggregateList.add(sort);
			AggregationOutput output = collection.aggregate(aggregateList);

			Iterator<DBObject> ite = output.results().iterator();
			while (ite.hasNext()) {
				DBObject dbObj = ite.next();
				JSONObject dobj = JSONObject.fromObject(dbObj);
				String dformat = dobj.getString("dformat");
				String count = dobj.getString("count");
				obj.put(dformat, count);
			}
		} catch (Exception ex) {
			_log.error("selectGroupByTime error" + ex);
		}
		return obj;
	}

	private static void generAuditLogWhere(BasicDBObject fiterJson, List<Constraint> constraints)
			throws ParseException {
		BasicDBObject orObj = new BasicDBObject();
		BasicDBList addList = new BasicDBList();
		BasicDBList orList = new BasicDBList();
		if (constraints != null && !constraints.isEmpty()) {
			for (Constraint cons : constraints) {
				TypeConstraint tcons = (TypeConstraint) cons;
				if (tcons.getName().equals(AuditLog2.Domain)) {
					addList.add(new BasicDBObject("domain", new BasicDBObject("$eq", tcons.getValue())));
				}
				if (tcons.getName().equals(AuditLog2.Operate)) {
					addList.add(new BasicDBObject("opType",
							new BasicDBObject("$eq",
									tcons.getValue() != null ? Integer.parseInt(tcons.getValue().toString())
											: tcons.getValue())));
				}
				if (tcons.getName().equals(AuditLog2.ClientIp)) {
					addList.add(new BasicDBObject("clientIp", new BasicDBObject("$eq", tcons.getValue())));
				}
				if (tcons.getName().equals(AuditLog2.OperUser)) {
					addList.add(new BasicDBObject("operUser", new BasicDBObject("$eq", tcons.getValue())));
				}
				if (tcons.getName().equals(IdsConstraint.AuditLog_Constraint_SubModule)) {
					addList.add(new BasicDBObject("tag1", new BasicDBObject("$eq", tcons.getValue())));
				}
				if (tcons.getName().equals(AuditLog2.Content)) {
					String value = tcons.getValue().toString();
					value = value.substring(1, value.length() - 1);
					Pattern pattern = Pattern.compile("^.*" + value + ".*$");
					addList.add(new BasicDBObject("opContent", pattern));
				}
				if (tcons.getName().equals(AuditLog2.OperUserId)) {
					String userId = tcons.getValue().toString();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("opObjectId", new BasicDBObject("$eq", userId));
					map.put("opObjectType",
							new BasicDBObject("$eq", "com.sudytech.security.ids.impl.rich.local.om.UserPO"));
					orList.add(map);
					BasicDBObject OpUserIdObj = new BasicDBObject("opUserId", new BasicDBObject("$eq", userId));
					orList.add(OpUserIdObj);
				}
				if (tcons.getName().equals(AuditLog2.OperTime)) {
					if (tcons.getCompare() == Constraint.Compare_GreaterThanOrEqual) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						addList.add(new BasicDBObject("opTime",
								new BasicDBObject("$gte", sdf.parse(tcons.getValue().toString()))));
					}
				}
				if (tcons.getName().equals(AuditLog2.OperTime)) {
					if (tcons.getCompare() == Constraint.Compare_LessThanOrEqual) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						addList.add(new BasicDBObject("opTime",
								new BasicDBObject("$lte", sdf.parse(tcons.getValue().toString()))));
					}
				}
			}
		}
		if (!orList.isEmpty() && orList.size() != 0) {
			orObj.put("$or", orList);
		}
		if (!addList.isEmpty() && addList.size() != 0) {
			if (orObj != null && !orObj.isEmpty()) {
				addList.add(orObj);
			}
			fiterJson.put("$and", addList);
		} else {
			if (orObj != null && !orObj.isEmpty()) {
				fiterJson.put("$or", orList);
			}
		}
	}

	/**
	 * mongo查询认证概况
	 * 
	 * @param loginName
	 * @param beginTime
	 * @param endTime
	 * @param dateFormat
	 * @param loginResult
	 * @return
	 */
	public JSONObject getAuthenticationDatas(String loginName, String beginTime, String endTime, String dateFormat,
			Integer loginResult) {
		// TODO Auto-generated method stub
		IMongoDBManager manager = null;
		JSONObject obj = new JSONObject();
		try {
			manager = MongoDBMgrFactory.getInstance().getManager();
			DB db = manager.getDB();
			BasicDBObject matchObj = new BasicDBObject();
			List<Constraint> constraints = new ArrayList<Constraint>();
			if (!StringUtil.isEmpty(beginTime)) {
				constraints
						.add(IdsQueryManager.getGreaterThanConstraint(LoginLog2.Constraint_LoginTime_Begin, beginTime));
			}
			if (!StringUtil.isEmpty(endTime)) {
				constraints.add(IdsQueryManager.getLessThanConstraint(LoginLog2.Constraint_LoginTime_End, endTime));
			}
			if (!StringUtil.isEmpty(loginName)) {
				constraints.add(IdsQueryManager.getEqualConstraint(LoginLog2.Constraint_LoginName, loginName));
			}
			if (loginResult != null) {
				constraints.add(IdsQueryManager.getEqualConstraint(LoginLog2.Constraint_LoginResult, loginResult + ""));
			}
			generLoginLogWhere(matchObj, constraints);

			String table = MongoMapperUtil.getCollectionName(LoginLog.class);
			DBCollection collection = db.getCollection(table);
			obj = selectGroupByTime(dateFormat, collection, matchObj, "$loginTime");
		} catch (Exception ex) {
			_log.error("查询MONGO数据时异常：" + ex);
		} finally {
			closeMongo(manager, "can not close IMongoDBManager!");
		}
		return obj;
	}

	private void generLoginLogWhere(BasicDBObject dbObject, List<Constraint> constraints) throws ParseException {
		BasicDBList addList = new BasicDBList();
		if (constraints != null && !constraints.isEmpty()) {
			for (Constraint cons : constraints) {
				if (cons instanceof TypeConstraint) {
					TypeConstraint tcons = (TypeConstraint) cons;
					if (tcons.getName().equals(LoginLog2.Constraint_LoginName)) {
						addList.add(new BasicDBObject("loginName",
								new BasicDBObject("$eq", tcons.getValue().toString().replace("%", ""))));
					}
					if (tcons.getName().equals(LoginLog2.Constraint_LoginResult)) {
						addList.add(new BasicDBObject("loginResult",
								new BasicDBObject("$eq", Integer.parseInt(tcons.getValue().toString()))));
					}
					if (tcons.getName().equals(LoginLog2.Constraint_LoginIp)) {
						addList.add(new BasicDBObject("loginIp", new BasicDBObject("$eq", tcons.getValue())));
					}
					if (tcons.getName().equals(LoginLog2.Constraint_LoginTime_Begin)) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						addList.add(new BasicDBObject("loginTime",
								new BasicDBObject("$gte", sdf.parse(tcons.getValue().toString()))));
					}
					if (tcons.getName().equals(LoginLog2.Constraint_LoginTime_End)) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						addList.add(new BasicDBObject("loginTime",
								new BasicDBObject("$lte", sdf.parse(tcons.getValue().toString()))));
					}
				} else if (!(cons instanceof RelationConstraint) && cons instanceof OrConstraint) {
					List<Constraint> orCons = ((OrConstraint) cons).getConstraints();
					for (int i = 0; i < orCons.size(); ++i) {
						if (orCons.get(i) instanceof TypeConstraint) {
							TypeConstraint tcons = (TypeConstraint) orCons.get(i);
							if (tcons.getName().equals(LoginLog2.Constraint_LoginName)) {
								Pattern pattern = Pattern.compile("^.*" + tcons.getValue() + ".*$",
										Pattern.CASE_INSENSITIVE);
								BasicDBObject obj = new BasicDBObject();
								obj.put("loginName", pattern);
								addList.add(obj);
							}
						}
					}
				}
			}
		}
		if (!addList.isEmpty() && addList.size() != 0) {
			dbObject.put("$and", addList);
		}
	}

	private void generOrder(BasicDBObject orderObj, Order[] orders) {
		if (orders != null && orders.length > 0) {
			for (Order order : orders) {
				orderObj.put(order.getFieldName(), "desc".equals(order.getOrder()) ? -1 : 1);
			}
		}
	}

	public void closeMongo(IMongoDBManager manager, String msg) {
		try {
			manager.close();
		} catch (Exception ex) {
			_log.error(msg, ex);
		}
	}

	/**
	 * mongo认证方式
	 * 
	 * @param beginTime
	 * @param endTime
	 * @param loginName
	 * @return
	 */
	public JSONArray buildLoginModePieChart(String beginTime, String endTime, String loginName) {
		// TODO Auto-generated method stub
		IMongoDBManager manager = null;
		JSONArray all = new JSONArray();
		try {
			manager = MongoDBMgrFactory.getInstance().getManager();
			DB db = manager.getDB();
			BasicDBObject matchObj = new BasicDBObject();
			List<Constraint> constraints = new ArrayList<Constraint>();
			if (!StringUtil.isEmpty(beginTime)) {
				constraints.add(IdsQueryManager.getLessThanConstraint(LoginLog2.Constraint_LoginTime_Begin, beginTime));
			}
			if (!StringUtil.isEmpty(endTime)) {
				constraints.add(IdsQueryManager.getGreaterThanConstraint(LoginLog2.Constraint_LoginTime_End, endTime));
			}
			if (!StringUtil.isEmpty(loginName)) {
				constraints.add(IdsQueryManager.getEqualConstraint(LoginLog2.Constraint_LoginName, loginName));
			}

			generLoginLogWhere(matchObj, constraints);
			String table = MongoMapperUtil.getCollectionName(LoginLog.class);
			DBCollection collection = db.getCollection(table);
			all = selectLoginModePieChart(collection, matchObj, "$loginMode");
		} catch (Exception ex) {
			_log.error("查询MONGO数据时异常：" + ex);
		} finally {
			closeMongo(manager, "can not close IMongoDBManager!");
		}
		return all;
	}

	private JSONArray selectLoginModePieChart(DBCollection collection, BasicDBObject matchObj, String columnName) {
		// TODO Auto-generated method stub
		JSONArray all = new JSONArray();
		try {
			BasicDBObject groupObj = new BasicDBObject();
			groupObj.put("_id", columnName);
			groupObj.put("count", new BasicDBObject("$sum", 1));
			Map<String, Object> projectMap = new HashMap<String, Object>();
			projectMap.put("groupName", "$_id");
			projectMap.put("num", "$count");
			projectMap.put("_id", 0);
			BasicDBObject projectObj = new BasicDBObject(projectMap);

			BasicDBObject match = new BasicDBObject("$match", matchObj);
			BasicDBObject group = new BasicDBObject("$group", groupObj);
			BasicDBObject project = new BasicDBObject("$project", projectObj);
			BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("_id", 1));

			List<BasicDBObject> aggregateList = new ArrayList<BasicDBObject>();
			aggregateList.add(match);
			aggregateList.add(group);
			aggregateList.add(project);
			aggregateList.add(sort);
			AggregationOutput output = collection.aggregate(aggregateList);

			Iterator<DBObject> ite = output.results().iterator();
			while (ite.hasNext()) {
				DBObject dbObj = ite.next();
				JSONObject dobj = JSONObject.fromObject(dbObj);
				String groupName = dobj.getString("groupName");
				String num = dobj.getString("num");
				String name = LoginMode.loadModeName(Integer.parseInt(groupName));
				JSONObject obj = new JSONObject();
				obj.put("name", name);
				obj.put("value", Integer.parseInt(num));
				all.add(obj);
			}
		} catch (Exception ex) {
			_log.error("selectGroupBy error" + ex);
		}
		return all;
	}

	/**
	 * 首页 应用认证访问量
	 * 
	 * @param beginTime
	 * @param endTime
	 * @param url
	 * @param loginName
	 * @return
	 */
	public int getVisitLogDatas(String beginTime, String endTime, String url, String loginName) {
		// TODO Auto-generated method stub
		try {
			BasicDBObject json = new BasicDBObject();
			List<Constraint> constraints = new ArrayList<Constraint>();
			if (!StringUtil.isEmpty(beginTime)) {
				constraints.add(IdsQueryManager.getGreaterThanConstraint(VisitLog2.Constraint_VL_BeginTime, beginTime));
			}
			if (!StringUtil.isEmpty(endTime)) {
				constraints.add(IdsQueryManager.getLessThanConstraint(VisitLog2.Constraint_VL_EndTime, endTime));
			}
			if (!StringUtil.isEmpty(loginName)) {
				constraints.add(IdsQueryManager.getEqualConstraint(VisitLog2.Constraint_VL_LoginName, loginName));
			}
			if (!StringUtil.isEmpty(url)) {
				constraints.add(IdsQueryManager.getEqualConstraint(VisitLog2.Constraint_VL_Region, url));
			}
			generVisitLogWhere(json, constraints);
			long count = new MongoManager().findDataCount(VisitLog.class, json);
			return (int) count;
		} catch (Exception ex) {
			_log.error("从Mongo查询浏览日志总数时异常！", ex);
		}
		return 0;
	}

	private void generVisitLogWhere(BasicDBObject fiterJson, List<Constraint> constraints) throws ParseException {
		BasicDBObject addObj = new BasicDBObject();
		BasicDBList addList = new BasicDBList();
		BasicDBList orList = new BasicDBList();
		if (constraints != null && !constraints.isEmpty()) {
			for (Constraint cons : constraints) {
				if (cons instanceof TypeConstraint) {
					TypeConstraint tcons = (TypeConstraint) cons;
					if (tcons.getName().equals(VisitLog2.Constraint_VL_LoginName)) {
						addList.add(new BasicDBObject("loginName", new BasicDBObject("$eq", tcons.getValue())));
					}
					if (tcons.getName().equals(VisitLog2.Constraint_VL_Region)) {
						String url = tcons.getValue().toString();
						if ("/cas/login".equals(url)) {
							addList.add(new BasicDBObject("url", new BasicDBObject("$eq", url)));
						} else {
							Pattern pattern = Pattern.compile("^.*" + url + ".*$", Pattern.CASE_INSENSITIVE);
							BasicDBObject obj = new BasicDBObject();
							obj.put("url", pattern);
							addList.add(obj);
						}
					}
					if (tcons.getName().equals(VisitLog2.Constraint_VL_BeginTime)) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						addList.add(new BasicDBObject("visitTime",
								new BasicDBObject("$gte", sdf.parse(tcons.getValue().toString()))));
					}
					if (tcons.getName().equals(VisitLog2.Constraint_VL_EndTime)) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						addList.add(new BasicDBObject("visitTime",
								new BasicDBObject("$lte", sdf.parse(tcons.getValue().toString()))));
					}
				} else if (!(cons instanceof RelationConstraint) && cons instanceof OrConstraint) {
					List<Constraint> orCons = ((OrConstraint) cons).getConstraints();
					for (int i = 0; i < orCons.size(); ++i) {
						if (orCons.get(i) instanceof TypeConstraint) {
							TypeConstraint tcons = (TypeConstraint) orCons.get(i);
							if (tcons.getName().equals(VisitLog2.Constraint_VL_ClienIp)) {
								Pattern pattern = Pattern.compile("^.*" + tcons.getValue() + ".*$",
										Pattern.CASE_INSENSITIVE);
								BasicDBObject obj = new BasicDBObject();
								obj.put("clienIp", pattern);
								orList.add(obj);
								// orList.add(new BasicDBObject("clienIp", new BasicDBObject("$eq",
								// tcons.getValue())));
							}
							if (tcons.getName().equals(VisitLog2.Constraint_VL_DomainName)) {
								orList.add(new BasicDBObject("domainName", new BasicDBObject("$eq", tcons.getValue())));
							}
							if (tcons.getName().equals(VisitLog2.Constraint_VL_Region)) {
								orList.add(new BasicDBObject("region", new BasicDBObject("$eq", tcons.getValue())));
							}

							if (tcons.getName().equals(VisitLog2.Constraint_VL_ServerType)) {
								orList.add(new BasicDBObject("serverType", new BasicDBObject("$eq", tcons.getValue())));
							}
							if (tcons.getName().equals(VisitLog2.Constraint_VL_LoginName)) {
								Pattern pattern = Pattern.compile("^.*" + tcons.getValue() + ".*$",
										Pattern.CASE_INSENSITIVE);
								BasicDBObject obj = new BasicDBObject();
								obj.put("loginName", pattern);
								orList.add(obj);
							}
						}
					}
				}
			}
		}

		if (!addList.isEmpty() && addList.size() != 0) {
			addObj.put("$and", addList);
		}
		if (!orList.isEmpty() && orList.size() != 0) {
			if (addObj != null && !addObj.isEmpty()) {
				orList.add(addObj);
			}
			fiterJson.put("$or", orList);
		} else {
			if (addObj != null && !addObj.isEmpty()) {
				fiterJson.put("$and", addList);
			}
		}
	}

	/**
	 * 大屏展示 累积认证数量
	 */
	public JSONObject accumulativeCertificateLS() {
		JSONObject obj = new JSONObject();
		IMongoDBManager manager = null;
		try {
			manager = MongoDBMgrFactory.getInstance().getManager();
			DB db = manager.getDB();
			String table = MongoMapperUtil.getCollectionName(LoginLog.class);
			DBCollection collection = db.getCollection(table);
			BasicDBObject groupObj = new BasicDBObject();
			BasicDBObject seccessSumObj = caseWhenConstraint("$loginResult", 1);
			groupObj.put("success", seccessSumObj);

			BasicDBObject failSumObj = caseWhenConstraint("$loginResult", 0);
			groupObj.put("fail", failSumObj);

			groupObj.put("_id", 0);
			groupObj.put("total", new BasicDBObject("$sum", 1));
			BasicDBObject group = new BasicDBObject("$group", groupObj);

			List<BasicDBObject> aggregateList = new ArrayList<BasicDBObject>();
			aggregateList.add(group);
			AggregationOutput output = collection.aggregate(aggregateList);

			Iterator<DBObject> ite = output.results().iterator();
			while (ite.hasNext()) {
				DBObject dbObj = ite.next();
				JSONObject dobj = JSONObject.fromObject(dbObj);
				String sum = dobj.get("total").toString();
				String success = dobj.get("success").toString();
				String fail = dobj.get("fail").toString();
				obj.put("sum", Integer.parseInt(sum));
				obj.put("success", Integer.parseInt(success));
			}
		} catch (Exception ex) {
			_log.error("查询MONGO数据时异常：" + ex);
		} finally {
			closeMongo(manager, "can not close IMongoDBManager!");
		}
		return obj;
	}

	/**
	 * mongo的 case when 语句约束
	 * @param column
	 * @param value
	 * @return
	 */
	private BasicDBObject caseWhenConstraint(String column, int value) {
		JSONArray arr = new JSONArray();
		arr.add(column);
		arr.add(value);
		BasicDBObject eqObj = new BasicDBObject("$eq", arr);
		JSONArray condArr = new JSONArray();
		condArr.add(eqObj);
		condArr.add(1);
		condArr.add(0);
		BasicDBObject condObj = new BasicDBObject("$cond", condArr);
		BasicDBObject sumObj = new BasicDBObject("$sum", condObj);
		return sumObj;
	}

	/**
	 * 大屏展示 认证成功率
	 * 
	 * @param beginTime
	 * @param endTime
	 * @param rowBeginIndex
	 * @param rows
	 * @return
	 */
	public JSONArray loadCertificateSuccessRateLS(String beginTime, String endTime, int rowBeginIndex, int rows) {
		// TODO Auto-generated method stub

		IMongoDBManager manager = null;
		JSONArray all = new JSONArray();
		ISSO sso = null;
        try {
        	sso = SSOFactory.getInstance().getSSO();
			manager = MongoDBMgrFactory.getInstance().getManager();
			DB db = manager.getDB();
			BasicDBObject matchObj = new BasicDBObject();
			List<Constraint> constraints = new ArrayList<Constraint>();
			if (!StringUtil.isEmpty(beginTime)) {
				beginTime += " 00:00:00";
				constraints.add(IdsQueryManager.getLessThanConstraint(LoginLog2.Constraint_LoginTime_Begin, beginTime));
			}
			if (!StringUtil.isEmpty(endTime)) {
				endTime += " 23:59:59";
				constraints.add(IdsQueryManager.getGreaterThanConstraint(LoginLog2.Constraint_LoginTime_End, endTime));
			}
			generLoginLogWhere(matchObj, constraints);
			String table = MongoMapperUtil.getCollectionName(LoginLog.class);
			DBCollection collection = db.getCollection(table);
			
			BasicDBObject groupObj = new BasicDBObject();
			BasicDBObject seccessSumObj = caseWhenConstraint("$loginResult", 1);
			groupObj.put("success", seccessSumObj);
			BasicDBObject failSumObj = caseWhenConstraint("$loginResult", 0);
			groupObj.put("fail", failSumObj);
			groupObj.put("_id", "$loginName");
			groupObj.put("total", new BasicDBObject("$sum", 1));

			Map<String, Object> projectMap = new HashMap<String, Object>();
			projectMap.put("loginName", "$_id");
			projectMap.put("success", "$success");
			projectMap.put("fail", "$fail");
			projectMap.put("total", "$total");
			
			BasicDBObject divideObj = new BasicDBObject();
			JSONArray arr = new JSONArray();
			arr.add("$success");
			arr.add("$total");
			divideObj.put("$divide", arr);
			projectMap.put("successRate", divideObj);
			projectMap.put("_id", 0);
			BasicDBObject projectObj = new BasicDBObject(projectMap);

			BasicDBObject match = new BasicDBObject("$match", matchObj);
			BasicDBObject group = new BasicDBObject("$group", groupObj);
			BasicDBObject project = new BasicDBObject("$project", projectObj);
			BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("successRate", -1));

			List<BasicDBObject> aggregateList = new ArrayList<BasicDBObject>();
			aggregateList.add(match);
			aggregateList.add(group);
			aggregateList.add(project);
			aggregateList.add(sort);
			BasicDBObject skip = new BasicDBObject("$skip", rowBeginIndex);
			BasicDBObject limit = new BasicDBObject("$limit", rows);
			aggregateList.add(skip);
			aggregateList.add(limit);
			AggregationOutput output = collection.aggregate(aggregateList);

			Iterator<DBObject> ite = output.results().iterator();
			while (ite.hasNext()) {
				DBObject dbObj = ite.next();
				JSONObject dobj = JSONObject.fromObject(dbObj);
				String loginName = dobj.getString("loginName");
				User2 user = sso.findUserByLoginName(loginName);
				String successRate = dobj.getString("successRate");
				String sum = dobj.getString("total");
				JSONObject obj = new JSONObject();
                obj.put("name", user==null ? "账号不存在" : user.getName());
                obj.put("sum", Integer.parseInt(sum));
                obj.put("successRate", parseSuccessRate(successRate));
                all.add(obj);
			}
		} catch (Exception ex) {
			_log.error("查询MONGO数据时异常：" + ex);
		} finally {
			closeMongo(manager, "can not close IMongoDBManager!");
			closeManager(sso, "can not close ISSO!");
		}
		return all;
	}

	private static String parseSuccessRate(String successRate) {
		// TODO Auto-generated method stub
		Double rd = Double.parseDouble(successRate);
		int ri = (int) (Math.round(rd * 100));
		return ri+"%";
	}
	
	private static void closeManager(IManager manager, String msg) {
        try {
            manager.close();
        } catch (Exception ex) {
            _log.error(msg, ex);
        }
    }

	public int getAccountStatisticDatas(String beginTime, String endTime, List<Constraint> cons, int type) {
		// TODO Auto-generated method stub
		IMongoDBManager manager = null;
		try {
			manager = MongoDBMgrFactory.getInstance().getManager();
			BasicDBObject matchObj = new BasicDBObject();
			if (!StringUtil.isEmpty(beginTime)) {
				cons.add(AuditLog2.buildOperTimeConstraint(DateTime.parseDateTime(beginTime).toDate(),
						Constraint.Compare_GreaterThanOrEqual));
			}
			if (!StringUtil.isEmpty(endTime)) {
				cons.add(AuditLog2.buildOperTimeConstraint(DateTime.parseDateTime(endTime).toDate(),
						Constraint.Compare_LessThanOrEqual));
			}
			
			generAuditLogWhere(matchObj, cons);
			long count = new MongoManager().findDataCount(AuditLog.class, matchObj);
			return (int) count;
		} catch (Exception ex) {
			_log.error("查询MONGO数据时异常：" + ex);
		} finally {
			closeMongo(manager, "can not close IMongoDBManager!");
		}
		return 0;
	}

	/**
	 * 大屏展示 认证数据
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public JSONObject loadCertificateDatasLS(String beginTime, String endTime) {
		// TODO Auto-generated method stub
		IMongoDBManager manager = null;
		JSONObject obj = new JSONObject();
		ISSO sso = null;
        try {
        	sso = SSOFactory.getInstance().getSSO();
			manager = MongoDBMgrFactory.getInstance().getManager();
			DB db = manager.getDB();
			BasicDBObject matchObj = new BasicDBObject();
			List<Constraint> constraints = new ArrayList<Constraint>();
			if (!StringUtil.isEmpty(beginTime)) {
				beginTime += " 00:00:00";
				constraints.add(IdsQueryManager.getLessThanConstraint(LoginLog2.Constraint_LoginTime_Begin, beginTime));
			}
			if (!StringUtil.isEmpty(endTime)) {
				endTime += " 23:59:59";
				constraints.add(IdsQueryManager.getGreaterThanConstraint(LoginLog2.Constraint_LoginTime_End, endTime));
			}
			generLoginLogWhere(matchObj, constraints);
			String table = MongoMapperUtil.getCollectionName(LoginLog.class);
			DBCollection collection = db.getCollection(table);
			
			BasicDBObject groupObj = new BasicDBObject();
			BasicDBObject seccessSumObj = caseWhenConstraint("$loginResult", 1);
			groupObj.put("success", seccessSumObj);
			BasicDBObject failSumObj = caseWhenConstraint("$loginResult", 0);
			groupObj.put("fail", failSumObj);
			groupObj.put("_id", "$_class");
			groupObj.put("total", new BasicDBObject("$sum", 1));

			Map<String, Object> projectMap = new HashMap<String, Object>();
			projectMap.put("success", "$success");
			projectMap.put("fail", "$fail");
			projectMap.put("total", "$total");
			projectMap.put("_class", "$_id");
			
			BasicDBObject divideObj = new BasicDBObject();
			JSONArray arr = new JSONArray();
			arr.add("$success");
			arr.add("$total");
			divideObj.put("$divide", arr);
			projectMap.put("successRate", divideObj);
			projectMap.put("_id", 0);
			BasicDBObject projectObj = new BasicDBObject(projectMap);

			BasicDBObject match = new BasicDBObject("$match", matchObj);
			BasicDBObject group = new BasicDBObject("$group", groupObj);
			BasicDBObject project = new BasicDBObject("$project", projectObj);

			List<BasicDBObject> aggregateList = new ArrayList<BasicDBObject>();
			aggregateList.add(match);
			aggregateList.add(group);
			aggregateList.add(project);
			AggregationOutput output = collection.aggregate(aggregateList);
			String successRate = "0";
			String avg = "0";
			String sum = "0";
			JSONArray authFastigium = new JSONArray();
			Iterator<DBObject> ite = output.results().iterator();
			while (ite.hasNext()) {
				DBObject dbObj = ite.next();
				JSONObject dobj = JSONObject.fromObject(dbObj);
				successRate = dobj.getString("successRate");
				sum = dobj.getString("total");
                int userCount = loadCertificateUserCount(collection,matchObj);
                avg = String.valueOf(Integer.parseInt(sum) / (float)userCount);
                authFastigium = loadCertificateFastigium(collection,matchObj);
			}
			obj.put("avg", avg.indexOf(".")>-1 ? avg.substring(0, avg.indexOf(".")+3) : avg);
			obj.put("sum", sum);
            obj.put("successRate", parseSuccessRate(successRate));
			obj.put("authFastigium", authFastigium);
			
		} catch (Exception ex) {
			_log.error("查询MONGO数据时异常：" + ex);
		} finally {
			closeMongo(manager, "can not close IMongoDBManager!");
			closeManager(sso, "can not close ISSO!");
		}
		return obj;
	}

	/**
	 * 认证高峰期 按小时分组
	 * @param collection
	 * @param matchObj
	 * @return
	 */
	private JSONArray loadCertificateFastigium(DBCollection collection,BasicDBObject matchObj) {
		// TODO Auto-generated method stub
		JSONArray all = new JSONArray();
		try {
			BasicDBObject groupObj = new BasicDBObject();
			BasicDBObject dataAddObj = new BasicDBObject();
			JSONArray arr = new JSONArray();
			arr.add("$loginTime");
			arr.add(28800000);// mongo少八小时
			dataAddObj.put("$add", arr);
			BasicDBObject dateObj = new BasicDBObject("date", dataAddObj);
			dateObj.put("format", "%HH");
			groupObj.put("_id", new BasicDBObject("$dateToString", dateObj));
			groupObj.put("count", new BasicDBObject("$sum", 1));

			Map<String, Object> projectMap = new HashMap<String, Object>();
			projectMap.put("dformat", "$_id");
			projectMap.put("count", "$count");
			projectMap.put("_id", 0);
			BasicDBObject projectObj = new BasicDBObject(projectMap);

			BasicDBObject match = new BasicDBObject("$match", matchObj);
			BasicDBObject group = new BasicDBObject("$group", groupObj);
			BasicDBObject project = new BasicDBObject("$project", projectObj);
			BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("count", -1));

			List<BasicDBObject> aggregateList = new ArrayList<BasicDBObject>();
			aggregateList.add(match);
			aggregateList.add(group);
			aggregateList.add(project);
			aggregateList.add(sort);
			AggregationOutput output = collection.aggregate(aggregateList);

			Iterator<DBObject> ite = output.results().iterator();
			int num = 0 ;
			while (ite.hasNext()) {
				if((num++) == 3)break;
				DBObject dbObj = ite.next();
				JSONObject dobj = JSONObject.fromObject(dbObj);
				String dformat = dobj.getString("dformat");
				String count = dobj.getString("count");
				dformat = dformat.startsWith("0") ? dformat.substring(1,2) : dformat.substring(0,2);
                String text = dformat+"点~"+(Integer.parseInt(dformat)+1)+"点";
                all.add(text);
			}
		} catch (Exception ex) {
			_log.error("loadCertificateFastigium error" + ex);
		}
		return all;
	}

	/**
	 * 认证人员数量
	 * @param collection
	 * @param matchObj
	 * @return
	 */
	private int loadCertificateUserCount(DBCollection collection,BasicDBObject matchObj) {
		// TODO Auto-generated method stub
		int num = 0;
        try {
			BasicDBObject groupObj = new BasicDBObject();
			groupObj.put("_id", "$loginName");
			groupObj.put("total", new BasicDBObject("$sum", 1));
			BasicDBObject match = new BasicDBObject("$match", matchObj);
			BasicDBObject group = new BasicDBObject("$group", groupObj);

			List<BasicDBObject> aggregateList = new ArrayList<BasicDBObject>();
			aggregateList.add(match);
			aggregateList.add(group);
			AggregationOutput output = collection.aggregate(aggregateList);

			Iterator<DBObject> ite = output.results().iterator();
			while (ite.hasNext()) {
				DBObject dbObj = ite.next();
				num++;
			}
		} catch (Exception ex) {
			_log.error("loadCertificateUserCount error：" + ex);
		} 
		return num;
	}
}
