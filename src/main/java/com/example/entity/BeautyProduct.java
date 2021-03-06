package com.example.entity;

import java.util.List;

public class BeautyProduct {

	private String name;                //产品名称
	private float price;               //产品价格
	private Integer CommunityRating;   //社区评分
	private Integer ExpertRating;      //专家评分
	private String EXPERTREVIEWS;      //专家评语
	private String COMMUNITYREVIEWS;   //社会评论
	private String CLAIMS;            //要求
	private String BRANDOVERVIEW;     //品牌概况
	private List<String> INGREDIENTS;   //成分
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getEXPERTREVIEWS() {
		return EXPERTREVIEWS;
	}
	public void setEXPERTREVIEWS(String eXPERTREVIEWS) {
		EXPERTREVIEWS = eXPERTREVIEWS;
	}
	public String getCOMMUNITYREVIEWS() {
		return COMMUNITYREVIEWS;
	}
	public void setCOMMUNITYREVIEWS(String cOMMUNITYREVIEWS) {
		COMMUNITYREVIEWS = cOMMUNITYREVIEWS;
	}
	public String getCLAIMS() {
		return CLAIMS;
	}
	public void setCLAIMS(String cLAIMS) {
		CLAIMS = cLAIMS;
	}
	public String getBRANDOVERVIEW() {
		return BRANDOVERVIEW;
	}
	public void setBRANDOVERVIEW(String bRANDOVERVIEW) {
		BRANDOVERVIEW = bRANDOVERVIEW;
	}
	public BeautyProduct() {
		super();
	}
	public Integer getCommunityRating() {
		return CommunityRating;
	}
	public void setCommunityRating(Integer communityRating) {
		CommunityRating = communityRating;
	}
	public Integer getExpertRating() {
		return ExpertRating;
	}
	public void setExpertRating(Integer expertRating) {
		ExpertRating = expertRating;
	}
	public List<String> getINGREDIENTS() {
		return INGREDIENTS;
	}
	public void setINGREDIENTS(List<String> iNGREDIENTS) {
		INGREDIENTS = iNGREDIENTS;
	}
	public BeautyProduct(String name, float price, Integer communityRating,
			Integer expertRating, String eXPERTREVIEWS,
			String cOMMUNITYREVIEWS, String cLAIMS, String bRANDOVERVIEW,
			List<String> iNGREDIENTS) {
		super();
		this.name = name;
		this.price = price;
		CommunityRating = communityRating;
		ExpertRating = expertRating;
		EXPERTREVIEWS = eXPERTREVIEWS;
		COMMUNITYREVIEWS = cOMMUNITYREVIEWS;
		CLAIMS = cLAIMS;
		BRANDOVERVIEW = bRANDOVERVIEW;
		INGREDIENTS = iNGREDIENTS;
	}
	
}
