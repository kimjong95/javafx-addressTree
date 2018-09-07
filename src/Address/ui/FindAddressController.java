package Address.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Address.logic.AddressServiceLogic;
import Address.service.AddressService;
import Address.util.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class FindAddressController implements Initializable {
	//
	// TextField
	@FXML
	private TextField searchAddressTextField;
	@FXML
	private TextField detailAddressTextField;

	// ListView
	@FXML
	private ListView<String> siListView;
	@FXML
	private ListView<String> guListView;
	@FXML
	private ListView<String> dongListView;
	@FXML
	private ListView<String> structureListView;
	
	// TableView
	@FXML
	private TableView<Node> dongTableView;
	@FXML
	private TableColumn<Node, String> dongTableColumn;
	@FXML
	private TableView<Node> structureTableView;
	@FXML
	private TableColumn<Node, String> structureTableColumn;
	
	// Button

	private AddressService addressService;
	private List<String> treeNodeList;
	private List<Node> tempNodeList;
	private ObservableList<String> listSi;
	private ObservableList<String> listGu;
	private ObservableList<String> listDong;
	private ObservableList<String> listStructure;

	private String[] addressArray;
	private String si, gu, dong, structure;

	public FindAddressController() {
		//
		this.addressService = new AddressServiceLogic();
		this.addressArray = new String[4];
		this.treeNodeList = new ArrayList<>();
		this.tempNodeList = new ArrayList<>();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//
		this.dongTableColumn.setCellValueFactory(new PropertyValueFactory<Node, String>("value"));
		this.structureTableColumn.setCellValueFactory(new PropertyValueFactory<Node, String>("value"));
		
//		treeNodeList = FXCollections.observableArrayList();
		tempNodeList = FXCollections.observableArrayList();
		listSi = FXCollections.observableArrayList();
		listGu = FXCollections.observableArrayList();
		listDong = FXCollections.observableArrayList();
		listStructure = FXCollections.observableArrayList();

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
		addressArray[0] = selectSi;
		arrayRebuild(addressArray, 0);

		detailAddressTextField.setText(si);

		treeNodeList = addressService.findAddress(addressArray);
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
		addressArray[1] = selectGu;
		arrayRebuild(addressArray, 1);

		detailAddressTextField.setText(si + gu);
		treeNodeList = addressService.findAddress(addressArray);
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
		addressArray[2] = selectDong;
		arrayRebuild(addressArray, 2);
		
		detailAddressTextField.setText(si + gu + dong);
		treeNodeList = addressService.findAddress(addressArray);
		listStructure.addAll(treeNodeList);
		structureListView.getItems().addAll(treeNodeList);
	}

	@FXML
	private void findStructure(MouseEvent evnet) {
		//
		treeNodeList = new ArrayList<>();
		String selectStructure = structureListView.getSelectionModel().getSelectedItem();

		structure = selectStructure;
		addressArray[3] = selectStructure;

		detailAddressTextField.setText(si + gu + dong + structure);
	}

	@FXML
	private void searchDong(MouseEvent evnet) {
		//
		structureTableView.getItems().clear();
		Node getTreeNode = dongTableView.getSelectionModel().getSelectedItem();
		
		tempNodeList = getTreeNode.getChildNodes();
		
		structureTableView.getItems().addAll(tempNodeList);
		
		addressArray[0] = getTreeNode.getParentNode().getParentNode().getValue();
		addressArray[1] = getTreeNode.getParentNode().getValue();
		addressArray[2] = getTreeNode.getValue();
		arrayRebuild(addressArray, 2);
		
		detailAddressTextField.setText(addressArray[0]+addressArray[1]+addressArray[2]);
	}

	@FXML
	private void searchStructure(MouseEvent evnet) {
		//
		Node getTreeNode = structureTableView.getSelectionModel().getSelectedItem();
		
		addressArray[0] = getTreeNode.getParentNode().getParentNode().getParentNode().getValue();
		addressArray[1] = getTreeNode.getParentNode().getParentNode().getValue();
		addressArray[2] = getTreeNode.getParentNode().getValue();
		addressArray[3] = getTreeNode.getValue();
		
		detailAddressTextField.setText(addressArray[0]+addressArray[1]+addressArray[2]+addressArray[3]);
	}

	@FXML
	private void searchButton(ActionEvent evnet) {
		//		
		String address = searchAddressTextField.getText();
		
		tempNodeList = addressService.searchAddress(address);
		String subString = address.substring(address.length() - 1);
		
		if (subString.equals("µ¿")) {
			dongTableView.getItems().addAll(tempNodeList);
		} else {
			structureTableView.getItems().addAll(tempNodeList);
		}
	}

	@FXML
	private void oKButton(ActionEvent evnet) {

	}

	private void arrayRebuild(String[] array, int index) {
		//
		for (int i = index + 1; i < array.length; i++) {
			array[i] = null;
		}
	}
}
