package com.hfut.domain;

public class FeedBack {
	private int fnum;		
	private String stuNum;		//����ѧ��ѧ��.
	private int type;            //��������.
	private String claName;		//�γ�����.
	private String teaName;		//��ʦ����.
	private String info;		//������Ϣ.
	private int state;          //�Ƿ�����.��0��������1����������
	private java.sql.Timestamp sendTime;    //�ύʱ��.
	private String status = "δ���";       //���״̬��δ���/δ�ظ�/�ѻظ���
	private String picture;			
	private String recipient;       //������.
	private String reply;           //�ظ�.
	
	public FeedBack() {}
	
	public int getFnum() {
		return fnum;
	}
	
	public void setFnum(int fnum) {
		this.fnum = fnum;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getStuNum() {
		return stuNum;
	}
	
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
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
	
	public java.sql.Timestamp getSendTime() {
		return sendTime;
	}
	
	public void setSendTime(java.sql.Timestamp sendTime) {
		this.sendTime = sendTime;
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
	
	public String getRecipient() {
		return recipient;
	}
	
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	public String getReply() {
		return reply;
	}
	
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	public String toString(){
		String s = null ;
		
		s = "FNum-" + fnum + ",StuName-" + stuNum + ",type-" + type + ",state-" + state + ",status-" + status + ",reply-" + reply ;
		
		return s ;
	}
}
