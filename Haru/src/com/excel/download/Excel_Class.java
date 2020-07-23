package com.excel.download;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.classinfo.dao.ClassInfoDao;
import com.classinfo.dto.ClassInfoDto;
import com.classmember.dao.ClassMemberDao;
import com.member.dto.MemberDto;
import com.oreilly.servlet.multipart.ExceededSizeException;

public class Excel_Class {
	private String dir = "";
	private int classpk;
	public Excel_Class(String dir) {this.dir = dir;}
	public Excel_Class(String dir,int classpk)
		{this(dir);this.classpk=classpk;}
	public Excel_Class $_() {return this;}
	public String getDir() {return this.dir;}
	public int resetURL() {
		ClassInfoDao dao = new ClassInfoDao();
		ClassInfoDto dto = dao.selectOne(classpk);
		String url = dto.getCellurl();
		if(url==null) {return 1;}
		if(url.isEmpty()) {return 1;}
		File file = new File(dir,url);
		if(file.exists()) {
			try {
				file.delete();
			}catch(Exception e) {}
		}
		return dao.resetExcelURL(classpk);
	}
	public int newCellThread() {
		int res = 0;
			try {
				new Thread(new Runnable() {
					@Override
					public void run(){
						new Excel_Class(dir,classpk).newCell();
					}
				}).start();
				res++;
			}catch(Exception e) {}
		return res;
	}
	public void newCell() {
		String fileName = "EXCEL_CLASS_"+classpk+"_F";
		int cnt = (int)(Math.random()*1000)+1000;
		fileName+=cnt+".xlsx";
		System.out.println("dir : "+dir);
		System.out.println("classpk : "+classpk);
		System.out.println("fileName : "+fileName);
		File file = new File(dir,fileName);
		ClassInfoDao infodao = new ClassInfoDao();
		ClassInfoDto info = infodao.selectOne(classpk);
		FileOutputStream fout = null;
		try {
			System.out.println("EXCEL 준비");
			fout = new FileOutputStream(file);
			SXSSFWorkbook wb = new SXSSFWorkbook(10);
			Sheet sheet1 = wb.createSheet("Member");
//			Row row[] = new Row[10];
//			Cell[][] cell = new Cell[row.length][];
//			Row row1 = sheet1.createRow(0);
//			Cell ce1 = row1.createCell(0);
			ClassMemberDao dao = new ClassMemberDao();
			List<MemberDto> list = dao.selectStudent(classpk);
			Row classtitleRow = sheet1.createRow(1);
			Cell classtitleCell = classtitleRow.createCell(1);
			classtitleCell.setCellValue("개설 클래스 이름 : "+info.getTitle());
			Row classstudentRow = sheet1.createRow(2);
			Cell classstudentCell = classstudentRow.createCell(1);
			classstudentCell.setCellValue("참여수 : "+list.size()+"명 / "+info.getAllstudent()+"명");
			sheet1.addMergedRegion(new CellRangeAddress(1,1,1,5));
			sheet1.addMergedRegion(new CellRangeAddress(2,2,1,5));//s행,e행,s열,e열
			int rowcnt=3,cellcnt=0;
			String table_title[] = {"이름","연락처","성별","닉네임"};
			String table_content[] = {};
			Row coltitle = sheet1.createRow(rowcnt++);
			cellcnt=0;
			for (String str : table_title) {
				Cell cellcell = coltitle.createCell(++cellcnt);
				cellcell.setCellValue(str);
			}
			for (MemberDto obj : list) {
				cellcnt=1;
				table_content = new String[] {
					obj.getName(), obj.getPhone(), obj.getGender(), obj.getNickname()
				};
				Row rowrow = sheet1.createRow(rowcnt++);
				for (String str : table_content) {
					Cell cellcell = rowrow.createCell(cellcnt);
					cellcell.setCellValue(str);
					cellcnt++;
				}
			}
			
			for (int i=0;i<5;i++) {
				sheet1.autoSizeColumn(i);
				sheet1.setColumnWidth(i, (sheet1.getColumnWidth(i))+1000 );
			}
			wb.write(fout);
			close(fout);
			wb.dispose();
			System.out.println("EXCEL 생성");
			//
			new ClassInfoDao().setExcelURL(classpk,fileName);
		}catch(Exception e) {}
	}
	public void close(FileOutputStream fout) {
		try {fout.close();}catch(Exception e) {}
	}
}
