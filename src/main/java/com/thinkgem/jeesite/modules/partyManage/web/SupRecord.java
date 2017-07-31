package com.thinkgem.jeesite.modules.partyManage.web;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.modules.partyManage.entity.*;
import com.thinkgem.jeesite.modules.partyManage.service.*;
import com.thinkgem.jeesite.modules.partyManage.utils.DocxUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 补录及一些公共方法.
 *
 * @author zhc
 * @version 2017-04-27
 */
@Controller
@RequestMapping(value = "${adminPath}/partyManage/public")
public class SupRecord {

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

  /*
    * 下载图片
    * */
  @RequestMapping(value = "downfile")
  public ResponseEntity<byte[]> downFile(String fileUrl, HttpServletRequest request, HttpServletResponse response) throws IOException {
    if (fileUrl != null && fileUrl != "") {
      String url = request.getSession().getServletContext().getRealPath("/") + "uploadfile" + File.separator;
      url = url + fileUrl;
      String fileName = fileUrl.substring(fileUrl.lastIndexOf(File.separator));
      return DocxUtils.fileDownload(new File(url), fileName, request);
    }
//    return DocxUtils.fileDownload(new File(""), "", request);
    return null;
  }

  /**
   * 拟接收预备党员的公示
   */
  @RequestMapping(value = "preppublic")
  public ResponseEntity<byte[]> prepPublic(HttpServletRequest request, HttpServletResponse response) throws IOException {
    User user = UserUtils.getUser();
    Date date = new Date();

    Map<String, String> params = Maps.newHashMap();
    params = getBasic(params, user.getProId());
    params = getAppSituation(params, user.getProId());

    params = getTermTrain(params, user.getProId());
    params = getMinutess(params, user.getProId());
    Calendar beginCalendar = Calendar.getInstance();
    beginCalendar.setTime(date);
    Calendar endCalendar = Calendar.getInstance();
    endCalendar.setTime(date);
    endCalendar.add(endCalendar.DATE, 7);
    params.put("beginDateYear", String.valueOf(beginCalendar.get(beginCalendar.YEAR)));
    params.put("beginDateMonth", String.valueOf(beginCalendar.get(beginCalendar.MONTH) + 1));
    params.put("beginDateDay", String.valueOf(beginCalendar.get(beginCalendar.DAY_OF_MONTH)));
    params.put("endDateYear", String.valueOf(endCalendar.get(endCalendar.YEAR)));
    params.put("endDateMonth", String.valueOf(endCalendar.get(endCalendar.MONTH) + 1));
    params.put("endDateDay", String.valueOf(endCalendar.get(endCalendar.DAY_OF_MONTH)));
    params.put("partyName", user.getOffice().getName());
    String url = DocxUtils.getDocxPath(request, "sPmPrepPartyPublicDocx", params);
    //DocxUtils.fileDownload(url, "关于拟接收" + params.get("personName") + "同志为中共预备党员的公示.docx", response);
    return DocxUtils.fileDownload(new File(url), "关于拟接收" + params.get("personName") + "同志为中共预备党员的公示.docx", request);
  }

  /**
   * 拟接收预备党员公示情况表
   */
  @RequestMapping(value = "prepsituation")
  public ResponseEntity<byte[]> prepSituation(HttpServletRequest request, HttpServletResponse response) throws IOException {
    User user = UserUtils.getUser();

    Map<String, String> params = Maps.newHashMap();
    params = getBasic(params, user.getProId());
    params = getAppSituation(params, user.getProId());

    params = getPrepPartyPublic(params, user.getProId());

    String url = DocxUtils.getDocxPath(request, "sPmPrepPartyPublicSituationDocx", params);
    return DocxUtils.fileDownload(new File(url), "接收预备党员公示情况表.docx", request);
  }

  /*
  * 预备党员备案表
  * */
  @RequestMapping(value = "preprecord")
  public ResponseEntity<byte[]> prepRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
    User user = UserUtils.getUser();

//    ProcessData processData = sPmBasicService.down(/*proId*/user.getProId(), "1");
    Map<String, String> params = Maps.newHashMap();
    params = getBasic(params, user.getProId());
    params = getAppSituation(params, user.getProId());

    //TODO
    /*支部大会讨论时间*/
    params.put("discussTime", "");
    /*党委审批时间*/
    params.put("examineTime", "");
    String url = DocxUtils.getDocxPath(request, "sPmPrepPartyRecordDocx", params);
    return DocxUtils.fileDownload(new File(url), "预备党员备案表.docx", request);
  }

  /*
  * 发展对象短期集中培训考核鉴定表
  * */
  @RequestMapping(value = "termtrain")
  public ResponseEntity<byte[]> termTrain(HttpServletRequest request, HttpServletResponse response) throws IOException {
    User user = UserUtils.getUser();

    Map<String, String> params = Maps.newHashMap();
    params = getBasic(params, user.getProId());
    params = getAppSituation(params, user.getProId());

    String url = DocxUtils.getDocxPath(request, "sPmTermTrainDocx", params);
    return DocxUtils.fileDownload(new File(url), "发展对象短期集中培训考核鉴定表.docx", request);
  }

  /*
    * 发展对象短期集中培训考核鉴定表
    * */
  @RequestMapping(value = "contactsetting")
  public ResponseEntity<byte[]> contactSetting(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    User user = UserUtils.getUser();

    Map<String, String> params = Maps.newHashMap();
    params = getContactSetting(params, id);
    params.put("partyName", user.getOffice().getName());

    String url = DocxUtils.getDocxPath(request, "sPmContactSettingDocx", params);
    return DocxUtils.fileDownload(new File(url), "培养联系人登记表.docx", request);
  }

  //确定入党介绍人
  @RequestMapping(value = "parthMenber")
  public ResponseEntity<byte[]> parthMenber(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    User user = UserUtils.getUser();

    Map<String, String> params = Maps.newHashMap();
    params = getParthMenber(params, id);
    params.put("partyName", user.getOffice().getName());

    String url = DocxUtils.getDocxPath(request, "sPmParthMenberDocx", params);
    return DocxUtils.fileDownload(new File(url), "入党介绍人登记表.docx", request);
  }

  //入党介绍人和党小组意见表下载
  @RequestMapping(value = "IntroduceOpinion")
  public ResponseEntity<byte[]> IntroduceOpinion(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    User user = UserUtils.getUser();

    Map<String, String> params = Maps.newHashMap();
    params = getBasic(params, user.getProId());

    String url = DocxUtils.getDocxPath(request, "sPmIntroduceOpinionDocx", params);
    return DocxUtils.fileDownload(new File(url), "入党介绍人和党小组意见表.docx", request);
  }

  //征求党员意见会议纪要
  @RequestMapping(value = "MeetingMinutes")
  public ResponseEntity<byte[]> MeetingMinutes(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    User user = UserUtils.getUser();
    Map<String, String> params = Maps.newHashMap();
    //params = getBasic(params, user.getProId());
    String url = DocxUtils.getDocxPath(request, "sPmMeetingMinutesDocx", params);
    return DocxUtils.fileDownload(new File(url), "征求党员意见会议纪要表.docx", request);
  }

  //征求群众意见会议纪要
  @RequestMapping(value = "MassOpinion")
  public ResponseEntity<byte[]> MassOpinion(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    User user = UserUtils.getUser();

    Map<String, String> params = Maps.newHashMap();
    //params = getBasic(params, user.getProId());

    String url = DocxUtils.getDocxPath(request, "sPmMassOpinionDocx", params);
    return DocxUtils.fileDownload(new File(url), "征求群众意见会议纪要表.docx", request);
  }

  //按期转为中共正式党员的报告
  @RequestMapping(value = "FormalMember")
  public ResponseEntity<byte[]> FormalMember(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    User user = UserUtils.getUser();

    Map<String, String> params = Maps.newHashMap();
    //params = getBasic(params, user.getProId());

    String url = DocxUtils.getDocxPath(request, "sPmFormalMemberDocx", params);
    return DocxUtils.fileDownload(new File(url), "按期转为中共正式党员报告.docx", request);
  }

  //转正工作预审表
  @RequestMapping(value = "PositivePre")
  public ResponseEntity<byte[]> PositivePre(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    User user = UserUtils.getUser();

    Map<String, String> params = Maps.newHashMap();
    params = getBasic(params, user.getProId());
    params = getAppSituation(params, user.getProId());

    String url = DocxUtils.getDocxPath(request, "sPmPositivePreDocx", params);
    return DocxUtils.fileDownload(new File(url), "转正工作预审表.docx", request);
  }

  //转正审查审核表
  @RequestMapping(value = "AuditChecklist")
  public ResponseEntity<byte[]> AuditChecklist(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    User user = UserUtils.getUser();

    Map<String, String> params = Maps.newHashMap();
    params = getBasic(params, user.getProId());
    params = getAppSituation(params, user.getProId());

    //TODO
    /*支部大会讨论时间*/
    params.put("discussTime", "");
    /*党委审批时间*/
    params.put("examineTime", "");

    String url = DocxUtils.getDocxPath(request, "sPmAuditChecklistDocx", params);
    return DocxUtils.fileDownload(new File(url), "转正审查审核表.docx", request);
  }
}
