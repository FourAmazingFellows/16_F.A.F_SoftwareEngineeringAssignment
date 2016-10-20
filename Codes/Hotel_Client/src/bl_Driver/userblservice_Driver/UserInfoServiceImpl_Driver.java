package bl_Driver.userblservice_Driver;

import businesslogicservice.userblservice.UserInfoService;
import po.UserType;
import vo.UserVO;

/**
 * 
 * @author sparkler
 * @version 
 * @see
 */
public class UserInfoServiceImpl_Driver {
    public void drive(UserInfoService userInfoService){
        UserVO userVO = userInfoService.getUserInfo(1111, UserType.Client);
        System.out.println("The client's credit value and records are: "+userVO);
        
        boolean result = userInfoService.modifyUserInfo(userVO);
        if(result)
           System.out.println("Modify Succeed!\n");
       else
           System.out.println("Modify Failed!\n");
    }
}