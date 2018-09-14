package Address.service;

import java.util.List;

import Address.util.TreeNode;

public interface AddressService {
	//
	public void addAddress();
	public TreeNode findRootNode();
	public List<TreeNode> searchAddress(String key, String searchType);
	
}
