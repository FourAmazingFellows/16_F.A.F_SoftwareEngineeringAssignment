package businesslogic.orderbl.checkAbnormalOrder;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import businesslogic.utilitybl.POList2VOList;
import dataservice.orderDAO.OrderDAO;
import po.OrderState;
import rmi.RemoteHelper;
import vo.BriefOrderInfoVO;
import vo.OrderVO;

public class AbnormalOrderList {
	private ArrayList<BriefOrderInfoVO> abnormalOrderList;
	private OrderDAO orderDaoService;
	private POList2VOList poTransformer;
	private OrderVO detailedAbnormalOrder;

	public AbnormalOrderList() {
		abnormalOrderList = new ArrayList<BriefOrderInfoVO>();
		orderDaoService = RemoteHelper.getInstance().getOrderDAO();
		poTransformer = new POList2VOList();
	}

	public ArrayList<BriefOrderInfoVO> getAbnormalOrderList(Date date) throws RemoteException {
		abnormalOrderList = poTransformer.briefPo2voList(orderDaoService.getAllAbnormalList(date));
		return abnormalOrderList;
	}

	public OrderVO getDetailedOrder(String orderID) throws RemoteException {
		detailedAbnormalOrder = poTransformer.orderPO2VO(orderDaoService.getDetailedOrder(orderID));

		// 检查该订单号对应的订单是不是异常订单 - Codes
		if(detailedAbnormalOrder.orderState == OrderState.ABNORMAL_ORDER)
			return detailedAbnormalOrder;
		else{
			System.out.println("所求订单号不是异常订单");
			return null;
		}
	}
}
