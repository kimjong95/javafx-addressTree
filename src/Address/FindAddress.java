package Address;

import Address.logic.AddressServiceLogic;
import Address.service.AddressService;

public class FindAddress {

	public static void main(String[] args) {
		//
		AddressService addressService = new AddressServiceLogic();
		
		addressService.addAddress();
	}

}
