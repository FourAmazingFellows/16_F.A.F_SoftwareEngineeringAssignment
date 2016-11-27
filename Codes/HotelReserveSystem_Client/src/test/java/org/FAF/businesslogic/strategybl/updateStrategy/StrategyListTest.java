package org.FAF.businesslogic.strategybl.updateStrategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import businesslogic.strategybl.exception.UnableAddStrategyException;
import businesslogic.strategybl.exception.UnableToDeleteStrategyException;
import businesslogic.strategybl.exception.UnableToModifyStrategyException;
import businesslogic.strategybl.exception.WrongInputException;
import businesslogic.strategybl.updateStrategy.MockStrategyList;
import businesslogic.strategybl.updateStrategy.StrategyItem;
import businesslogic.strategybl.updateStrategy.StrategyList;
import po.StrategyType;
import vo.StrategyVO;

public class StrategyListTest {

    private StrategyList strategyList;
    private String address;
    private Enum<StrategyType> strategyType;
    private String name;
    private StrategyVO strategyVO;
    
    @SuppressWarnings("deprecation")
    @Before
    public void setUp() throws Exception{
        address="江苏省南京市栖霞区仙林大道163号";
        strategyList=MockStrategyList.getInstance(address);
        strategyType=StrategyType.SpecificTimePromotion;
        name="双十一折扣";
        strategyVO=new StrategyVO(address, strategyType, name, 80, new Date(2016,11,10,00,00,00), new Date(2016,11,12,00,00,00));
    }
    
    @Test 
    public void testGetStrategyList(){
        ArrayList<StrategyItem> strategyItems=strategyList.getStrategyList(address, strategyType);
        assertEquals(1,strategyItems.size());
        StrategyVO strategyVOFromArray=strategyItems.get(0).toVO();
        assertEquals(strategyVO.address, strategyVOFromArray.address);
        assertEquals(strategyVO.strategyType, strategyVOFromArray.strategyType);
        assertEquals(strategyVO.strategyName, strategyVOFromArray.strategyName);
        assertEquals(strategyVO.discount, strategyVOFromArray.discount, 0.01f);
        assertEquals(strategyVO.startTime, strategyVOFromArray.startTime);
        assertEquals(strategyVO.endTime, strategyVOFromArray.endTime);
    }
    
    @Test
    public void testGetStrategyInfo(){
        StrategyItem strategyItem=strategyList.getStrategyInfo(address, strategyType, name);
        StrategyVO strategyInfo=strategyItem.toVO();
        assertEquals(strategyVO.address, strategyInfo.address);
        assertEquals(strategyVO.strategyType, strategyInfo.strategyType);
        assertEquals(strategyVO.strategyName, strategyInfo.strategyName);
        assertEquals(strategyVO.discount, strategyInfo.discount, 0.01f);
        assertEquals(strategyVO.startTime, strategyInfo.startTime);
        assertEquals(strategyVO.endTime, strategyInfo.endTime);
    }
    
    @Test
    public void testAdd(){
        boolean added = false;
        try {
            added = strategyList.add(address, strategyVO);
        } catch (UnableAddStrategyException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(added);
    }
    
    @Test
    public void testModify(){
        boolean modifyed = false;
        try {
            modifyed =strategyList.modify(address, strategyVO);
        } catch (UnableToModifyStrategyException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(modifyed);
    }
    
    @Test
    public void testDelete(){
        boolean deleted = false;
        try {
            deleted = strategyList.delete(address, strategyVO);
        } catch (UnableToDeleteStrategyException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(deleted);
    }
    
    @Test
    public void testValid(){
        boolean valied = false;
        try {
            valied =strategyList.valid(address, strategyVO);
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(valied);
    }
}
