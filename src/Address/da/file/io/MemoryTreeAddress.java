package Address.da.file.io;

import Address.util.AddressTree;

public class MemoryTreeAddress {
	//
	private static MemoryTreeAddress singletonMap; 
	
	private AddressTree addressTree;
	
	public MemoryTreeAddress() {
		//
		this.addressTree = new AddressTree();
	}
	
	public static MemoryTreeAddress getInstance() {
		// 
		if (singletonMap == null) {
			singletonMap = new MemoryTreeAddress(); 
		}
		
		return singletonMap; 
	}
	
	public AddressTree getAddressTree() {
		// 
		return this.addressTree; 
	}
}
