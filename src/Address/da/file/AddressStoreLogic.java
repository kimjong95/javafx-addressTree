package Address.da.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import Address.util.Node;

public class AddressStoreLogic implements AddressStore {
	//
	private AddressTree addressTree;
	private Map<String, ArrayList<Node>> addressMapDong;
	private Map<String, ArrayList<Node>> addressMapStructure;
	private CharTree charTree;

	public AddressStoreLogic() {
		//
		this.addressTree = MemoryTreeAddress.getInstance().getAddressTree();
		this.addressMapDong = MemoryMapAddress.getInstance().getAddressDongMap();
		this.addressMapStructure = MemoryMapAddress.getInstance().getAddressStructureMap();
		this.charTree = MemoryTreeChar.getInstance().getCharTree();
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
				Node parentNode = addressTree.getRootNode();
				Row row = sheet.getRow(rowindex);
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					for (columnindex = 0; columnindex <= cells; columnindex++) {
						Cell cell = row.getCell(columnindex);
						String value = "";
						ArrayList<Node> nodeList = new ArrayList<>();

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
						Node newNode = new Node(value);

						addressTree.addNode(parentNode, newNode);
						parentNode = addressTree.getNode(parentNode, value);

						if (columnindex == 2) {
							//
//							inputValuesInMap(addressMapDong, newNode, value);

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

							char[] charList = new char[10];
							charList = value.toCharArray();
							CharNode parentCharNode = charTree.getRootNode();

							for (Character ch : charList) {
								CharNode newCharNode = new CharNode(ch);

								charTree.addNode(parentCharNode, newCharNode);
								parentCharNode = charTree.getNode(parentCharNode, ch);
							}
						}

						if (columnindex == 3) {
							//
//							inputValuesInMap(addressMapStructure, newNode, value);
							if (addressMapStructure.containsKey(value)) {
								nodeList = addressMapStructure.get(value);
								nodeList.add(newNode);
								addressMapStructure.put(value, nodeList);
							}

							if (addressMapDong.get(value) == null) {
								nodeList.add(newNode);
								addressMapStructure.put(value, nodeList);
							}
							char[] charList = new char[10];
							charList = value.toCharArray();
							CharNode parentCharNode = charTree.getRootNode();

							for (Character ch : charList) {
								CharNode newCharNode = new CharNode(ch);

								charTree.addNode(parentCharNode, newCharNode);
								parentCharNode = charTree.getNode(parentCharNode, ch);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			//
			e.printStackTrace();
		}
	}

//	@Override
//	public List<Node> retrieveAddress(String[] values) {
//		//
//		Node findNode = addressTree.getRootNode();
//		List<String> addressList = new ArrayList<>();
//
//		for (String value : values) {
//
//			for (Node node : findNode.getChildNodes()) {
//				if (node.getValue().equals(value)) {
//
//					findNode = node;
//					break;
//				}
//			}
//		}
//
//		for (Node node : findNode.getChildNodes()) {
//			addressList.add(node.getValue());
//		}
//
//		return addressList;
//	}

	@Override
	public Node retrieveRootNode() {
		//
		return addressTree.getRootNode();
	}

	private boolean isContainNode(Node newNode, ArrayList<Node> list) {
		//
		boolean isContain = false;

		for (Node node : list) {
			if (node == newNode) {
				isContain = true;
			}
		}
		return isContain;
	}

	@Override
	public List<Node> lookAddress(String key, String searchType) {
		//
		List<Node> addressList = new ArrayList<>();
		CharNode charRootNode = charTree.getRootNode();

		StringBuilder builder = new StringBuilder();
		char[] keyPieces = key.toCharArray();

		for (char piece : keyPieces) {
			for (CharNode childNode : charRootNode.getChildNodes()) {
				if (childNode.getValue().equals(piece)) {
					builder.append(piece);
					break;
				}
			}
		}

		if (searchType.equals("dong")) {
			for (String getKey : addressMapDong.keySet()) {

				if (getKey.contains(builder.toString())) {
					addressList.addAll(addressMapDong.get(getKey));
				}
			}
		} else {
			for (String getKey : addressMapStructure.keySet()) {

				if (getKey.contains(builder.toString())) {
					addressList.addAll(addressMapStructure.get(getKey));
				}
			}
		}

		return addressList;
	}

	@Override
	public List<Node> retrieveNodeList(Node findNode) {
		//
		List<Node> findNodeList = new ArrayList<>();

		for (Node node : findNode.getChildNodes()) {
			findNodeList.add(node);
		}

		return findNodeList;
	}

	@Override
	public List<Node> retrieveAddress(String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

//	private void inputValuesInMap(Map<String, ArrayList<Node>> map,Node newNode,  String value) {
//		//
//		ArrayList<Node> nodeList = new ArrayList<>();
//		
//		if (map.containsKey(value)) {
//			nodeList = map.get(value);
//			if (isContainNode(newNode, nodeList)) {
//				nodeList.add(newNode);
//				map.put(value, nodeList);
//			}
//		}
//
//		if (map.get(value) == null) {
//			nodeList.add(newNode);
//			map.put(value, nodeList);
//		}
//	}
}
