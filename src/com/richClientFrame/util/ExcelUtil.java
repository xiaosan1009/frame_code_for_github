package com.richClientFrame.util;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.excel.CellBean;
import com.richClientFrame.data.excel.RowBean;
import com.richClientFrame.data.excel.SheetBean;
import com.richClientFrame.data.param.RequestParam.Cell;
import com.richClientFrame.data.param.RequestParam.CellBorder;
import com.richClientFrame.data.param.RequestParam.CellDataFormat;
import com.richClientFrame.data.param.RequestParam.CellFont;
import com.richClientFrame.data.param.RequestParam.CellGround;
import com.richClientFrame.data.param.RequestParam.CellLayout;
import com.richClientFrame.data.param.RequestParam.CellStyle;
import com.richClientFrame.data.param.RequestParam.Excel;
import com.richClientFrame.data.param.RequestParam.Row;
import com.richClientFrame.data.response.ResponseExcel;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.info.ControlRequestMap;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: ExcelUtil 
* @Description: 操作excel对象类
* @author king
* @since Oct 8, 2012 9:32:55 AM 
*  
*/
public final class ExcelUtil {
    
    /**
     * 构造函数
     */
    private ExcelUtil() {
    }
    
    private static org.apache.poi.ss.usermodel.CellStyle cellStyleItem;
    
    private static CreationHelper helper;
    
    private static CellDataFormat format;
    
    /** 
    * @Description: 读取excel文件内容
    * @author king
    * @since Oct 8, 2012 9:33:47 AM 
    * 
    * @param filename 文件名
    * @param type 文件类型
    * @return excel文件内容对象
    * @throws RichClientWebException RichClientWebException
    */
    public static Workbook readExcelFile(String filename, String type) throws RichClientWebException {
        try {
            if (ConstantsUtil.Excel.FILE_TYPE_XLS.equals(type)) {
                return new HSSFWorkbook(new FileInputStream(filename));
            } else if (ConstantsUtil.Excel.FILE_TYPE_XLSX.equals(type)) {
                return new XSSFWorkbook(new FileInputStream(filename));
            }
            return new XSSFWorkbook(new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_FILE_NOT_FOUND, e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR);
        }
    }
    
    /** 
    * @Description: 读取excel文件内容【流的形式】
    * @author king
    * @since Oct 8, 2012 9:34:37 AM 
    * 
    * @param inputStream 文件流
    * @param type 文件类型
    * @return excel文件内容对象
    * @throws RichClientWebException RichClientWebException
    */
    public static Workbook readExcelFile(InputStream inputStream, String type) 
        throws RichClientWebException {
        try {
            if (ConstantsUtil.Excel.FILE_TYPE_XLS.equals(type)) {
                return new HSSFWorkbook(inputStream);
            } else if (ConstantsUtil.Excel.FILE_TYPE_XLSX.equals(type)) {
                return new XSSFWorkbook(inputStream);
            }
            return new XSSFWorkbook(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_FILE_NOT_FOUND, e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
        }
    }
    
    /**
     * 根据<excel>标签设置的excel文件类型创建相应版本的excel对象.
     * @param fileType excel版本类型 [FILE_TYPE_XLS对应2007之前的版本；FILE_TYPE_XLSX对应2007及以后版本]
     * @param type 生成文件的模式类型
     * @return 相应版本的excel对象
     * @throws RichClientWebException RichClientWebException
     */
    public static Workbook createExcelFile(String fileType, String type) throws RichClientWebException {
        if (ConstantsUtil.Excel.FILE_TYPE_XLS.equals(fileType)) {
            return new HSSFWorkbook();
        } else if (ConstantsUtil.Excel.FILE_TYPE_XLSX.equals(fileType)) {
            if ("stream".equals(type) || "write".equals(type)) {
                return new SXSSFWorkbook();
            }
            return new XSSFWorkbook();
        }
        return new XSSFWorkbook();
    }
    
    /**
     * 判断是否设置了excel路径或者读取了excel文件流.
     * @param excelItem 对应<excel>标签中的信息对象
     * @param inputStream excel文件流
     * @return 是否设置了excel路径或者读取了excel文件流
     */
    public static boolean hasExcelFile(Excel excelItem, InputStream inputStream) {
        final String inPath = excelItem.getInPath();
        final String inName = excelItem.getInName();
        return (CommonUtil.isNotEmpty(inPath) && CommonUtil.isNotEmpty(inName)) || inputStream != null;
    }

    /**
     * 生成excel文件的方法.
     * 
     * @param excelItem 对应<excel>标签中的信息对象
     * @param type 类型
     * @param outWb excel对象
     * @return excel生成信息
     * @throws RichClientWebException RichClientWebException
     */
    public static ResponseExcel createExcelFile(
            final Excel excelItem, 
            String type,
            final Workbook outWb) throws RichClientWebException {
        if (ConstantsUtil.Excel.TYPE_STREAM.equals(type)) {
            final ResponseExcel resExcel = new ResponseExcel();
            resExcel.setWorkbook(outWb);
            String title = "excel";
            final String outName = excelItem.getOutName();
            if (CommonUtil.isNotEmpty(outName)) {
                title = outName;
            }
            title += ConstantsUtil.Str.DOT + excelItem.getFileType();
            resExcel.setTitle(title);
            return resExcel;
        } else {
            String outPath = excelItem.getOutPath();
            final String outName = excelItem.getOutName();
            if (CommonUtil.isEmpty(outPath) || CommonUtil.isEmpty(outName)) {
                throw new RichClientWebException(EngineExceptionEnum.FM_EXCEL_XML_SET_WRITE_MUST_SET_OUT);
            }
            if (!excelItem.isOutAbsolute()) {
                outPath = ControlConfig.getRealPath() + outPath;
            }
            final String outFileName = outPath + File.separator + outName 
                + ConstantsUtil.Str.DOT + excelItem.getFileType();
            FileOutputStream stream = null;
            try {
                stream = new FileOutputStream(outFileName);
                outWb.write(stream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RichClientWebException(EngineExceptionEnum.FM_COM_FILE_NOT_FOUND, e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
            } finally {
                try {
                    if (stream != null) {
                        stream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
                }
            }
        }
        return null;
    }
    
    /**
     * 生成excel文件的方法.
     * 
     * @param excelItem 对应<excel>标签中的信息对象
     * @param type 类型
     * @param fileList excel文件数组
     * @return excel生成信息
     * @throws RichClientWebException RichClientWebException
     */
    public static ResponseExcel createExcelFile(
            final Excel excelItem, 
            String type,
            final List<File> fileList) throws RichClientWebException {
        if (ConstantsUtil.Excel.TYPE_STREAM.equals(type)) {
            final ResponseExcel resExcel = new ResponseExcel();
            resExcel.setFileList(fileList);
            String title = "excel";
            final String outName = excelItem.getOutName();
            if (CommonUtil.isNotEmpty(outName)) {
                title = outName;
            }
            title += ConstantsUtil.Str.DOT + "zip";
            resExcel.setTitle(title);
            return resExcel;
        } else {
            String outPath = excelItem.getOutPath();
            final String outName = excelItem.getOutName();
            if (CommonUtil.isEmpty(outPath) || CommonUtil.isEmpty(outName)) {
                throw new RichClientWebException(EngineExceptionEnum.FM_EXCEL_XML_SET_WRITE_MUST_SET_OUT);
            }
            if (!excelItem.isOutAbsolute()) {
                outPath = ControlConfig.getRealPath() + outPath;
            }
            final String outFileName = outPath + File.separator + outName 
                + ConstantsUtil.Str.DOT + "zip";
            FileUtil.makeFilesToZipForCreate(fileList.toArray(new File[0]), outFileName);
        }
        return null;
    }

    /**
     * 创建excel容器对象.
     * @param excelItem 根据<excel>标签中的数据生成的excel内容对象
     * @return excel容器对象
     * @throws RichClientWebException RichClientWebException
     */
    public static Workbook createExcelFile(final Excel excelItem) throws RichClientWebException {
        return createExcelFile(excelItem, 0);
    }
    
    /**
     * 创建excel容器对象.
     * @param excelItem 根据<excel>标签中的数据生成的excel内容对象
     * @param index 序号
     * @return excel容器对象
     * @throws RichClientWebException RichClientWebException
     */
    public static Workbook createExcelFile(final Excel excelItem, int index) throws RichClientWebException {
        
        // <excel>标签设置的excel文件类型
        final String fileType = excelItem.getFileType();
        
        final String type = excelItem.getType();
        
        // 根据<excel>标签设置的excel文件类型创建相应版本的excel对象
        Workbook outWb = ExcelUtil.createExcelFile(fileType, type);
        
        String inPath = excelItem.getInPath();
        String inName = excelItem.getInName();
        if (index != 0) {
            inName += ConstantsUtil.Str.UNDERLINE + index;
        }
        
        // 判断是否设置了excel路径或者读取了excel文件流, 如果是，那么通过读取的excel文件创建模板
        if (hasExcelFile(excelItem, null)) {
            if (!excelItem.isInAbsolute()) {
                inPath = ControlConfig.getRealPath() + inPath;
            }
            final String inFileName = inPath + File.separator + inName 
                + ConstantsUtil.Str.DOT + excelItem.getFileType();
            outWb = ExcelUtil.readExcelFile(inFileName, fileType);
        }
        return outWb;
    }
    
    

    /** 
    * @Description: 计算excel表格行数
    * @author king
    * @since Oct 8, 2012 9:30:05 AM 
    * 
    * @param row 行对象
    * @param k 序列
    * @return excel表格行数
    */
    public static int countRowIndex(final Row row, int k) {
        int rowSite = 0;
        final int startRow = row.getStart();
        final int endRow = row.getEnd();
        if (startRow != -1 && endRow != -1) {
            final int cellSize = row.getCells().size();
            rowSite = row.getSite() + (cellSize * k / (endRow - startRow + 1));
        } else {
            rowSite = row.getSite() + k;
        }
        return rowSite;
    }
    
    /** 
    * @Description: 计算excel表格列数
    * @author king
    * @since Oct 8, 2012 9:30:39 AM 
    * 
    * @param col 列数
    * @param row 行对象
    * @param index 序列
    * @param k 序列
    * @return excel表格列数
    */
    public static int countColIndex(int col, final Row row, int index, int k) {
        final int startRow = row.getStart();
        final int endRow = row.getEnd();
        final int cellSize = row.getCells().size();
        if (startRow != -1 && endRow != -1) {
            col = ((cellSize * index) % (endRow - startRow + 1)) + k;
        }
        return col;
    }

    
    
    /**
     * 根据在<cell>标签上设置的类型取得单元格对应类中的数据类型.
     * @param type <cell>标签上设置的类型
     * @return 单元格对应类中的数据类型
     */
    public static int getCellType(String type) {
        int cellType = XSSFCell.CELL_TYPE_STRING;
        if ("CELL_TYPE_NUMERIC".equals(type)) {
            cellType = XSSFCell.CELL_TYPE_NUMERIC;
        } else if ("CELL_TYPE_STRING".equals(type)) {
            cellType = XSSFCell.CELL_TYPE_STRING;
        } else if ("CELL_TYPE_FORMULA".equals(type)) {
            cellType = XSSFCell.CELL_TYPE_FORMULA;
        } else if ("CELL_TYPE_BLANK".equals(type)) {
            cellType = XSSFCell.CELL_TYPE_BLANK;
        } else if ("CELL_TYPE_BOOLEAN".equals(type)) {
            cellType = XSSFCell.CELL_TYPE_BOOLEAN;
        } else if ("CELL_TYPE_ERROR".equals(type)) {
            cellType = XSSFCell.CELL_TYPE_ERROR;
        }
        return cellType;
    }

    /** 
    * @Description: 读取表格的值
    * @author king
    * @since Oct 8, 2012 9:31:25 AM 
    * 
    * @param rowTab 数据容器
    * @param rowPoi 行对象
    * @param cell 列对象
    * @param sheetPoi sheetPoi
    * @param palette palette
    */
    public static void readCellValue(
            TableRowMap rowTab, org.apache.poi.ss.usermodel.Row rowPoi, Cell cell, 
            final org.apache.poi.ss.usermodel.Sheet sheetPoi, HSSFPalette palette) {
        final String targetId = cell.getTargetId();
        final int col = cell.getSite();
        if (CommonUtil.isNotEmpty(targetId)) {
            analyzingCellInfo(rowTab, rowPoi, cell, targetId, col, palette);
        } else {
            if (col == -1) {
                final int minColIx = rowPoi.getFirstCellNum();
                final int maxColIx = rowPoi.getLastCellNum();
                final List<TableRowMap> datas = new ArrayList<TableRowMap>();
                for (int i = 0; i < maxColIx; i++) {
                    final TableRowMap cellTab = new TableRowMap();
                    cellTab.put(ConstantsUtil.Excel.CELL_SIT_KEY, i);
                    if (i < minColIx) {
                        datas.add(cellTab);
                        continue;
                    }
                    final CellBean cellBean = isCellMerged(sheetPoi, rowPoi.getRowNum(), i);
                    if (!analyzingCellInfo(cellTab, rowPoi, cell, ConstantsUtil.Excel.CELL_VALUE_KEY, i, palette) 
                            || (cellBean != null && cellBean.isMerged())) {
                        continue;
                    }
                    if (cellBean != null) {
                        cellTab.put(ConstantsUtil.Excel.CELL_ROWSPAN_KEY, cellBean.getRowspan());
                        cellTab.put(ConstantsUtil.Excel.CELL_COLSPAN_KEY, cellBean.getColspan());
                    }
                    datas.add(cellTab);
                }
                rowTab.setTabValue(datas);
            }
        }
    }
    
    /**
     * @Description: 
     * @author king
     * @since Aug 2, 2013 10:21:28 AM 
     * @version V1.0
     * @param datas datas
     * @return ExcelInfo
     */
    public static List<SheetBean> createExcelInfo(List<TableRowMap> datas) {
        final List<SheetBean> sheetList = new ArrayList<SheetBean>();
        for (int i = 0; i < datas.size(); i++) {
            final SheetBean sheetBean = new SheetBean();
            final TableRowMap sheetTab = datas.get(i);
            sheetBean.setSheetName(sheetTab.getString(ConstantsUtil.Excel.SHEET_NAME_KEY));
            sheetBean.setRowList(createRowInfo(sheetTab));
            sheetBean.setRowTabs((List<TableRowMap>)sheetTab.getTabValue());
            sheetList.add(sheetBean);
        }
        return sheetList;
    }
    
    /**
     * @Description: 
     * @author king
     * @since Aug 2, 2013 10:31:51 AM 
     * @version V1.0
     * @param sheetTab sheetTab
     * @return RowInfo
     */
    private static List<RowBean> createRowInfo(TableRowMap sheetTab) {
        final List<RowBean> rowBeanList = new ArrayList<RowBean>();
        final List<TableRowMap> rowList = (List<TableRowMap>)sheetTab.getTabValue();
        for (int i = 0; i < rowList.size(); i++) {
            final RowBean rowBean = new RowBean();
            final TableRowMap rowTab = rowList.get(i);
            rowBean.setSite(rowTab.getString(ConstantsUtil.Excel.ROW_SIT_KEY));
            rowBean.setCellList(createCellInfo(rowTab));
            rowBeanList.add(rowBean);
        }
        return rowBeanList;
    }
    
    /**
     * @Description: 
     * @author king
     * @since Aug 2, 2013 10:31:59 AM 
     * @version V1.0
     * @param rowTab rowTab
     * @return CellInfo
     */
    private static List<CellBean> createCellInfo(TableRowMap rowTab) {
        final List<CellBean> cellBeanList = new ArrayList<CellBean>();
        final List<TableRowMap> cellList = (List<TableRowMap>)rowTab.getTabValue();
        if (cellList == null) {
            return null;
        }
        for (int j = 0; j < cellList.size(); j++) {
            final CellBean cellBean = new CellBean();
            final TableRowMap cellTab = cellList.get(j);
            cellBean.setSite(cellTab.getString(ConstantsUtil.Excel.CELL_SIT_KEY));
            cellBean.setValue(cellTab.getString(ConstantsUtil.Excel.CELL_VALUE_KEY));
            cellBean.setBackground(cellTab.getString(ConstantsUtil.Excel.CELL_BACKGROUND_KEY));
            cellBean.setRowspan(cellTab.getString(ConstantsUtil.Excel.CELL_ROWSPAN_KEY));
            cellBean.setColspan(cellTab.getString(ConstantsUtil.Excel.CELL_COLSPAN_KEY));
            cellBean.setAlign(cellTab.getString(ConstantsUtil.Excel.CELL_ALIGNMENT_KEY));
            cellBean.setValign(cellTab.getString(ConstantsUtil.Excel.CELL_VERTICAL_ALIGNMENT_KEY));
            cellBeanList.add(cellBean);
        }
        return cellBeanList;
    }
    
    /**
     * @Description: 判断单元格是否为合并单元格
     * @author king
     * @since Jul 31, 2013 2:53:32 PM 
     * @version V1.0
     * @param sheetPoi sheetPoi
     * @param row row
     * @param cell cell
     * @return isCellMerged
     */
    private static CellBean isCellMerged(final org.apache.poi.ss.usermodel.Sheet sheetPoi, int row, int cell) {
        for (int i = 0; i < sheetPoi.getNumMergedRegions(); i++) {
            final CellRangeAddress region = sheetPoi.getMergedRegion(i);
            if (region.isInRange(row, cell)) {
                final CellBean cellBean = new CellBean();
                final int startCell = region.getFirstColumn();
                final int startRow = region.getFirstRow();
                final int endCell = region.getLastColumn();
                final int endRow = region.getLastRow();
                if (cell == startCell && row == startRow) {
                    cellBean.setRowspan(CommonUtil.toString(endRow - startRow + 1));
                    cellBean.setColspan(CommonUtil.toString(endCell - startCell + 1));
                }
                return cellBean;
            }
        }
        return null;
    }

    /**
     * @Description: 
     * @author king
     * @since Jul 30, 2013 2:58:44 PM 
     * @version V1.0
     * @param tab tab
     * @param rowPoi rowPoi
     * @param cell cell
     * @param targetId targetId
     * @param col col
     * @param palette palette
     * @return analyzing result
     */
    private static boolean analyzingCellInfo(
            TableRowMap tab, org.apache.poi.ss.usermodel.Row rowPoi,
            Cell cell, final String targetId, final int col, HSSFPalette palette) {
        String data = ConstantsUtil.Str.EMPTY;
        final org.apache.poi.ss.usermodel.Cell cellPoi = rowPoi.getCell(col);
        if (cellPoi == null) {
            tab.put(targetId, ConstantsUtil.Str.EMPTY);
            return false;
        } else {
            if (cellPoi.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC) {
                if (DateUtil.isCellDateFormatted(cellPoi)) {
                    final CellStyle cellStyle = cell.getStyle();
                    if (cellStyle != null) {
                        final CellDataFormat cellDataFormat = cellStyle.getFormat();
                        data = DateUtils.dateTimeFormat(
                                cellDataFormat.getFormat(), cellPoi.getDateCellValue());
                    } else {
                        data = CommonUtil.toString(cellPoi.getNumericCellValue());
                    }
                } else {
                    data = CommonUtil.toString(cellPoi.getNumericCellValue());
                }
            } else if (cellPoi.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING) {
                data = cellPoi.getStringCellValue();
            } else if (cellPoi.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA) {
                data = getFormulaValue(cellPoi);
            } else if (cellPoi.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN) {
                data = CommonUtil.toString(cellPoi.getBooleanCellValue());
            }
            
            setExcelStyle(tab, cellPoi, palette);
            setExcelAlignmentData(tab, cellPoi);
            tab.put(targetId, data);
        }
        return true;
    }
    
    /**
     * @Description: 
     * @author king
     * @since Aug 1, 2013 4:26:29 PM 
     * @version V1.0
     * @param tab tab
     * @param cellPoi cellPoi
     */
    private static void setExcelAlignmentData(TableRowMap tab, org.apache.poi.ss.usermodel.Cell cellPoi) {
        final short aligment = cellPoi.getCellStyle().getAlignment();
        String alignmentData = ConstantsUtil.Str.EMPTY;
        switch (aligment) {
            case org.apache.poi.ss.usermodel.CellStyle.ALIGN_GENERAL:
            case org.apache.poi.ss.usermodel.CellStyle.ALIGN_LEFT:
                alignmentData = "left";
                break;
            case org.apache.poi.ss.usermodel.CellStyle.ALIGN_RIGHT:
                alignmentData = "right";
                break;
            case org.apache.poi.ss.usermodel.CellStyle.ALIGN_CENTER:
            case org.apache.poi.ss.usermodel.CellStyle.ALIGN_FILL:
            case org.apache.poi.ss.usermodel.CellStyle.ALIGN_CENTER_SELECTION:
                alignmentData = "center";
                break;
            case org.apache.poi.ss.usermodel.CellStyle.ALIGN_JUSTIFY:
                alignmentData = "justify";
                break;

            default:
                break;
        }
        tab.put(ConstantsUtil.Excel.CELL_ALIGNMENT_KEY, alignmentData);
        String verticalAlignmentData = ConstantsUtil.Str.EMPTY;
        final short verticalAligment = cellPoi.getCellStyle().getVerticalAlignment();
        switch (verticalAligment) {
            case org.apache.poi.ss.usermodel.CellStyle.VERTICAL_TOP:
                verticalAlignmentData = "top";
                break;
            case org.apache.poi.ss.usermodel.CellStyle.VERTICAL_CENTER:
                verticalAlignmentData = "middle";
                break;
            case org.apache.poi.ss.usermodel.CellStyle.VERTICAL_BOTTOM:
                verticalAlignmentData = "bottom";
                break;
            case org.apache.poi.ss.usermodel.CellStyle.VERTICAL_JUSTIFY:
                verticalAlignmentData = "baseline";
                break;

            default:
                break;
        }
        tab.put(ConstantsUtil.Excel.CELL_VERTICAL_ALIGNMENT_KEY, verticalAlignmentData);
    }

    /**
     * @Description: 
     * @author king
     * @since Jul 31, 2013 11:51:00 AM 
     * @version V1.0
     * @param tab tab
     * @param cellPoi cellPoi
     * @param palette palette
     */
    private static void setExcelStyle(TableRowMap tab,
            final org.apache.poi.ss.usermodel.Cell cellPoi, HSSFPalette palette) {
        final org.apache.poi.ss.usermodel.CellStyle cellStyle = cellPoi.getCellStyle();
        final Map<String, Short> colorMap = getExcelColorMap();
        if (palette == null) {
            for (String key : colorMap.keySet()) {
                if (colorMap.get(key) == cellStyle.getFillForegroundColor()) {
                    if ("AUTOMATIC".equals(key)) {
                        tab.put(ConstantsUtil.Excel.CELL_BACKGROUND_KEY, ConstantsUtil.Str.EMPTY);
                    } else {
                        tab.put(ConstantsUtil.Excel.CELL_BACKGROUND_KEY, key);
                    }
                    break;
                }
            }
        } else {
            final HSSFColor hColor = palette.getColor(cellStyle.getFillForegroundColor());
            tab.put(ConstantsUtil.Excel.CELL_BACKGROUND_KEY, convertToStardColor(hColor));
        }
    }
    
    private static String convertToStardColor(HSSFColor hc) {
        StringBuffer sb = new StringBuffer("");
        if (hc != null) {
            int a = HSSFColor.AUTOMATIC.index;
            int b = hc.getIndex();
            if (a == b) {
                return ConstantsUtil.Str.EMPTY;
            }
            sb.append("#");
            for (int i = 0; i < hc.getTriplet().length; i++) {
                String str;
                String str_tmp = Integer.toHexString(hc.getTriplet()[i]);
                if (str_tmp != null && str_tmp.length() < 2) {
                    str = "0" + str_tmp;
                } else {
                    str = str_tmp;
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    /**
     * @Description: 
     * @author king
     * @since Jul 30, 2013 10:33:37 AM 
     * @version V1.0
     * @param cellItem cellItem
     * @return FormulaValue
     */
    private static String getFormulaValue(final org.apache.poi.ss.usermodel.Cell cellItem) {
        String data;
        try {
            data = CommonUtil.toString(cellItem.getNumericCellValue());
        } catch (IllegalStateException e) {
            data = CommonUtil.toString(cellItem.getRichStringCellValue());
        }
        return data;
    }
    
    /**
     * 设置excel单元格样式.
     * @param wb excel对象
     * @param cellItem excel的单元格对象
     * @param cell 对应<cell>标签的信息对象
     * @param row 对应<row>标签的信息对象
     * @throws RichClientWebException RichClientWebException
     */
    public static void setCellStyle(Workbook wb, org.apache.poi.ss.usermodel.Cell cellItem, Cell cell, Row row) 
        throws RichClientWebException {
        final String rowClassName = row.getClassName();
        String className = cell.getClassName();
        if (CommonUtil.isNotEmpty(rowClassName)) {
            if (CommonUtil.isEmpty(className)) {
                className = rowClassName;
            } else {
                className = rowClassName + ConstantsUtil.Str.BLANK + cell.getClassName();
            }
        }
        if (CommonUtil.isNotEmpty(className)) {
            final String[] classNames = className.split(ConstantsUtil.Str.BLANK);
            for (int i = 0; i < classNames.length; i++) {
                // 从<resource>标签中取得共通的excel单元格样式信息
                final CellStyle resourceCellStyle = 
                    ControlRequestMap.getInstance().getCellStyleInResource(classNames[i]);
                setCellStyle(wb, cellItem, resourceCellStyle);
            }
        }
        final CellStyle cellStyle = cell.getStyle();
        // 设置excel单元格样式
        setCellStyle(wb, cellItem, cellStyle);
        
    }

    /**
     * 设置excel单元格样式
     * @param wb excel对象
     * @param cellItem excel的单元格对象
     * @param cellStyle <cellStyle>标签中的信息内容
     * @throws RichClientWebException RichClientWebException
     */
    private static void setCellStyle(
            Workbook wb, 
            org.apache.poi.ss.usermodel.Cell cellItem,
            final CellStyle cellStyle) 
        throws RichClientWebException {
        if (cellStyle != null) {
            cellStyleItem = wb.createCellStyle();
            if (cellItem.getCellStyle() != null) {
                cellStyleItem.cloneStyleFrom(cellItem.getCellStyle());
            }
            
            // 表框样式设置
            setCellBorderStyle(cellStyle, cellStyleItem);
            
            // 字体样式设置
            setCellFontStyle(wb, cellStyle, cellStyleItem);
            
            // 格式化样式设置
            setCellDataFormatStyle(wb, cellItem, cellStyle, cellStyleItem);
            
            // 布局样式设置
            setCellLayoutStyle(cellStyle, cellStyleItem);
            
            // 背景色设置
            setCellGroundStyle(cellStyle, cellStyleItem);
            cellItem.setCellStyle(cellStyleItem);
        }
    }
    
    /** 
    * @Description: 设置excel单元格样式
    * @author king
    * @since Nov 9, 2012 2:18:39 PM 
    * 
    * @param wb excel对象
    * @param cellItem excel的单元格对象
    * @param color 背景颜色
    * @throws RichClientWebException RichClientWebException
    */
    public static void setCellBackGroundStyle(
            Workbook wb, 
            org.apache.poi.ss.usermodel.Cell cellItem,
            final String color) 
        throws RichClientWebException {
        if (CommonUtil.isNotEmpty(color)) {
            cellStyleItem = wb.createCellStyle();
            if (cellItem.getCellStyle() != null) {
                cellStyleItem.cloneStyleFrom(cellItem.getCellStyle());
            }
            cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.SOLID_FOREGROUND);
            cellStyleItem.setFillForegroundColor(ExcelUtil.getExcelColor(color));
            cellItem.setCellStyle(cellStyleItem);
            cellStyleItem = null;
        }
    }

    /**
     * 设置excel单元格背景样式
     * @param cellStyleItem excel的单元格样式对象
     * @param cellStyle <cellStyle>标签中的信息内容
     */
    private static void setCellGroundStyle(final CellStyle cellStyle,
            final org.apache.poi.ss.usermodel.CellStyle cellStyleItem) {
        final CellGround ground = cellStyle.getGround();
        if (ground != null) {
            final String foreColor = ground.getForeColor();
            if (CommonUtil.isNotEmpty(foreColor)) {
                cellStyleItem.setFillForegroundColor(getExcelColor(foreColor));
            }
            final String backColor = ground.getBackColor();
            if (CommonUtil.isNotEmpty(backColor)) {
                cellStyleItem.setFillBackgroundColor(getExcelColor(backColor));
            }
            final String fill = ground.getFill();
            if (CommonUtil.isNotEmpty(fill)) {
                if ("NO_FILL".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.NO_FILL);
                } else if ("SOLID_FOREGROUND".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.SOLID_FOREGROUND);
                } else if ("FINE_DOTS".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.FINE_DOTS);
                } else if ("ALT_BARS".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.ALT_BARS);
                } else if ("SPARSE_DOTS".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.SPARSE_DOTS);
                } else if ("THICK_HORZ_BANDS".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.THICK_HORZ_BANDS);
                } else if ("THICK_VERT_BANDS".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.THICK_VERT_BANDS);
                } else if ("THICK_BACKWARD_DIAG".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.THICK_BACKWARD_DIAG);
                } else if ("THICK_FORWARD_DIAG".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.THICK_FORWARD_DIAG);
                } else if ("BIG_SPOTS".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.BIG_SPOTS);
                } else if ("BRICKS".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.BRICKS);
                } else if ("THIN_HORZ_BANDS".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.THIN_HORZ_BANDS);
                } else if ("THIN_VERT_BANDS".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.THIN_VERT_BANDS);
                } else if ("THIN_BACKWARD_DIAG".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.THIN_BACKWARD_DIAG);
                } else if ("THIN_FORWARD_DIAG".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.THIN_FORWARD_DIAG);
                } else if ("SQUARES".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.SQUARES);
                } else if ("DIAMONDS".equals(fill)) {
                    cellStyleItem.setFillPattern(org.apache.poi.ss.usermodel.CellStyle.DIAMONDS);
                }
            }
        }
    }

    /**
     * 设置excel单元格布局样式
     * @param cellStyleItem excel的单元格样式对象
     * @param cellStyle <cellStyle>标签中的信息内容
     */
    private static void setCellLayoutStyle(final CellStyle cellStyle,
            final org.apache.poi.ss.usermodel.CellStyle cellStyleItem) {
        final CellLayout layout = cellStyle.getLayout();
        if (layout != null) {
            final String align = layout.getAlign();
            if (CommonUtil.isNotEmpty(align)) {
                if ("ALIGN_GENERAL".equals(align)) {
                    cellStyleItem.setAlignment(org.apache.poi.ss.usermodel.CellStyle.ALIGN_GENERAL);
                } else if ("ALIGN_LEFT".equals(align)) {
                    cellStyleItem.setAlignment(org.apache.poi.ss.usermodel.CellStyle.ALIGN_LEFT);
                } else if ("ALIGN_CENTER".equals(align)) {
                    cellStyleItem.setAlignment(org.apache.poi.ss.usermodel.CellStyle.ALIGN_CENTER);
                } else if ("ALIGN_RIGHT".equals(align)) {
                    cellStyleItem.setAlignment(org.apache.poi.ss.usermodel.CellStyle.ALIGN_RIGHT);
                } else if ("ALIGN_FILL".equals(align)) {
                    cellStyleItem.setAlignment(org.apache.poi.ss.usermodel.CellStyle.ALIGN_FILL);
                } else if ("ALIGN_JUSTIFY".equals(align)) {
                    cellStyleItem.setAlignment(org.apache.poi.ss.usermodel.CellStyle.ALIGN_JUSTIFY);
                } else if ("ALIGN_CENTER_SELECTION".equals(align)) {
                    cellStyleItem.setAlignment(org.apache.poi.ss.usermodel.CellStyle.ALIGN_CENTER_SELECTION);
                }
            }
            final String vertical = layout.getVertical();
            if (CommonUtil.isNotEmpty(vertical)) {
                if ("VERTICAL_TOP".equals(vertical)) {
                    cellStyleItem.setVerticalAlignment(org.apache.poi.ss.usermodel.CellStyle.VERTICAL_TOP);
                } else if ("VERTICAL_CENTER".equals(vertical)) {
                    cellStyleItem.setVerticalAlignment(org.apache.poi.ss.usermodel.CellStyle.VERTICAL_CENTER);
                } else if ("VERTICAL_BOTTOM".equals(vertical)) {
                    cellStyleItem.setVerticalAlignment(org.apache.poi.ss.usermodel.CellStyle.VERTICAL_BOTTOM);
                } else if ("VERTICAL_JUSTIFY".equals(vertical)) {
                    cellStyleItem.setVerticalAlignment(org.apache.poi.ss.usermodel.CellStyle.VERTICAL_JUSTIFY);
                }
            }
        }
    }

    /**
     * 设置excel单元格格式化样式
     * @param wb excel对象
     * @param cellPoi excel的单元格对象
     * @param cellStyleItem excel的单元格样式对象
     * @param cellStyle <cellStyle>标签中的信息内容
     * @throws RichClientWebException RichClientWebException
     */
    private static void setCellDataFormatStyle(
            Workbook wb, 
            org.apache.poi.ss.usermodel.Cell cellPoi,
            final CellStyle cellStyle, 
            final org.apache.poi.ss.usermodel.CellStyle cellStyleItem)
        throws RichClientWebException {
        format = cellStyle.getFormat();
        if (format != null) {
            final String formatStyle = format.getFormat();
            if (CommonUtil.isNotEmpty(formatStyle)) {
                final String sourceFormatStyle = format.getSourceFormat();
                helper = wb.getCreationHelper();
                if (CommonUtil.isNotEmpty(sourceFormatStyle)) {
                    cellPoi.setCellValue(DateUtils.getStringToDate(
                            cellPoi.getStringCellValue(), sourceFormatStyle));
                }
                cellStyleItem.setDataFormat(helper.createDataFormat().getFormat(formatStyle));
            }
        }
    }

    /**
     * 设置excel单元格字体样式
     * @param wb excel对象
     * @param cellStyleItem excel的单元格样式对象
     * @param cellStyle <cellStyle>标签中的信息内容
     */
    private static void setCellFontStyle(Workbook wb, final CellStyle cellStyle,
            final org.apache.poi.ss.usermodel.CellStyle cellStyleItem) {
        final CellFont font = cellStyle.getFont();
        if (font != null) {
            final Font fontItem = wb.createFont();
            final String bold = font.getBold();
            if (CommonUtil.isNotEmpty(bold)) {
                if ("normal".equals(bold)) {
                    fontItem.setBoldweight(Font.BOLDWEIGHT_NORMAL);
                } else {
                    fontItem.setBoldweight(Font.BOLDWEIGHT_BOLD);
                }
            }
            final String color = font.getColor();
            if (CommonUtil.isNotEmpty(color)) {
                fontItem.setColor(getExcelColor(color));
            }
            final int fontHeight = font.getFontHeight();
            if (fontHeight != -1) {
                fontItem.setFontHeight((short)fontHeight);
            }
            final String name = font.getName();
            if (CommonUtil.isNotEmpty(name)) {
                fontItem.setFontName(name);
            }
            final String typeOffset = font.getTypeOffset();
            if (CommonUtil.isNotEmpty(typeOffset)) {
                if ("normal".equals(typeOffset)) {
                    fontItem.setTypeOffset(Font.SS_NONE);
                } else if ("super".equals(typeOffset)) {
                    fontItem.setTypeOffset(Font.SS_SUPER);
                } else if ("subscript".equals(typeOffset)) {
                    fontItem.setTypeOffset(Font.SS_SUB);
                }
            }
            final String underline = font.getUnderline();
            if (CommonUtil.isNotEmpty(underline)) {
                if ("NONE".equals(underline)) {
                    fontItem.setUnderline(Font.U_NONE);
                } else if ("SINGLE".equals(underline)) {
                    fontItem.setUnderline(Font.U_SINGLE);
                } else if ("DOUBLE".equals(underline)) {
                    fontItem.setUnderline(Font.U_DOUBLE);
                } else if ("SINGLE_ACCOUNTING".equals(underline)) {
                    fontItem.setUnderline(Font.U_SINGLE_ACCOUNTING);
                } else if ("DOUBLE_ACCOUNTING".equals(underline)) {
                    fontItem.setUnderline(Font.U_DOUBLE_ACCOUNTING);
                }
            }
            cellStyleItem.setFont(fontItem);
        }
    }

    /**
     * 设置excel单元格边框样式
     * @param cellStyleItem excel的单元格样式对象
     * @param cellStyle <cellStyle>标签中的信息内容
     */
    private static void setCellBorderStyle(
            final CellStyle cellStyle,
            final org.apache.poi.ss.usermodel.CellStyle cellStyleItem) {
        final CellBorder border = cellStyle.getBorder();
        if (border != null) {
            final String borderColor = border.getWholeColor();
            if (CommonUtil.isNotEmpty(borderColor)) {
                cellStyleItem.setTopBorderColor(getExcelColor(borderColor));
                cellStyleItem.setBottomBorderColor(getExcelColor(borderColor));
                cellStyleItem.setLeftBorderColor(getExcelColor(borderColor));
                cellStyleItem.setRightBorderColor(getExcelColor(borderColor));
            }
            
            final String borderTopColor = border.getTopColor();
            if (CommonUtil.isNotEmpty(borderTopColor)) {
                cellStyleItem.setTopBorderColor(getExcelColor(borderTopColor));
            }
            
            final String borderBottomColor = border.getBottomColor();
            if (CommonUtil.isNotEmpty(borderBottomColor)) {
                cellStyleItem.setBottomBorderColor(getExcelColor(borderBottomColor));
            }
            
            final String borderLeftColor = border.getLeftColor();
            if (CommonUtil.isNotEmpty(borderLeftColor)) {
                cellStyleItem.setLeftBorderColor(getExcelColor(borderLeftColor));
            }
            
            final String borderRightColor = border.getRightColor();
            if (CommonUtil.isNotEmpty(borderRightColor)) {
                cellStyleItem.setRightBorderColor(getExcelColor(borderRightColor));
            }
            
            final String borderStyle = border.getWholeStyle();
            if (CommonUtil.isNotEmpty(borderStyle)) {
                cellStyleItem.setBorderTop(getExcelBorderStyle(borderStyle));
                cellStyleItem.setBorderBottom(getExcelBorderStyle(borderStyle));
                cellStyleItem.setBorderLeft(getExcelBorderStyle(borderStyle));
                cellStyleItem.setBorderRight(getExcelBorderStyle(borderStyle));
            }
            
            final String borderTopStyle = border.getTopStyle();
            if (CommonUtil.isNotEmpty(borderTopStyle)) {
                cellStyleItem.setBorderTop(getExcelBorderStyle(borderTopStyle));
            }
            
            final String borderBottomStyle = border.getBottomStyle();
            if (CommonUtil.isNotEmpty(borderBottomStyle)) {
                cellStyleItem.setBorderBottom(getExcelBorderStyle(borderBottomStyle));
            }
            
            final String borderLeftStyle = border.getLeftStyle();
            if (CommonUtil.isNotEmpty(borderLeftStyle)) {
                cellStyleItem.setBorderLeft(getExcelBorderStyle(borderLeftStyle));
            }
            
            final String borderRightStyle = border.getRightStyle();
            if (CommonUtil.isNotEmpty(borderRightStyle)) {
                cellStyleItem.setBorderRight(getExcelBorderStyle(borderRightStyle));
            }
        }
    }
    
    public static short getExcelColor(String colorKey) {
        final short color = getExcelColorMap().get(colorKey);
        return color;
    }
    
    public static short getExcelBorderStyle(String borderStyleKey) {
        final short borderStyle = getBorderStyleMap().get(borderStyleKey);
        return borderStyle;
    }
    
    /**
     * excel的颜色表.
     * @return excel的颜色表
     */
    public static Map<String, Short> getExcelColorMap() {
        final Map<String, Short> map = new HashMap<String, Short>();
        map.put("BLACK", IndexedColors.BLACK.index);
        map.put("WHITE", IndexedColors.WHITE.index);
        map.put("RED", IndexedColors.RED.index);
        map.put("BRIGHT_GREEN", IndexedColors.BRIGHT_GREEN.index);
        map.put("BLUE", IndexedColors.BLUE.index);
        map.put("YELLOW", IndexedColors.YELLOW.index);
        map.put("PINK", IndexedColors.PINK.index);
        map.put("TURQUOISE", IndexedColors.TURQUOISE.index);
        map.put("DARK_RED", IndexedColors.DARK_RED.index);
        map.put("GREEN", IndexedColors.GREEN.index);
        map.put("DARK_BLUE", IndexedColors.DARK_BLUE.index);
        map.put("DARK_YELLOW", IndexedColors.DARK_YELLOW.index);
        map.put("VIOLET", IndexedColors.VIOLET.index);
        map.put("TEAL", IndexedColors.TEAL.index);
        map.put("GREY_25_PERCENT", IndexedColors.GREY_25_PERCENT.index);
        map.put("GREY_50_PERCENT", IndexedColors.GREY_50_PERCENT.index);
        map.put("CORNFLOWER_BLUE", IndexedColors.CORNFLOWER_BLUE.index);
        map.put("MAROON", IndexedColors.MAROON.index);
        map.put("LEMON_CHIFFON", IndexedColors.LEMON_CHIFFON.index);
        map.put("ORCHID", IndexedColors.ORCHID.index);
        map.put("CORAL", IndexedColors.CORAL.index);
        map.put("ROYAL_BLUE", IndexedColors.ROYAL_BLUE.index);
        map.put("LIGHT_CORNFLOWER_BLUE", IndexedColors.LIGHT_CORNFLOWER_BLUE.index);
        map.put("SKY_BLUE", IndexedColors.SKY_BLUE.index);
        map.put("LIGHT_TURQUOISE", IndexedColors.LIGHT_TURQUOISE.index);
        map.put("LIGHT_GREEN", IndexedColors.LIGHT_GREEN.index);
        map.put("LIGHT_YELLOW", IndexedColors.LIGHT_YELLOW.index);
        map.put("PALE_BLUE", IndexedColors.PALE_BLUE.index);
        map.put("ROSE", IndexedColors.ROSE.index);
        map.put("LAVENDER", IndexedColors.LAVENDER.index);
        map.put("TAN", IndexedColors.TAN.index);
        map.put("LIGHT_BLUE", IndexedColors.LIGHT_BLUE.index);
        map.put("AQUA", IndexedColors.AQUA.index);
        map.put("LIME", IndexedColors.LIME.index);
        map.put("GOLD", IndexedColors.GOLD.index);
        map.put("LIGHT_ORANGE", IndexedColors.LIGHT_ORANGE.index);
        map.put("ORANGE", IndexedColors.ORANGE.index);
        map.put("BLUE_GREY", IndexedColors.BLUE_GREY.index);
        map.put("GREY_40_PERCENT", IndexedColors.GREY_40_PERCENT.index);
        map.put("DARK_TEAL", IndexedColors.DARK_TEAL.index);
        map.put("SEA_GREEN", IndexedColors.SEA_GREEN.index);
        map.put("DARK_GREEN", IndexedColors.DARK_GREEN.index);
        map.put("OLIVE_GREEN", IndexedColors.OLIVE_GREEN.index);
        map.put("BROWN", IndexedColors.BROWN.index);
        map.put("PLUM", IndexedColors.PLUM.index);
        map.put("INDIGO", IndexedColors.INDIGO.index);
        map.put("GREY_80_PERCENT", IndexedColors.GREY_80_PERCENT.index);
        map.put("AUTOMATIC", IndexedColors.AUTOMATIC.index);
        return map;
    }
    
    /**
     * excel的边框样式表.
     * @return excel的边框样式表
     */
    public static Map<String, Short> getBorderStyleMap() {
        final Map<String, Short> map = new HashMap<String, Short>();
        map.put("BORDER_NONE", org.apache.poi.ss.usermodel.CellStyle.BORDER_NONE);
        map.put("BORDER_THIN", org.apache.poi.ss.usermodel.CellStyle.BORDER_THIN);
        map.put("BORDER_MEDIUM", org.apache.poi.ss.usermodel.CellStyle.BORDER_MEDIUM);
        map.put("BORDER_DASHED", org.apache.poi.ss.usermodel.CellStyle.BORDER_DASHED);
        map.put("BORDER_DOTTED", org.apache.poi.ss.usermodel.CellStyle.BORDER_DOTTED);
        map.put("BORDER_THICK", org.apache.poi.ss.usermodel.CellStyle.BORDER_THICK);
        map.put("BORDER_DOUBLE", org.apache.poi.ss.usermodel.CellStyle.BORDER_DOUBLE);
        map.put("BORDER_HAIR", org.apache.poi.ss.usermodel.CellStyle.BORDER_HAIR);
        map.put("BORDER_MEDIUM_DASHED", org.apache.poi.ss.usermodel.CellStyle.BORDER_MEDIUM_DASHED);
        map.put("BORDER_DASH_DOT", org.apache.poi.ss.usermodel.CellStyle.BORDER_DASH_DOT);
        map.put("BORDER_MEDIUM_DASH_DOT", org.apache.poi.ss.usermodel.CellStyle.BORDER_MEDIUM_DASH_DOT);
        map.put("BORDER_DASH_DOT_DOT", org.apache.poi.ss.usermodel.CellStyle.BORDER_DASH_DOT_DOT);
        map.put("BORDER_MEDIUM_DASH_DOT_DOT", org.apache.poi.ss.usermodel.CellStyle.BORDER_MEDIUM_DASH_DOT_DOT);
        map.put("BORDER_SLANTED_DASH_DOT", org.apache.poi.ss.usermodel.CellStyle.BORDER_SLANTED_DASH_DOT);
        return map;
    }

}
