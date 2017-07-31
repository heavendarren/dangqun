package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.ProcessDataDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmCheckLogDao;
import com.thinkgem.jeesite.modules.partyManage.entity.DQRecord;
import com.thinkgem.jeesite.modules.partyManage.entity.ProcessData;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmCheckLog;
import com.thinkgem.jeesite.modules.partyManage.utils.PmUtils;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 发展纪实服务类
 * @author xigntianpeng
 * 4-11
 */
@Service
@Transactional(readOnly = true)
public class DQRecordService extends CrudService<DQRecordDao, DQRecord> {

	// 申请人角色ID
	private String SQR_ROLE_ID = "f4c87b9b7eb74baa838d07475e253ce5";
	@Autowired
	private ProcessDataDao pddao;
	@Autowired
	private SPmCheckLogDao cldao;

	public Page<DQRecord> findPage(Page<DQRecord> page, DQRecord dqrecord, String stage, User user) {
		dqrecord.setPage(page);
		List<DQRecord> datalist = new ArrayList<DQRecord>();
		List<DQRecord> list = new ArrayList<DQRecord>();
		List<Role> lr = user.getRoleList();
		Office office = user.getOffice();
		Role role = getRole(lr);
		list = dao.findByStage(stage, user.getId(), office.getId());
		for(DQRecord r : list){
			DQRecord record = new DQRecord();
			record.setId(r.getId());
			record.setName(r.getName());
			record.setOrgName(r.getOrgName());
			if(r.getApplicationTime() != null && stage.equals("0")){
				record.setApplicationTime(new java.sql.Date(r.getApplicationTime().getTime()));
			}
			if(r.getActivistTime() != null && stage.equals("1")){
				record.setApplicationTime(new java.sql.Date(r.getActivistTime().getTime()));
			}
			if(r.getObjectTime() != null && stage.equals("2")){
				record.setApplicationTime(new java.sql.Date(r.getObjectTime().getTime()));
			}
			if(r.getReserveTime() != null && stage.equals("3")){
				record.setApplicationTime(new java.sql.Date(r.getReserveTime().getTime()));
			}
			if(r.getFormalTime() != null && stage.equals("4")){
				record.setApplicationTime(new java.sql.Date(r.getFormalTime().getTime()));
			}
			if(role != null){
				ProcessData pd = pddao.findByAppID(r.getId(), "now");
				if(pd != null){
					record.setState(nowState(pd, user));
				}else{
					record.setState("3");
				}
			}
			record.setMakeupflag(r.getMakeupflag());
			record.setEndflag(r.getEndflag());
			datalist.add(record);
		}
		page.setCount(datalist.size());
		page.setList(datalist);
		return page;
	}

	/**
	 * 是否当前操作权限
	 * @return
	 */
	public String nowState(ProcessData pd, User user){
		List<Role> lr = user.getRoleList();
		Role role = getRole(lr);
		if(pd != null && role != null){
			if(pd.getRoleID().equals(role.getId()) && pd.getOfficID().equals(user.getOffice().getId())){
				if(SQR_ROLE_ID.equals(role.getId())){
					if(user.getId().equals(pd.getCreateby())){
						return "0";
					}else{
						return "1";
					}
				}else{
					return "0";
				}
			}else{
				return "1";
			}
		}else{
			return "3";
		}
	}

	/**
	 * 查询同组织人员
	 * @param name
	 * @param orgID
	 * @return
	 */
	public List<DQRecord> findByName(String name, String orgID){
		return dao.findByName(name, orgID);
	}

	/**
	 * id查询  角色  组织
	 * @param ID
	 * @return
	 */
	public DQRecord findByID(String ID){
		return dao.findByID(ID);
	}

	/**
	 * 发展纪实，增添
	 */
	public void saveRecord(User user){
		List<Role> rl = user.getRoleList();
		if(rl.size() != 0){
			Role role = getRole(rl);
			if(role != null && SQR_ROLE_ID.equals(role.getId())){
				DQRecord record = new DQRecord();
				record.setId(IdGen.uuid());
				record.setName(user.getName());
				record.setReviewerID(role.getId());
				Office office = user.getOffice();
				if(office != null){
					record.setOrgID(office.getId());
					record.setOrgName(office.getName());
				}
				record.setUserID(user.getId());
				record.setCreateby(user.getId());
				record.setCreateDate(new Date());
				record.setStage("0");
				record.setDelflag("0");
				saveProcess(record,"1", office.getId(), role.getId()); // 初始  第一阶段第一节点
				dao.saveRecord(record);
			}
		}
	}

	public String getSQR_ROLE_ID() {
		return SQR_ROLE_ID;
	}


	/**
	 * 流程进度 增添
	 * @param appid
	 * @param stage
	 * @param node
	 * @return
	 */
	public void saveProcess(DQRecord record, String node, String OrgID, String rId){
		ProcessData pd = new ProcessData();
		pd.setId(IdGen.uuid());
		pd.setStage("0"); // 入党申请人
		pd.setNode(node); // 第一节点
		pd.setStatus("now");
		pd.setCreateby(record.getUserID());
		pd.setCreateDate(record.getCreateDate());
		pd.setAppID(record.getId());
		pd.setDelflag("0");
		pd.setFillflag("0");
		pd.setSubmitflag("0");
		pd.setCheckflag("0");
		pd.setChildNum(0);
		pd.setMakeupflag("0");
		pd.setRoleID(rId);
		pd.setOfficID(OrgID);
		pddao.insert(pd);
	}

	public ProcessData findByAppID(String appID){
		return pddao.findByAppID(appID, "now");
	}

	public String findByUserID(String userID, String node){
		return dao.findByUserID(userID, node);
	}

	public List<ProcessData> findlist(String appID){
		return pddao.findlist(appID);
	}

	/**
	 * 审核
	 * @param recordid
	 * @param pdid
	 * @param checkresult
	 * @param user
	 */
	public void check(String recordid, String pdid, String checkresult, User user){
		DQRecord record = dao.findByRID(recordid);
		ProcessData pd = pddao.findByID(pdid);
		if(record != null){
			if("26".equals(pd.getNode())){
				record.setFormalTime(new Date());
				record.setEndflag("1");
			}
			if("0".equals(record.getStage())){
				// 进入积极分子阶段
				if("6".equals(pd.getNode())){
					record.setStage("1");
					record.setActivistTime(new Date());
					dao.update(record);
				}
			}else if("1".equals(record.getStage())){
				// 进入发展对象阶段
				if("10".equals(pd.getNode())){
					record.setStage("2");
					record.setObjectTime(new Date());
					dao.update(record);
				}
			}else if("2".equals(record.getStage())){
				// 进入预备党员阶段
				if("16".equals(pd.getNode())){
					record.setStage("3");
					record.setObjectTime(new Date());
					dao.update(record);
				}
			}else if("3".equals(record.getStage())){
				// 进入预备党员阶段
				if("22".equals(pd.getNode())){
					record.setStage("4");
					record.setObjectTime(new Date());
					dao.update(record);
				}
			}
			List<SPmCheckLog> cllist = cldao.findByIdNode(recordid, pdid);
			if(cllist != null){
				for(SPmCheckLog cl : cllist){
					cl.setDelFlag("1");
					cldao.update(cl);
				}
			}
			// 审核记录对象
			SPmCheckLog spcl = new SPmCheckLog();
			spcl.setId(IdGen.uuid());
			spcl.setRecordID(recordid);
			spcl.setRecordName(record.getName());
			spcl.setCheckDate(new Date());
			spcl.setCheckID(user.getId());
			spcl.setCheckName(user.getName());
			spcl.setCheckNodeID(pdid);
			spcl.setCheckResult(checkresult);
			spcl.setDelFlag("0");
			cldao.insert(spcl);
		}
		if(pd != null){
			pd.setStatus("old");
			pd.setFillflag("1");
			pd.setSubmitflag("1");
			// 代办流程信息
			ProcessData prd = PmUtils.getProcessNextOrBack(pd, user, 1);
			pddao.update(pd);
			// 下一节点追加
			addNextNode(record, prd, user);
		}
	}

	/**
	 * 环节节点信息增加
	 */
	public void addNextNode(DQRecord record, ProcessData prd, User user){
		ProcessData pd = new ProcessData();
		if(Integer.valueOf(prd.getNode()) < 26){
			pd.setId(IdGen.uuid());
			pd.setStage(record.getStage()); // 入党申请人
			String node = String.valueOf(Integer.valueOf(prd.getNode())+1);
			pd.setNode(node);
			pd.setStatus("now");
			if(Integer.valueOf(prd.getNode()) == 12
				|| Integer.valueOf(prd.getNode()) == 18
				|| Integer.valueOf(prd.getNode()) == 25){
					pd.setCreateby(user.getId());
			}else if(Integer.valueOf(prd.getNode()) == 21){
				pd.setCreateby(record.getUserID());
			}else{
				String userID = seckUserID(record.getId());
				pd.setCreateby(userID);
			}
			pd.setCreateDate(new Date());
			pd.setAppID(record.getId());
			pd.setDelflag("0");
			pd.setFillflag("0");
			pd.setSubmitflag("0");
			pd.setCheckflag("0");
			pd.setChildNum(0);
			User puser = UserUtils.get(pd.getCreateby());
			if(puser != null){
				Office office = puser.getOffice();
				pd.setOfficID(office.getId());
				Role role = getRole(puser.getRoleList());
				pd.setRoleID(role.getId());
			}
			pd.setMakeupflag("0");
			pddao.insert(pd);
		}
	}



	/**
	 * 未通过 退回
	 * @param recordid
	 * @param pdid
	 * @param checkresult
	 * @param wtgtext
	 * @param user
	 */
	public void weiTG(String recordid, String pdid, String checkresult, String wtgtext, User user){
		DQRecord record = dao.findByID(recordid);
		if(record != null){
			List<SPmCheckLog> cllist = cldao.findByIdNode(recordid, pdid);
			if(cllist != null){
				for(SPmCheckLog cl : cllist){
					cl.setDelFlag("1");
					cldao.update(cl);
				}
			}
			// 审核记录对象
			SPmCheckLog spcl = new SPmCheckLog();
			spcl.setId(IdGen.uuid());
			spcl.setRecordID(recordid);
			spcl.setRecordName(record.getName());
			spcl.setCheckDate(new Date());
			spcl.setCheckID(user.getId());
			spcl.setCheckName(user.getName());
			spcl.setCheckNodeID(pdid);
			spcl.setCheckResult(checkresult);
			spcl.setBackreason(wtgtext);
			spcl.setDelFlag("0");
			cldao.insert(spcl);

			ProcessData pd = pddao.findByID(pdid);
			if(pd != null){
				User uu = UserUtils.get(pd.getCreateby());
				Office office = uu.getOffice();
				List<Role> lr = uu.getRoleList();
				Role role = getRole(lr);
				// 代办流程信息
				ProcessData prd = PmUtils.getProcessNextOrBack(pd, user, -1);
				if("10".equals(pd.getNode()) || "6".equals(pd.getNode())
						 || "15".equals(pd.getNode()) || "14".equals(pd.getNode())
						 || "21".equals(pd.getNode()) ){
					pd.setFillflag("0");
				}
				pd.setSubmitflag("0");
				pd.setChildNum(prd.getChildNum());
				pd.setOfficID(office.getId());
				pd.setRoleID(role.getId());
				pddao.update(pd);
			}
		}
	}

	/**
	 * 提交申请
	 */
	public void submit(String pdID, String recordid, User user){
		ProcessData pd = pddao.findByID(pdID);
		if(pd != null){
			// 代办流程信息
			ProcessData prd = null;
			if("6".equals(pd.getNode())){
				pd.setFillflag("1");
				prd = PmUtils.getProcessNextOrBack(pd, user, 1);
				pd.setChildNum(prd.getChildNum());
				pd.setOfficID(prd.getOfficID());
				pd.setRoleID(prd.getRoleID());
				pddao.update(pd);
			}else{
				pd.setSubmitflag("1");
				prd = PmUtils.getProcessNextOrBack(pd, user, 1);
				pd.setChildNum(prd.getChildNum());
				pd.setOfficID(prd.getOfficID());
				pd.setRoleID(prd.getRoleID());
				pddao.update(pd);
			}

			DQRecord record = dao.findByRID(recordid);
			if(record != null){
				record.setApplicationTime(new Date());
				dao.update(record);
				List<SPmCheckLog> cllist = cldao.findByIdNode(recordid, pdID);
				if(cllist != null){
					for(SPmCheckLog cl : cllist){
						cl.setDelFlag("1");
						cldao.update(cl);
					}
				}
				// 审核记录对象
				SPmCheckLog spcl = new SPmCheckLog();
				spcl.setId(IdGen.uuid());
				spcl.setRecordID(recordid);
				spcl.setRecordName(record.getName());
				spcl.setCheckDate(new Date());
				spcl.setCheckID(user.getId());
				spcl.setCheckName(user.getName());
				spcl.setCheckNodeID(pdID);
				spcl.setCheckResult("2");
				spcl.setBackreason("");
				spcl.setDelFlag("0");
				cldao.insert(spcl);
			}
		}
	}

	/**
	 * 填报
	 */
	public void fillIn(String pdID){
		ProcessData pd = pddao.findByID(pdID);
		if(pd != null){
			User user = UserUtils.getUser(); //登录用户
			pddao.update(pd);
			if("2".equals(pd.getNode())){
				pd.setFillflag("1");
				fillCheck(pd);
			}else if("3".equals(pd.getNode()) || "18".equals(pd.getNode())
					 || "16".equals(pd.getNode()) || "22".equals(pd.getNode())
					 ||"24".equals(pd.getNode()) || "14".equals(pd.getNode())
					 || "15".equals(pd.getNode()) || "4".equals(pd.getNode())
						|| "8".equals(pd.getNode())){
				pd.setFillflag("1");
				pddao.update(pd);
			}else if("10".equals(pd.getNode())){
				pd.setFillflag("1");
				changeCheckLog(pd);
			}else if("7".equals(pd.getNode())){
				List<Role> lr = user.getRoleList();
				Role role = getRole(lr);
				if(SQR_ROLE_ID.equals(role.getId())){
					pd.setFillflag("1");
				}
				// 代办流程信息
				ProcessData prd = PmUtils.getProcessNextOrBack(pd, user, 1);
				pd.setChildNum(prd.getChildNum());
				pd.setOfficID(prd.getOfficID());
				pd.setRoleID(prd.getRoleID());
				pddao.update(pd);
			}else if("5".equals(pd.getNode())){
				pd.setFillflag("1");
				// 代办流程信息
				ProcessData prd = PmUtils.getProcessNextOrBack(pd, user, 1);
				pd.setChildNum(prd.getChildNum());
				pd.setOfficID(prd.getOfficID());
				pd.setRoleID(prd.getRoleID());
				pddao.update(pd);
			}else if("13".equals(pd.getNode()) || "20".equals(pd.getNode())){
				fillCheck(pd);
			}
		}
	}

	public void fillSe(String pdID){
		ProcessData pd = pddao.findByID(pdID);
		pddao.update(pd);
		if("7".equals(pd.getNode())){
			pd.setSubmitflag("1");
			changeCheckLog(pd);
		}
	}


	public DQRecord findOne(String userID){
		return dao.findOne(userID);
	}


	/**
	 * 填报提交保存  雷同审核
	 */
	public void fillCheck(ProcessData pd){
		User user =  UserUtils.getUser(); //登录用户
		DQRecord record = dao.findByRID(pd.getAppID());
		pd.setStatus("old");
		// 代办流程信息
		ProcessData prd = PmUtils.getProcessNextOrBack(pd, user, 1);
		pd.setChildNum(prd.getChildNum());
		pd.setOfficID(prd.getOfficID());
		pd.setRoleID(prd.getRoleID());
		pddao.update(pd);

		// 节点追加
		addNextNode(record, prd, user);

		if(record != null){
			if("0".equals(record.getStage())){
				// 进入积极分子阶段
				if("4".equals(pd.getNode())){
					record.setStage("1");
					record.setActivistTime(new Date());
					dao.update(record);
				}
			}else if("1".equals(record.getStage())){
				// 进入发展对象阶段
				if("7".equals(pd.getNode())){
					record.setStage("2");
					record.setObjectTime(new Date());
					dao.update(record);
				}
			}else if("2".equals(record.getStage())){
				// 进入预备党员阶段
				if("14".equals(pd.getNode())){
					record.setStage("3");
					record.setObjectTime(new Date());
					dao.update(record);
				}
			}else if("3".equals(record.getStage())){
				// 进入预备党员阶段
				if("22".equals(pd.getNode())){
					record.setStage("4");
					record.setObjectTime(new Date());
					dao.update(record);
				}
			}
			List<SPmCheckLog> cllist = cldao.findByIdNode(pd.getAppID(), pd.getId());
			if(cllist != null){
				for(SPmCheckLog cl : cllist){
					cl.setDelFlag("1");
					cldao.update(cl);
				}
			}
			// 审核记录对象
			SPmCheckLog spcl = new SPmCheckLog();
			spcl.setId(IdGen.uuid());
			spcl.setRecordID(pd.getAppID());
			spcl.setRecordName(record.getName());
			spcl.setCheckDate(new Date());
			spcl.setCheckID(user.getId());
			spcl.setCheckName(user.getName());
			spcl.setCheckNodeID(pd.getId());
			spcl.setCheckResult("0");
			spcl.setDelFlag("0");
			cldao.insert(spcl);
		}
	}

	/**
	 * 审核记录变更
	 */
	public void changeCheckLog(ProcessData pd){
		if(pd != null){
			User user =  UserUtils.getUser(); //登录用户
			DQRecord record = dao.findByRID(pd.getAppID());
			List<SPmCheckLog> cllist = cldao.findByIdNode(pd.getAppID(), pd.getId());
			if(cllist != null){
				for(SPmCheckLog cl : cllist){
					cl.setDelFlag("1");
					cldao.update(cl);
				}
			}
			// 审核记录对象
			SPmCheckLog spcl = new SPmCheckLog();
			spcl.setId(IdGen.uuid());
			spcl.setRecordID(pd.getAppID());
			spcl.setRecordName(record.getName());
			spcl.setCheckDate(new Date());
			spcl.setCheckID(user.getId());
			spcl.setCheckName(user.getName());
			spcl.setCheckNodeID(pd.getId());
			spcl.setCheckResult("2");
			spcl.setDelFlag("0");
			cldao.insert(spcl);

			// 代办流程信息
			ProcessData prd = PmUtils.getProcessNextOrBack(pd, user, 1);
			pd.setChildNum(prd.getChildNum());
			pd.setOfficID(prd.getOfficID());
			pd.setRoleID(prd.getRoleID());
			pddao.update(pd);
		}
	}

	/**
	 * 需求角色 取得
	 * @param lr
	 * @return
	 */
	public Role getRole(List<Role> lr){
		Role rl = new Role();
		for(Role r: lr){
			if(SQR_ROLE_ID.equals(r.getId()) ){
				rl = r;
			}
			if(PmUtils.getZBSJ_ROLE_ID().equals(r.getId())){
				rl = r;
			}
			if(PmUtils.getZZSJ_ROLE_ID().equals(r.getId())){
				rl = r;
			}
			if(PmUtils.getDW2SJ_ROLE_ID().equals(r.getId())){
				rl = r;
			}
			if(PmUtils.getDWSJ_ROLE_ID().equals(r.getId())){
				rl = r;
			}
			if(rl == null){
				return r;
			}
		}
		return rl;
	}

	/**
	 * 二级党委通过
	 */
	@RequestMapping(value="/finish")
	public void finish(String pdid){
		ProcessData pd = pddao.findByID(pdid);
		User user = UserUtils.getUser(); //登录用户
		// 代办流程信息
		ProcessData prd = PmUtils.getProcessNextOrBack(pd, user, 1);
		pd.setSubmitflag("1");
		pd.setChildNum(prd.getChildNum());
		pd.setOfficID(prd.getOfficID());
		pd.setRoleID(prd.getRoleID());
		pddao.update(pd);
	}


	public String seckUserID(String recordId){
		String userID = dao.seckUserID(recordId, PmUtils.getZBSJ_ROLE_ID());
		return userID;
	}

	public String getRidByPid(String proId) {
		ProcessData processData = pddao.findByID(proId);
		if (processData == null) {
			return "";
		} else {
			return processData.getAppID();
		}
	}
	
	/**
	 * 终止流程,所有节点变更删除标记
	 */
	public void Stop(String recordid, String pdid){
		DQRecord record = dao.findByRID(recordid);
		if(record != null){
			record.setDelflag("1");
			dao.update(record);
			List<ProcessData> lpd = pddao.findlist(recordid);
			for(ProcessData pd : lpd){
				// 流程变更删除
				pd.setDelflag("1");
				pddao.update(pd);
				// 日志记录变更删除
				List<SPmCheckLog> cllist = cldao.findByIdNode(pd.getAppID(), pd.getId());
				if(cllist != null){
					for(SPmCheckLog cl : cllist){
						cl.setDelFlag("1");
						cldao.update(cl);
					}
				}
			}
			User user =  UserUtils.getUser(); //登录用户
			// 审核记录对象
			SPmCheckLog spcl = new SPmCheckLog();
			spcl.setId(IdGen.uuid());
			spcl.setRecordID(recordid);
			spcl.setRecordName(record.getName());
			spcl.setCheckDate(new Date());
			spcl.setCheckID(user.getId());
			spcl.setCheckName(user.getName());
			spcl.setCheckNodeID(pdid);
			spcl.setCheckResult("3");
			spcl.setDelFlag("0");
			cldao.insert(spcl);

		}
	}
	
	
	/**
	 * 补录节点   补录标记变更
	 */
	public void makeUpFlagChange(String recordid, int node){
		DQRecord record = dao.findByRID(recordid);
		User user = UserUtils.getUser(); //登录用户
		record.setMakeupflag("1");
		record.setMakenode(String.valueOf(node));
		if(node >= 4 && node < 7){
			record.setStage("1");
			dao.update(record);
		}else if(node >= 7 && node < 14){
			record.setStage("2");
			dao.update(record);
		}else if(node >= 14 && node < 22){
			record.setStage("3");
			dao.update(record);
		}else if(node >= 22){
			record.setStage("4");
			dao.update(record);
		}
		for(int i = 2; i <= node; i++){
			ProcessData pd = new ProcessData();
			pd.setId(IdGen.uuid());
			pd.setStage(record.getStage());
			pd.setNode(String.valueOf(i));
			pd.setStatus("old");
			pd.setCreateby(user.getId());
			pd.setCreateDate(new Date());
			pd.setAppID(recordid);
			pd.setDelflag("0");
			pd.setFillflag("1");
			pd.setSubmitflag("1");
			pd.setCheckflag("1");
			pd.setChildNum(0);
			Office office = user.getOffice();
			pd.setOfficID(office.getId());
			Role role = getRole(user.getRoleList());
			pd.setRoleID(role.getId());
			pd.setMakeupflag("1");
			pddao.insert(pd);
			

			// 审核记录对象
			SPmCheckLog spcl = new SPmCheckLog();
			spcl.setId(IdGen.uuid());
			spcl.setRecordID(recordid);
			spcl.setRecordName(record.getName());
			spcl.setCheckDate(new Date());
			spcl.setCheckID(user.getId());
			spcl.setCheckName(user.getName());
			spcl.setCheckNodeID(pd.getId());
			spcl.setCheckResult("0");
			spcl.setDelFlag("0");
			cldao.insert(spcl);
		}
		ProcessData opd = pddao.findByAppID(recordid, "now");
		if(opd != null){
			opd.setStatus("old");
			opd.setMakeupflag("1");
			pddao.update(opd);
		}

		ProcessData pd = new ProcessData();
		pd.setNode(String.valueOf(node));
		addNextNode(record, pd, user);
	}
	
	
	public Page<DQRecord> makePage(Page<DQRecord> page, DQRecord dqrecord, String stage, User user) {
		dqrecord.setPage(page);
		List<DQRecord> datalist = new ArrayList<DQRecord>();
		List<DQRecord> list = new ArrayList<DQRecord>();
		List<Role> lr = user.getRoleList();
		Office office = user.getOffice();
		Role role = getRole(lr);
		list = dao.findByStage(stage, user.getId(), office.getId());
		for(DQRecord r : list){
			DQRecord record = new DQRecord();
			record.setId(r.getId());
			record.setName(r.getName());
			record.setOrgName(r.getOrgName());
			if(role != null){
				ProcessData pd = pddao.findByAppID(r.getId(), "now");
				if(pd != null){
					record.setState(nowState(pd, user));
				}else{
					record.setState("3");
				}
			}
			ProcessData pd = pddao.findByAppID(r.getId(), "now");
			if(pd != null){
				record.setNowNode(pd.getNode());
			}
			record.setMakeupflag(r.getMakeupflag());
			record.setEndflag(r.getEndflag());
			record.setMakenode(r.getMakenode());
			datalist.add(record);
		}
		page.setCount(datalist.size());
		page.setList(datalist);
		return page;
	}
	
}
