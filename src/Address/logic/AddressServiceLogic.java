package Address.logic;

import Address.da.file.AddressStoreLogic;
import Address.service.AddressService;
import Address.store.AddressStore;
import Address.util.TreeNode;

public class AddressServiceLogic implements AddressService{
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
	public TreeNode findAddress(String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
