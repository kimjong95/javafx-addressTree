package Address.service;

import java.util.List;

import Address.util.Node;

public interface AddressService {
	//
	public void addAddress();

	public List<Node> findAddress(String[] values);
	
	public Node findRootNode();
	
	public List<Node> searchAddress(String key, String searchType);
	
	public List<Node> findNodeList(Node findNode);
	
}
