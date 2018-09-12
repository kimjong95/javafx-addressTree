package Address.da.file.io;

import Address.util.CharTree;

public class MemoryTreeChar {
	//
	private static MemoryTreeChar singletonMap; 
	
	private CharTree addressTree;
	
	public MemoryTreeChar() {
		//
		this.addressTree = new CharTree();
	}
	
	public static MemoryTreeChar getInstance() {
		// 
		if (singletonMap == null) {
			singletonMap = new MemoryTreeChar(); 
		}
		
		return singletonMap; 
	}
	
	public CharTree getCharTree() {
		// 
		return this.addressTree; 
	}
}
