package data.databaseutility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * 该线程启动之后，每隔1小时对未执行订单进行检查，将超时未入住的订单置为异常订单，并添加相应酒店的空房数
 * @author 原
 * @version
 * @see
 */
public class GenerateAbnormalOrder implements Runnable {

	/**
	 * 遍历订单列表，获得在当前时间之前且并未执行的订单的列表
	 * 将所有这些订单置为异常订单，并在订单对应酒店中增加对应房型的空房数量
	 * @see
	 */
	private void checkAbnormalOrders() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//初始化数据库连接
			conn = JDBC_Connection.getConnection();
			
			pstmt = conn.prepareStatement("select * from orderinfo where orderState = 1 and lastedOrderDoneTime < date_sub(sysdate(),interval 60 second)");
			
			rs = pstmt.executeQuery();
			
			//遍历结果，构造orderPO，并添加到列表中
			while(rs.next()) {
				setUndoOrdersToAbOrders(rs.getInt("orderID"));
				addSpareRoom(rs.getString("hotelAddress"), rs.getInt("roomType"), rs.getInt("num"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			//释放数据库资源
			JDBC_Connection.free(rs, conn, pstmt);
		}
	}
	
	/**
	 * 根据传过来的订单号，将该订单号对应的订单置为异常订单
	 * @param orderID 已超时的未执行订单的订单号
	 * @see checkAbnormalOrders
	 */
	private void setUndoOrdersToAbOrders(int orderID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//初始化数据库连接
			conn = JDBC_Connection.getConnection();
			//将对应的订单置为异常订单
			pstmt = conn.prepareStatement("update orderinfo set orderState = 0 where orderID = ?");
			pstmt.setInt(1, orderID);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("There is something wrong with the method setUndoOrdersToAbOrders!!!");
			e.printStackTrace();
		} finally {
			//释放数据库资源
			JDBC_Connection.free(null, conn, pstmt);
		}
	}
	
	/**
	 * 增加对应酒店的特定房型的空房数
	 * @param hotelAddress 需要增加房间数量的酒店的地址
	 * @param roomType 需要增加的空房的房型
	 * @param roomNums 需要增加的空房数量
	 * @see checkAbnormalOrders
	 */
	private void addSpareRoom(String hotelAddress, int roomType, int roomNums) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//初始化数据库连接
			conn = JDBC_Connection.getConnection();
			//根据酒店地址获得数据库数据
			Calendar calendar = Calendar.getInstance();
			String day = "" + String.format("%02d", calendar.get(Calendar.MONTH) + 1) + String.format("%02d", calendar.get(Calendar.DATE));
			pstmt = conn.prepareStatement("update room" + day + " set roomNum = roomNum + ? where hotelAddress = ? and roomType = ?");
			pstmt.setInt(1, roomNums);
			pstmt.setString(2, hotelAddress);
			pstmt.setInt(3, roomType);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			//释放数据库资源
			JDBC_Connection.free(null, conn, pstmt);
		}
	}
	
	/**
	 * 启动这个循环检查的线程，并且每隔1小时检查一次
	 */
	@Override
	public void run() {
		while(true) {
			checkAbnormalOrders();
			try {
				Thread.sleep(3600000);
			} catch (InterruptedException e) {
				System.out.println("线程挂起错误！");
				e.printStackTrace();
			}
		}
	}

}
