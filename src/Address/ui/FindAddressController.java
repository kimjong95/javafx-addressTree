package Address.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Address.logic.AddressServiceLogic;
import Address.service.AddressService;
import Address.util.TreeNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FindAddressController implements Initializable{
	//
	//TextField
	@FXML private TextField searchAddressTextField;
	@FXML private TextField detailAddressTextField;
	
	//ListView
	@FXML private ListView<String> siListView;
	@FXML private ListView<String> guListView;
	@FXML private ListView<String> dongListView;
	@FXML private ListView<String> structureListView;
	
	//Button
	
	private AddressService addressService;
	private List<String> treeNodeList;
	private ObservableList<String> listSi = FXCollections.observableArrayList();
	private ObservableList<String> listGu = FXCollections.observableArrayList();
	private ObservableList<String> listDong = FXCollections.observableArrayList();
	private ObservableList<String> listStructure = FXCollections.observableArrayList();
	private String si, gu, dong, structure;
	
	public FindAddressController() {
		//
		this.addressService = new AddressServiceLogic();
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//
		treeNodeList = new ArrayList<>();
		siListView.setItems(FXCollections.observableArrayList());
		
		treeNodeList = addressService.findRootNodeChilds();
		listSi.addAll(treeNodeList);
		siListView.getItems().addAll(treeNodeList);
	}
	@FXML
	private void findSi(MouseEvent evnet) {
		//
		guListView.getItems().clear();
		dongListView.getItems().clear();
		structureListView.getItems().clear();
		treeNodeList = new ArrayList<>();
		String selectSi = siListView.getSelectionModel().getSelectedItem();
		
		si = selectSi;
		
		detailAddressTextField.setText(si);	
		
		treeNodeList = addressService.findAddress(si);
		listGu.addAll(treeNodeList);
		guListView.getItems().addAll(treeNodeList);
	}
	
	@FXML
	private void findGu(MouseEvent evnet) {
		//
		dongListView.getItems().clear();
		structureListView.getItems().clear();
		treeNodeList = new ArrayList<>();
		String selectGu = guListView.getSelectionModel().getSelectedItem();

		gu = selectGu;
		
		detailAddressTextField.setText(si+gu);	
		treeNodeList = addressService.findAddress(gu);
		listDong.addAll(treeNodeList);
		dongListView.getItems().addAll(treeNodeList);
	}
	
	@FXML
	private void findDong(MouseEvent evnet) {
		//
		structureListView.getItems().clear();
		treeNodeList = new ArrayList<>();
		String selectDong = dongListView.getSelectionModel().getSelectedItem();
		
		dong = selectDong;
		
		detailAddressTextField.setText(si+gu+dong);	
		treeNodeList = addressService.findAddress(dong);
		listStructure.addAll(treeNodeList);
		structureListView.getItems().addAll(treeNodeList);
	}
	
	@FXML
	private void findStructure(MouseEvent evnet) {
		//
		treeNodeList = new ArrayList<>();
		String selectStructure = structureListView.getSelectionModel().getSelectedItem();
		
		structure = selectStructure;
		
		detailAddressTextField.setText(si+gu+dong+structure);	
//		treeNodeList = addressService.findAddress(structure);
//		listStructure.addAll(treeNodeList);
//		structureListView.getItems().addAll(treeNodeList);
	}
	
	@FXML
	private void searchButton(ActionEvent evnet) {
		
	}
	
	@FXML
	private void oKButton(ActionEvent evnet) {
		
	}
}
