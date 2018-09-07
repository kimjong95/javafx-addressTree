package Address.util;

import java.util.List;

public class AddressTree {
	//
	private Node rootNode;
	private int size;

	public AddressTree() {
		//
		rootNode = new Node("");
		size = 1;
	}

	public Node getRootNode() {
		//
		return rootNode;
	}

	public void addNode(Node parentNode, Node newNode) {
		//

		newNode.setParentNode(parentNode);

		if (isHaveNode(parentNode, newNode)) {
			return;
		}

		parentNode.addChild(newNode);

		size++;
	}

	public Node getNode(Node parentNode, String value) {
		//
		List<Node> childList = parentNode.getChildNodes();
		for (Node node : childList) {
			if (node.getValue().equals(value)) {
				return node;
			}
		}
		return null;
	}

	public Node getNodeOfValue(String value) {
		//
		Node parentNode = rootNode;

		Node findNode = getNodeOfRecursion(parentNode, value);

		return findNode;
	}

	public Node getNodeOfRecursion(Node node, String value) {
		//
		Node isGetNode = node;
		for (Node findNode : node.getChildNodes()) {

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

	private boolean isHaveNode(Node parentNode, Node findNode) {
		//
		boolean isHave = false;

		List<Node> childList = parentNode.getChildNodes();
		for (Node node : childList) {
			if (node.getValue().equals(findNode.getValue())) {
				isHave = true;
			}
		}
		return isHave;
	}
}
