package com.hfut.controller;

import java.sql.Timestamp;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hfut.domain.*;
import com.hfut.filter.*;
import com.hfut.service.*;
import com.hfut.state.FeedBackQueryState;

@SuppressWarnings("serial")
@Controller
@RequestMapping(value="/teacher")
public class TeacherController extends HttpServlet {
	@Resource 
    private TeacherService teacherSvc;
	
	@Resource
	private FeedBackService feedbackService;
    
	@RequestMapping(value="/TeaChangePwdCtrl", method=RequestMethod.POST)
    public String teaChangePwd(Model model, HttpSession session, String userid, String password1 , String password2)  {
		
		System.out.println( userid + "-" + password1 ) ;
		boolean ok = false ;
		
		if(password1.equals(password2)){
			ok = teacherSvc.teachangepwd(userid,password1);
		}else{
			model.addAttribute( "error" , "�������벻һ��" ) ;
			return "teacher/personalInfo/changePwd" ;
		}
		
		
		if(ok==false){
			model.addAttribute("error", "�޸�ʧ�ܣ�����ϵ����Ա");
			return "teacher/personalInfo/changePwd" ;
		}
		return "teacher/personalInfo/changePwdSucc";
	}
	
	@RequestMapping(value="/finalcheckCtrl", method=RequestMethod.POST)
    public String finalcheck(Model model, HttpSession session, String engBack, String project,
    		String paper,String patent,String theSoft,String mono,String teaPaper,String teahonor,String teaMorality
    		,String coorResult)  {
		
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		teacher.setEngBack(engBack);
		teacher.setCoorResult(coorResult);
		
		teacher.setMono(mono);
		teacher.setPatent(patent);
		teacher.setPaper(teaPaper);
		teacher.setTeahonor(teahonor);
		teacher.setProject(project);
		teacher.setTeaPaper(teaPaper);
		teacher.setTeaMorality(teaMorality);
		teacher.setTheSoft(theSoft);
		
		boolean ok = teacherSvc.finalcheckUpdate(teacher);
		if(ok==false){
			model.addAttribute("finalcheck", "��д���� ����ϵ����Ա");
			return "redirect:/teacher/personalInfo/finalcheck.jsp";
		}
		model.addAttribute("finalcheck", "��д���");
		return "redirect:/teacher/personalInfo/finalcheck.jsp";
	}
	
	@RequestMapping(value="/changeTeaInfoByselfCtrl", method=RequestMethod.POST)
    public String changeTeaInfoByself(Model model, HttpSession session, String teatel, String teaemail,
    		String teaBelong,String teatitle,String teadegree,String teaCourse,String teagraduate,String overSeaExp ,String teakind,String teaage)  {
		
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		teacher.setTeaTel(teatel);
		teacher.setTeaEmail(teaemail);
		teacher.setTeaBelong(teaBelong);
		teacher.setTeaTitle(teatitle);
		teacher.setTeaAge(Integer.parseInt(teaage));
		teacher.setTeaKind(teakind);
		teacher.setTdegree(teadegree);
		teacher.setTeaCourse(teaCourse);
		teacher.setTgraduate(teagraduate);
		teacher.setOverSeaExp(overSeaExp);
		
		boolean ok = teacherSvc.UpdateTeaSelfInfo(teacher);
		if(ok==false){
			model.addAttribute("updateselfinfo", "��д���� ����ϵ����Ա");
			return "redirect:/teacher/personalInfo/change.jsp";
		}
		return "redirect:/teacher/personalInfo/show.jsp";
	}

	//�鿴���з���.
	@RequestMapping(value="/allFeedBackCtrl", method=RequestMethod.GET)
	public String listAll(HttpSession session, String page, Model model) {
		Teacher teacher = (Teacher) session.getAttribute(TeacherLoginFilter.ATTR_TEACHER);
		String recipient = teacher.getTeaNo();
		
		FeedBackQueryState state = null;
		if (page == null) {
			page = "0";
			session.removeAttribute("FeedBackQueryState");
			state = new FeedBackQueryState();
		} else {
			state = (FeedBackQueryState)
					session.getAttribute("FeedBackQueryState");
			if (state == null) {
				state = new FeedBackQueryState();
			}
		}	
		
		List<FeedBack> list = null;
		try {
			int lastPage = feedbackService.getLastPageByRecipient(state, recipient);
			state.setLastPage(lastPage);
			
			list = feedbackService.getFeedBackByPageAndRecipient(state, page, recipient);
			session.setAttribute("FeedBackQueryState", state);
			model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<FeedBack>();
			Map<String, String> errMap = new HashMap<String, String>();
			errMap.put("GLOBAL", "������Ԥ�ڴ�������ϵ����Ա");
			model.addAttribute("errMap", errMap);
		}

		model.addAttribute("feedbacks", list); 
		return "teacher/feedback/list";
	}
	
	/* 
	 * ��������
	 * date1 ����1
	 * date2 ����2
	 * status ״̬��δ�ظ�/�ѻظ���
	 */
	@RequestMapping(value="/allFeedBackCtrl", method=RequestMethod.POST)
	public String listBy(Model model, HttpSession session, String date1, String date2, String status) {
		Teacher teacher = (Teacher) session.getAttribute(TeacherLoginFilter.ATTR_TEACHER);
		String recipient = teacher.getTeaNo();
		
    	java.sql.Timestamp startDate = null;
    	java.sql.Timestamp endDate = null;
    	
    	if(date1 != null && !"".equals(date1)){
    		date1 += " 00:00:00"; 
    		startDate = new java.sql.Timestamp(System.currentTimeMillis());
    		startDate = Timestamp.valueOf(date1);
    	}
    	
    	if(date2 != null && !"".equals(date2)){
    		date2 += " 00:00:00"; 
    		endDate = new java.sql.Timestamp(System.currentTimeMillis());
    		endDate = Timestamp.valueOf(date2);
    	}
    	
		session.removeAttribute("FeedBackQueryState");
		
		FeedBackQueryState state = new FeedBackQueryState(0, startDate, endDate, status);
        List<FeedBack> list = null;
        try {
            int lastPage = feedbackService.getLastPageByRecipient(state, recipient);
			state.setLastPage(lastPage);
			
			list = feedbackService.getFeedBackByRecipient(state, recipient);
			session.setAttribute("FeedBackQueryState", state);
            model.addAttribute("feedbacks",list);
        } catch (Exception e) {
        	e.printStackTrace();
			list = new ArrayList<FeedBack>();
			Map<String, String> errMap = new HashMap<String, String>();
			errMap.put("GLOBAL", "������Ԥ�ڴ�������ϵ����Ա");
			model.addAttribute("errMap", errMap);
        }
        model.addAttribute("feedbacks",list);
        return "teacher/feedback/list";
    }
	
	/*  
	 * �鿴��ϸ����
	 *  fnum �������
	 */
	@RequestMapping(value="/detailFeedBackDetailCtrl", method=RequestMethod.GET)
	public String getOneFeedBack(Model model, String fnum) {
		
		System.out.println("teacherFeedbackInfo-in");
		
		
		
		FeedBack fb = feedbackService.getByFnum(Integer.parseInt(fnum));
		String feedbackStuName = feedbackService.getFeedbackStuName(fb.getStuNum()) ;
		model.addAttribute("feedback", fb);
		model.addAttribute( "feedbackStuName" , feedbackStuName ) ;
		
		System.out.println("teacherFeedbackInfo-out");
		
		return "teacher/feedback/info";
	}
	
	/* 
	 * �ظ�����
	 * reply �ظ�����
	 * fnum �������
	 * �жϷ���״̬��δ�ظ�����ʾ�ظ���ť
	 */
	@RequestMapping(value="/replyCtrl", method=RequestMethod.POST)
	public String sendReply(HttpSession session, Model model, String reply, String fnum) {
		FeedBack fb = feedbackService.getByFnum(Integer.parseInt(fnum));
		fb.setReply(reply);
		fb.setStatus("�ѻظ�");
		feedbackService.setReply(fb);
		model.addAttribute("feedback", fb);
		return "redirect:allFeedBackCtrl";
	}
	
	//�鿴�γ���Ϣ.
	@RequestMapping(value="/TeaCourseInfoCtrl", method=RequestMethod.GET)
	public String getCourseInfo(Model model, HttpSession session){
		Teacher teacher = (Teacher) session.getAttribute(TeacherLoginFilter.ATTR_TEACHER);
		String teano = teacher.getTeaNo();
		
		List<CourseInfo> list = null;
        try {	
			list = teacherSvc.getCourseInfo(teano);
            model.addAttribute("courseInfos",list);
        } catch (Exception e) {
        	e.printStackTrace();
			list = new ArrayList<CourseInfo>();
			Map<String, String> errMap = new HashMap<String, String>();
			errMap.put("GLOBAL", "������Ԥ�ڴ�������ϵ����Ա");
			model.addAttribute("errMap", errMap);
        }
        model.addAttribute("courseInfos",list);
		return "teacher/course/info";
	}
	
	//�鿴�γ�ѧ����Ϣ.
	@RequestMapping(value="/TeaCourseStuCtrl", method=RequestMethod.GET)
	public String getCourseStudent(Model model, String cno) {
		List<SCentity> list = null;
        try {	
			list = teacherSvc.getCourseStudent(cno);
            model.addAttribute("courseStudents",list);
        } catch (Exception e) {
        	e.printStackTrace();
			list = new ArrayList<SCentity>();
			Map<String, String> errMap = new HashMap<String, String>();
			errMap.put("GLOBAL", "������Ԥ�ڴ�������ϵ����Ա");
			model.addAttribute("errMap", errMap);
        }
        model.addAttribute("courseStudents",list);
		return "";
	}
}
