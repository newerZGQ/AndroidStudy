// PersonManager.aidl
package study.zgq.com.androidstudy;

// Declare any non-default types here with import statements
import study.zgq.com.androidstudy.Person;
interface PersonManager {
    List<Person> getPersonList();
    void addPersonList(inout Person person);
}
