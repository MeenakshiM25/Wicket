package com.tavavnt.wicket.pullreport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PullThruReportVO implements  Comparable<PullThruReportVO>{
    
	private String id;
	
	private String userId;
	private String userName;
//	private PullThruReportVO parent;
	private List<PullThruReportVO> hierarchicalInfo = new ArrayList<PullThruReportVO>();
	
	public PullThruReportVO(String ident){
		this.id = ident;
		userId = ident + "userId";
		userName = ident + "userName";
	}
	public PullThruReportVO(PullThruReportVO parent ,String ident){
		this(ident);
//		this.parent = parent;
		parent.hierarchicalInfo.add(this);
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
//
//	public PullThruReportVO getParent() {
//		return parent;
//	}
//	public void setParent(PullThruReportVO parent) {
//		this.parent = parent;
//	}
	public List<PullThruReportVO> getHierarchicalInfo() {
		return Collections.unmodifiableList(hierarchicalInfo);
	}

	public void setHierarchicalInfo(List<PullThruReportVO> hierarchicalInfo) {
		this.hierarchicalInfo = hierarchicalInfo;
	}
	@Override
	public int compareTo(PullThruReportVO arg0) {
		return this.id.compareTo(arg0.id);
	}
	@Override
	public String toString() {
		return id;
	}

}
