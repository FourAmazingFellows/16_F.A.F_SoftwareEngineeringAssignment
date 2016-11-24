package org.FAF.businesslogic.hotelbl.commentOnHotel;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import businesslogic.hotelbl.OrderInfo;
import businesslogic.hotelbl.commentOnHotel.CommentableOrderList;
import businesslogic.orderbl.MockOrderInfoImpl;
import po.OrderState;
import po.RoomType;
import vo.OrderVO;

public class CommentableOrderListTest {

	private CommentableOrderList commentableOrderList;
	private OrderInfo orderInfo;
	private String userID;
	private String orderID;
	private String hotelName;
	private String hotelAddress;
	private Date beginDate;
	private Date finishDate;
	private RoomType roomType;
	private int num;
	private int totalPrice;
	private Enum<OrderState> orderState;
	private Date orderProducedTime;
	private Date lastedOrderDoneTime;
	private int numOfPerson;
	private boolean isChildren;
	private boolean isOnSale;
	private boolean isCommented;
	private HashMap<RoomType, Integer> roomTypeAndPrice;
	private HashMap<String, String> comments;
	private HashMap<RoomType, Integer> roomTypeAndNums;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		orderInfo = new MockOrderInfoImpl();
		this.orderID = "0001000100010001";
		this.userID = "19970206";
		this.hotelName = "汉庭酒店";
		this.hotelAddress = "江苏省南京市栖霞区仙林大道163号";
		this.beginDate = new Date(2016, 12, 20);
		this.finishDate = new Date(2016, 12, 21);
		this.roomType = RoomType.STANDARD_ROOM;
		this.num = 1;
		this.totalPrice = 200;
		this.orderState = OrderState.NOT_DONE_ORDER;
		this.orderProducedTime = new Date(2016, 12, 15);
		this.lastedOrderDoneTime = new Date(2016, 12, 20);
		this.numOfPerson = 2;
		this.isChildren = false;
		this.isOnSale = false;
		this.isCommented = false;
		roomTypeAndPrice = new HashMap<>();
		roomTypeAndPrice.put(RoomType.KING_SIZE_ROOM, 1000);
		comments = new HashMap<>();
		comments.put("原", "该酒店服务到位，应有尽有！");
		roomTypeAndNums = new HashMap<>();
		roomTypeAndNums.put(RoomType.KING_SIZE_ROOM, 20);
	}

	@Test
	public void testGetCommentableOrderList() {
		commentableOrderList = new CommentableOrderList("原");
		commentableOrderList.setOrderInfo(orderInfo);
		ArrayList<OrderVO> orderVOs = commentableOrderList.getCommentableOrderList();
		assertEquals(1, orderVOs.size());
		assertEquals(orderID, orderVOs.get(0).orderID);
		assertEquals(userID, orderVOs.get(0).userID);
		assertEquals(hotelName, orderVOs.get(0).hotelName);
		assertEquals(hotelAddress, orderVOs.get(0).hotelAddress);
		assertEquals(beginDate, orderVOs.get(0).beginDate);
		assertEquals(finishDate, orderVOs.get(0).finishDate);
		assertEquals(roomType, orderVOs.get(0).roomType);
		assertEquals(num, orderVOs.get(0).num);
		assertEquals(totalPrice, orderVOs.get(0).totalPrice);
		assertEquals(orderState, orderVOs.get(0).orderState);
		assertEquals(orderProducedTime, orderVOs.get(0).orderProducedTime);
		assertEquals(lastedOrderDoneTime, orderVOs.get(0).lastedOrderDoneTime);
		assertEquals(numOfPerson, orderVOs.get(0).numOfPerson);
		assertEquals(isChildren, orderVOs.get(0).isChildren);
		assertEquals(isOnSale, orderVOs.get(0).isOnSale);
		assertEquals(isCommented, orderVOs.get(0).isCommented);
	}

}