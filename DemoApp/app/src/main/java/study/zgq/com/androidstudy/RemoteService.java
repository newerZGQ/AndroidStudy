package study.zgq.com.androidstudy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RemoteService extends Service {
    private List<Person> list = new ArrayList<>();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private IBinder binder = new PersonManager.Stub() {
        @Override
        public List<Person> getPersonList() throws RemoteException {
            list.add(new Person("a",1));
            list.add(new Person("b",2));
            return list;
        }

        @Override
        public void addPersonList(Person person) throws RemoteException {
            list.add(person);
        }
    };
}
