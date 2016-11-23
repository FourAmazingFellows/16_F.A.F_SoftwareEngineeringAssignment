package po;

import java.sql.Date;

/**
 * 会员信息PO（继承于用户信息PO），负责持久化数据传输
 * @author sparkler
 * @version 1.0
 */
public class VipInfoPO extends UserPO {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8906068652233749202L;
	
	
	private Date birth;
    private String enterpriseID;
    private String enterprisePassword;
    
    public VipInfoPO(String userID, String password, String telNum, UserType userType, Date birth,
            String enterpriseID, String enterprisePasspord) {
        super(userID, password, telNum, userType);
        this.birth = birth;
        this.enterpriseID = enterpriseID;
        this.enterprisePassword = enterprisePasspord;
    }
    
    public void setBirth(Date birth){
        this.birth = birth;
    }
    public Date getBirth(){
        return birth;
    }
    public void setEnterpriseID(String enterpriseID){
        this.enterpriseID = enterpriseID;
    }
    public String getEnterpriseID(){
        return enterpriseID;
    }
    public void setEnterprisePasspord(String enterprisePasspord){
        this.enterprisePassword = enterprisePasspord;
    }
    public String getEnterprisePasspord(){
        return enterprisePassword;
    }
}
