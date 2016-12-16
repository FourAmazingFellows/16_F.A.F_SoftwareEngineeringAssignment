package presentation.strategyui.manageHotelPromotion;

import java.text.ParseException;
import java.util.Optional;

import businesslogic.strategybl.exception.WrongInputException;
import businesslogicservice.strategyblservice.UpdateStrategyService;
import factory.StrategyUIFactoryService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import po.StrategyType;
import presentation.roomui.CheckIn.CheckInEditPanelController;
import presentation.roomui.util.LocalDateAdapter;
import presentation.strategyui.model.Strategy;
import vo.StrategyVO;

public class PromotionEditPanelController {

    @FXML
    private TabPane strategyTypeTabPane;

    @FXML
    private Tab birthdayPromotionTab;

    @FXML
    private Tab multiRoomPromotionTab;

    @FXML
    private Tab cooperationEnterPrisePromotionTab;

    @FXML
    private Tab specialTimePromotionTab;

    @FXML
    private TextField strategyNameTextField1;

    @FXML
    private TextField discountTextField1;

    @FXML
    private TextField strategyNameTextField2;

    @FXML
    private TextField discountTextField2;

    @FXML
    private TextField minRoomTextField2;

    @FXML
    private TextField strategyNameTextField3;

    @FXML
    private TextField discountTextField3;

    @FXML
    private TextField cooperationEnterpriseTextField3;

    @FXML
    private TextField securityCodeTextField3;

    @FXML
    private TextField strategyNameTextField4;

    @FXML
    private TextField discountTextField4;

    @FXML
    private DatePicker startTimeDatePicker4;

    @FXML
    private DatePicker endTimeDatePicker4;

    private Stage dialogStage;
    private Strategy strategy;
    private boolean isConfirmed = false;

    private StrategyUIFactoryService strategyUIFactoryService;
    private UpdateStrategyService updateStrategyService = strategyUIFactoryService.createUpdateStrategyService();
    private String address;
    private boolean isNewaPromotion;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setStrategy(Strategy strategy, String address, boolean isNewaPromotion) {
        // 把传进来的strategy设为成员变量，便于修改
        this.strategy = strategy;
        this.address = address;
        // 把其他策略类型的tab设置为disable,并把不能修改的东西设成disable,如果是修改strategy,把默认值设为原始值
        if (strategy.getStrategyType() == StrategyType.BirthdayPromotion) {
            cooperationEnterPrisePromotionTab.setDisable(true);
            multiRoomPromotionTab.setDisable(true);
            specialTimePromotionTab.setDisable(true);
            if(!isNewaPromotion){
                strategyNameTextField1.setText(strategy.getStrategyName());
                discountTextField1.setText(String.valueOf(strategy.getDiscount()));
            }
        } else if (strategy.getStrategyType() == StrategyType.CooperationEnterprisePromotion) {
            birthdayPromotionTab.setDisable(true);
            multiRoomPromotionTab.setDisable(true);
            specialTimePromotionTab.setDisable(true);
            if(!isNewaPromotion){
                strategyNameTextField3.setText(strategy.getStrategyName());
                discountTextField3.setText(String.valueOf(strategy.getDiscount()));
                cooperationEnterpriseTextField3.setText(strategy.getEnterpriseName());
                securityCodeTextField3.setText(strategy.getSecurityCode());
            }
        } else if (strategy.getStrategyType() == StrategyType.MultiRoomPromotion) {
            birthdayPromotionTab.setDisable(true);
            cooperationEnterPrisePromotionTab.setDisable(true);
            specialTimePromotionTab.setDisable(true);
            if(!isNewaPromotion){
                strategyNameTextField2.setText(strategy.getStrategyName());
                discountTextField2.setText(String.valueOf(strategy.getDiscount()));
                minRoomTextField2.setText(String.valueOf(strategy.getMinRoomNum()));
            }
        } else if (strategy.getStrategyType() == StrategyType.SpecificTimePromotion) {
            specialTimePromotionTab.setDisable(true);
            cooperationEnterPrisePromotionTab.setDisable(true);
            multiRoomPromotionTab.setDisable(true);
            if(!isNewaPromotion){
                strategyNameTextField4.setText(strategy.getStrategyName());
                discountTextField4.setText(String.valueOf(strategy.getDiscount()));
                try {
                    startTimeDatePicker4.setValue(LocalDateAdapter.fromDate(strategy.getStartTime()));
                    endTimeDatePicker4.setValue(LocalDateAdapter.fromDate(strategy.getEndTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!isNewaPromotion) {
            strategyNameTextField1.setDisable(true);
        }
    }

    @FXML
    void handleCancel() {
        dialogStage.close();
    }

    @FXML
    void handleConfirm() {
        // 判断输入的数据是否有效
        boolean inputValid = false;
        while (!inputValid) {
            if (isInputValid()) {
                inputValid = true;
            }
        }

        // 弹出对话框请求确认
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("确认添加或修改策略");
        alert.setHeaderText("你是否确定要增加或修改该策略？");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            isConfirmed = true;
        } else {
            isConfirmed = false;
            handleCancel();
            return;
        }

        // 把新建或修改的strategy传给上一个界面
        strategy.setStrategyName(strategyNameTextField1.getText());
        strategy.setDiscount(Integer.parseInt(discountTextField1.getText()));
        if (strategy.getStrategyType() == StrategyType.MultiRoomPromotion) {
            strategy.setMinRoomNum(Integer.parseInt(minRoomTextField2.getText()));
        } else if (strategy.getStrategyType() == StrategyType.CooperationEnterprisePromotion) {
            strategy.setEnterpriseName(cooperationEnterpriseTextField3.getText());
        } else if (strategy.getStrategyType() == StrategyType.SpecificTimePromotion) {
            strategy.setStartTime(LocalDateAdapter.toDate(startTimeDatePicker4.getValue()));
            strategy.setEndTime(LocalDateAdapter.toDate(endTimeDatePicker4.getValue()));
        }

    }

    private boolean isInputValid() {
        if (strategy.getStrategyType() == StrategyType.BirthdayPromotion) {
            if(strategyNameTextField1.getText()==""){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("策略信息错误");
                alert.setHeaderText("策略名称空缺");
                alert.setContentText("请输入策略名称");

                alert.showAndWait();
                return false;
            }
            if (discountTextField1.getText()==""||!isDigit(discountTextField1.getText())) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("策略信息错误");
                alert.setHeaderText("折扣百分比错误");
                alert.setContentText("请在折扣百分比中输入数字");

                alert.showAndWait();
                return false;
            }
        }
        if(strategy.getStrategyType()==StrategyType.CooperationEnterprisePromotion){
            if(strategyNameTextField3.getText()==""||cooperationEnterpriseTextField3.getText()==""
                    ||securityCodeTextField3.getText()==""){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("策略信息错误");
                alert.setHeaderText("策略信息空缺");
                alert.setContentText("请完善策略信息");

                alert.showAndWait();
                return false;
            }
            if (discountTextField3.getText()==""||!isDigit(discountTextField3.getText())) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("策略信息错误");
                alert.setHeaderText("折扣百分比错误");
                alert.setContentText("请在折扣百分比中输入数字");

                alert.showAndWait();
                return false;
            }
        }
        if(strategy.getStrategyType()==StrategyType.MultiRoomPromotion){
            if(strategyNameTextField2.getText()==""){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("策略信息错误");
                alert.setHeaderText("策略名称空缺");
                alert.setContentText("请输入策略名称");

                alert.showAndWait();
                return false;
            }
            if (discountTextField2.getText()==""||!isDigit(discountTextField2.getText())) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("策略信息错误");
                alert.setHeaderText("折扣百分比错误");
                alert.setContentText("请在折扣百分比中输入数字");

                alert.showAndWait();
                return false;
            }
            if (minRoomTextField2.getText()==""||!isDigit(minRoomTextField2.getText())) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("策略信息错误");
                alert.setHeaderText("最少房间数错误");
                alert.setContentText("请在最少房间数中输入数字");

                alert.showAndWait();
                return false;
            }
        }
        if(strategy.getStrategyType()==StrategyType.SpecificTimePromotion){
            if(strategyNameTextField4.getText()==""){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("策略信息错误");
                alert.setHeaderText("策略名称空缺");
                alert.setContentText("请输入策略名称");

                alert.showAndWait();
                return false;
            }
            if (discountTextField4.getText()==""||!isDigit(discountTextField4.getText())) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("策略信息错误");
                alert.setHeaderText("折扣百分比错误");
                alert.setContentText("请在折扣百分比中输入数字");

                alert.showAndWait();
                return false;
            }
            if(startTimeDatePicker4.getValue()==null||endTimeDatePicker4.getValue()==null){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("策略信息错误");
                alert.setHeaderText("时间空缺");
                alert.setContentText("请选择开始时间和结束时间");

                alert.showAndWait();
                return false;
            }
        }
        StrategyVO strategyVO = null;
        try {
            strategyVO=strategy.toVO(address);
        } catch (ParseException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("策略信息错误");
            alert.setHeaderText("信息格式错误");
            alert.setContentText("");
            alert.showAndWait();
            return false;
        }
        try {
            if(updateStrategyService.valid(address, strategyVO)){
                return true;
            }
        } catch (WrongInputException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("策略信息错误");
            alert.setHeaderText("策略信息错误");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
    }
    
    private boolean isDigit(String str){
        return CheckInEditPanelController.isDigit(str);
    }
    
    //在mainApp的方法
    /**
    public boolean showPromotionEditDialog(Strategy strategy, String address, boolean isNewaPromotion) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HotelMainApp.class.getResource("strategyui/manegeHotelPromotion/PromotionEditPanel.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Promotion");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PromotionEditPanelController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStrategy(strategy, address, isNewaPromotion);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isConfirmed();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    */
}
