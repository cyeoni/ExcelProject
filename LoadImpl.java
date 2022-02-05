import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoadImpl implements ILoad {

	List<ExcelVO> ex = new ArrayList<ExcelVO>();
	List<MemoVO> me = new ArrayList<MemoVO>();
	List<ResultVO> re = new ArrayList<ResultVO>();

	@Override
	public void doRoadExcel() {
		String path = "D:\\Excel\\"; // 파일 경로 설정
		String filename = "excel.xlsx"; // 파일명 설정

		try {
			FileInputStream file = new FileInputStream(path + filename);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			NumberFormat f = NumberFormat.getInstance();
			f.setGroupingUsed(false); // 지수로 안나오게 설정

			XSSFSheet sheet = workbook.getSheetAt(0); // 0번째 시트
			// 행 갯수
			int rows = sheet.getPhysicalNumberOfRows();

			for (int r = 0; r < rows; r++) {
				ExcelVO excelVO = new ExcelVO();
				XSSFRow row = sheet.getRow(r);

				int cells = row.getPhysicalNumberOfCells();

				// System.out.print("| " + r + " |");
				for (int c = 0; c < cells; c++) {
					XSSFCell cell = row.getCell(c);

					String value = "";
					if (cell != null) {
						// 타입 체크
						switch (cell.getCellType()) {
						case STRING:
							value = cell.getStringCellValue();
							excelVO.setTag(value);
							break;
						case NUMERIC:
							value = f.format(cell.getNumericCellValue()) + "";
							excelVO.setBuffer(value);
							break;
						case BLANK:
							value = cell.getBooleanCellValue() + "";
							break;
						case ERROR:
							value = cell.getErrorCellValue() + "";
							break;
						}
					}
					// System.out.print(" " + value + " |");
				}
				ex.add(excelVO); //행당 list 저장
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void doRoadMemo() {
		String path = "D:\\Excel\\"; // 파일 경로 설정
		String filename = "memo.xlsx"; // 파일명 설정

		try {
			FileInputStream file = new FileInputStream(path + filename);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			NumberFormat f = NumberFormat.getInstance();
			f.setGroupingUsed(false); // 지수로 안나오게 설정

			XSSFSheet sheet = workbook.getSheetAt(0); // 0번째 시트
			// 행 갯수
			int rows = sheet.getPhysicalNumberOfRows();

			for (int r = 0; r < rows; r++) {
				MemoVO memoVO = new MemoVO();
				XSSFRow row = sheet.getRow(r);

				int cells = row.getPhysicalNumberOfCells();

				// System.out.print("| " + r + " |");
				for (int c = 0; c < cells; c++) {
					XSSFCell cell = row.getCell(c);

					String value = "";
					if (cell != null) {
						// 타입 체크
						switch (cell.getCellType()) {
						case STRING:
							value = cell.getStringCellValue();
							if(value.startsWith("EM0") || value.startsWith("DM0")) {
								memoVO.setBufferFirst(value);
							} else {
								memoVO.setTag(value);
							}
							break;
						case NUMERIC:
							value = f.format(cell.getNumericCellValue()) + "";
							memoVO.setBufferLast(value);
							break;
						case BLANK:
							value = cell.getBooleanCellValue() + "";
							break;
						case ERROR:
							value = cell.getErrorCellValue() + "";
							break;
						}
					}
					// System.out.print(" " + value + " |");
				}
				String first = memoVO.bufferFirst.substring(3);
				String value = ((Integer.parseInt(first) + Integer.parseInt(memoVO.bufferLast)) -1 ) + "";
				memoVO.setBufferRange(value);
				
				String name = memoVO.bufferFirst.substring(0,3);
				memoVO.setName(name);
				
				me.add(memoVO); //행당 list 저장
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doReadExcel() {

		doRoadExcel();

		for (int i = 0; i < ex.size(); i++) {
			System.out.print(ex.get(i).tag + "\t");
			System.out.println(ex.get(i).buffer);
		}
	}
	
	@Override
	public void doReadMemo() {

		doRoadMemo();

		for (int i = 0; i < me.size(); i++) {
			System.out.print(me.get(i).tag + "\t");
			System.out.print(me.get(i).bufferFirst + "\t");
			System.out.print(me.get(i).bufferLast + "\t");
			System.out.print(me.get(i).bufferRange + "\t");
			System.out.println(me.get(i).name);
		}
	}
	
	@Override
	public void doResult() {
		doRoadExcel();
		doRoadMemo();
		
		System.out.println("tag\tarea\tword\tfull");
		for(int i = 0; i < ex.size(); i++) { // 24
			ResultVO resultVO = new ResultVO();
			for(int j = 0; j < me.size(); j ++) { // 27
				if(ex.get(i).tag.equals(me.get(j).tag)) {
					if(Integer.parseInt(ex.get(i).buffer) >= Integer.parseInt(me.get(j).bufferFirst.substring(3))
							&& Integer.parseInt(ex.get(i).buffer) <= Integer.parseInt(me.get(j).bufferRange)) {
						//System.out.println(ex.get(i).tag + "\t" + me.get(j).name + "\t" + me.get(j).name.substring(0,1) + me.get(j).name.substring(2,3) + "\t" + me.get(j).name + ex.get(i).buffer);
						resultVO.setTag(ex.get(i).tag);
						resultVO.setArea(me.get(j).name);
						resultVO.setWord(me.get(j).name.substring(0,1) + me.get(j).name.substring(2,3));
						String value = String.format("%05d",Integer.parseInt(ex.get(i).buffer));
						resultVO.setFull(me.get(j).name + value);
						//System.out.println(resultVO.tag + "\t" + resultVO.area + "\t" + resultVO.word + "\t" + resultVO.full);
						re.add(resultVO);
					}
				}
			}
		}
	}
	
	@Override
	public void doReadResult() {
		doResult();
		
		for (int i = 0; i < re.size(); i++) {
			System.out.print(re.get(i).tag + "\t");
			System.out.print(re.get(i).area + "\t");
			System.out.print(re.get(i).word + "\t");
			System.out.println(re.get(i).full);
		}
	}
}
