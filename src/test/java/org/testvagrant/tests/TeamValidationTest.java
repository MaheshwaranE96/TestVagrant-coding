package org.testvagrant.tests;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testvagrant.utils.ApiUtils;

import java.util.LinkedHashMap;
import java.util.List;

public class TeamValidationTest {
    ApiUtils apiUtils = new ApiUtils();
    JsonPath json = apiUtils.jsonFileToJson("./src/main/resources/TeamRCB.json");
    List<LinkedHashMap> listOfPlayers = json.getList("player");

    @Test(description = "This test is to validate that the team has only 4 foreign players")
    public void validateForeignPlayersCount(){
        try{
            int foreignPlayersCount = 0;
            for (LinkedHashMap player : listOfPlayers)
                if (!player.get("country").toString().trim().equalsIgnoreCase("india"))
                    foreignPlayersCount++;
            Assert.assertEquals(foreignPlayersCount, 4, "The team has more/less than 4 foreign players");
            Reporter.log("The team has only 4 foreign players as expected", true);
        }catch (Exception e){
            Reporter.log("Failed in method with Exception: "+e.getLocalizedMessage()+" in method: "+
                    this.getClass().getEnclosingMethod(), true);
            Assert.fail();
        }
    }
    @Test(description = "This test is to validate that the team has at least one wicket keeper")
    public void validateWicketKeeperCount(){
        try{
            int wicketKeeperCount = 0;
            for (LinkedHashMap player : listOfPlayers)
                if (player.get("role").toString().trim().equalsIgnoreCase("wicket-keeper"))
                    wicketKeeperCount++;
            Assert.assertTrue(wicketKeeperCount>=1, "The team does not have any wicket keeper");
            Reporter.log("The team has 1 or more wicket keepers as expected, number of wicket keepers: "
                    +wicketKeeperCount, true);
        }catch (Exception e){
            Reporter.log("Failed in method with Exception: "+e.getLocalizedMessage()+" in method: "+
                    this.getClass().getEnclosingMethod(), true);
            Assert.fail();
        }
    }
}
