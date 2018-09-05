package Address.logic;

import java.util.List;

import Address.da.file.AddressStoreLogic;
import Address.service.AddressService;
import Address.store.AddressStore;

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
	public List<String> findAddress(String value) {
		// 
		
		return addressStore.retrieveAddress(value);
	}

	@Override
	public List<String> findRootNodeChilds() {
		// 
		return addressStore.retrieveRootNodeChilds();
	}

}
