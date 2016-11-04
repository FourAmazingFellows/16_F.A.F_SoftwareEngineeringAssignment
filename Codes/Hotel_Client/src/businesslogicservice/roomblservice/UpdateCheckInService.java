package businesslogicservice.roomblservice;

import java.util.Date;
import java.util.ArrayList;

import po.RoomType;
import vo.RoomVO;

/**
 * 给view层的更新入住信息任务提供roombl接口
 * @author 双
 * @version 
 * @see
 */
public interface UpdateCheckInService {
    
    /**
     * 获取入住信息列表
     * @param address String型， 酒店地址
     * @return ArrayList<RoomVO>，返回入住信息列表
     * @see
     */
    public ArrayList<RoomVO> getCheckInList(String address);
    
    /**
     * 按入住时间查找相应的入住信息
     * @param address String型， 酒店地址
     * @param time Date型，入住时间
     * @return ArrayList<RoomVO>型，返回入住信息列表
     * @see
     */
    public ArrayList<RoomVO> searchCheckInInfo(String address ,Date time);
    
    /**
     * 按房间类型查找相应的入住信息
     * @param address String型， 酒店地址
     * @param roomType 枚举类，酒店房间类型
     * @return ArrayList<RoomVO>型，返回入住信息列表
     * @see
     */
    public ArrayList<RoomVO> searchCheckInInfo(String address , Enum<RoomType> roomType);
    
    /**
     * 增加入住信息
     * @param address String型， 酒店地址
     * @param checkIn RoomVO型，入住信息
     * @return boolean型，返回是否增加入住信息成功
     * @see
     */
    public boolean addCheckIn(String address, RoomVO checkIn);
    
    /**
     * 修改某条入住信息
     * @param address String型， 酒店地址
     * @param checkIn RoomVO型，入住信息
     * @return boolean型，返回是否修改入住信息成功
     * @see
     */
    public boolean modifyCheckIn(String address, RoomVO checkIn);
    
    /**
     * 删除某条入住信息
     * @param address String型， 酒店地址
     * @param checkIn RoomVO型，入住信息
     * @return boolean型，返回是否删除入住信息成功
     * @see
     */
    public boolean delCheckIn(String address, RoomVO checkIn);
    
    /**
     * 检查入住信息是否符合规范
     * @param address String型， 酒店地址
     * @param checkIn RoomVO型，入住信息
     * @return boolean型，返回入住信息是否符合规范
     * @see
     */
    public boolean validCheckIn(String address, RoomVO checkIn);
    
}
