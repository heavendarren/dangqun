package com.thinkgem.jeesite.modules.partyManage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.partyManage.entity.ProcessData;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmContactSettings;
import com.thinkgem.jeesite.modules.partyManage.entity.SPmParthMenber;
import com.thinkgem.jeesite.modules.partyManage.dao.DQRecordDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmContactSettingsDao;
import com.thinkgem.jeesite.modules.partyManage.dao.SPmParthMenberDao;

/**
 * 确定入党介绍人Service
 * @author psy
 * @version 2017-04-25
 */
@Service
@Transactional(readOnly = true)
public class SPmParthMenberService extends CrudService<SPmParthMenberDao, SPmParthMenber> {

	@Autowired
	private DQRecordService dqRecordService;

	@Autowired
	private SPmContactSettingsDao sPmContactSettingsDao;

	@Autowired
	private SPmBasicService sPmBasicService;

	@Autowired
	private SPmContactSettingsService sPmContactSettingsService;


	public SPmParthMenber get(String id) {
		return super.get(id);
	}

	public List<SPmParthMenber> findList(SPmParthMenber sPmParthMenber) {
		return super.findList(sPmParthMenber);
	}

	public Page<SPmParthMenber> findPage(Page<SPmParthMenber> page, SPmParthMenber sPmParthMenber) {
		return super.findPage(page, sPmParthMenber);
	}



	public Page<SPmParthMenber> findPage(Page<SPmParthMenber> page,
			SPmParthMenber sPmParthMenber, String proId) {
		List<SPmParthMenber> sPmParthMenberList1 = new ArrayList<SPmParthMenber>();
		sPmParthMenber.setProId(proId);
		sPmParthMenber.setPage(page);
		if (proId != null) {

			List<SPmParthMenber> sPmParthMenberList2 = new ArrayList<SPmParthMenber>();
			sPmParthMenberList2 = dao.findLists(sPmParthMenber);
			if (sPmParthMenberList2 == null || sPmParthMenberList2.size() == 0) {
				ProcessData processData = sPmBasicService.down(proId, "7");
				SPmContactSettings sPmContactSettings = new SPmContactSettings();
				sPmContactSettings.setProId(processData.getId());
				List<SPmContactSettings> sPmContactSettingsList = sPmContactSettingsDao
						.findLists(sPmContactSettings);

				for (int i = 0; i < sPmContactSettingsList.size(); i++) {
					SPmParthMenber sPmParthMenbers = new SPmParthMenber();
					if (sPmContactSettingsList.get(i) != null) {
						sPmParthMenbers.setContactNum(sPmContactSettingsList
								.get(i).getContactNum());
						sPmParthMenbers.setName(sPmContactSettingsList.get(i)
								.getName());
						sPmParthMenbers.setSex(sPmContactSettingsList.get(i)
								.getSex());
						sPmParthMenbers.setOriginPlace(sPmContactSettingsList
								.get(i).getOriginPlace());
						sPmParthMenbers.setBirthTime(sPmContactSettingsList
								.get(i).getBirthTime());
						sPmParthMenbers.setNation(sPmContactSettingsList.get(i)
								.getNation());
						sPmParthMenbers.setJionTime(sPmContactSettingsList.get(
								i).getJionTime());
						sPmParthMenbers
						.setEducationDegree(sPmContactSettingsList.get(
								i).getEducationDegree());
						sPmParthMenbers.setPartyBranch(sPmContactSettingsList
								.get(i).getPartyBranch());
						sPmParthMenbers.setPostPlace(sPmContactSettingsList
								.get(i).getPostPlace());
						sPmParthMenbers.setPersonResume(sPmContactSettingsList
								.get(i).getPersonResume());
						sPmParthMenbers.setProId(proId);
						// sPmParthMenbers.setContactId(sPmContactSettingsList.get(i).getContactId());
						super.save(sPmParthMenbers);
						sPmParthMenberList1 = dao.findLists(sPmParthMenbers);
					}
				}
			} else {
				sPmParthMenberList1 = dao.findLists(sPmParthMenber);
			}

			if (sPmParthMenberList1.size() == 0) {
				sPmParthMenberList1 = new ArrayList<SPmParthMenber>();
				sPmParthMenberList1.add(sPmParthMenber);
				page.setList(sPmParthMenberList1);
			} else {
				page.setList(sPmParthMenberList1);
			}
		}
		return page;
	}



	@Transactional(readOnly = false)
	public void save(SPmParthMenber sPmParthMenber) {
		super.save(sPmParthMenber);
	}

	public String save(SPmParthMenber sPmParthMenber, String proId) {
		if (proId != null && proId != "") {
			sPmParthMenber.setProId(proId);
			super.save(sPmParthMenber);
			dqRecordService.fillIn(proId);
			return "success";
		}
		return "error proId is not found";
	}

	@Transactional(readOnly = false)
	public void delete(SPmParthMenber sPmParthMenber) {
		super.delete(sPmParthMenber);
	}

}