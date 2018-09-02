package Address.da.file;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Address.da.file.io.MemoryTreeAddress;
import Address.store.AddressStore;
import Address.util.AddressTree;
import Address.util.TreeNode;

public class AddressStoreLogic implements AddressStore{
	//
	private AddressTree addressTree;
	
	public AddressStoreLogic() {
		//
		this.addressTree = MemoryTreeAddress.getInstance().getAddressTree();
	}
	@Override
	public void registAddress() {
		// 
		try {
			FileInputStream fis = new FileInputStream("resource\\Data\\SeoulAddress.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			int rowindex = 0;
			int columnindex = 0;

			XSSFSheet sheet = workbook.getSheetAt(0);

			int rows = sheet.getPhysicalNumberOfRows();
			for (rowindex = 1; rowindex < rows; rowindex++) {
				TreeNode parentNode = addressTree.getRootNode();
				XSSFRow row = sheet.getRow(rowindex);
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					for (columnindex = 0; columnindex <= cells; columnindex++) {
						XSSFCell cell = row.getCell(columnindex);
						String value = "";
						if (cell == null) {
							continue;
						} else {
							switch (cell.getCellType()) {
							case XSSFCell.CELL_TYPE_FORMULA:
								value = cell.getCellFormula();
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:
								value = cell.getNumericCellValue() + "";
								break;
							case XSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue() + "";
								break;
							case XSSFCell.CELL_TYPE_BLANK:
								value = cell.getBooleanCellValue() + "";
								break;
							case XSSFCell.CELL_TYPE_ERROR:
								value = cell.getErrorCellValue() + "";
								break;
							}
						}
						System.out.println("°¢ ¼¿  ³»¿ë:" + value);
						addressTree.addNode(parentNode, value);

						parentNode = addressTree.getNode(parentNode, value);
						System.out.println("Ok");
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public TreeNode retrieveAddress(String value) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
