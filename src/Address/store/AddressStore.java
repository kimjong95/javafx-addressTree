package Address.store;

import java.util.List;

import Address.util.TreeNode;

public interface AddressStore {
	//
	public void registAddress();
	public TreeNode retrieveRootNode();
	public List<TreeNode> lookAddress(String key, String searchType);
}
