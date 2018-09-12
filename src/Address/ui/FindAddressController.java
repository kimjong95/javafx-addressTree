package Address.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Address.logic.AddressServiceLogic;
import Address.service.AddressService;
import Address.util.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class FindAddressController implements Initializable {
	//
	// TextField
	@FXML
	private TextField searchAddressTextField;
	@FXML
	private TextField detailAddressTextField;

	// CheckBox
	@FXML
	private RadioButton dongRadioButton;
	@FXML
	private RadioButton structureRadioButton;
	@FXML
	private ToggleGroup searchType;

	// TableView
	@FXML
	private TableView<Node> dongTableView;
	@FXML
	private TableColumn<Node, String> dongTableColumn;
	@FXML
	private TableView<Node> structureTableView;
	@FXML
	private TableColumn<Node, String> structureTableColumn;
	@FXML
	private TableView<Node> siTableView;
	@FXML
	private TableColumn<Node, String> siTableColumn;
	@FXML
	private TableView<Node> guTableView;
	@FXML
	private TableColumn<Node, String> guTableColumn;
	@FXML
	private TableView<Node> searchTableView;
	@FXML
	private TableColumn<Node, String> searchTableColumn;

	// Button

	private AddressService addressService;
	private List<Node> tempNodeList;

	private List<String> addressArray;
	private String si, gu, dong, structure;

	public FindAddressController() {
		//
		this.addressService = new AddressServiceLogic();
		this.addressArray = new ArrayList<>();
		this.tempNodeList = new ArrayList<>();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//
		this.dongTableColumn.setCellValueFactory(new PropertyValueFactory<Node, String>("value"));
		this.structureTableColumn.setCellValueFactory(new PropertyValueFactory<Node, String>("value"));
		this.siTableColumn.setCellValueFactory(new PropertyValueFactory<Node, String>("value"));
		this.guTableColumn.setCellValueFactory(new PropertyValueFactory<Node, String>("value"));
		this.searchTableColumn.setCellValueFactory(new PropertyValueFactory<Node, String>("value"));

		this.dongRadioButton.setSelected(true);
		tempNodeList = addressService.findRootNode().getChildNodes();
		siTableView.getItems().addAll(tempNodeList);
	}

	@FXML
	private void findSi(MouseEvent evnet) {
		//
		guTableView.getItems().clear();
		dongTableView.getItems().clear();
		structureTableView.getItems().clear();
		Node getTreeNode = siTableView.getSelectionModel().getSelectedItem();

		tempNodeList = getTreeNode.getChildNodes();

		guTableView.getItems().addAll(tempNodeList);

	}

	@FXML
	private void findGu(MouseEvent evnet) {
		//
		dongTableView.getItems().clear();
		structureTableView.getItems().clear();
		Node getTreeNode = guTableView.getSelectionModel().getSelectedItem();

		tempNodeList = getTreeNode.getChildNodes();

		dongTableView.getItems().addAll(tempNodeList);
	}

	@FXML
	private void findDong(MouseEvent evnet) {
		//
		structureTableView.getItems().clear();
		Node getTreeNode = dongTableView.getSelectionModel().getSelectedItem();

		tempNodeList = getTreeNode.getChildNodes();

		structureTableView.getItems().addAll(tempNodeList);
	}

	@FXML
	private void findStructure(MouseEvent evnet) {
		//
//		treeNodeList = new ArrayList<>();
//		String selectStructure = structureListView.getSelectionModel().getSelectedItem();
//
//		structure = selectStructure;
//		addressArray[3] = selectStructure;
//
//		detailAddressTextField.setText(si + gu + dong + structure);
	}

	@FXML
	private void search(MouseEvent evnet) {
		//
		siTableView.getItems().clear();
		guTableView.getItems().clear();
		dongTableView.getItems().clear();
		structureTableView.getItems().clear();
		
		Node getTreeNode = searchTableView.getSelectionModel().getSelectedItem();
		
		if(dongRadioButton.isSelected()) {
			dongTableView.getItems().addAll(getTreeNode);
			guTableView.getItems().addAll(getTreeNode.getParentNode());
			siTableView.getItems().addAll(getTreeNode.getParentNode().getParentNode());
		} else {
			structureTableView.getItems().addAll(getTreeNode);
			dongTableView.getItems().addAll(getTreeNode.getParentNode());
			guTableView.getItems().addAll(getTreeNode.getParentNode().getParentNode());
			siTableView.getItems().addAll(getTreeNode.getParentNode().getParentNode().getParentNode());
		}
		
		
	}

	@FXML
	private void searchButton(KeyEvent event) {
		//
		searchTableView.getItems().clear();

		String searchAddress = searchAddressTextField.getText();

		String searchType = checkSearchType(event);

		tempNodeList = addressService.searchAddress(searchAddress, searchType);

		searchTableView.getItems().addAll(tempNodeList);

	}

//	@FXML
//	private void searchButton(ActionEvent evnet) {
//		//		
//		dongTableView.getItems().clear();
//		structureTableView.getItems().clear();
//		
//		String address = searchAddressTextField.getText();
//		
//		tempNodeList = addressService.searchAddress(address);
//		String subString = address.substring(address.length() - 1);
//		
//		if (subString.equals("동")) {
//			dongTableView.getItems().addAll(tempNodeList);
//		} else {
//			structureTableView.getItems().addAll(tempNodeList);
//		}
//	}

	@FXML
	private void oKButton(ActionEvent evnet) {
		//
		System.out.println(detailAddressTextField.getText());
		System.exit(0);
	}

	@FXML
	private String checkSearchType(KeyEvent event) {
		if (dongRadioButton.isSelected()) {
			return "dong";
		} else {
			return "structure";
		}
	}

}
