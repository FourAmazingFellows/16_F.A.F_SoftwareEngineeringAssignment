package bl_Driver.userblservice_Driver;


import java.sql.Date;

import businesslogicservice.userblservice.SignVipService;
import vo.VipInfoVO;

public class SignVipServiceImpl_Driver {
    @SuppressWarnings("deprecation")
    public void drive(SignVipService signVipService){
       VipInfoVO VipInfoVO = new VipInfoVO("原", "qwe123", "12327777345", new Date(2016,11,12), "hotel123", "12341234");
        
        boolean result2 = signVipService.signRegularVip(VipInfoVO);
        if(result2)
           System.out.println("Sign Succeed!\n");
       else
           System.out.println("Sign Failed!\n");
        
        boolean result3 = signVipService.signEnterpriseVip(VipInfoVO);
        if(result3)
           System.out.println("Sign Succeed!\n");
       else
           System.out.println("Sign Failed!\n");
        
    }
}
