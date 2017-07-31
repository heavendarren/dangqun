package com.thinkgem.jeesite.modules.partyManage.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.partyManage.entity.DQRecord;
import com.thinkgem.jeesite.modules.partyManage.entity.ProcessData;
import com.thinkgem.jeesite.modules.partyManage.service.DQRecordService;
import com.thinkgem.jeesite.modules.partyManage.utils.PmUtils;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 党员发展纪实controller
 * @author xingtianpeng
 * 4-11
 */
@Controller
@RequestMapping(value="${adminPath}/partyManage/DQRecord")
public class DQRecordController extends BaseController {
	
	@Autowired
	private DQRecordService recordservice;

	@RequestMapping(value="/record")
	public String DQRecord(HttpServletRequest request, HttpServletResponse response, Model model){
		String stage = request.getParameter("stage");
		User user = UserUtils.getUser(); //登录用户
		Page<DQRecord> page = new Page<DQRecord>(request,response);
		DQRecord red = new DQRecord();
		if(stage == null){
			red = recordservice.findOne(user.getId());
		}
		
		if(red == null){
			page = recordservice.findPage(new Page<DQRecord>(request,response), new DQRecord(),"0", user);
			model.addAttribute("page", page);
			model.addAttribute("stage", "0");
		}else{
			if(stage == null){
				page = recordservice.findPage(new Page<DQRecord>(request,response), new DQRecord(), red.getStage(), user);
				model.addAttribute("page", page);
				model.addAttribute("stage", red.getStage());
			}else{
				page = recordservice.findPage(new Page<DQRecord>(request,response), new DQRecord(), stage, user);
				model.addAttribute("page", page);
				model.addAttribute("stage", stage);
			}
		}
		return "modules/partyManage/DQRecord";
	}
	

	
	/**
	 * 名字查询
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/find")
	public String find(HttpServletRequest request, HttpServletResponse response, Model model){
		String name = request.getParameter("name");
		User user = UserUtils.getUser(); //登录用户
		String stage = "";
		List<DQRecord> datalist = new ArrayList<DQRecord>();
		List<DQRecord> list = recordservice.findByName(name, user.getOffice().getId());
		for(DQRecord r : list){
			stage = r.getStage(); //阶段
			DQRecord record = new DQRecord();
			record.setIDCard(r.getIDCard());
			record.setName(r.getName());
			record.setOrgName(r.getOrgName());
			if(stage.equals("0")){
				record.setApplicationTime(new java.sql.Date(r.getApplicationTime().getTime()));
			}
			if(stage.equals("1")){
				record.setApplicationTime(new java.sql.Date(r.getActivistTime().getTime()));
			}
			if(stage.equals("2")){
				record.setApplicationTime(new java.sql.Date(r.getObjectTime().getTime()));
			}
			if(stage.equals("3")){
				record.setApplicationTime(new java.sql.Date(r.getReserveTime().getTime()));
			}
			if(stage.equals("4")){
				record.setApplicationTime(new java.sql.Date(r.getFormalTime().getTime()));
			}
			record.setState(r.getState());
			datalist.add(record);
		}
		Page<DQRecord> page = new Page<DQRecord>(request,response);
		if(datalist.size() == 0){
			model.addAttribute("message", "未找到该姓名相关信息");
		}
		page.setCount(datalist.size());
		page.setList(datalist);
		model.addAttribute("stage", stage);
		model.addAttribute("page", page);
		return "modules/partyManage/DQRecord";
	}
	
	/**
	 * 转向入党进度列表
	 * @return
	 */
	@RequestMapping(value="/schedule")
	public String Schedule(HttpServletRequest request, HttpServletResponse response, Model model){
		String back = (String) request.getAttribute("back");
		String id = null;
		if("back".equals(back)){
			 id = (String) request.getAttribute("id");
		}else{
			 id = request.getParameter("id");
		}
		User user = UserUtils.getUser(); //登录用户
		DQRecord record = recordservice.findByID(id);
		if(record != null){
			List<ProcessData> lpd = recordservice.findlist(record.getId());
			List<ProcessData> list = new ArrayList<ProcessData>();
			List<ProcessData> dlist = new ArrayList<ProcessData>();
			for(ProcessData pd : lpd){
				if(pd.getCheckdel() == null){
					pd.setCheckdel("0");
				}
				if(("now".equals(pd.getStatus()) || "old".equals(pd.getStatus())) && "0".equals(pd.getCheckdel())){
					if(pd.getCheckDate() != null){
						pd.setCheckDate(new java.sql.Date(pd.getCheckDate().getTime()));
					}
					pd.setNowState(recordservice.nowState(pd, user));
					list.add(pd);
				}
			}
			for(int i = 1; i < 27; i++){
				for(ProcessData pd: list){
					if(i <= list.size()){
						int node = Integer.valueOf(pd.getNode());
						if(node == i){
							dlist.add(pd);
						}
					}
				}
			}
			
			model.addAttribute("list", dlist);
			model.addAttribute("record", record);
			List<Role> lr = user.getRoleList();
			Role role = recordservice.getRole(lr);
			if(role != null){
				if(user.getId().equals(record.getUserID())){
					if(recordservice.getSQR_ROLE_ID().equals(role.getId())){
						record.setState("0"); // 本人
					}else{
						record.setState("1"); // 同组织人员
					}
				}else{
					record.setState("1"); //同组织人员
				}
				if(PmUtils.getZBSJ_ROLE_ID().equals(role.getId())){
					record.setState("3"); // 书记
				}
				/*if(PmUtils.getZZSJ_ROLE_ID().equals(role.getId())){
					record.setState("4"); // 书记
				}*/
				if(PmUtils.getDW2SJ_ROLE_ID().equals(role.getId())){
					record.setState("4"); // 书记
				}
				if(PmUtils.getDWSJ_ROLE_ID().equals(role.getId())){
					record.setState("5"); // 书记
				}
			}
		}
			
		return "modules/partyManage/DQSchedule";
	}

	/**
	 * 审核
	 */
	@RequestMapping(value="/check")
	public String Check(HttpServletRequest request, HttpServletResponse response, Model model){
		String recordid = request.getParameter("recordid");
		String pdid = request.getParameter("pdid");
		String checkresult = request.getParameter("checkresult");
		User user = UserUtils.getUser(); //登录用户
		// 审核记录
		recordservice.check(recordid, pdid, checkresult, user);
		// 重新加载 进度页面
		request.setAttribute("back", "back");
		request.setAttribute("id", recordid);
		return Schedule(request, response, model);
	}
	
	/**
	 * 未通过 退回
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/wtg1","/th1"})
	public String wtgFirst(HttpServletRequest request, HttpServletResponse response, Model model){
		String recordid = request.getParameter("recordid");
		String pdid = request.getParameter("pdid");
		String checkresult = request.getParameter("checkresult");
		String wtgtext = request.getParameter("wtgtext");
		String tuihui = request.getParameter("tuihui");
		User user = UserUtils.getUser(); //登录用户
		//未通过
		if(checkresult.equals("1")){
			recordservice.weiTG(recordid, pdid, checkresult, wtgtext, user);
		}else if(checkresult.equals("4")){// 退回
			recordservice.weiTG(recordid, pdid, checkresult, tuihui, user);
		}
		// 重新加载 进度页面
		request.setAttribute("back", "back");
		request.setAttribute("id", recordid);
		return Schedule(request, response, model);
	}
	
	
	/**
	 * 提交申请
	 */
	@RequestMapping(value="/submit")
	public String submit(HttpServletRequest request, HttpServletResponse response, Model model){
		String pdid = request.getParameter("pdid");
		String recordid = request.getParameter("recordid");
		User user = UserUtils.getUser(); //登录用户
		recordservice.submit(pdid, recordid, user);
		// 重新加载 进度页面
		request.setAttribute("back", "back");
		request.setAttribute("id", recordid);
		return Schedule(request, response, model);	
	}
	
	/**
	 * 二级党委通过
	 */
	@RequestMapping(value="/finish")
	public String finish(HttpServletRequest request, HttpServletResponse response, Model model){
		String pdid = request.getParameter("pdid");
		String recordid = request.getParameter("recordid");
		recordservice.finish(pdid);
		// 重新加载 进度页面
		request.setAttribute("back", "back");
		request.setAttribute("id", recordid);
		return Schedule(request, response, model);
	}
	
	/**
	 * 终止流程
	 */
	@RequestMapping(value="/stop")
	public String Stop(HttpServletRequest request, HttpServletResponse response, Model model){
		String pdid = request.getParameter("pdid");
		String recordid = request.getParameter("recordid");
		recordservice.Stop(recordid, pdid);
		// 重新加载 发展纪实页面
		return DQRecord(request, response, model);
	}
	
	/**
	 * 补录
	 */
	@RequestMapping(value="/bulu")
	public String bulu(HttpServletRequest request, HttpServletResponse response, Model model){
		String recordid = request.getParameter("recordid");
		String bulunode = request.getParameter("bulunode");
		// 补录流程节点
		recordservice.makeUpFlagChange(recordid, Integer.valueOf(bulunode));
		
		// 重新加载 进度页面
		request.setAttribute("back", "back");
		request.setAttribute("id", recordid);
		return Schedule(request, response, model);
	}
	
	@RequestMapping(value="/makeup")
	public String DQMakeUp(HttpServletRequest request, HttpServletResponse response, Model model){
		String stage = request.getParameter("stage");
		User user = UserUtils.getUser(); //登录用户
		Page<DQRecord> page = new Page<DQRecord>(request,response);
		DQRecord red = new DQRecord();
		if(stage == null){
			red = recordservice.findOne(user.getId());
		}
		
		if(red == null){
			page = recordservice.makePage(new Page<DQRecord>(request,response), new DQRecord(),"0", user);
			model.addAttribute("page", page);
			model.addAttribute("stage", "0");
		}else{
			if(stage == null){
				page = recordservice.makePage(new Page<DQRecord>(request,response), new DQRecord(), red.getStage(), user);
				model.addAttribute("page", page);
				model.addAttribute("stage", red.getStage());
			}else{
				page = recordservice.makePage(new Page<DQRecord>(request,response), new DQRecord(), stage, user);
				model.addAttribute("page", page);
				model.addAttribute("stage", stage);
			}
		}
		Role role = recordservice.getRole(user.getRoleList());
		if(PmUtils.getZBSJ_ROLE_ID().equals(role.getId())){
			model.addAttribute("zbsj", "zb");
		} 
		model.addAttribute("bulu", "4");
		model.addAttribute("makenode", "4");
		return "modules/partyManage/DQMakeUp";
	}
}
