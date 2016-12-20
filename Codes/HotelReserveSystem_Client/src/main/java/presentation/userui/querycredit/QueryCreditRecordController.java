package presentation.userui.querycredit;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogicservice.userblservice.QueryClientCreditRecordService;
import factory.UserUIFactoryService;
import factory.UserUIFactoryServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.ClientMainApp;
import vo.CreditRecordVO;

public class QueryCreditRecordController {
	private UserUIFactoryService userFactory;
	private QueryClientCreditRecordService queryClientCreditRecord;
	private ClientMainApp mainApp;
	private String userID;
	private CreditRecordList creditRecordList;
	private ObservableList<CreditRecord> creditRecordData = FXCollections.observableArrayList();
	@FXML
	private Label creditLabel;

	@FXML
	private Button returnButton;

	@FXML
	private TableView<CreditRecord> creditTable;

	@FXML
	private TableColumn<CreditRecord, String> timeColumn;

	@FXML
	private TableColumn<CreditRecord, String> actionColumn;

	@FXML
	private TableColumn<CreditRecord, String> procassColumn;

	@FXML
	private TableColumn<CreditRecord, String> orderIDColumn;

	@FXML
	private TableColumn<CreditRecord, String> creditResultColumn;

	@FXML
	public void initialize() {
		userFactory = new UserUIFactoryServiceImpl();
		queryClientCreditRecord = userFactory.createQueryClientCreditRecordService();

		timeColumn.setCellValueFactory(cellData -> cellData.getValue().changeTimeProperty());
		orderIDColumn.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
		actionColumn.setCellValueFactory(cellData -> cellData.getValue().actionProperty());
		procassColumn.setCellValueFactory(cellData -> cellData.getValue().processProperty());
		creditResultColumn.setCellValueFactory(cellData -> cellData.getValue().creditResultProperty());
	}

	public void setMainApp(ClientMainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void showCreditRecordList(String userID) {
		this.userID = userID;
		ArrayList<CreditRecordVO> creditRecordVOs;
		try {
			creditRecordVOs = queryClientCreditRecord.queryCreditRecord(this.userID);
			if (creditRecordVOs.isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("wrong");
				alert.setHeaderText("找不到信用记录！");
				alert.setContentText("请重试！");
				alert.show();
				return;
			}
			creditRecordList.setCreditRecordList(creditRecordVOs);
			creditRecordData.clear();
			creditRecordData.addAll(creditRecordList.getCreditRecordList());
		} catch (RemoteException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("NetWork Warning");
			alert.setHeaderText("Fail to connect with the server!");
			alert.setContentText("Please check your network connection!");
			alert.showAndWait();
		}
	}

	@FXML
	public void returnButtonAction(ActionEvent event) {
		return;
	}
}