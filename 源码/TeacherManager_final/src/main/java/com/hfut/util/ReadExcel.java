package com.hfut.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**

 * �������ȡExcel��������
 */
public class ReadExcel {
	//excel����
	private String exceltype = "0";
    //������
    private int totalRows = 0;  
    //������
    private int totalCells = 0; 
    //������Ϣ������
    private String errorMsg;
    //���췽��
    public ReadExcel(String exceltype){
    	this.exceltype = exceltype;
    }
    //��ȡ������
    public int getTotalRows()  { return totalRows;} 
    //��ȡ������
    public int getTotalCells() {  return totalCells;} 
    //��ȡ������Ϣ
    public String getErrorInfo() { return errorMsg; }
    
  /**
   * ��EXCEL�ļ�
   */
  public ArrayList<ArrayList<String>> getExcelInfo(MultipartFile Mfile){
      
      //��spring�ļ��ϴ���MultipartFileת����CommonsMultipartFile����
       CommonsMultipartFile cf= (CommonsMultipartFile)Mfile; //��ȡ���ش洢·��
       File file = new  File("D:\\fileupload");
       //����һ��Ŀ¼ 
       if (!file.exists()) file.mkdirs();
       //�½�һ���ļ�
       File file1 = null;
       if(exceltype.equals("xls")){
    	   file1 = new File("D:\\fileupload\\" + Mfile.getOriginalFilename() + new Date().getTime() + ".xls"); 
       }
       if(exceltype.equals("xlsx")){
    	   file1 = new File("D:\\fileupload\\" + Mfile.getOriginalFilename() + new Date().getTime() + ".xlsx"); 
       }
       //���ϴ����ļ�д���½����ļ���
       try {
           cf.getFileItem().write(file1);
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       
       ArrayList<ArrayList<String>> relist=new ArrayList<ArrayList<String>>();
       //��ʼ��������
       FileInputStream is = null;
       Workbook wb = null;
       try{
          //�����½����ļ�ʵ����������
          is = new FileInputStream(file1);
          //����excel��������ݶ�ȡ�ͻ���Ϣ
          
          //��excel��2003ʱ
          if(exceltype.equals("xls"))
          wb = new HSSFWorkbook(is);
          //��excel��2007ʱ
          if(exceltype.equals("xlsx"))
          wb = new XSSFWorkbook(is);
          
          //��ȡExcel����ͻ�����Ϣ
          relist=readExcelValue(wb);
          is.close();
      }catch(Exception e){
          e.printStackTrace();
      } finally{
          if(is !=null)
          {
              try{
                  is.close();
              }catch(IOException e){
                  is = null;    
                  e.printStackTrace();  
              }
          }
      }
      return relist;
  }
 
  /**
   * ��ȡExcel����ͻ�����Ϣ
   * @param wb
   * @return
   */
  private ArrayList<ArrayList<String>> readExcelValue(Workbook wb){ 
      //�õ���һ��shell  
       Sheet sheet=wb.getSheetAt(0);
       
      //�õ�Excel������
       this.totalRows=sheet.getPhysicalNumberOfRows();
       
      //�õ�Excel������(ǰ����������)
       if(totalRows>=1 && sheet.getRow(0) != null){//�ж���������һ�����ҵ�һ�б����б��⣨������bug���ļ���һ��ûֵ�����ˣ�
            this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
       }else{
           return null;
       }
       
       ArrayList<ArrayList<String>> relist=new ArrayList<ArrayList<String>>();//����һ�����󼯺�
       
       
      
       
      //ѭ��Excel����,�ӵڶ��п�ʼ�����ⲻ���
       for(int r=1;r<totalRows;r++){
           Row row = sheet.getRow(r);
           if (row == null) continue;
           ArrayList<String> list = new ArrayList<String>();
           //ѭ��Excel����
           for(int c = 0; c <this.totalCells; c++){ 
               Cell cell = row.getCell(c);
               if (null != cell){
                   list.add(getValue(cell));
                   //�ö�άlist����
               }
           }
           //��Ӷ��󵽼�����
           relist.add(list);
       }
       return relist;
  }
  

  @SuppressWarnings({ "static-access", "unused" })
  private String getValue(Cell cell) {
      if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
          // ���ز������͵�ֵ
          return String.valueOf(cell.getBooleanCellValue());
      } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
          // ������ֵ���͵�ֵ
          return String.valueOf(cell.getNumericCellValue());
      } else {
          // �����ַ������͵�ֵ
          return String.valueOf(cell.getStringCellValue());
      }
  }

}
