/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.api.senlin.v1;

import com.google.common.base.Preconditions;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.senlin.Action;

import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test cases for action on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/action")
public class ActionServiceTest extends AbstractTest {

    private static final String ACTIONS="/senlin/v1/actions.json";
    private static final String ACTION="/senlin/v1/action.json";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListAction() throws Exception{
        respondWith(ACTIONS);
        List<? extends Action> actionList = osv3().senlin().action().list();
        assertEquals(4, actionList.size());
        Preconditions.checkNotNull(actionList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Action from List : "+ actionList.get(0));
        assertEquals(actionList.get(0).getId(), "1ac0a47b-06de-44cd-a014-79981f1dec19");
    }
    @Test
    public void testGetAction() throws Exception{
        respondWith(ACTION);
        String actionID = "ffbb9175-d510-4bc1-b676-c6aba2a4ca81";
        Action action = osv3().senlin().action().get(actionID);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Action by ID : "+ action);
        assertNotNull(action);
        assertEquals(actionID, action.getId());
    }

}
