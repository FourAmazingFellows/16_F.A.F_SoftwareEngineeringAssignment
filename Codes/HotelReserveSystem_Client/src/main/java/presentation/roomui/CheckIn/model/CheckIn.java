package presentation.roomui.CheckIn.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import po.RoomType;
import vo.CheckInVO;

/**
 * 入住信息的模型类
 * @author 双
 * @version 
 * @see
 */
public class CheckIn {

    private final StringProperty roomType;
    private final StringProperty roomNum;
    private final StringProperty checkInTime;
    private final StringProperty expDepartTime;
    
    private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public CheckIn(){
        this(null, 0, null, null);
    }
    
    public CheckIn(Enum<RoomType> roomType, int roomNum, Date checkInTime, Date expDepartTime){
        if(roomType!=null)
            this.roomType=new SimpleStringProperty(RoomType.enumToChinese(roomType));
        else 
            this.roomType=new SimpleStringProperty();
        this.roomNum=new SimpleStringProperty(String.valueOf(roomNum));
        if(checkInTime!=null)
            this.checkInTime=new SimpleStringProperty(dateFormat.format(checkInTime));
        else 
            this.checkInTime=new SimpleStringProperty();
        if(expDepartTime!=null)
            this.expDepartTime=new SimpleStringProperty(dateFormat.format(expDepartTime));
        else 
            this.expDepartTime=new SimpleStringProperty();
    }
    
    public StringProperty roomTypeProperty(){
        return roomType;
    }
    
    public StringProperty roomNumProperty(){
        return roomNum;
    }
    
    public StringProperty checkInTimeProperty(){
        return checkInTime;
    }
    
    public StringProperty expDepartTimeProperty(){
        return expDepartTime;
    }
    
    public void setRoomType(Enum<RoomType> roomType){
        String roomTypeStr=RoomType.enumToChinese(roomType);
        this.roomType.set(roomTypeStr);
    }
    
    public void setRoomNum(int roomNum){
        this.roomNum.set(String.valueOf(roomNum));
    }
    
    public void setCheckInTime(Date date, int hour, int minute){
        SimpleDateFormat tmpDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=tmpDateFormat.format(date);
        dateStr=dateStr+" "+hour+":"+minute+":00";
        Date tmpDate = null;
        try {
            tmpDate=dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.checkInTime.set(dateFormat.format(tmpDate));
    }
    
    public void setExpDepartTime(Date date, int hour, int minute){
        SimpleDateFormat tmpDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=tmpDateFormat.format(date);
        dateStr=dateStr+" "+hour+":"+minute+":00";
        Date tmpDate = null;
        try {
            tmpDate=dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.expDepartTime.set(dateFormat.format(tmpDate));
    }
    
    public Enum<RoomType> getRoomType(){
        return RoomType.chineseToEnum(roomType.get());
    }
    
    public int getRoomNum(){
        return Integer.parseInt(roomNum.get());
    }
    
    public Date getCheckInTime() throws ParseException{
        return dateFormat.parse(checkInTime.get());
    }
    
    public Date getExpDepartTime() throws ParseException{
        return dateFormat.parse(expDepartTime.get());
    }
    
    public CheckInVO toVO(String address) throws ParseException{
        return new CheckInVO(getRoomType(), getRoomNum(),address, 
                getCheckInTime(), getExpDepartTime());
    }
       
}
