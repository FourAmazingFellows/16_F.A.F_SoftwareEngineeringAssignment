package presentation.hotelui.reservedhotel;

import java.util.HashMap;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import presentation.ClientMainApp;

public class HotelCommentsPanelController {
	@FXML
	private GridPane commentsGridPane;

	@FXML
	private TextArea commentOne;

	@FXML
	private TextArea commentThree;

	@FXML
	private Label userOneLabel;

	@FXML
	private TextArea commentTwo;

	@FXML
	private Button returnButton;

	@FXML
	private Label userTwoLabel;

	@FXML
	private Label userThreeLabel;

	private ClientMainApp mainApp;
	private String hotelAddress;
	private String[] conditions;
	
	@FXML
	private void initialize() {
		userOneLabel.setText("");
		userTwoLabel.setText("");
		userThreeLabel.setText("");
	}
	
	public void setMainApp(ClientMainApp mainApp, String hotelAddress, String[] conditions) {
		this.mainApp = mainApp;
		this.hotelAddress = hotelAddress;
		this.conditions = conditions;
	}

	public void returnAction() {
		mainApp.showDetailedHotelPanel(hotelAddress, conditions);
	}

	/**
	 * 显示评论内容
	 * @param comments HashMap<String, String>
	 * @see
	 */
	public void showComments(HashMap<String, String> comments) {
		int count = 0;
		Set<String> userIDs = comments.keySet();
		for (String userID : userIDs) {
			count++;
			String comment = comments.get(userID);
			if (count == 1) {
				userOneLabel.setText(userID);
				commentOne.setText(comment);
			}else if (count == 2) {
				userTwoLabel.setText(userID);
				commentTwo.setText(comment);
			}else if (count == 3) {
				userThreeLabel.setText(userID);
				commentThree.setText(comment);
			}
		}
	}
}
