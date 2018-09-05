package Address.util;

import java.util.List;

public class AddressTree {
	//
	private TreeNode rootNode;
	private int size;

	public AddressTree() {
		//
		rootNode = new TreeNode("");
		size = 1;
	}

	public TreeNode getRootNode() {
		//
		return rootNode;
	}

	public void addNode(TreeNode parentNode, TreeNode newNode) {
		//
//		TreeNode newNode = new TreeNode(value);

		newNode.setParentNode(parentNode);

		if (isHaveNode(parentNode, newNode)) {
			return;
		}

		parentNode.addChild(newNode);

		size++;
	}

	public TreeNode getNode(TreeNode parentNode, String value) {
		//
		List<TreeNode> childList = parentNode.getChildNodes();
		for (TreeNode node : childList) {
			if (node.getValue().equals(value)) {
				return node;
			}
		}
		return null;
	}

	public TreeNode getNodeOfValue(String value) {
		//
		TreeNode parentNode = rootNode;
//		for(TreeNode node : rootNode.getChildNodes()) {
//			if(node.getValue().equals(value)){
//				parentNode = node;
//			}
//		}
//		
		TreeNode findNode = getNodeOfRecursion(parentNode, value);

		return findNode;
	}

	public TreeNode getNodeOfRecursion(TreeNode node, String value) {
		//
		TreeNode isGetNode = node;
		System.out.println(node.getValue());
		for (TreeNode findNode : node.getChildNodes()) {
			
			System.out.println(findNode.getValue());
			isGetNode = getNode(isGetNode, value);
			if(isGetNode == null) {
				isGetNode = getNodeOfRecursion(findNode, value);
				if(isGetNode != null) {
					break;
				}
			} else {
				return isGetNode;
			}		
//			if (findNode.getValue().equals(value)) {
//				isGetNode = findNode;
//			} else {
//				if(!findNode.getChildNodes().isEmpty()) {
//					isGetNode = getNodeOfChild(findNode, value);
//				}
//			}
			
//			if (isGetNode == null) {
//				return isGetNode;
//			}
		}
		return isGetNode;
	}

//	public List<TreeNode> getNodes(TreeNode parentNode){
//		//
//		TreeNode findNode = rootNode;
//		
//		if(parentNode.equals(rootNode)) {
//			return rootNode.getChildNodes();
//		} else {
//			return parentNode.get
//		}
//		
//	}

	public int size() {
		//
		return size;
	}

	private boolean isHaveNode(TreeNode parentNode, TreeNode findNode) {
		//
		boolean isHave = false;

		List<TreeNode> childList = parentNode.getChildNodes();
		for (TreeNode node : childList) {
			if (node.getValue().equals(findNode.getValue())) {
				isHave = true;
			}
		}
		return isHave;
	}
}
