package Address.util;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		//
		AddressTree tree = new AddressTree();

		TreeNode parentNode = tree.getRootNode();

		tree.addNode(parentNode, "�����");

		System.out.println(tree.getNode(parentNode, "�����").getValue());

		parentNode = tree.getNode(parentNode, "�����");

		tree.addNode(parentNode, "������");

		System.out.println(tree.getNode(parentNode, "������").getValue());

		parentNode = tree.getRootNode();

		tree.addNode(parentNode, "�����");



		parentNode = tree.getNode(parentNode, "�����");

		tree.addNode(parentNode, "������");

		System.out.println(tree.getNode(parentNode, "������").getValue());
		
		System.out.println("��");

	}

}