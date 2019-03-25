package com.hfut.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hfut.domain.ExTeacher;
import com.hfut.domain.Teacher;
import com.hfut.service.TeacherMngService;
import com.hfut.state.TeacherQueryState;




@SuppressWarnings("serial")
@Controller
@RequestMapping(value="/admin")
public class TeacherMngController extends HttpServlet {
	
	@Resource
    private TeacherMngService teacherMngService;
	
	@RequestMapping(value="/AddTeacherInfoCtrl", method=RequestMethod.POST)	//��ʦ�״ε�¼ʱ����д��Ϣ��������Щ��Ϣд�����ݿ�,isActive�ֶγ�ʼ��Ϊn;
	 public String addTeaInfo(Model model, HttpSession session,String  TeaName,String TeaNo, String TeaKind,
	 String TeaBelong, String TeaCourse,String TeaTitle,String Teadegree,String TeaAge , String TeaSex , String Teagraduate,String OverSeaExp){
		
		
		Map<String, String> errMap = new HashMap<String, String>();
		if (TeaName == null || "".equals(TeaName)) {
			errMap.put("TeaName","����������");
		}		
		if (TeaNo == null || "".equals(TeaNo)) {
			errMap.put("TeaNo","�����빤��");
		}		
		if (!(TeaKind.equals("Ժ�ڽ�ʦ")||TeaKind.equals("У�ڽ�ʦ"))) {
			errMap.put("TeaKind","���������ͣ���д'Ժ�ڽ�ʦ'��'У�ڽ�ʦ'");
		}		
		if (TeaBelong == null || "".equals(TeaBelong)) {
			errMap.put("TeaBelong","����������ѧԺ");
		}	
		if (TeaCourse== null || "".equals(TeaCourse)) {
			errMap.put("TeaCourse","��������ڿγ�");
		}
		if (TeaTitle== null || "".equals(TeaTitle)) {
			errMap.put("TeaTitle","������ְ��");
		}
		if (Teadegree== null || "".equals(Teadegree)) {
			errMap.put("Teadegree","������ѧλ");
		}
		if (TeaAge== null || "".equals(TeaAge)) {
			errMap.put("TeaAge","����������");
		}
		if (TeaSex== null || "".equals(TeaSex)) {
			errMap.put("TeaSex","����д�Ա�");
		}
		if (Teagraduate== null || "".equals(Teagraduate)) {
			errMap.put("Teagraduate","����д��ҵѧУ");
		}
		if (OverSeaExp== null || "".equals(OverSeaExp)) {
			errMap.put("OverSeaExp","����д���⾭��");
		}
		
		if (errMap.size() != 0) {
			model.addAttribute("errMap", errMap);
	        return "teacher/AddTeacherInfo";
		}
		
		int age=Integer.parseInt(TeaAge);
		Teacher t=new Teacher(TeaName,TeaNo,TeaKind,TeaBelong,TeaCourse,TeaTitle,Teadegree,age,TeaSex,Teagraduate,OverSeaExp);
		try{
			teacherMngService.updateActiveTeacher(t);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "teacher/Login";
	}

	@RequestMapping(value="/ListActiveTeaInfoCtrl")	//�����ݿ��еõ�����˵Ľ�ʦlist����ʾ��CheckTeacher.jsp
	 public String listActiveTeaInfo(Model model, HttpSession session){
		try{
			List<Teacher>list=teacherMngService.getActiveTeacher();
			model.addAttribute("TeacherInfo",list);
			System.out.println(list.size()+"===========");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "admin/educationalAdm/CheckTeacher";
	}
	
	@RequestMapping(value="/ListTeacherInfoCtrl", method=RequestMethod.GET)	//�����ݿ��еõ�����У�ڽ�ʦ��Ϣ
	 public String listAllTeaInfo(Model model, HttpSession session, String page){
		TeacherQueryState state = null;
		if (page == null) {
			page = "0";
			session.removeAttribute("TeacherQueryState");
			state = new TeacherQueryState();
		} else {
			state = (TeacherQueryState)
					session.getAttribute("TeacherQueryState");
			if (state == null) {
				state = new TeacherQueryState();
			}
		  }
		List<Teacher>list=null;
		try{
			int lastPage = teacherMngService.getLastPage(state);
			state.setLastPage(lastPage);
			list =teacherMngService.getAllTeacherByPage(state, page);
			session.setAttribute("TeacherQueryState", state);
			model.addAttribute("lastPage", lastPage);
			model.addAttribute("AllTeacherInfo",list);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "admin/educationalAdm/ListTeacherInfo";
	}
	

	@RequestMapping(value="/ListTeacherInfoCtrl", method=RequestMethod.POST)
	public String listBy(Model model, HttpSession session,String TeaName,String TeaBelong) {
		session.removeAttribute("TeacherQueryState");
		List<Teacher> list=null;
		TeacherQueryState state= new TeacherQueryState(0,TeaName,TeaBelong);
		
		if((TeaName==null||"".equals(TeaName))&&((TeaBelong==null)||"".equals(TeaBelong))){
			Map<String, String> errMap = new HashMap<String, String>();
			errMap.put("condition", "��ѯ��������Ϊ��");
			model.addAttribute("conErrMap", errMap);
		}
		
		if(TeaName!=null&&!"".equals(TeaName))
			model.addAttribute("TeaName", TeaName);
		
		if(TeaBelong!=null&&!"".equals(TeaBelong))
			model.addAttribute("TeaBelong", TeaBelong);
		try {
				int lastPage =teacherMngService.getLastPage(state);
				state.setLastPage(lastPage);
				list = teacherMngService.getAllTeacher(state);
				session.setAttribute("TeacherQueryState", state);
				model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
		  }
		model.addAttribute("AllTeacherInfo", list); 
		return "admin/educationalAdm/ListTeacherInfo";
	}

	@RequestMapping(value="/ListTeaInfoCtrl")		//�鿴��ϸ��Ϣ
	 public String listTeaInfo(Model model, HttpSession session,String tid){
		Teacher t=teacherMngService.getTeacher(tid);
		model.addAttribute("teacherInfo", t);

		return "admin/educationalAdm/ListTeaDetailInfo";
	}
	
	
	@RequestMapping(value="/CheckTeacherCtrl")	
	 public String checkTeaInfo(Model model, HttpSession session,String tid){
		Teacher t=teacherMngService.getTeacher(tid);
		model.addAttribute("teacher", t);

		return "admin/educationalAdm/CheckActive";
	}
	

	
	@RequestMapping(value="/PassCtrl")	//���ͨ��
	 public String pass(Model model, HttpSession session,String tid){
		try{
			teacherMngService.updatePassTeacher(tid);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return "redirect:/admin/ListActiveTeaInfoCtrl";
	}
	
	@RequestMapping(value="/NoPassCtrl")	//��˲�ͨ��
	 public String noPass(Model model, HttpSession session,String tid){
		try{
			
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return "redirect:/admin/ListActiveTeaInfoCtrl";
	}
	
	@RequestMapping(value="/UpdateTeaInfoCtrl")	//���Ľ�ʦ��Ϣ
	 public String updateTeaInfo(Model model, HttpSession session,String tid){
		try{
	
			Teacher t=teacherMngService.getTeacher(tid);
			model.addAttribute("updateteacherInfo", t);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("updateTeaInfoCtrl-out");
		
		return "admin/educationalAdm/UpdateTeacherInfo";
	}

	
	@RequestMapping(value="/UpdateTeaInfoCtrl", method=RequestMethod.POST)	//���Ľ�ʦ��Ϣ
	 public String updateTeaInfo(Model model, HttpSession session ,String  teaName,String  tid, String teaTel,
			 String  teaEmail, String  teaKind,String teaBelong,String  teaCourse,String  teaTitle , String teadegree , String  teaAge,String teaSex ,String teagraduate,String overSeaExp){
		try{
			int age=0;
			if(teaAge!=null){
				age=Integer.parseInt(teaAge);
			}	
			Teacher t=new Teacher(teaName,tid,teaTel,teaEmail,teaKind,teaBelong,teaCourse,teaTitle , teadegree , age, teaSex ,teagraduate, overSeaExp);
			teacherMngService.updateTeacher(t);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "redirect:/admin/ListTeacherInfoCtrl";
	}
	
	@RequestMapping(value="/DeleteTeaInfoCtrl")	//ɾ����ʦ��Ϣ
	 public String deleteTeaInfo(Model model, HttpSession session,String tid){
		try{
	
			teacherMngService.deleteTeacher(tid);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "redirect:/admin/ListTeacherInfoCtrl";
	}

	
	

}
