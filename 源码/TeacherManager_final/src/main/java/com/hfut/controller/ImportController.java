package com.hfut.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.hfut.service.ImportInfo;
import com.hfut.util.ReadExcel;
import com.hfut.util.WDWUtil;


@Controller
@RequestMapping("/admin")
public class ImportController {

    @Resource
    private ImportInfo importservice;
    
    /**
     * �ϴ�Excel,��ȡExcel������
     */
    
    /* ����ѡ��  */
    @RequestMapping(value = "/ImportSCInfoCtrl",method = RequestMethod.POST)
    public String importSCInfo(@RequestParam(value="filename") MultipartFile file,Model model, HttpSession session ,HttpServletRequest request) throws IOException{
        
        String Msg =null;
        
        //�ж��ļ��Ƿ�Ϊ��
        if(file==null){
            Msg ="�ļ���Ϊ�գ�";
            model.addAttribute("msg",Msg);
            return "redirect:/admin/educationalAdm/lessonIn.jsp";
        }
        
        //��ȡ�ļ���
        String name=file.getOriginalFilename();
        
        //��һ���ж��ļ��Ƿ�Ϊ�գ����ж����С�Ƿ�Ϊ0���������Ƿ�Ϊnull����֤�ļ����Ƿ�ϸ�
        long size=file.getSize();
        String exceltype = WDWUtil.validateExcel(name);
        if(name==null || ("").equals(name) && size==0 && exceltype.equals("0")){ //"0"Ϊfalse
            Msg ="�ļ���ʽ����ȷ����ʹ��.xls��.xlsx��׺�ĵ���";
            model.addAttribute("msg",Msg);
            return "redirect:/admin/educationalAdm/lessonIn.jsp";
        }
        
        //����EXCEL
        ReadExcel readExcel=new ReadExcel(exceltype);
        //return ��άArrayList
        
        //����excel����ȡ��Ϣ���ϡ�
        ArrayList<ArrayList<String>> relist = readExcel.getExcelInfo(file);
        
        if(relist != null && !relist.toString().equals("[]") && relist.size()>=1){
        	Iterator<ArrayList<String>> i=relist.iterator();
        	boolean ok = true;
            while(i.hasNext()){
                ok = importservice.importSCInfo(i.next());
            }
            if(ok!=false){
            	Msg ="��������EXCEL�ɹ���";
             	model.addAttribute("msg",Msg);
            }
            else{
            	Msg ="��������EXCELʧ�ܣ�";
                model.addAttribute("msg",Msg);
            }
        }else{
             Msg ="��������EXCELʧ�ܣ�";
             model.addAttribute("msg",Msg);
        } 
       return "redirect:/admin/educationalAdm/lessonIn.jsp";
    }
    /* ����γ���Ϣ  */
    @RequestMapping(value = "/ImportCouInfoCtrl",method = RequestMethod.POST)
    public String importCouInfo(@RequestParam(value="filename") MultipartFile file,Model model, HttpSession session ,HttpServletRequest request) throws IOException{
        
        String Msg =null;
        if(file==null){
            Msg ="�ļ���Ϊ�գ�";
            model.addAttribute("msg",Msg);
            return "redirect:/admin/educationalAdm/lessonIn.jsp";
        }
        String name=file.getOriginalFilename();
        long size=file.getSize();
        String exceltype = WDWUtil.validateExcel(name);
        if(name==null || ("").equals(name) && size==0 && exceltype.equals("0")){ //"0"Ϊfalse
            Msg ="�ļ���ʽ����ȷ����ʹ��.xls��.xlsx��׺�ĵ���";
            model.addAttribute("msg",Msg);
            return "redirect:/admin/educationalAdm/lessonIn.jsp";
        }
        
        ReadExcel readExcel=new ReadExcel(exceltype);
        ArrayList<ArrayList<String>> relist = readExcel.getExcelInfo(file);
        
        if(relist != null && !relist.toString().equals("[]") && relist.size()>=1){
        	Iterator<ArrayList<String>> i=relist.iterator();
        	boolean ok = true;
            while(i.hasNext()){
                ok = importservice.importCouInfo(i.next());
            }
            if(ok!=false){
            	Msg ="��������EXCEL�ɹ���";
             	model.addAttribute("msg",Msg);
            }
            else{
            	Msg ="��������EXCELʧ�ܣ�";
                model.addAttribute("msg",Msg);
            }
        }else{
             Msg ="��������EXCELʧ�ܣ�";
             model.addAttribute("msg",Msg);
        } 
       return "redirect:/admin/educationalAdm/lessonIn.jsp";
    }
    
    
    
    /*����ѧ����Ϣ*/
    @RequestMapping(value = "/ImportStuInfoCtrl",method = RequestMethod.POST)
    public String importStuInfo(@RequestParam(value="filename") MultipartFile file,Model model, HttpSession session ,HttpServletRequest request) throws IOException{
        
        String Msg =null;
        
        //�ж��ļ��Ƿ�Ϊ��
        if(file==null){
            Msg ="�ļ���Ϊ�գ�";
            model.addAttribute("msg",Msg);
            return "redirect:/admin/educationalAdm/studentMng.jsp";
        }
        
        //��ȡ�ļ���
        String name=file.getOriginalFilename();
        
        //��һ���ж��ļ��Ƿ�Ϊ�գ����ж����С�Ƿ�Ϊ0���������Ƿ�Ϊnull����֤�ļ����Ƿ�ϸ�
        long size=file.getSize();
        String exceltype = WDWUtil.validateExcel(name);
        if(name==null || ("").equals(name) && size==0 && exceltype.equals("0")){ //"0"Ϊfalse
            Msg ="�ļ���ʽ����ȷ����ʹ��.xls��.xlsx��׺�ĵ���";
            model.addAttribute("msg",Msg);
            return "redirect:/admin/educationalAdm/studentMng.jsp";
        }
        
        //����EXCEL
        ReadExcel readExcel=new ReadExcel(exceltype);
        //return ��άArrayList
        
        //����excel����ȡ��Ϣ���ϡ�
        ArrayList<ArrayList<String>> relist = readExcel.getExcelInfo(file);
        
        if(relist != null && !relist.toString().equals("[]") && relist.size()>=1){
        	Iterator<ArrayList<String>> i=relist.iterator();
        	boolean ok = true;
            while(i.hasNext()){
                ok = importservice.importStuInfo(i.next());
            }
            if(ok!=false){
            	Msg ="��������EXCEL�ɹ���";
             	model.addAttribute("msg",Msg);
            }
            else{
            	Msg ="��������EXCELʧ�ܣ�";
                model.addAttribute("msg",Msg);
            }
        }else{
             Msg ="��������EXCELʧ�ܣ�";
             model.addAttribute("msg",Msg);
        } 
       return "redirect:/admin/educationalAdm/studentMng.jsp";
    }
    
    
    
    /*����У�ڽ�ʦ��Ϣ*/
    @RequestMapping(value = "/ImportTeaInfoCtrl",method = RequestMethod.POST)
    public String importTeaInfo(@RequestParam(value="filename") MultipartFile file,Model model, HttpSession session ,HttpServletRequest request) throws IOException{
        
        String Msg =null;
        if(file==null){
            Msg ="�ļ���Ϊ�գ�";
            model.addAttribute("msg",Msg);
            return "redirect:/admin/educationalAdm/ListTeacherInfo.jsp";
        }
        
        //��ȡ�ļ���
        String name=file.getOriginalFilename();
        
        //��һ���ж��ļ��Ƿ�Ϊ�գ����ж����С�Ƿ�Ϊ0���������Ƿ�Ϊnull����֤�ļ����Ƿ�ϸ�
        long size=file.getSize();
        String exceltype = WDWUtil.validateExcel(name);
        if(name==null || ("").equals(name) && size==0 && exceltype.equals("0")){ //"0"Ϊfalse
            Msg ="�ļ���ʽ����ȷ����ʹ��.xls��.xlsx��׺�ĵ���";
            model.addAttribute("msg",Msg);
            return "redirect:/admin/educationalAdm/ListTeacherInfo.jsp";
        }
        
        ReadExcel readExcel=new ReadExcel(exceltype);
        
        ArrayList<ArrayList<String>> relist = readExcel.getExcelInfo(file);
        
        if(relist != null && !relist.toString().equals("[]") && relist.size()>=1){
        	Iterator<ArrayList<String>> i=relist.iterator();
        	boolean ok = true;
            while(i.hasNext()){
                ok = importservice.importTeaInfo(i.next());
            }
            if(ok!=false){
            	Msg ="��������EXCEL�ɹ���";
             	model.addAttribute("msg",Msg);
            }
            else{
            	Msg ="��������EXCELʧ�ܣ�";
                model.addAttribute("msg",Msg);
            }
        }else{
             Msg ="��������EXCELʧ�ܣ�";
             model.addAttribute("msg",Msg);
        } 
       return "redirect:/admin/ListTeacherInfoCtrl";
    }
    /*������Ƹ��ʦ������Ϣ*/
    @RequestMapping(value = "/ImportExTeaInfoCtrl",method = RequestMethod.POST)
    public String importExTeaInfo(@RequestParam(value="filename") MultipartFile file,Model model, HttpSession session ,HttpServletRequest request) throws IOException{
        
        String Msg =null;
        if(file==null){
            Msg ="�ļ���Ϊ�գ�";
            model.addAttribute("msg",Msg);
            return "redirect:/admin/educationalAdm/ListExTeacherInfo.jsp";
        }
        
        //��ȡ�ļ���
        String name=file.getOriginalFilename();
        
        //��һ���ж��ļ��Ƿ�Ϊ�գ����ж����С�Ƿ�Ϊ0���������Ƿ�Ϊnull����֤�ļ����Ƿ�ϸ�
        long size=file.getSize();
        String exceltype = WDWUtil.validateExcel(name);
        if(name==null || ("").equals(name) && size==0 && exceltype.equals("0")){ //"0"Ϊfalse
            Msg ="�ļ���ʽ����ȷ����ʹ��.xls��.xlsx��׺�ĵ���";
            model.addAttribute("msg",Msg);
            return "redirect:/admin/educationalAdm/ListExTeacherInfo.jsp";
        }
        
        ReadExcel readExcel=new ReadExcel(exceltype);
        
        ArrayList<ArrayList<String>> relist = readExcel.getExcelInfo(file);
        
        if(relist != null && !relist.toString().equals("[]") && relist.size()>=1){
        	Iterator<ArrayList<String>> i=relist.iterator();
        	boolean ok = true;
            while(i.hasNext()){
                ok = importservice.importExTeaInfo(i.next());
            }
            if(ok!=false){
            	Msg ="��������EXCEL�ɹ���";
             	model.addAttribute("msg",Msg);
            }
            else{
            	Msg ="��������EXCELʧ�ܣ�";
                model.addAttribute("msg",Msg);
            }
        }else{
             Msg ="��������EXCELʧ�ܣ�";
             model.addAttribute("msg",Msg);
        } 
       return "redirect:/admin/ListExTeacherInfoCtrl";
    }
    
    /*������Ƹ��ʦ�α�*/
    @RequestMapping(value = "/ImportExCTInfoCtrl",method = RequestMethod.POST)
    public String importExCTInfo(@RequestParam(value="filename") MultipartFile file,Model model, HttpSession session ,HttpServletRequest request) throws IOException{
        String Msg =null;
        if(file==null){
            Msg ="�ļ���Ϊ�գ�";
            model.addAttribute("msg",Msg);
            return "redirect:/admin/educationalAdm/lessonIn.jsp";
        }
        String name=file.getOriginalFilename();
        long size=file.getSize();
        String exceltype = WDWUtil.validateExcel(name);
        if(name==null || ("").equals(name) && size==0 && exceltype.equals("0")){ //"0"Ϊfalse
            Msg ="�ļ���ʽ����ȷ����ʹ��.xls��.xlsx��׺�ĵ���";
            model.addAttribute("msg",Msg);
            System.out.println("�ļ���ʽ����ȷ����ʹ��.xls��.xlsx��׺�ĵ���");
            return "redirect:/admin/educationalAdm/lessonIn.jsp";
        }
        ReadExcel readExcel=new ReadExcel(exceltype);
        ArrayList<ArrayList<String>> relist = readExcel.getExcelInfo(file);
        if(relist != null && !relist.toString().equals("[]") && relist.size()>=1){
        	Iterator<ArrayList<String>> i=relist.iterator();
        	boolean ok = true;
            while(i.hasNext()){
                ok = importservice.importExCTInfo(i.next());
            }
            if(ok!=false){
            	Msg ="��������EXCEL�ɹ���";
             	model.addAttribute("msg",Msg);
            }
            else{
            	Msg ="��������EXCELʧ�ܣ�";
                model.addAttribute("msg",Msg);
            }
        }else{
             Msg ="��������EXCELʧ�ܣ�";
             model.addAttribute("msg",Msg);
        } 
       return "redirect:/admin/educationalAdm/lessonIn.jsp";
    }
    
    /*�������Ա�˺�*/
    @RequestMapping(value = "/ImportMngInfoCtrl",method = RequestMethod.POST)
    public String importMngInfo(@RequestParam(value="filename") MultipartFile file,Model model, HttpSession session ,HttpServletRequest request) throws IOException{
        String Msg =null;
        if(file==null){
            Msg ="�ļ���Ϊ�գ�";
            model.addAttribute("msg",Msg);
            return "admin/educationalAdm/addnew";
        }
        String name=file.getOriginalFilename();
        long size=file.getSize();
        String exceltype = WDWUtil.validateExcel(name);
        if(name==null || ("").equals(name) && size==0 && exceltype.equals("0")){ //"0"Ϊfalse
            Msg ="�ļ���ʽ����ȷ����ʹ��.xls��.xlsx��׺�ĵ���";
            model.addAttribute("msg",Msg);
            System.out.println("�ļ���ʽ����ȷ����ʹ��.xls��.xlsx��׺�ĵ���");
            return "admin/educationalAdm/addnew";
        }
        ReadExcel readExcel=new ReadExcel(exceltype);
        ArrayList<ArrayList<String>> relist = readExcel.getExcelInfo(file);
        if(relist != null && !relist.toString().equals("[]") && relist.size()>=1){
        	Iterator<ArrayList<String>> i=relist.iterator();
        	boolean ok = true;
            while(i.hasNext()){
                ok = importservice.importMngInfo(i.next());
            }
            if(ok!=false){
            	Msg ="��������EXCEL�ɹ���";
             	model.addAttribute("msg",Msg);
            }
            else{
            	Msg ="��������EXCELʧ�ܣ�";
                model.addAttribute("msg",Msg);
            }
        }else{
             Msg ="��������EXCELʧ�ܣ�";
             model.addAttribute("msg",Msg);
        } 
       return "admin/educationalAdm/addnew";
    }
    
    /*�����쵼�˺�*/
    @RequestMapping(value = "/ImportBossInfoCtrl",method = RequestMethod.POST)
    public String importBossInfo(@RequestParam(value="filename") MultipartFile file,Model model, HttpSession session ,HttpServletRequest request) throws IOException{
        String Msg =null;
        if(file==null){
            Msg ="�ļ���Ϊ�գ�";
            model.addAttribute("msg2",Msg);
            return "admin/educationalAdm/addnew";
        }
        String name=file.getOriginalFilename();
        long size=file.getSize();
        String exceltype = WDWUtil.validateExcel(name);
        if(name==null || ("").equals(name) && size==0 && exceltype.equals("0")){ //"0"Ϊfalse
            Msg ="�ļ���ʽ����ȷ����ʹ��.xls��.xlsx��׺�ĵ���";
            model.addAttribute("msg2",Msg);
            System.out.println("�ļ���ʽ����ȷ����ʹ��.xls��.xlsx��׺�ĵ���");
            return "admin/educationalAdm/addnew";
        }
        ReadExcel readExcel=new ReadExcel(exceltype);
        ArrayList<ArrayList<String>> relist = readExcel.getExcelInfo(file);
        if(relist != null && !relist.toString().equals("[]") && relist.size()>=1){
        	Iterator<ArrayList<String>> i=relist.iterator();
        	boolean ok = true;
            while(i.hasNext()){
                ok = importservice.importBossInfo(i.next());
            }
            if(ok!=false){
            	Msg ="��������EXCEL�ɹ���";
             	model.addAttribute("msg2",Msg);
            }
            else{
            	Msg ="��������EXCELʧ�ܣ�";
                model.addAttribute("msg2",Msg);
            }
        }else{
             Msg ="��������EXCELʧ�ܣ�";
             model.addAttribute("msg2",Msg);
        } 
       return "admin/educationalAdm/addnew";
    }
}
