package Address.util;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		//
		AddressTree tree = new AddressTree();

		TreeNode parentNode = tree.getRootNode();

		tree.addNode(parentNode, "서울시");

		System.out.println(tree.getNode(parentNode, "서울시").getValue());

		parentNode = tree.getNode(parentNode, "서울시");

		tree.addNode(parentNode, "강남구");

		System.out.println(tree.getNode(parentNode, "강남구").getValue());

		parentNode = tree.getRootNode();

		tree.addNode(parentNode, "서울시");



		parentNode = tree.getNode(parentNode, "서울시");

		tree.addNode(parentNode, "강서구");

		System.out.println(tree.getNode(parentNode, "강서구").getValue());
		
		System.out.println("끝");

	}

}