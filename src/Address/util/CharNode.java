package Address.util;

import java.util.ArrayList;
import java.util.List;

public class CharNode {
	//
	private CharNode parentNode;
	private Character value;
	boolean isLast;
	private List<CharNode> childNodes;

	public CharNode(Character newValue) {
		//
		value = newValue;
		parentNode = null;
		isLast = false;
		childNodes = new ArrayList<>();
	}

	public void addChild(CharNode childNode) {
		//
		childNode.parentNode = this;
		childNodes.add(childNode);
	}
	
	public Character getValue() {
		//
		return value;
	}
	
	public CharNode getParentNode() {
		//
		return parentNode;
	}
	
	public void setParentNode(CharNode setParentNode) {
		//
		this.parentNode = setParentNode;
	}
	
	public List<CharNode> getChildNodes(){
		//
		return childNodes;
	}

}