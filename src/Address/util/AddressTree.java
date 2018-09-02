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
	
	public void addNode(TreeNode parentNode, String value) {
		//
		TreeNode newNode = new TreeNode(value);
		newNode.setParentNode(parentNode);
		
		if(isHaveNode(parentNode, newNode)) {
			return;
		}
		
		parentNode.addChild(newNode);
		
		size++;
	}
	
	public TreeNode getNode(TreeNode parentNode, String value) {
		//
		List<TreeNode> childList = parentNode.getChildNodes();
		for(TreeNode node : childList) {
			if(node.getValue().equals(value)) {
				return node;
			}
		}
		return null;
	}
	
	public int size() {
		//
		return size;
	}
	
	private boolean isHaveNode(TreeNode parentNode, TreeNode findNode) {
		//
		boolean isHave = false;
		
		List<TreeNode> childList = parentNode.getChildNodes();
		for(TreeNode node : childList) {
			if(node.getValue().equals(findNode.getValue())) {
				isHave = true;
			}
		}
		return isHave;
	}
}
