package ru.indraft.service.labor;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CellStyleService {

    private XSSFWorkbook workbook;
    private FontService fontService;

    public CellStyleService(XSSFWorkbook workbook) {
        this.workbook = workbook;
        this.fontService = new FontService(workbook);
    }

    private XSSFCellStyle titleCellStyle;

    public XSSFCellStyle getTitleCellStyle() {
        if (titleCellStyle == null) {
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setFont(fontService.getTitleFont());
            cellStyle.setWrapText(true);
            addBorder(cellStyle);
            titleCellStyle = cellStyle;
        }
        return titleCellStyle;
    }

    private XSSFCellStyle createTaskRowCellStyle(HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(horizontalAlignment);
        cellStyle.setVerticalAlignment(verticalAlignment);
        cellStyle.setFont(fontService.getDefaultFont());
        cellStyle.setWrapText(true);
        addBorder(cellStyle);
        return cellStyle;
    }

    private XSSFCellStyle taskNameCellStyle;

    public XSSFCellStyle getTaskNameCellStyle() {
        if (taskNameCellStyle == null) {
            taskNameCellStyle = createTaskRowCellStyle(HorizontalAlignment.LEFT, VerticalAlignment.TOP);
        }
        return taskNameCellStyle;
    }

    private XSSFCellStyle taskTermCellStyle;

    public XSSFCellStyle getTaskTermCellStyle() {
        if (taskTermCellStyle == null) {
            taskTermCellStyle = createTaskRowCellStyle(HorizontalAlignment.LEFT, VerticalAlignment.CENTER);
        }
        return taskTermCellStyle;
    }

    private XSSFCellStyle projectNameCellStyle;

    public XSSFCellStyle getProjectNameCellStyle() {
        if (projectNameCellStyle == null) {
            projectNameCellStyle = createTaskRowCellStyle(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
        }
        return projectNameCellStyle;
    }

    private XSSFCellStyle workTimeCellStyle;

    public XSSFCellStyle getWorkTimeCellStyle() {
        if (workTimeCellStyle == null) {
            workTimeCellStyle = createTaskRowCellStyle(HorizontalAlignment.RIGHT, VerticalAlignment.CENTER);
        }
        return workTimeCellStyle;
    }

    private XSSFCellStyle overTimeCellStyle;

    public XSSFCellStyle getOverTimeCellStyle() {
        if (overTimeCellStyle == null) {
            overTimeCellStyle = createTaskRowCellStyle(HorizontalAlignment.RIGHT, VerticalAlignment.CENTER);
        }
        return overTimeCellStyle;
    }

    private void addBorder(XSSFCellStyle cellStyle) {
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
    }

}
