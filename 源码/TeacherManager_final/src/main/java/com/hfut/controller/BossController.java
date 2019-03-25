package com.hfut.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.hfut.domain.Boss;
import com.hfut.domain.FeedBack;
import com.hfut.filter.BossLoginFilter;
import com.hfut.service.BossService;
import com.hfut.service.FeedBackService;
import com.hfut.state.FeedBackQueryState;

@SuppressWarnings("serial")
@Controller
@RequestMapping(value="/boss")
public class BossController extends HttpServlet {	
	@Resource
	private FeedBackService feedbackService;
	@Resource
	private BossService bossSvc;
	
	@RequestMapping(value="/BossChangePwdCtrl", method=RequestMethod.POST)
    public String teaChangePwd(Model model, HttpSession session, String userid, String password1 , String password2)  {
		
		System.out.println( userid + "-" + password1 ) ;
		boolean ok = false ;
		
		if(password1.equals(password2)){
			ok = bossSvc.bosschangepwd(userid,password1);
		}else{
			model.addAttribute( "error" , "�������벻һ��" ) ;
			return "boss/personalInfo/changePwd" ;
		}
		
		
		if(ok==false){
			model.addAttribute("error", "�޸�ʧ�ܣ�����ϵ����Ա");
			return "boss/personalInfo/changePwd" ;
		}
		return "boss/personalInfo/changePwdSucc";
	}
	
	//�鿴���з���.
	@RequestMapping(value="/allFeedBackCtrl", method=RequestMethod.GET)
	public String listAll(HttpSession session, String page, Model model) {
		Boss boss = (Boss) session.getAttribute(BossLoginFilter.ATTR_BOSS);
		String recipient = boss.getBossnum();
		
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
		return "boss/feedback/list";
	}
	
	/* 
	 * ��������
	 * date1 ����1
	 * date2 ����2
	 * status ״̬��δ�ظ�/�ѻظ���
	 */
	@RequestMapping(value="/allFeedBackCtrl", method=RequestMethod.POST)
	public String listBy(Model model, HttpSession session, String date1, String date2, String status) {
		Boss boss = (Boss) session.getAttribute(BossLoginFilter.ATTR_BOSS);
		String recipient = boss.getBossnum();
		
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
        return "boss/feedback/list";
    }
	
	/*  
	 * 	�鿴��ϸ����
	 *  fnum �������
	 */
	@RequestMapping(value="/detailFeedBackDetailCtrl", method=RequestMethod.GET)
	public String getOneFeedBack(Model model, String fnum) {
		FeedBack fb = feedbackService.getByFnum(Integer.parseInt(fnum));
		String feedbackStuName = feedbackService.getFeedbackStuName(fb.getStuNum()) ;
		model.addAttribute("feedback", fb);
		model.addAttribute( "feedbackStuName" , feedbackStuName ) ;
		
		return "boss/feedback/info";
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
}
