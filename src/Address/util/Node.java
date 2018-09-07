package Address.util;

import java.util.ArrayList;
import java.util.List;

public class Node {
	//
	private Node parentNode;
	private String value;
	private List<Node> childNodes;

	public Node(String newValue) {
		//
		value = newValue;
		parentNode = null;
		childNodes = new ArrayList<>();
	}

	public void addChild(Node childNode) {
		//
		childNode.parentNode = this;
		childNodes.add(childNode);
	}
	
	public String getValue() {
		//
		return value;
	}
	
	public Node getParentNode() {
		//
		return parentNode;
	}
	
	public void setParentNode(Node setParentNode) {
		//
		this.parentNode = setParentNode;
	}
	
	public List<Node> getChildNodes(){
		//
		return childNodes;
	}

}
