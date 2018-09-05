package Address.store;

import java.util.List;

import Address.util.TreeNode;

public interface AddressStore {
	//
	public void registAddress();
	public  List<String> retrieveAddress(String value);
	public List<String> retrieveRootNodeChilds();
}
