package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

@SuppressWarnings("unchecked")
public class JsonFile_RW {

    String filePath;
    public Map<Object, Object> jsonData;

    public JsonFile_RW(String filePath){

        try {
            this.filePath = filePath;
            this.jsonData = jsonReader();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public <T> T getJsonDataAsMap(String keyName){

        try { return (T) jsonData.get(keyName);}

        catch (Exception e) { System.out.println(e.getMessage()); }

        return null;
    }

//    public <T> T getSecondMapValue(String keyOne, String keyTwo){
//
//        try{
//
//            Map<T, T> firstMap = getJsonDataAsMap(keyOne);
//            return firstMap.get(keyTwo);
//        }catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
//
//    public <T> T getThirdMapValue(String keyOne, String keyTwo, String keyThree){
//
//        try{
//            Map<T, T> firstMap = getJsonDataAsMap(keyOne);
//            Map<T, T> secondMap = (Map<T, T>) firstMap.get(keyTwo);
//            return secondMap.get(keyThree);
//        }catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
//
//    public <T> T getFourthMap(String keyOne, String keyTwo, String keyThree, String keyFour){
//
//        try{
//            Map<T, T> firstMap = getJsonDataAsMap(keyOne);
//            Map<T, T> secondMap = (Map<T, T>) firstMap.get(keyTwo);
//            Map<T, T> thirdMap = (Map<T, T>) secondMap.get(keyThree);
//            return thirdMap.get(keyFour);
//        }catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }

    ObjectMapper mapper;

    // Json file reader and return as Map
    // para: filePath - json file path
    @SuppressWarnings(value = "unchecked")
    public Map<Object, Object> jsonReader() {

        mapper = new ObjectMapper();
        Map<Object, Object> json = null;

        try {
            json = mapper.readValue(Paths.get(System.getProperty("user.dir") + filePath).toFile(), Map.class);

        } catch (Exception ex) {
            System.out.println("problem on jsonRead method");
        }
        return json;
    }

    // Json file writer
    //para: Object - Map, filePath - json File path
    public void jsonPayloadWriter() {

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(System.getProperty("user.dir") + filePath), jsonData);
        } catch (Exception ex) {
            System.out.println("problem on jsonPayloadWriter method");
        }
    }
}
