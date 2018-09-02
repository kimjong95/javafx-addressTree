package Address.util;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	//
	private TreeNode parentNode;
	private String value;
	private List<TreeNode> childNodes;

	public TreeNode(String newValue) {
		//
		value = newValue;
		parentNode = null;
		childNodes = new ArrayList<>();
	}

	public void addChild(TreeNode childNode) {
		//
		childNode.parentNode = this;
		childNodes.add(childNode);
	}
	
	public String getValue() {
		//
		return value;
	}
	
	public TreeNode getParentNode() {
		//
		return parentNode;
	}
	
	public void setParentNode(TreeNode setParentNode) {
		//
		this.parentNode = setParentNode;
	}
	
	public List<TreeNode> getChildNodes(){
		//
		return childNodes;
	}

}
