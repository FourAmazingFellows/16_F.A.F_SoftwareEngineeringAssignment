package businesslogicservice.orderblservice;

import vo.OrderVO;

/**
 * 撤销订单服务
 * @author Accident
 * @version 1.0
 * @see
 */
public interface WithdrawOrderService {
	/**
	 * 判断该订单是否可以被撤销
	 * @param vo 订单VO
	 * @return 该订单当前是否可以被撤销
	 * @see
	 */
	public boolean isWithdrawable(OrderVO vo); 

	/**
	 * 判断此次撤销操作是否需要扣除信用值
	 * @param vo 订单VO
	 * @return 此次撤销操作是否需要扣除信用值
	 * @see
	 */
	public boolean isTooLate(OrderVO vo); 

	/**
	 * 撤销订单（（扣除信用值），系统将订单置为撤销状态，记录撤销时间，更新空房列表）
	 * @param vo 订单VO
	 * @return boolean 
	 * @see
	 */
	public boolean withdrawOrder(OrderVO vo); 
}