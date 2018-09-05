package Address.service;

import java.util.List;

import Address.util.TreeNode;

public interface AddressService {
	//
	public void addAddress();

	public List<String> findAddress(String value);
	
	public List<String> findRootNodeChilds();
}
