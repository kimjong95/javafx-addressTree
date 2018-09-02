package Address.store;

import Address.util.TreeNode;

public interface AddressStore {
	//
	public void registAddress();
	public TreeNode retrieveAddress(String value);
}
