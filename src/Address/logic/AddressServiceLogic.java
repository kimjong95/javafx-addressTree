package Address.logic;

import java.util.List;

import Address.da.file.AddressStoreLogic;
import Address.service.AddressService;
import Address.store.AddressStore;
import Address.util.TreeNode;

public class AddressServiceLogic implements AddressService {
	//
	private AddressStore addressStore;

	public AddressServiceLogic() {
		//
		this.addressStore = new AddressStoreLogic();
	}

	@Override
	public void addAddress() {
		//
		addressStore.registAddress();
	}

	@Override
	public TreeNode findRootNode() {
		//
		return addressStore.retrieveRootNode();
	}

	@Override
	public List<TreeNode> searchAddress(String key, String searchType) {
		// 
		return addressStore.lookAddress(key, searchType);
	}
}
