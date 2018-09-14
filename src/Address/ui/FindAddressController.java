package Address.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Address.logic.AddressServiceLogic;
import Address.service.AddressService;
import Address.util.TreeNode;
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
	private TableView<TreeNode> dongTableView;
	@FXML
	private TableColumn<TreeNode, String> dongTableColumn;
	@FXML
	private TableView<TreeNode> structureTableView;
	@FXML
	private TableColumn<TreeNode, String> structureTableColumn;
	@FXML
	private TableView<TreeNode> siTableView;
	@FXML
	private TableColumn<TreeNode, String> siTableColumn;
	@FXML
	private TableView<TreeNode> guTableView;
	@FXML
	private TableColumn<TreeNode, String> guTableColumn;
	@FXML
	private TableView<TreeNode> searchTableView;
	@FXML
	private TableColumn<TreeNode, String> searchTableColumn;

	private AddressService addressService;
	private List<TreeNode> tempNodeList;

	public FindAddressController() {
		//
		this.addressService = new AddressServiceLogic();
		this.tempNodeList = new ArrayList<>();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//
		this.dongTableColumn.setCellValueFactory(new PropertyValueFactory<TreeNode, String>("value"));
		this.structureTableColumn.setCellValueFactory(new PropertyValueFactory<TreeNode, String>("value"));
		this.siTableColumn.setCellValueFactory(new PropertyValueFactory<TreeNode, String>("value"));
		this.guTableColumn.setCellValueFactory(new PropertyValueFactory<TreeNode, String>("value"));
		this.searchTableColumn.setCellValueFactory(new PropertyValueFactory<TreeNode, String>("value"));

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
		TreeNode getTreeNode = siTableView.getSelectionModel().getSelectedItem();

		tempNodeList = getTreeNode.getChildNodes();

		guTableView.getItems().addAll(tempNodeList);
		
		detailAddressTextField.setText(getTreeNode.getValue());
	}

	@FXML
	private void findGu(MouseEvent evnet) {
		//
		dongTableView.getItems().clear();
		structureTableView.getItems().clear();
		TreeNode getTreeNode = guTableView.getSelectionModel().getSelectedItem();

		tempNodeList = getTreeNode.getChildNodes();

		dongTableView.getItems().addAll(tempNodeList);
		
		StringBuilder builder = new StringBuilder();
		builder.append(getTreeNode.getParentNode().getValue()).append(" ");
		builder.append(getTreeNode.getValue());
		
		detailAddressTextField.setText(builder.toString());
	}

	@FXML
	private void findDong(MouseEvent evnet) {
		//
		structureTableView.getItems().clear();
		TreeNode getTreeNode = dongTableView.getSelectionModel().getSelectedItem();

		tempNodeList = getTreeNode.getChildNodes();

		structureTableView.getItems().addAll(tempNodeList);
		
		StringBuilder builder = new StringBuilder();
		builder.append(getTreeNode.getParentNode().getParentNode().getValue()).append(" ");
		builder.append(getTreeNode.getParentNode().getValue()).append(" ");
		builder.append(getTreeNode.getValue());
		
		detailAddressTextField.setText(builder.toString());
	}

	@FXML
	private void findStructure(MouseEvent evnet) {
		//
		TreeNode getTreeNode = structureTableView.getSelectionModel().getSelectedItem();
		
		StringBuilder builder = new StringBuilder();
		builder.append(getTreeNode.getParentNode().getParentNode().getParentNode().getValue()).append(" ");
		builder.append(getTreeNode.getParentNode().getParentNode().getValue()).append(" ");
		builder.append(getTreeNode.getParentNode().getValue()).append(" ");
		builder.append(getTreeNode.getValue());
		
		detailAddressTextField.setText(builder.toString());
	}

	@FXML
	private void search(MouseEvent evnet) {
		//
		siTableView.getItems().clear();
		guTableView.getItems().clear();
		dongTableView.getItems().clear();
		structureTableView.getItems().clear();
		
		TreeNode getTreeNode = searchTableView.getSelectionModel().getSelectedItem();
		
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
	private void searchKeyPressed(KeyEvent event) {
		//
		searchTableView.getItems().clear();
		
		String searchAddress = searchAddressTextField.getText();

		String searchType = checkSearchType(event);

		tempNodeList = addressService.searchAddress(searchAddress, searchType);

		searchTableView.getItems().addAll(tempNodeList);

	}

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
