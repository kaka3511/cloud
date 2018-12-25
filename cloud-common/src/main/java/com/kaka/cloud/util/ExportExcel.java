package com.kaka.cloud.util;

import com.kaka.cloud.annotation.ExcelField;
import org.apache.poi.hssf.usermodel.*;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fuwei
 * @version V1.0
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018/6/11 15:38
 */
public class ExportExcel {

  private static void cteateCell(HSSFWorkbook wb, HSSFRow row, int col,String val,HSSFCellStyle cellStyle) {
    HSSFCell cell = row.createCell(col);
    cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    cell.setCellStyle(cellStyle);
    cell.setCellValue(val);
  }

  /**
   * 初始化
   * @param clazz
   * @return
   */
  public static void initClazzAnnotationNamesAndFieldMap(Class<?> clazz,
                                                         List<String> classFieldNames,
                                                         Map<String,Integer> classFieldColMap,
                                                         Map<String,Integer> classFieldLenMap) {
    Field[] fields = clazz.getDeclaredFields();
    int i = 0;
    for (Field field : fields) {
        ExcelField excelField = field.getAnnotation(ExcelField.class);
        if (excelField != null && excelField.required()) {
          classFieldNames.add(excelField.name());
          classFieldColMap.put(excelField.name(), i++);
          classFieldLenMap.put(excelField.name(), excelField.maxLength());
        }
    }
  }

  public static void doExportExcel(List srcList, Class<?> clazz, OutputStream os) throws Exception{
    // 创建工作薄
    HSSFWorkbook wb = new HSSFWorkbook();
    // 在工作薄上建一张工作表
    HSSFSheet sheet = wb.createSheet();

    //所有属性名称
    List<String> classFieldNames = new ArrayList<>();
    //属性名称对应的column包装map
    Map<String,Integer> classFieldColMap = new HashMap<>();
    //属性名称对应的length包装map
    Map<String,Integer> classFieldLenMap = new HashMap<>();


    //初始化上面三个集合
    initClazzAnnotationNamesAndFieldMap(clazz, classFieldNames, classFieldColMap, classFieldLenMap);

    int rowIndex = 0;
    // Excel首行填充
    HSSFRow rowTitle = sheet.createRow(rowIndex++);

    HSSFCellStyle cellStyle = wb.createCellStyle();

    for (int j = 0; j < classFieldNames.size(); j++) {
      String classFieldName = classFieldNames.get(j);
      sheet.setColumnWidth(j, Integer.parseInt(classFieldLenMap.get(classFieldName).toString()) * 300);
      cteateCell(wb, rowTitle, j, classFieldName,cellStyle);
    }

    //Excel内容填充
    for (Object obj : srcList) {
      HSSFRow rowContent = sheet.createRow(rowIndex++);
      Field[] declaredFields = obj.getClass().getDeclaredFields();
      for (Field field : declaredFields) {
        ExcelField excelColField = field.getAnnotation(ExcelField.class);
        if (excelColField != null && excelColField.required()) {
          String excelColName = excelColField.name();
          //获得该属性对应的column下标
          int colIndex = Integer.parseInt(classFieldColMap.get(excelColName).toString());
          //设置属性可以访问
          field.setAccessible(true);
          if (field.get(obj) != null) {
            cteateCell(wb, rowContent, colIndex, field.get(obj).toString(),cellStyle);
          }
        }
      }
    }

    wb.write(os);
    os.flush();
//    os.close();
  }
}
