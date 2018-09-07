package Address.store;

import java.util.List;

import Address.util.Node;

public interface AddressStore {
	//
	public void registAddress();
	public  List<String> retrieveAddress(String[] values);
	public Node retrieveRootNodeChilds();
	public List<Node> lookAddress(String key);
	public List<Node> retrieveNodeList(Node findNode);
}
