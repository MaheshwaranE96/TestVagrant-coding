package org.testvagrant.tests;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testvagrant.utils.ApiUtils;

import java.util.LinkedHashMap;
import java.util.List;

public class TeamValidation {
    @Test(description = "This test is to validate that the team has only 4 foreign players")
    public void validateForeignPlayersCount(){
        try{
            ApiUtils apiUtils = new ApiUtils();
            JsonPath json = apiUtils.jsonFileToJson("./main/resources/TeamRCB.json");
            List<LinkedHashMap> listOfPlayers = json.getList("player");
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
}
