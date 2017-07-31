package com.thinkgem.jeesite.modules.partyManage.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmRecordSDao;
import com.thinkgem.jeesite.modules.partyManage.entity.ProcessData;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmRecordS;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

public class PmUtils {

	private static SPmRecordSDao dao = SpringContextHolder
			.getBean(SPmRecordSDao.class);
	private static String ZBSJ_ROLE_ID = "fefa8e689c7c4d49a704e55aa3acba7d";// 支部书记角色id
	private static String ZZSJ_ROLE_ID = "16736e679241401aab66ea1264112eeb";// 总支书记角色id
	private static String DWSJ_ROLE_ID = "593d3b95660d43149fd788cd22f8847d";// 党委书记角色id
	private static String DW2SJ_ROLE_ID = "55ce1956fd434d6cb3f6258a6d95dd69";// 2级党委书记角色id
	public static String getZBSJ_ROLE_ID() {
		return ZBSJ_ROLE_ID;
	}

	public static String getZZSJ_ROLE_ID() {
		return ZZSJ_ROLE_ID;
	}

	public static String getDWSJ_ROLE_ID() {
		return DWSJ_ROLE_ID;
	}

	public static String getDW2SJ_ROLE_ID() {
		return DW2SJ_ROLE_ID;
	}

	private static String DWSJ_OFFICE_ID = "404002";// 党委书记机构id

	private static String ZZ_OFFICE_TYPE = "党总支部";// 机构类型
	private static String DW_OFFICE_TYPE = "企业党委";// 机构类型
	private static String DW2_OFFICE_TYPE = "党委";// 2级党委机构类型

	// 获取代办流程信息
	public static ProcessData getProcessNextOrBack(ProcessData p, User u,
			int act) {
		String node = p.getNode();// 主流程id
		// User createUser =;// 子流程创建人
		String officeId = UserUtils.get(p.getCreateby()).getOffice().getId();// 创建人组织节点
		String nodeType = getRecordNodeType(node);// 获取流程处理方式
		ArrayList<HashMap<String, Object>> pList = getProcessList(nodeType,
				officeId);
		handleProcessInfo(pList, p, act);
		return p;
	}

	// 处理代办信息
	private static void handleProcessInfo(
			ArrayList<HashMap<String, Object>> pList, ProcessData p, int act) {
		try {
			HashMap<String, Object> hashMap = null;
			if (act > 0) {// 审核通过
				hashMap = pList.get(p.getChildNum() + 1);
				String stat = hashMap.get("stat").toString();
				if(!"-1".equals(stat)){
					p.setOfficID(hashMap.get("officeId").toString());
					p.setRoleID(hashMap.get("roleId").toString());
				}
				p.setChildNum(Integer.valueOf(stat));
			} else if (act < 0) {// 审核驳回
				// hashMap = pList.get(p.getChildNum() - 1);
				hashMap = pList.get(0);
				p.setChildNum(Integer.valueOf((hashMap.get("stat").toString())));
			}
		} catch (IndexOutOfBoundsException e) {
			HashMap<String, Object> hashMap = null;
			if (act > 0) {// 审核通过
				hashMap = pList.get(pList.size() - 1);
			} else if (act < 0) {// 审核驳回
				hashMap = pList.get(0);
			}
			p.setChildNum(Integer.valueOf((hashMap.get("stat").toString())));
		}
	}

	// 获取所有环节节点
	private static ArrayList<HashMap<String, Object>> getProcessList(
			String nodeType, String officeId) {
		ArrayList<HashMap<String, Object>> plist = Lists.newArrayList();

		HashMap<String, Object> s = Maps.newHashMap();
		s.put("stat", 0);
		plist.add(s);
		if ("1".equals(nodeType)) {// 申请人到支部 1-2
			HashMap<String, Object> m1 = Maps.newHashMap();
			m1.put("stat", 1);
			m1.put("officeId", officeId);
			m1.put("roleId", ZBSJ_ROLE_ID);// 支部书记
			plist.add(m1);
		} else if ("2".equals(nodeType)) {// 支部书记到企业党委 1-3
			HashMap<String, Object> m1 = Maps.newHashMap();
			m1.put("stat", 1);
			m1.put("officeId", DWSJ_OFFICE_ID);
			m1.put("roleId", DWSJ_ROLE_ID);// 企业党委书记
			plist.add(m1);
		} else if ("3".equals(nodeType)) {// 支部书记到企业党委 1-2-3
			List<HashMap<String, Object>> recursionList = dao
					.getRecursionList(officeId);
			int num=0;
			for (int i = 0; i < recursionList.size(); i++) {
				HashMap<String, Object> r = recursionList.get(i);
				if (officeId.equals(r.get("officeId"))) {// 和发起节点一致跳过
					continue;
				}
				if (ZZ_OFFICE_TYPE.equals(r.get("officeType"))
						|| DW_OFFICE_TYPE.equals(r.get("officeType"))) {// 插入中间环节
					num=num+1;
					HashMap<String, Object> m = Maps.newHashMap();
					m.put("stat", num);
					m.put("officeId", r.get("officeId"));
					if (ZZ_OFFICE_TYPE.equals(r.get("officeType"))) {
						m.put("roleId", ZZSJ_ROLE_ID);// 总支书记
					} else if (DW_OFFICE_TYPE.equals(r.get("officeType"))) {
						m.put("roleId", DWSJ_ROLE_ID);// 企业党委书记
					}
					plist.add(m);
				}
			}
		} else if ("4".equals(nodeType)) {// 支部书记到企业党委包含2级
			List<HashMap<String, Object>> recursionList = dao
					.getRecursionList(officeId);
			int num=0;
			for (int i = 0; i < recursionList.size(); i++) {
				HashMap<String, Object> r = recursionList.get(i);
				if (officeId.equals(r.get("officeId"))) {// 和发起节点一致跳过
					continue;
				}
				if (DW2_OFFICE_TYPE.equals(r.get("officeType"))
						|| DW_OFFICE_TYPE.equals(r.get("officeType"))) {// 插入中间环节
					num=num+1;
					HashMap<String, Object> m = Maps.newHashMap();
					m.put("stat",num);
					m.put("officeId", r.get("officeId"));
					if (DW2_OFFICE_TYPE.equals(r.get("officeType"))) {
						m.put("roleId", DW2SJ_ROLE_ID);// 2级党委书记
					} else if (DW_OFFICE_TYPE.equals(r.get("officeType"))) {
						m.put("roleId", DWSJ_ROLE_ID);// 企业党委书记
					}
					plist.add(m);
				}
			}
		}
		HashMap<String, Object> e = Maps.newHashMap();
		e.put("stat", -1);
		plist.add(e);
		return plist;
	}

	// 获取流程处理方式
	private static String getRecordNodeType(String node) {
		SPmRecordS sPmRecordS = new SPmRecordS();
		sPmRecordS.setNodeNo(node);
		sPmRecordS = dao.get(sPmRecordS);
		return sPmRecordS.getNodeType();

	}
}
