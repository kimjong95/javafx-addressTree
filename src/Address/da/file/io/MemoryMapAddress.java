package Address.da.file.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Address.util.TreeNode;

public class MemoryMapAddress {
	//
	private static MemoryMapAddress singletonMap; 
	
	private Map<String, ArrayList<TreeNode>> addressDongMap;
	private Map<String, ArrayList<TreeNode>> addressStructureMap;
	
	public MemoryMapAddress() {
		//
		this.addressDongMap = new HashMap<>();
		this.addressStructureMap = new HashMap<>();
	}
	
	public static MemoryMapAddress getInstance() {
		// 
		if (singletonMap == null) {
			singletonMap = new MemoryMapAddress(); 
		}
		
		return singletonMap; 
	}
	
	public Map<String, ArrayList<TreeNode>> getAddressDongMap() {
		// 
		return this.addressDongMap; 
	}
	
	public Map<String, ArrayList<TreeNode>> getAddressStructureMap() {
		// 
		return this.addressStructureMap; 
	}
}