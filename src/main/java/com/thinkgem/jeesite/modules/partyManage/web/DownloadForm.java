package com.thinkgem.jeesite.modules.partyManage.web;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.modules.partyManage.entity.*;
import com.thinkgem.jeesite.modules.partyManage.service.*;
import com.thinkgem.jeesite.modules.partyManage.utils.DocxUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/partyManage/download")

public class DownloadForm{
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	  @Autowired
	  private SPmBasicService sPmBasicService;

	  public Map<String, String> getBasic(Map<String, String> params, String proId) {
	    ProcessData processData = sPmBasicService.down(proId, "1");
	    SPmBasic sPmBasic = sPmBasicService.getByproId(processData.getId());
	    if (sPmBasic != null) {
	      params.put("personName", sPmBasic.getName());
	      params.put("personSex", DictUtils.getDictLabel(sPmBasic.getSex(), "XB", ""));
	      params.put("originPlace", DictUtils.getDictLabel(sPmBasic.getOriginPlace(), "JG", ""));
	      params.put("personNation", DictUtils.getDictLabel(sPmBasic.getNation(), "MZ", ""));
	      params.put("personEdu", DictUtils.getDictLabel(sPmBasic.getEducation(), "WHCD", ""));
	      params.put("personPost", sPmBasic.getTechnicalPost());
	      if (sPmBasic.getWorkTime() == null) {
	        params.put("postTime", "");
	      } else {
	        if (sPmBasic.getWorkTime().toString() == "") {
	          params.put("postTime", "");
	        }
	        params.put("postTime", sdf.format(sPmBasic.getWorkTime()));
	      }


	      Date date = new Date();
	      if (sPmBasic.getBirthTime() == null) {
	        params.put("birthTime", "");
	        params.put("personAge", "");
	      } else {
	        if (sPmBasic.getBirthTime().toString() == "") {
	          params.put("birthTime", "");
	          params.put("personAge", "");
	        }
	        params.put("birthTime", sdf.format(sPmBasic.getBirthTime()));
	        Calendar beforeCalendar = Calendar.getInstance();
	        beforeCalendar.setTime(date);
	        Calendar afterCalendar = Calendar.getInstance();
	        afterCalendar.setTime(sPmBasic.getBirthTime());
	        params.put("personAge", String.valueOf(beforeCalendar.get(beforeCalendar.YEAR) -
	                afterCalendar.get(afterCalendar.YEAR)) + "岁");
	      }
	    } else {
	      params.put("personName", "");
	      params.put("personSex", "");
	      params.put("originPlace", "");
	      params.put("personNation", "");
	      params.put("personEdu", "");
	      params.put("personPost", "");
	      params.put("postTime", "");
	      params.put("birthTime", "");
	      params.put("personAge", "");
	    }
	    return params;
	  }

	  @Autowired
	  private SPmAppSituationService sPmAppSituationService;

	  public Map<String, String> getAppSituation(Map<String, String> params, String proId) {
	    ProcessData processData = sPmBasicService.down(proId, "1");
	    SPmAppSituation sPmAppSituation = sPmAppSituationService.getByproId(processData.getId());
	    if (sPmAppSituation != null) {
	      if (sPmAppSituation.getJionTime() != null) {
	        params.put("jionTime", "");
	      } else {
	        if (sPmAppSituation.getJionTime().toString() != "") {
	          params.put("jionTime", "");
	        }
	        params.put("jionTime", sdf.format(sPmAppSituation.getJionTime()));
	      }
	      if (sPmAppSituation.getActivistTime() == null) {
	        params.put("actTime", "");
	      } else {
	        if (sPmAppSituation.getActivistTime().toString() == "") {
	          params.put("actTime", "");
	        }
	        params.put("actTime", sdf.format(sPmAppSituation.getActivistTime()));
	      }

	      if (sPmAppSituation.getDevelopmentTime() == null) {
	        params.put("devTime", "");
	      } else {
	        if (sPmAppSituation.getDevelopmentTime().toString() == "") {
	          params.put("devTime", "");
	        }
	        params.put("devTime", sdf.format(sPmAppSituation.getDevelopmentTime()));
	      }
	    } else {
	      params.put("jionTime", "");
	      params.put("actTime", "");
	      params.put("devTime", "");
	    }
	    return params;
	  }


	  @Autowired
	  private SPmTermTrainService sPmTermTrainService;

	  public Map<String, String> getTermTrain(Map<String, String> params, String proId) {
	    ProcessData processData = sPmBasicService.down(proId, "13");
	    SPmTermTrain sPmTermTrain = sPmTermTrainService.getByproId(processData.getId());
	    sPmTermTrain = sPmTermTrainService.getForms(sPmTermTrain);

	    if (sPmTermTrain != null) {
	      if (sPmTermTrain.getUploadTime() == null) {
	        params.put("tremTime", "");
	      } else {
	        if (sPmTermTrain.getUploadTime().toString() == "") {
	          params.put("tremTime", "");
	        }
	        params.put("tremTime", sdf.format(sPmTermTrain.getUploadTime()));
	      }

	    } else {
	      params.put("tremTime", "");
	    }
	    return params;
	  }

	  @Autowired
	  private SPmMinutesSService sPmMinutesSService;

	  public Map<String, String> getMinutess(Map<String, String> params, String proId) {
	    ProcessData processData = sPmBasicService.down(proId, "6");
	    SPmMinutesS sPmMinutesS = sPmMinutesSService.getByproId(processData.getId());
	    if (sPmMinutesS != null) {
	      params.put("jionPersonName", sPmMinutesS.getPerPar());
	    } else {
	      params.put("jionPersonName", "");
	    }
	    return params;
	  }

	  @Autowired
	  private SPmPrepPartyPublicService sPmPrepPartyPublicService;

	  public Map<String, String> getPrepPartyPublic(Map<String, String> params, String proId) {
	    ProcessData processData = sPmBasicService.down(proId, "16");
	    SPmPrepPartyPublic sPmPrepPartyPublic = sPmPrepPartyPublicService.getByproId(processData.getId());
	    if (sPmPrepPartyPublic != null) {
	      sPmPrepPartyPublic = sPmPrepPartyPublicService.getForms(sPmPrepPartyPublic);
	      if (sPmPrepPartyPublic.getUploadTime() == null) {
	        params.put("prepPublicTime", "");
	      } else {
	        if (sPmPrepPartyPublic.getUploadTime().toString() == "") {
	          params.put("prepPublicTime", "");
	        }
	        params.put("prepPublicTime", sdf.format(sPmPrepPartyPublic.getUploadTime()));
	      }

	    } else {
	      params.put("prepPublicTime", "");
	    }
	    return params;
	  }

	  @Autowired
	  private SPmContactSettingsService sPmContactSettingsService;

	  public Map<String, String> getContactSetting(Map<String, String> params, String id) {
	    SPmContactSettings sPmContactSettings = sPmContactSettingsService.get(id);
	    if (sPmContactSettings != null) {

	      params.put("personName", sPmContactSettings.getName());
	      params.put("personSex", DictUtils.getDictLabel(sPmContactSettings.getSex(), "XB", ""));
	      params.put("originPlace", DictUtils.getDictLabel(sPmContactSettings.getOriginPlace(), "JG", ""));

	      if (sPmContactSettings.getBirthTime() == null) {
	        params.put("birthTime", "");
	      } else {
	        if (sPmContactSettings.getBirthTime().toString() == "") {
	          params.put("birthTime", "");
	        }
	        params.put("birthTime", sdf.format(sPmContactSettings.getBirthTime()));
	      }
	      params.put("personNation", DictUtils.getDictLabel(sPmContactSettings.getNation(), "MZ", ""));

	      if (sPmContactSettings.getJionTime() == null) {
	        params.put("jionTime", "");
	      } else {
	        if (sPmContactSettings.getJionTime().toString() == "") {
	          params.put("jionTime", "");
	        }
	        params.put("jionTime", sdf.format(sPmContactSettings.getJionTime()));
	      }
	      params.put("personEdu", sPmContactSettings.getEducationDegree());
	      params.put("personPost", sPmContactSettings.getPostPlace());
	      params.put("personResume", sPmContactSettings.getPersonResume());
	    } else {
	      params.put("personName", "");
	      params.put("personSex", "");
	      params.put("originPlace", "");
	      params.put("birthTime", "");
	      params.put("personNation", "");
	      params.put("jionTime", "");
	      params.put("personEdu", "");
	      params.put("personPost", "");
	      params.put("personResume", "");
	    }
	    return params;
	  }

	  //确定入党介绍人
	  @Autowired
	  private SPmParthMenberService sPmParthMenberService;

	  public Map<String, String> getParthMenber(Map<String, String> params, String id) {
	    SPmParthMenber sPmParthMenber = sPmParthMenberService.get(id);
	    if (sPmParthMenber != null) {
	      params.put("personName", sPmParthMenber.getName());
	      params.put("personSex", DictUtils.getDictLabel(sPmParthMenber.getSex(), "XB", ""));
	      params.put("originPlace", DictUtils.getDictLabel(sPmParthMenber.getOriginPlace(), "JG", ""));
	      if (sPmParthMenber.getBirthTime() == null) {
	        params.put("birthTime", "");
	      } else {
	        if (sPmParthMenber.getBirthTime().toString() == "") {
	          params.put("birthTime", "");
	        }
	        params.put("birthTime", sdf.format(sPmParthMenber.getBirthTime()));
	      }
	      params.put("personNation", DictUtils.getDictLabel(sPmParthMenber.getNation(), "MZ", ""));
	      if (sPmParthMenber.getJionTime() == null) {
	        params.put("jionTime", "");
	      } else {
	        if (sPmParthMenber.getJionTime().toString() == "") {
	          params.put("jionTime", "");
	        }
	        params.put("jionTime", sdf.format(sPmParthMenber.getJionTime()));
	      }

	      params.put("personEdu", sPmParthMenber.getEducationDegree());
	      params.put("personPost", sPmParthMenber.getPostPlace());
	      params.put("personResume", sPmParthMenber.getPersonResume());
	      params.put("postTime", "");
	    } else {
	      params.put("personName", "");
	      params.put("personSex", "");
	      params.put("originPlace", "");
	      params.put("birthTime", "");
	      params.put("personNation", "");
	      params.put("jionTime", "");
	      params.put("personEdu", "");
	      params.put("personPost", "");
	      params.put("personResume", "");
	      params.put("postTime", "");
	    }
	    return params;
	  }
	
	@Autowired
	private SPmMrsService sPmMrsService;
	
	public Map<String, String> getMrs(Map<String, String> params, String id) {
	    SPmMrs sPmMrs = sPmMrsService.get(id);
	    if(sPmMrs != null){
	    	params.put("recTime", sdf.format(sPmMrs.getRecTime()));
	    	params.put("recLoca", sPmMrs.getRecLoca());
	    	params.put("attEnd", String.valueOf(sPmMrs.getAttend()));
	    }else{
	    	params.put("recTime", "");
	    	params.put("recLoca", "");
	    	params.put("attEnd", "");
	    }
	    return params;
	    
	}
	
	@Autowired
	private SPmPmsService sPmPmsService;
	
	public Map<String, String> getPms(Map<String, String> params, String id) {
	    SPmPms sPmPms = sPmPmsService.get(id);
	    if(sPmPms != null){
	    	params.put("recTime", sdf.format(sPmPms.getRecTime()));
	    	params.put("recLoca", sPmPms.getRecLoca());
	    	params.put("tnoPm", String.valueOf(sPmPms.getTnoPm()));
	    	params.put("numPm", String.valueOf(sPmPms.getNumPm()));
	    }else{
	    	params.put("recTime", "");
	    	params.put("recLoca", "");
	    	params.put("tnoPm", "");
	    	params.put("numPm", "");
	    }
	    return params;
	    
	}
	
	@Autowired
	private SPmPrfService sPmPrfService;
	
	public Map<String, String> getPrf(Map<String, String> params ,String id){
		SPmPrf sPmPrf = sPmPrfService.get(id);
		if(sPmPrf != null){
			params.put("recOb", sPmPrf.getRecOb());
			params.put("sex", sPmPrf.getSex());
			params.put("plaOf", sPmPrf.getPlaOf());
			params.put("dateBirth", sdf.format(sPmPrf.getDateBirth()));
			params.put("nation", sPmPrf.getNation());
			params.put("degEdu", sPmPrf.getDegEdu());
			params.put("apcatime", sdf.format(sPmPrf.getApcaTime()));
			params.put("offCap", sPmPrf.getOffCap());
		} else {
			params.put("recOb", "");
			params.put("sex", "");
			params.put("plaOf", "");
			params.put("dateBirth", "");
			params.put("nation", "");
			params.put("degEdu", "");
			params.put("apcatime", "");
			params.put("offCap", "");
		}
		return params;
	}
	
	@Autowired
	private SPmActiviService sPmActiviService;
	
	public Map<String, String> getActivi(Map<String, String> params, String id) {
		SPmActivi sPmActivi = sPmActiviService.get(id);
	    if(sPmActivi != null){
	    	params.put("name", sPmActivi.getName());
	    	params.put("sex", sPmActivi.getSex());
	    	params.put("plaOf", sPmActivi.getPlaOf());
	    	params.put("dateBirth", sdf.format(sPmActivi.getDateBirth()));
	    	params.put("nation", sPmActivi.getNation());
	    	params.put("degEdu", sPmActivi.getDegEdu());
	    	params.put("joinTime", sdf.format(sPmActivi.getJoinTime()));
	    	params.put("apcaTime", sdf.format(sPmActivi.getApcaTime()));
	    }else{
	    	params.put("name","");
	    	params.put("sex","");
	    	params.put("plaOf","");
	    	params.put("dateBirth","");
	    	params.put("nation", "");
	    	params.put("degEdu", "");
	    	params.put("joinTime", "");
	    	params.put("apcaTime", "");
	    }
	    return params;
	    
	}
	
	@Autowired
	private SPmCheckService sPmCheckService;
	
	public Map<String, String> getCheck(Map<String, String> params, String id) {
		SPmCheck sPmCheck = sPmCheckService.get(id);
	    if(sPmCheck != null){
	    	params.put("name", sPmCheck.getName());
	    	params.put("sex", sPmCheck.getSex());
	    	params.put("dateBirth", sdf.format(sPmCheck.getDateBirth()));
	    	params.put("apcaTime", sdf.format(sPmCheck.getApcaTime()));
	    	params.put("incumbent", sPmCheck.getIncumbent());
	    }else{
	    	params.put("name","");
	    	params.put("sex","");
	    	params.put("dateBirth","");
	    	params.put("apcaTime", "");
	    	params.put("incumbent","");
	    }
	    return params;
	    
	}
	
	@Autowired
	private SPmReviewService sPmReviewService;
	
	public Map<String, String> getReview(Map<String, String> params, String id) {
		SPmReview sPmReview = sPmReviewService.get(id);
	    if(sPmReview != null){
	    	params.put("name", sPmReview.getName());
	    	params.put("sex", sPmReview.getSex());
	    	params.put("nation", sPmReview.getNation());
	    	params.put("dateBirth", sdf.format(sPmReview.getDateBirth()));
	    	params.put("degree", sPmReview.getDegree());
	    	params.put("workHours", sdf.format(sPmReview.getWorkHours()));
	    	params.put("partyTime", sdf.format(sPmReview.getPartyTime()));
	    	params.put("objectTime", sdf.format(sPmReview.getObjectTime()));
	    	params.put("activistTime", sdf.format(sPmReview.getActivistTime()));
	    	params.put("shortTime", sdf.format(sPmReview.getShortTime()));
	    	
	    }else{
	    	params.put("name", "");
	    	params.put("sex", "");
	    	params.put("nation", "");
	    	params.put("dateBirth", "");
	    	params.put("degree", "");
	    	params.put("workHours", "");
	    	params.put("partyTime", "");
	    	params.put("objectTime", "");
	    	params.put("activistTime", "");
	    	params.put("shortTime", "");
	    }
	    return params;
	    
	}
	
	@Autowired
	private SPmProbationaryService sPmProbationaryService;
	
	public Map<String, String> getProbationary(Map<String, String> params) {
		SPmProbationary sPmProbationary = sPmProbationaryService.get(params.get("id"));
	    if(sPmProbationary != null){
			params.put("name", sPmProbationary.getName());
			params.put("sex", sPmProbationary.getSex());
			params.put("plaOf", sPmProbationary.getPlaOf());
			params.put("dateBirth", sdf.format(sPmProbationary.getDateBirth()));
			params.put("nation", sPmProbationary.getNation());
			params.put("degEdu", sPmProbationary.getDegEdu());
			params.put("joinTime", sdf.format(sPmProbationary.getJoinTime()));
			params.put("apcaTime", sdf.format(sPmProbationary.getApcaTime()));
//			if (sPmProbationary.getType() .equals("2")){
//				params.put("name", sPmProbationary.getName());
//				params.put("sex", sPmProbationary.getSex());
//				params.put("plaOf", sPmProbationary.getPlaOf());
//				params.put("dateBirth", sdf.format(sPmProbationary.getDateBirth()));
//				params.put("nation", sPmProbationary.getNation());
//				params.put("degEdu", sPmProbationary.getDegEdu());
//				params.put("joinTime", sdf.format(sPmProbationary.getJoinTime()));
//				params.put("apcaTime", sdf.format(sPmProbationary.getApcaTime()));
//			}
//			if (sPmProbationary.getType() .equals("3")){
//				params.put("name", sPmProbationary.getName());
//				params.put("sex", sPmProbationary.getSex());
//				params.put("plaOf", sPmProbationary.getPlaOf());
//				params.put("dateBirth", sdf.format(sPmProbationary.getDateBirth()));
//				params.put("nation", sPmProbationary.getNation());
//				params.put("degEdu", sPmProbationary.getDegEdu());
//				params.put("joinTime", sdf.format(sPmProbationary.getJoinTime()));
//				params.put("apcaTime", sdf.format(sPmProbationary.getApcaTime()));
//			}
//			if (sPmProbationary.getType() .equals("4")){
//				params.put("name", sPmProbationary.getName());
//				params.put("sex", sPmProbationary.getSex());
//				params.put("plaOf", sPmProbationary.getPlaOf());
//				params.put("dateBirth", sdf.format(sPmProbationary.getDateBirth()));
//				params.put("nation", sPmProbationary.getNation());
//				params.put("degEdu", sPmProbationary.getDegEdu());
//				params.put("joinTime", sdf.format(sPmProbationary.getJoinTime()));
//				params.put("apcaTime", sdf.format(sPmProbationary.getApcaTime()));
//			}
		}else{
	    	params.put("name", "");
	    	params.put("sex", "");
	    	params.put("plaOf", "");
	    	params.put("dateBirth", "");
	    	params.put("nation", "");
	    	params.put("degEdu", "");
	    	params.put("joinTime", "");
	    	params.put("apcaTime", "");
	    }
	    return params;
	    
	}
	
	
   /*
    * 
    * 群众推荐表* */
	@RequestMapping(value="mrs")
	public ResponseEntity<byte[]> Mrs(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 User user = UserUtils.getUser();

		 Map<String, String> params = Maps.newHashMap();
		 params = getBasic(params, user.getProId());
		 params = getAppSituation(params, user.getProId());
		 params = getMrs(params, user.getProId());
		 
		 String url = DocxUtils.getDocxPath(request, "sPmMrsDocx", params);
		 return DocxUtils.fileDownload(new File(url), "群众推荐入党积极分子汇总表.docx", request);
	}
	
	/*
	 * 
	 * 党员推荐表*/
	@RequestMapping(value="pms")
	public ResponseEntity<byte[]> Pms(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 User user = UserUtils.getUser();

		 Map<String, String> params = Maps.newHashMap();
		 params = getBasic(params, user.getProId());
		 params = getAppSituation(params, user.getProId());
		 params = getPms(params, user.getProId());
		 
		 String url = DocxUtils.getDocxPath(request, "sPmPmsDocx", params);
		 return DocxUtils.fileDownload(new File(url), "党员推荐入党积极分子汇总表.docx", request);
	}
	
	/*
	 * 
	 * 群团推优表*/
	@RequestMapping(value="prf")
	public ResponseEntity<byte[]> Prf(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 User user = UserUtils.getUser();

		 Map<String, String> params = Maps.newHashMap();
		 params = getBasic(params, user.getProId());
		 params = getAppSituation(params, user.getProId());
		 params = getPrf(params, user.getProId());
		 
		 String url = DocxUtils.getDocxPath(request, "sPmPrfDocx", params);
		 return DocxUtils.fileDownload(new File(url), "党员推荐入党积极分子汇总表.docx", request);
	}
	
	/*
	 * 
	 * 入党积极分子备案表*/
	@RequestMapping(value="activi")
	public ResponseEntity<byte[]> Activi(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 User user = UserUtils.getUser();

		 Map<String, String> params = Maps.newHashMap();
		 params = getBasic(params, user.getProId());
		 params = getAppSituation(params, user.getProId());
		 params = getActivi(params, user.getProId());
		 
		 String url = DocxUtils.getDocxPath(request, "sPmActiviDocx", params);
		 return DocxUtils.fileDownload(new File(url), "入党积极分子备案表.docx", request);
	}
	
	/*
	 * 
	 * 发展对象政审表*/
	
	@RequestMapping(value="check")
	public ResponseEntity<byte[]> Check(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 User user = UserUtils.getUser();

		 Map<String, String> params = Maps.newHashMap();
		 params = getBasic(params, user.getProId());
		 params = getAppSituation(params, user.getProId());
		 params = getCheck(params, user.getProId());
		 
		 String url = DocxUtils.getDocxPath(request, "sPmCheckDocx", params);
		 return DocxUtils.fileDownload(new File(url), "发展对象政审表.docx", request);
	}
	
	/*
	 * 
	 * 发展党员工作预审表*/
	
	@RequestMapping(value="review")
	public ResponseEntity<byte[]> Review(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 User user = UserUtils.getUser();

		 Map<String, String> params = Maps.newHashMap();
		 params = getBasic(params, user.getProId());
		 params = getAppSituation(params, user.getProId());
		 params = getReview(params, user.getProId());
		 
		 String url = DocxUtils.getDocxPath(request, "sPmReviewDocx", params);
		 return DocxUtils.fileDownload(new File(url), "发展党员工作预审表.docx", request);
	}
	
	/*
	 * 
	 * 预备党员考察纪实表*/
	
	@RequestMapping(value="pmss")
	public ResponseEntity<byte[]> PmsS(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 User user = UserUtils.getUser();

		 Map<String, String> params = Maps.newHashMap();
		 //params = getBasic(params, user.getProId());
		 //params = getAppSituation(params, user.getProId());
		 
		 String url = DocxUtils.getDocxPath(request, "sPmPmsSDocx", params);
		 return DocxUtils.fileDownload(new File(url), "接收预备党员表决票汇总表.docx", request);
	}
	
	/*
	 * 
	 * 接收预备党员表决票汇总表*/
	
	@RequestMapping(value="probationary")
	public ResponseEntity<byte[]> Probationary(String id,HttpServletRequest request, HttpServletResponse response) throws IOException{
		 //User user = UserUtils.getUser();
		 Map<String, String> params = Maps.newHashMap();
		// params = getBasic(params, user.getProId());
		// params = getAppSituation(params, user.getProId());
		 params.put("id",id);
		 params = getProbationary(params);

		 String url = DocxUtils.getDocxPath(request, "sPmProbationaryDocx", params);
		 return DocxUtils.fileDownload(new File(url), "预备党员考察纪实表.docx", request);
	}
	
	
	
}














