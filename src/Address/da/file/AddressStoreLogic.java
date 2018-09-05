package Address.da.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Address.da.file.io.MemoryTreeAddress;
import Address.store.AddressStore;
import Address.util.AddressTree;
import Address.util.TreeNode;

public class AddressStoreLogic implements AddressStore {
	//
	private AddressTree addressTree;
	private Map<String, ArrayList<TreeNode>> addressMapDong;
	private Map<String, ArrayList<TreeNode>> addressMapStructure;

	public AddressStoreLogic() {
		//
		this.addressTree = MemoryTreeAddress.getInstance().getAddressTree();
		this.addressMapDong = new HashMap<>();
		this.addressMapStructure = new HashMap<>();
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
						ArrayList<TreeNode> nodeList = new ArrayList<>();

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
						TreeNode newNode = new TreeNode(value);

						addressTree.addNode(parentNode, newNode);
						parentNode = addressTree.getNode(parentNode, value);
				
						if (columnindex == 2) {
							//
							if (addressMapDong.containsKey(value)) {
								nodeList = addressMapDong.get(value);
								nodeList.add(newNode);
								addressMapDong.put(value, nodeList);
							}

							if (addressMapDong.get(value) == null) {
								nodeList.add(newNode);
								addressMapDong.put(value, nodeList);
							}
						}
						if (columnindex == 3) {
							//
							if (addressMapStructure.containsKey(value)) {
								nodeList = addressMapStructure.get(value);
								nodeList.add(newNode);
								addressMapStructure.put(value, nodeList);
							}

							if (addressMapDong.get(value) == null) {
								nodeList.add(newNode);
								addressMapStructure.put(value, nodeList);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (int i = 0; i < addressMapStructure.get("교회").size(); i++) {
//			System.out.println(addressMapStructure.get("교회").get(i).getValue());
//			System.out.println(i);
//		}
	}

	@Override
	public List<String> retrieveAddress(String value) {
		// 
		if(value == null) {
			throw new NullPointerException("nullPointerException");
		}
		
		TreeNode findNode = addressTree.getNodeOfValue(value);
		List<String> valueList = new ArrayList<>();
		
		for(TreeNode node : findNode.getChildNodes()) {
			valueList.add(node.getValue());
		}

		return valueList;
	}

	@Override
	public List<String> retrieveRootNodeChilds() {
		// 
		List<String> childList = new ArrayList<>();
		for(TreeNode node : addressTree.getRootNode().getChildNodes()) {
			childList.add(node.getValue());
		}
		
		return childList;
	}

//	private void addAddressInTree(TreeNode parentNode, TreeNode newNode) {
//		//
////		TreeNode newNode = new TreeNode(value);
//
//		addressTree.addNode(parentNode, newNode);
//		parentNode = addressTree.getNode(parentNode, newNode.getValue());
//	}
}
