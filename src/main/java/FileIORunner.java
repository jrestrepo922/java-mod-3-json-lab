
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileIORunner {
    public static String readFromFile(String fileName, boolean addNewLine) throws IOException{
        String returnString = new String();
        Scanner fileReader = null;

        try{
            // this only creates a file object but not an actual file in your directory
            File myFile = new File(fileName);
            if(!myFile.exists()) {
                // createNewFile creates the actual file in your directory. An exception will be thrown if
                // the file already exist so .exists method needs to be use
                myFile.createNewFile();
            }
            fileReader = new Scanner(myFile);
            while(fileReader.hasNextLine()){
                returnString += fileReader.nextLine();
                if(addNewLine){
                    returnString += "\n";
                }
            }
        }catch (Exception e){
            System.out.println("file does not exist!");
            e.printStackTrace();
        } finally {
            if(fileReader != null){
                fileReader.close();
            }
        }
        return returnString;
    }

    public static void writeToFile(String fileName, String text) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(text);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (fileWriter != null)
                fileWriter.close();
        }
    }

    static void printPersonListAsJSON(List<Person> personList) throws Exception {
        String json = new ObjectMapper().writeValueAsString(personList);
        System.out.println(json);
    }

    static void savePersonListAsJSON(List<Person> personList, String fileName) throws IOException, Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(fileName+ ".json"), personList);
        printPersonListAsJSON(personList);
    }

    static List<Person> peopleObjectFromJSON(String fileName)  {
        List<Person> restoredPersons = null;
        try{
            restoredPersons = Arrays.asList(
                    new ObjectMapper().readValue(new File(fileName + ".json"), Person[].class));


            for(int i = 0; i < restoredPersons.size(); i++){
                System.out.println(restoredPersons.get(i));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return restoredPersons;
    }


}

