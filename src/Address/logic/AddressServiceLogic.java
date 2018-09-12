package Address.logic;

import java.util.ArrayList;
import java.util.List;

import Address.da.file.AddressStoreLogic;
import Address.service.AddressService;
import Address.store.AddressStore;
import Address.util.Node;

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
	public List<Node> findAddress(String[] values) {
		// 
		
		return addressStore.retrieveAddress(values);
	}

	@Override
	public Node findRootNode() {
		//
		return addressStore.retrieveRootNode();
	}

	@Override
	public List<Node> searchAddress(String key, String searchType) {
		// 
		return addressStore.lookAddress(key, searchType);
	}

	@Override
	public List<Node> findNodeList(Node findNode) {
		// 
		return addressStore.retrieveNodeList(findNode);
	}
	


}
