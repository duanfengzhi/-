package com.hfut.util;


import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hfut.domain.ExTeacher;
import com.hfut.domain.SCentity;
import com.hfut.domain.Teacher;

import java.io.FileOutputStream;

public class FromDbToExcel{
	
	public static void fromDbToExcelA(ArrayList<Object> relist){
		
        HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("��ʦͳ��");  
        HSSFRow row = sheet.createRow((int) 0);  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("���");  
        cell = row.createCell((short) 1);  
        cell.setCellValue("����");  
        cell = row.createCell((short) 2);  
        cell.setCellValue("��λ��ѧԺ");  
        cell = row.createCell((short) 3);  
        cell.setCellValue("���ڿγ�"); 
        cell = row.createCell((short) 4); 
        cell.setCellValue("ְ��");  
        cell = row.createCell((short) 5);  
        cell.setCellValue("ѧλ");  
        cell = row.createCell((short) 6);  
        cell.setCellValue("����");  
        cell = row.createCell((short) 7);  
        cell.setCellValue("��ҵԺУ"); 
        cell = row.createCell((short) 8);  
        cell.setCellValue("���⾭��");  
        cell = row.createCell((short) 9);  
        cell.setCellValue("���̱���");  
        cell = row.createCell((short) 10);  
        cell.setCellValue("������Ŀ");  
        cell = row.createCell((short) 11);  
        cell.setCellValue("�����������");  
        cell = row.createCell((short) 12);  
        cell.setCellValue("ר��");  
        cell = row.createCell((short) 13);
        cell.setCellValue("����");  
        cell = row.createCell((short) 14);
        cell.setCellValue("ר��");  
        cell = row.createCell((short) 15);
        cell.setCellValue("��������");  
        cell = row.createCell((short) 16);
        cell.setCellValue("���н�ѧ��");  
        cell = row.createCell((short) 17);
        cell.setCellValue("ʦ��ʦ������");  
        cell = row.createCell((short) 18);
        cell.setCellValue("�����ѧԺ���������гɹ�");  
        

        for (int i = 0; i < relist.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            Teacher tea = (Teacher) relist.get(i);  
            row.createCell((short) 0).setCellValue(tea.getTeaNo());  
            row.createCell((short) 1).setCellValue(tea.getTeaName());
            row.createCell((short) 2).setCellValue(tea.getTeaBelong());
            row.createCell((short) 3).setCellValue(tea.getTeaCourse());
            row.createCell((short) 4).setCellValue(tea.getTeaTitle());
            row.createCell((short) 5).setCellValue(tea.getTeadegree());
            row.createCell((short) 6).setCellValue(tea.getTeaAge());
            row.createCell((short) 7).setCellValue(tea.getTeagraduate());
            row.createCell((short) 8).setCellValue(tea.getOverSeaExp());
            row.createCell((short) 9).setCellValue(tea.getEngBack());
            row.createCell((short) 10).setCellValue(tea.getProject());
            row.createCell((short) 11).setCellValue(tea.getPaper());
            row.createCell((short) 12).setCellValue(tea.getPatent());
            row.createCell((short) 13).setCellValue(tea.getTheSoft());
            row.createCell((short) 14).setCellValue(tea.getMono());
            row.createCell((short) 15).setCellValue(tea.getTeaPaper());
            row.createCell((short) 16).setCellValue(tea.getTeahonor());
            row.createCell((short) 17).setCellValue(tea.getTeaMorality());
            row.createCell((short) 18).setCellValue(tea.getCoorResult());
            
        }  
         
        try  
        {  
            FileOutputStream fout = new FileOutputStream("D:\\fileupload\\teachercheck.xls");  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }
	}
	
	
public static void fromDbToExcelB(ArrayList<Object> relist){
		
        HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("��ʦͳ��");  
        HSSFRow row = sheet.createRow((int) 0);  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("����");  
        cell = row.createCell((short) 1);  
        cell.setCellValue("��λ��ѧԺ");  
        cell = row.createCell((short) 2);  
        cell.setCellValue("����"); 
        cell = row.createCell((short) 3); 
        cell.setCellValue("ְλ");  
        cell = row.createCell((short) 4);  
        cell.setCellValue("ѧλ");  
        cell = row.createCell((short) 5);  
        cell.setCellValue("��ҵ����");  
        cell = row.createCell((short) 6);  
        cell.setCellValue("��ҵѧУ"); 
        cell = row.createCell((short) 7);  
        cell.setCellValue("���ڿγ�");
        cell = row.createCell((short) 8);
        cell.setCellValue("��Ҫ����ר��");  
        cell = row.createCell((short) 9);
        cell.setCellValue("�е����Ĺ�����Ŀ");  
        cell = row.createCell((short) 10);
        cell.setCellValue("���ʵ����������֤��");  
        

        for (int i = 0; i < relist.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            ExTeacher tea = (ExTeacher) relist.get(i);  
            row.createCell((short) 0).setCellValue(tea.getExTeaName());
            row.createCell((short) 1).setCellValue(tea.getWorkUnit());
            row.createCell((short) 2).setCellValue(tea.getWorkKind());
            row.createCell((short) 3).setCellValue(tea.getExTitle());
            row.createCell((short) 4).setCellValue(tea.getExTdegree());
            row.createCell((short) 5).setCellValue(tea.getJobYear());
            row.createCell((short) 6).setCellValue(tea.getExTgraduate());
            row.createCell((short) 7).setCellValue(tea.getExTeaCourse());
            row.createCell((short) 8).setCellValue(tea.getMajor());
            row.createCell((short) 9).setCellValue(tea.getExProject());
            row.createCell((short) 10).setCellValue(tea.getLisence());
            
        }  
         
        try  
        {  
            FileOutputStream fout = new FileOutputStream("D:\\fileupload\\exteachercheck.xls");  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }
	}


	public static void fromDbToExcelStu(ArrayList<Object> relist){
		
        HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("ѧ������");  
        HSSFRow row = sheet.createRow((int) 0);  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("ѧ��");  
        cell = row.createCell((short) 1);  
        cell.setCellValue("����");  
        
        

        for (int i = 0; i < relist.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            SCentity sc = (SCentity) relist.get(i);  
            row.createCell((short) 0).setCellValue(sc.getStuNo());
            row.createCell((short) 1).setCellValue(sc.getStuName());
        }  
         
        try  
        {  
            FileOutputStream fout = new FileOutputStream("D:\\fileupload\\stulist.xls");  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }
	}
}
