package com.itcast.utils;

import com.itcast.domain.Account;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ZxExportExcel {

    /**
     * 生成Excel
     */
    public void zxExprotExcelXLSX(String[] all, List<Account> list, String path) {
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("0");
        for (int i = 0; i < 9; i++) {
            sheet.setColumnWidth(i, 4300);
        }

        /**
         * 单元格 样式
         */
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setTopBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        cellStyle.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        cellStyle.setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        cellStyle.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 上下居中

        cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());

        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        /*
        * cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setTopBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());*/
        /**
         * 标题样式 样式
         */
        XSSFFont titleFont = wb.createFont();
        titleFont.setFontHeight(24);
        titleFont.setBold(true);
        CellStyle titleCellStyle = wb.createCellStyle();
        titleCellStyle.setBorderTop(BorderStyle.THIN);
        titleCellStyle.setBorderBottom(BorderStyle.THIN);
        titleCellStyle.setBorderLeft(BorderStyle.THIN);
        titleCellStyle.setBorderRight(BorderStyle.THIN);
        titleCellStyle.setTopBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        titleCellStyle.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        titleCellStyle.setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        titleCellStyle.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 上下居中// 上下居中
        titleCellStyle.setFont(titleFont);

        /**
         * 主 标题 在这里插入主标题
         */
        Row titleRow;
        Cell titleCell;
        sheet.addMergedRegion(new CellRangeAddress((short) 0, (short) 2, (short) 0, (short) 8));
        for (int i = 0; i <= 2; i++) {
            titleRow = sheet.createRow(i);
            for (int j = 0; j < 9; j++) {
                titleCell = titleRow.createCell(j);
                titleCell.setCellType(CellType.STRING);
                titleCell.setCellStyle(titleCellStyle);
                titleCell.setCellValue("JAVA天下第一");//文件标题
            }
        }

        /**
         * 列 标题 在这里插入标题
         */
        Row rowLabel;
        Cell cellLabel;
        /*for (int i = 3; i < 4; i++) {
            rowLabel = sheet.createRow(i);
            for (int j = 0; j < 9; j++) {
                cellLabel = rowLabel.createCell(j);
                cellLabel.setCellType(CellType.STRING);
                cellLabel.setCellStyle(cellStyle);
                cellLabel.setCellValue("测试标题列【" + (j + 1) + "】");
            }
        }*/
//        String[] all = {"ID","姓名","余额"};
        for (int i = 3; i < 4; i++) {
            rowLabel = sheet.createRow(i);
            for (int j = 0; j < all.length; j++) {
                cellLabel = rowLabel.createCell(j);
                cellLabel.setCellType(CellType.STRING);
                cellLabel.setCellStyle(cellStyle);
                cellLabel.setCellValue(all[j]);
            }
        }


        /**
         * 列 数据 在这里插入数据
         */
        Row rowCheck;
        Cell cellCheck;

/*
        for (int i = 3; i < 100; i++) {
            rowCheck = sheet.createRow((i + 1));
            for (int j = 0; j < 3; j++) {
                cellCheck = rowCheck.createCell(j);
                cellCheck.setCellType(CellType.STRING);
                cellCheck.setCellStyle(cellStyle);
                cellCheck.setCellValue("测试 - 0" + (i - 2));
            }


        }*/

        for (int i = 0; i < list.size(); i++) {
            rowCheck = sheet.createRow((i + 4));


            cellCheck = rowCheck.createCell(0);
            cellCheck.setCellType(CellType.STRING);
            cellCheck.setCellStyle(cellStyle);
            cellCheck.setCellValue(list.get(i).getAid());

            cellCheck = rowCheck.createCell(1);
            cellCheck.setCellType(CellType.STRING);
            cellCheck.setCellStyle(cellStyle);
            cellCheck.setCellValue(list.get(i).getNumber());

            cellCheck = rowCheck.createCell(2);
            cellCheck.setCellType(CellType.STRING);
            cellCheck.setCellStyle(cellStyle);
            cellCheck.setCellValue(String.valueOf(list.get(i).getBalance()));

        }

        /**
         * 页脚
         */
        setExcelFooterName("测试", 0, wb);

        /**
         * 进行导出
         */
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = format.format(date);
        exportOutPutExcel(path, wb);

    }

    /**
     * 设置Excel页脚
     */
    public void setExcelFooterName(String customExcelFooterName, int setExcelFooterNumber, XSSFWorkbook wb) {
        wb.setSheetName(setExcelFooterNumber, customExcelFooterName);
    }

    /**
     * 输出流 导出Excel到指定路径
     */
    public void exportOutPutExcel(String exportPositionPath, XSSFWorkbook wb) {
        try {
            File file = new File(exportPositionPath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            wb.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
//        new ZxExportExcel().zxExprotExcelXLSX();
    }

}
