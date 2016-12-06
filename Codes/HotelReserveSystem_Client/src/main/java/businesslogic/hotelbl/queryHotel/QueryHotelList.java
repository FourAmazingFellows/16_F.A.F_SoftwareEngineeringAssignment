package businesslogic.hotelbl.queryHotel;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.hotelDAO.HotelDAO;
import po.BriefHotelInfoPO;
import po.BriefOrderInfoPO;
import rmi.RemoteHelper;

public class QueryHotelList {
	private HotelDAO hotelDAO;
	
	public QueryHotelList() {
		this.hotelDAO = RemoteHelper.getInstance().getHotelDAO();
	}
	
	public ArrayList<BriefHotelInfoPO> getHotelBriefInfoListByQuerying(String[] condition, ArrayList<BriefOrderInfoPO> orderedHotelList) {
		ArrayList<BriefHotelInfoPO> orderedHotelInfoPOs;
		try {
			orderedHotelInfoPOs = hotelDAO.getHotelBriefInfoListByQuerying(condition, orderedHotelList);
			return orderedHotelInfoPOs;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
