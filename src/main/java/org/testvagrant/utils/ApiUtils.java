package org.testvagrant.utils;

import io.restassured.path.json.JsonPath;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * This class contains the utility methods required for Api operations
 * @author mahesh
 */
public class ApiUtils {

    /**
     * This method returns the JsonPath object of provided json file
     * @param pathToJsonFile - Path to the desired json file
     * @return io.restassured.path.json.JsonPath
     * @since 07/08/2022
     */
    public JsonPath jsonFileToJson(String pathToJsonFile){
        JsonPath json = null;
        try{
            // Convert the json file contents into a String
            String jsonFileAsString = FileUtils.readFileToString(new File(pathToJsonFile), StandardCharsets.UTF_8);
            // Convert the json file contents String into a JsonPath object to return
            json = new JsonPath(jsonFileAsString);
        }catch (Exception e){
            Reporter.log("Failed in method with Exception: "+e.getLocalizedMessage()+" in method: "+
                    this.getClass().getEnclosingMethod(), true);
            Assert.fail();
        }
        return json;
    }
}
