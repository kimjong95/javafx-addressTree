package Address.util;

import java.util.List;

public class CharTree {
	//
	private CharNode rootNode;
	private int size;
	private Character rot;
	
	public CharTree() {
		//
		rootNode = new CharNode(rot);
		size = 1;
	}

	public CharNode getRootNode() {
		//
		return rootNode;
	}

	public void addNode(CharNode parentNode, CharNode newNode) {
		//

		newNode.setParentNode(parentNode);

		if (isHaveNode(parentNode, newNode)) {
			return;
		}

		parentNode.addChild(newNode);

		size++;
	}

	public CharNode getNode(CharNode parentNode, Character value) {
		//
		List<CharNode> childList = parentNode.getChildNodes();
		for (CharNode node : childList) {
			if (node.getValue().equals(value)) {
				return node;
			}
		}
		return null;
	}

	public CharNode getNodeOfValue(Character value) {
		//
		CharNode parentNode = rootNode;

		CharNode findNode = getNodeOfRecursion(parentNode, value);

		return findNode;
	}

	public CharNode getNodeOfRecursion(CharNode node, Character value) {
		//
		CharNode isGetNode = node;
		for (CharNode findNode : node.getChildNodes()) {

			isGetNode = getNode(isGetNode, value);
			if (isGetNode == null) {
				
				isGetNode = getNodeOfRecursion(findNode, value);
				if (isGetNode != null) {
					break;
				}
			} else {
				return isGetNode;
				}
			}
	
		return isGetNode;
	}

	public int size() {
		//
		return size;
	}

	private boolean isHaveNode(CharNode parentNode, CharNode findNode) {
		//
		boolean isHave = false;

		List<CharNode> childList = parentNode.getChildNodes();
		for (CharNode node : childList) {
			if (node.getValue().equals(findNode.getValue())) {
				isHave = true;
			}
		}
		return isHave;
	}
}
