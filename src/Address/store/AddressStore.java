package Address.store;

import java.util.List;

import Address.util.Node;

public interface AddressStore {
	//
	public void registAddress();
	public  List<Node> retrieveAddress(String[] values);
	public Node retrieveRootNode();
	public List<Node> lookAddress(String key, String searchType);
	public List<Node> retrieveNodeList(Node findNode);
}
