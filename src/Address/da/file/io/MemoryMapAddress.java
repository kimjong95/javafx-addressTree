package Address.da.file.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Address.util.TreeNode;

public class MemoryMapAddress {
	//
	private static MemoryMapAddress singletonMap; 
	
//	List<TreeNode> treeList;
	
	private Map<String, ArrayList<TreeNode>> addressMap;
	
	public MemoryMapAddress() {
		//
//		this.treeList = new ArrayList<>();
		this.addressMap = new HashMap<>();
	}
	
	public static MemoryMapAddress getInstance() {
		// 
		if (singletonMap == null) {
			singletonMap = new MemoryMapAddress(); 
		}
		
		return singletonMap; 
	}
	
	public Map<String, ArrayList<TreeNode>> getAddressMap() {
		// 
		return this.addressMap; 
	}
}