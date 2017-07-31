package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

public class CodeTree extends TreeEntity<CodeTree> {

	private static final long serialVersionUID = 1L;
	private String code;
	private String type;
	private String canSelect;
	private String leaf;

	public CodeTree() {
		super();
	}

	public CodeTree(String id) {
		super(id);
	}

	public CodeTree getParent() {
		return parent;
	}

	public void setParent(CodeTree parent) {
		this.parent = parent;
	}

	@Length(min = 1, max = 10)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min = 0, max = 100)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getCanSelect() {
		return canSelect;
	}

	public void setCanSelect(String canSelect) {
		this.canSelect = canSelect;
	}

	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	
	
}