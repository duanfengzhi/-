package com.hfut.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hfut.domain.ExTeacher;
import com.hfut.service.ExTeacherMngService;



@SuppressWarnings("serial")
@Controller
@RequestMapping(value="/admin")
public class ExTeacherMngController extends HttpServlet {
	
	@Resource
    private ExTeacherMngService exteacherMngService;
	
	
	
	@RequestMapping(value="/AddExTeacherInfoCtrl", method=RequestMethod.POST)	//��Ƹ��ʦ�״ε�¼ʱ����д��Ϣ��������Щ��Ϣд�����ݿ�,isActive�ֶγ�ʼ��Ϊn;
	 public String addTeaInfo(Model model, HttpSession session,String ExTeaName,String ExTeaNo,String ExTeaTel,String ExTeaEmail,String workKind,String workUnit,String exTeaCourse,String exTitle,String exTdegree,String exTgraduate,String jobYear,String major,String exProject,String lisence){
		
		
		Map<String, String> errMap = new HashMap<String, String>();
		if (ExTeaName == null || "".equals(ExTeaName)) {
			errMap.put("ExTeaName","����������");
		}		
		if (ExTeaTel == null || "".equals(ExTeaTel)) {
			errMap.put("ExTeaTel","��������ϵ�绰");
		}		
	
		if (ExTeaEmail == null || "".equals(ExTeaEmail)) {
			errMap.put("ExTeaName","����������");
		}		
	
		if (!(workKind.equals("��ҵ")||workKind.equals("���л���")||workKind.equals("��У"))) {
			errMap.put("workKind","���������ͣ���д'��ҵ','���л���',��'��У'");
		}		
		if (workUnit== null || "".equals(workUnit)) {
			errMap.put("workUnit","�����빤����λ");
		}	
		if (exTeaCourse== null || "".equals(exTeaCourse)) {
			errMap.put("exTeaCourse","��������ڿγ�");
		}
		if (exTitle== null || "".equals(exTitle)) {
			errMap.put("TeaTitle","������ְ��");
		}
		if (exTdegree== null || "".equals(exTdegree)) {
			errMap.put("exTdegree","������ѧλ");
		}
		if (jobYear== null || "".equals(jobYear)) {
			errMap.put("jobYear","�������ҵ����");
		}
		if (major== null || "".equals( major)) {
			errMap.put("major","����д����ר��");
		}
		if (exProject== null || "".equals(exProject)) {
			errMap.put("exProject","����д�е�������Ŀ");
		}
		if (lisence== null || "".equals(lisence)) {
			errMap.put("lisence","����д֤��");
		}
		
		if (errMap.size() != 0) {
			model.addAttribute("exerrMap", errMap);
	        return "exteacher/educationalAdm/AddExTeacherInfo";
		}
		
		int year=Integer.parseInt(jobYear);
		ExTeacher ext=new ExTeacher(ExTeaName,ExTeaNo, ExTeaTel, ExTeaEmail,workKind,workUnit, exTeaCourse, exTitle,year,exTdegree,exTgraduate,major,exProject,lisence);
		try{
			exteacherMngService.updateExTeacher(ext);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exteacher/Login";
	}

	@RequestMapping(value="/ListExTeacherInfoCtrl")	//�����ݿ��еõ���Ƹ��ʦ��Ϣ
	 public String listAllExTeaInfo(Model model, HttpSession session){
		try{
			List<ExTeacher>list=exteacherMngService.getAllExTeacher();
			model.addAttribute("AllExTeacherInfo",list);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "admin/educationalAdm/ListExTeacherInfo";
	}

	
	@RequestMapping(value="/UpdateExTeaInfoCtrl")	//������Ƹ��ʦ��Ϣ
	 public String updateExTeaInfo(Model model, HttpSession session,String tid){
		try{
	
			ExTeacher t=exteacherMngService.getExTeacher(tid);
			model.addAttribute("updateExteacherInfo", t);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "admin/educationalAdm/UpdateExTeacherInfo";
	}
	
	@RequestMapping(value="/UpdateExTeaInfoCtrl", method=RequestMethod.POST)	//������Ƹ��ʦ��Ϣ
	 public String updateExTeaInfo(Model model, HttpSession session ,String exTeaName,String tid,String exTeaTel,String exTeaEmail,String workUnit,
			 String workKind ,String exTitle ,String exTdegree,String jobYear,String exTgraduate ,String  exTeaCourse ,String major,
			 String exProject ,String  lisence ){
		try{
			int age=0;
			if(jobYear!=null){
				age=Integer.parseInt(jobYear);
			}	
			ExTeacher ext=new ExTeacher(exTeaName, tid,exTeaTel,exTeaEmail,workUnit,workKind ,
					exTitle ,exTdegree, age, exTgraduate , exTeaCourse , major, exProject ,lisence);
			
			exteacherMngService.updateExTeacher(ext);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "redirect:/admin/ListExTeacherInfoCtrl";
	}
	
	
	@RequestMapping(value="/DeleteExTeaInfoCtrl")	//ɾ����Ƹ��ʦ��Ϣ
	 public String deleteExTeaInfo(Model model, HttpSession session,String tid){
		try{
	
		exteacherMngService.deleteExTeacher(tid);;
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "redirect:/admin/ListExTeacherInfoCtrl";
	}
	


}
