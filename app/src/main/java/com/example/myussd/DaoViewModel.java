package com.example.myussd;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class DaoViewModel extends ViewModel {

    private MutableLiveData<Dao> daoMutableLiveData;

    public MutableLiveData<String> phoneNumber = new MutableLiveData<>();
    public MutableLiveData<String> result = new MutableLiveData<>();

    public DaoViewModel() {
        Dao dao = new Dao();
        phoneNumber.setValue(dao.getPhoneNumber());
        result.setValue(dao.getResult());
    }

    public LiveData<Dao> getDao() {
        if (daoMutableLiveData == null) {
            daoMutableLiveData = new MutableLiveData<>();
        }
        return daoMutableLiveData;
    }

}
