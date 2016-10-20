package bl_Driver.strategybl_Driver;

import java.util.Date;

import businesslogic.strategybl.StrategyInfoService;
import po.OrderState;
import po.RoomType;
import vo.OrderVO;

/**
 * 
 * @author 双
 * @version 
 * @see
 */
public class StrategyInfoService_Driver {
    public void drive(StrategyInfoService strategyInfoService){
        @SuppressWarnings("deprecation")
        OrderVO orderVO=new OrderVO(1,"0001000100010001","仙林大酒店","",new Date(2016,10,16),new Date(2016,10,17),RoomType.KING_SIZE_ROOM,1,100,new Date(2016,10,16,18,0),new java.util.Date(2016, 10, 16, 20, 0),2,false,true,OrderState.NOT_DONE_ORDER,false);
        String availPromotion=strategyInfoService.getAvailblePromotionName(orderVO);
        System.out.println("the availble promotion of this order is "+availPromotion);
        
        String availMarketStrategy=strategyInfoService.getAvailbleMarketStrategyName(orderVO);
        System.out.println("the availble Market Strategy of this order is "+availMarketStrategy);
        
        int bestDiscount=strategyInfoService.getBestDiscount(orderVO);
        System.out.println("the best discount of this order is "+bestDiscount);
    }
}