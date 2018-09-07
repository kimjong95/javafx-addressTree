package Address.service;

import java.util.List;

import Address.util.Node;

public interface AddressService {
	//
	public void addAddress();

	public List<String> findAddress(String[] values);
	
	public List<String> findRootNodeChilds();
	
	public List<Node> searchAddress(String key);
	
	public List<Node> findNodeList(Node findNode);
}
