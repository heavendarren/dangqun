package com.thinkgem.jeesite.modules.partyManage.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class SPmRecordS extends DataEntity<SPmRecordS> {

	private static final long serialVersionUID = 1L;
	private String nodeNo;
	private String nodeType;
	public String getNodeNo() {
		return nodeNo;
	}
	public void setNodeNo(String nodeNo) {
		this.nodeNo = nodeNo;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
}
