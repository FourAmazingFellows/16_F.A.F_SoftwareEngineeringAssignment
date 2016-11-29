package org.FAF.businesslogic.hotelbl.checkOrderedHotel;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import businesslogic.hotelbl.checkOrderedHotel.OrderedHotelItem;
import data_Stub.HotelDAOImpl_Stub;
import dataservice.hotelDAO.HotelDAO;
import vo.BriefHotelInfoVO;

public class OrderedHotelItemTest {

	private OrderedHotelItem orderedHotel;
	private HotelDAO hotelDAO;
	private String hotelName;
	private String tradeArea;
	private String hotelAddress;
	private int starLevel;
	private float mark;
	private String city;
	
	@Before
	public void setUp() throws Exception {
		this.hotelName = "Jingling Hotel";
		this.tradeArea = "新街口";
		this.hotelAddress = "江苏省南京市栖霞区仙林大道163号";
		this.starLevel = 5;
		this.mark = 5.0f;
		this.city = "南京市";
		hotelDAO = new HotelDAOImpl_Stub(hotelName, tradeArea, hotelAddress, starLevel, mark, city);
	}

	@Test
	public void testGetBriefHotelInfo1() {
		this.orderedHotel = new OrderedHotelItem("江苏省南京市栖霞区仙林大道163号");
		orderedHotel.setHotelDAO(hotelDAO);
		BriefHotelInfoVO briefHotelInfo = orderedHotel.getBriefHotelInfo();
		assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in hotelName!", hotelName, briefHotelInfo.hotelName);
		assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in tradeArea!", tradeArea, briefHotelInfo.tradeArea);
		assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in hotelAddress!", hotelAddress, briefHotelInfo.hotelAddress);
		assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in starLevel!", starLevel, briefHotelInfo.starLevel);
		assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in mark!", mark, briefHotelInfo.mark, 0);
		assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in city!", city, briefHotelInfo.city);
	}
	
//	@Test
//	public void testGetBriefHotelInfo2() {
//		this.orderedHotel = new OrderedHotelItem("江苏省南京市栖霞区仙林大道166号");
//		orderedHotel.setHotelDAO(hotelDAO);
//		BriefHotelInfoVO briefHotelInfo = orderedHotel.getBriefHotelInfo();
//		assertNull(briefHotelInfo);
//	}

}
