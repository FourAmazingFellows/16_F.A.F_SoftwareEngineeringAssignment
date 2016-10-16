package po;

import java.util.HashMap;

/**
 * 酒店详细信息的实体类，负责持久化数据传输
 * @author 原
 * @version 1.0
 * @see
 */
public class HotelPO extends HotelBriefInfoPO {
	
	private String briefIntroduction;
	private String facilityAndService;
	private HashMap<RoomType, Integer> roomTypeAndPrice;
	private HashMap<String, String> comments;
	
	public String getBriefIntroduction() {
		return briefIntroduction;
	}
	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}
	public String getFacilityAndService() {
		return facilityAndService;
	}
	public void setFacilityAndService(String facilityAndService) {
		this.facilityAndService = facilityAndService;
	}
	public HashMap<RoomType, Integer> getRoomTypeAndPrice() {
		return roomTypeAndPrice;
	}
	public void setRoomTypeAndPrice(HashMap<RoomType, Integer> roomTypeAndPrice) {
		this.roomTypeAndPrice = roomTypeAndPrice;
	}
	public HashMap<String, String> getComments() {
		return comments;
	}
	public void setComments(HashMap<String, String> comments) {
		this.comments = comments;
	}
	
	
}
