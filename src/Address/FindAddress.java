package Address;

import Address.logic.AddressServiceLogic;
import Address.service.AddressService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FindAddress extends Application{

	public static void main(String[] args) {
		//
		AddressService addressService = new AddressServiceLogic();
		
		addressService.addAddress();
		System.out.println("Add finished.");
		
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		// 
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/FindAddress.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("FindAddress");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
