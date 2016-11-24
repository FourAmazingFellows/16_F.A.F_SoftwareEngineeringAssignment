package strategydata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.rmi.RemoteException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import data.strategydata.StrategyDAOImpl;
import dataservice.strategyDAO.StrategyDAO;
import po.StrategyPO;
import po.StrategyType;

public class StrategyDAOImplTest {

	private StrategyDAO strategyDAO;
	private String address;
    private String hotelName;
	private Enum<StrategyType> strategyType;
    private String strategyName;
    private float discount;
    private int minRoomNum;
    private String enterpriseName;
    private String securityCode;
    private Date startTime;
    private Date endTime;
    private String tradeArea;
    private int vipRank;
    
    
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		strategyDAO = new StrategyDAOImpl();
		this.address = "江苏省南京市栖霞区仙林大道163号";
		this.discount = 0.85f;
		this.strategyType = StrategyType.SpecificTimeMarket;
		this.strategyName = "中秋促销折扣";
		this.startTime = new Date(116, 8, 20);
		this.endTime = new Date(116, 8, 22);
	}

	@Test
	public void testGetStrategyList() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			ArrayList<StrategyPO> strategyPOs = strategyDAO.getStrategyList(address, strategyType);
			assertEquals(2, strategyPOs.size());
			assertEquals(address, strategyPOs.get(0).getAddress());
			assertEquals(discount, strategyPOs.get(0).getDiscount(), 0);
			assertEquals(strategyType, strategyPOs.get(0).getStrategyType());
			assertEquals(strategyName, strategyPOs.get(0).getStrategyName());
			assertEquals(sdf.format(startTime), sdf.format(strategyPOs.get(0).getStartTime()));
			assertEquals(sdf.format(endTime), sdf.format(strategyPOs.get(0).getEndTime()));
		} catch (RemoteException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}