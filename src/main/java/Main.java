import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // your code here
//        Person person1 = new Person("Juan", "Restrepo", 1990, 9,22);
//        Person person2 = new Person("Susan", "Francia", 1990, 7,27);
//        List<Person> People = new ArrayList<Person>(Arrays.asList(person1,person2));
//        FileIORunner.savePersonListAsJSON(People,"people" );
      FileIORunner.peopleObjectFromJSON("people");
    }
}
