package Address.da.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Address.da.file.io.MemoryMapAddress;
import Address.da.file.io.MemoryTreeAddress;
import Address.da.file.io.MemoryTreeChar;
import Address.store.AddressStore;
import Address.util.AddressTree;
import Address.util.CharNode;
import Address.util.CharTree;
import Address.util.TreeNode;

public class AddressStoreLogic implements AddressStore {
	//
	private AddressTree addressTree;
	private Map<String, ArrayList<TreeNode>> addressMapDong;
	private Map<String, ArrayList<TreeNode>> addressMapStructure;
	private CharTree dongCharTree;
	private CharTree structureCharTree;

	public AddressStoreLogic() {
		//
		this.addressTree = MemoryTreeAddress.getInstance().getAddressTree();
		this.addressMapDong = MemoryMapAddress.getInstance().getAddressDongMap();
		this.addressMapStructure = MemoryMapAddress.getInstance().getAddressStructureMap();
		this.dongCharTree = MemoryTreeChar.getInstance().getDongCharTree();
		this.structureCharTree = MemoryTreeChar.getInstance().getStructureCharTree();
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
				Row row = sheet.getRow(rowindex);
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					for (columnindex = 0; columnindex <= cells; columnindex++) {
						Cell cell = row.getCell(columnindex);
						String value = "";
						ArrayList<TreeNode> nodeList = new ArrayList<>();

						if (cell == null) {
							continue;
						} else {
							switch (cell.getCellTypeEnum()) {
							case FORMULA:
								value = cell.getCellFormula();
								break;
							case NUMERIC:
								value = cell.getNumericCellValue() + "";
								break;
							case STRING:
								value = cell.getStringCellValue() + "";
								break;
							case BLANK:
								value = cell.getBooleanCellValue() + "";
								break;
							case ERROR:
								value = cell.getErrorCellValue() + "";
								break;
							default:
								break;
							}
						}
						TreeNode newNode = new TreeNode(value);

						addressTree.addNode(parentNode, newNode);
						parentNode = addressTree.getNode(parentNode, value);

						if (columnindex == 2) {
							//
//							addressMapDong = inputValuesInMap(addressMapDong, newNode, value);

							if (addressMapDong.containsKey(value)) {
								nodeList = addressMapDong.get(value);
								if (isContainNode(newNode, nodeList)) {
									nodeList.add(newNode);
									addressMapDong.put(value, nodeList);
								}
							}

							if (addressMapDong.get(value) == null) {
								nodeList.add(newNode);
								addressMapDong.put(value, nodeList);
							}
							inputCharTree(value, dongCharTree);
						}

						if (columnindex == 3) {
							//
//							addressMapStructure = inputValuesInMap(addressMapStructure, newNode, value);
							if (addressMapStructure.containsKey(value)) {
								nodeList = addressMapStructure.get(value);
								nodeList.add(newNode);
								addressMapStructure.put(value, nodeList);
							}

							if (addressMapStructure.get(value) == null) {
								nodeList.add(newNode);
								addressMapStructure.put(value, nodeList);
							}
							inputCharTree(value, structureCharTree);
						}
					}
				}
			}
		} catch (IOException e) {
			//
			e.printStackTrace();
		}
	}
	
	private void inputCharTree(String value, CharTree charTree) {
		//
		char[] charList = new char[10];
		charList = value.toCharArray();
		
		CharNode parentCharNode = charTree.getRootNode();

		for (Character ch : charList) {
			CharNode newCharNode = new CharNode(ch);

			charTree.addNode(parentCharNode, newCharNode);
			parentCharNode = charTree.getNode(parentCharNode, ch);
		}
	}
	@Override
	public TreeNode retrieveRootNode() {
		//
		return addressTree.getRootNode();
	}

	private boolean isContainNode(TreeNode newNode, ArrayList<TreeNode> list) {
		//
		boolean isContain = false;

		for (TreeNode node : list) {
			if (node == newNode) {
				isContain = true;
			}
		}
		return isContain;
	}

	@Override
	public List<TreeNode> lookAddress(String key, String searchType) {
		//
		List<String> addressList = new ArrayList<>();
		List<TreeNode> nodeList = new ArrayList<>();

		if (searchType.equals("dong")) {
			findAddressInCharTree(key, dongCharTree, addressList);

			findTreeNodeInAddressMap(addressMapDong, addressList, nodeList);
		} else {
			findAddressInCharTree(key, structureCharTree, addressList);

			findTreeNodeInAddressMap(addressMapStructure, addressList, nodeList);
		}
		return nodeList;
	}

	private void findTreeNodeInAddressMap(Map<String, ArrayList<TreeNode>> map, List<String> addressList,
			List<TreeNode> nodeList) {
		//
		for (String getKey : map.keySet()) {

			for (String findKey : addressList) {
				if (findKey.equals(getKey)) {
					nodeList.addAll(map.get(getKey));
				}
			}
		}
	}

	private void findAddressInCharTree(String key, CharTree charTree, List<String> addressList) {
		//
		CharNode charRootNode = null;

		StringBuilder builder = new StringBuilder();
		char[] keyPieces = key.toCharArray();

		charRootNode = charTree.getRootNode();
		for (char piece : keyPieces) {
			for (CharNode childNode : charRootNode.getChildNodes()) {
				if (childNode.getValue().equals(piece)) {
					builder.append(piece);
					String address = builder.toString();

					charRootNode = childNode;

					if (address.equals(key)) {
						if (childNode.getChildNodes() != null) {
							findOfRecursion(childNode, builder, addressList);
						}
					}
				}
			}
		}
	}

	private void findOfRecursion(CharNode charNode, StringBuilder builder, List<String> list) {
		//
		if (charNode.getChildNodes().isEmpty()) {
			list.add(builder.toString());
			return;
		}

		for (CharNode node : charNode.getChildNodes()) {
			builder.append(node.getValue());
			if (!(node.getChildNodes().isEmpty())) {
				findOfRecursion(node, builder, list);
			} else {
				list.add(builder.toString());
			}
			builder.deleteCharAt(builder.length() - 1);
		}
	}

//	private Map<String, ArrayList<TreeNode>> inputValuesInMap(Map<String, ArrayList<TreeNode>> map, TreeNode newNode,
//			String value) {
//		//
//		ArrayList<TreeNode> nodeList = new ArrayList<>();
//
//		if (map.containsKey(value)) {
//			if (isContainNode(newNode, nodeList)) {
//				nodeList = map.get(value);
//				nodeList.add(newNode);
//				map.put(value, nodeList);
//			}
//		} else {
//			nodeList.add(newNode);
//			map.put(value, nodeList);
//		}
//
//		return map;
//	}
}
