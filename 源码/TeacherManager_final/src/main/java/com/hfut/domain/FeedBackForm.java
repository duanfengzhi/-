package com.hfut.domain;

public class FeedBackForm {
	private int type;            //��������.
	private String claName;		//�γ�����.
	private String teaName;		//��ʦ����.
	private String info;		//������Ϣ.
	private int state;          //�Ƿ�����.��0��������1����������
	private String status = "δ���";       //���״̬��δ���/δ�ظ�/�ѻظ���
	private String picture;			
	
	public FeedBackForm() {}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public String getClaName() {
		return claName;
	}
	
	public void setClaName(String claName) {
		this.claName = claName;
	}
	
	public String getTeaName() {
		return teaName;
	}
	
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	
	public String toString(){
		String s = null ;
		
		s = "type-" + type + ",state-" + state + ",status-" + status;
		
		return s ;
	}

}
