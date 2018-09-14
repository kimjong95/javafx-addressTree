package Address.da.file.io;

import Address.util.CharTree;

public class MemoryTreeChar {
	//
	private static MemoryTreeChar singletonMap; 
	
	private CharTree dongCharTree;
	private CharTree structureCharTree;
	
	public MemoryTreeChar() {
		//
		this.dongCharTree = new CharTree();
		this.structureCharTree = new CharTree();
	}
	
	public static MemoryTreeChar getInstance() {
		// 
		if (singletonMap == null) {
			singletonMap = new MemoryTreeChar(); 
		}
		
		return singletonMap; 
	}
	
	public CharTree getDongCharTree() {
		// 
		return this.dongCharTree; 
	}
	
	public CharTree getStructureCharTree() {
		// 
		return this.structureCharTree; 
	}
}
