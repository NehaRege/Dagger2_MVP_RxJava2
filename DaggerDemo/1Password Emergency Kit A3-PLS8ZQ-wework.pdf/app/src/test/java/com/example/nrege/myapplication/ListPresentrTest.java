//package com.example.nrege.myapplication;
//
//import android.util.Log;
//
//import com.example.nrege.myapplication.Detail.DetailPresenter;
//import com.example.nrege.myapplication.Detail.DetailPresenterImpl;
//import com.example.nrege.myapplication.List.ListPresenter;
//import com.example.nrege.myapplication.List.ListPresenterImpl;
//import com.example.nrege.myapplication.List.ListView;
//import com.example.nrege.myapplication.Models.Address;
//import com.example.nrege.myapplication.Models.Company;
//import com.example.nrege.myapplication.Models.User;
//import com.example.nrege.myapplication.Repo.Repo;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnit;
//import org.mockito.junit.MockitoRule;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//
//import static org.mockito.Matchers.eq;
//import static org.mockito.Mockito.verify;
//
///**
// * Created by nrege on 6/15/17.
// */
//@RunWith(MockitoJUnitRunner.class)
//public class ListPresentrTest {
//
//    @Rule
//    public MockitoRule rule = MockitoJUnit.rule();
//
//    @Mock
//    Repo repo;
//
//    @Mock
//    ListView listView;
//
//    @Captor
//    ArgumentCaptor<Repo.OnCallbackFinished> captor;
//
//    @Test
//    public void callSuccessfulTest() {
//
//        Address address = Mockito.spy(new Address("101 E San Fernando St", "San Jose", "95113"));
//        Company company = Mockito.spy(new Company("Google"));
//        User user1 = Mockito.spy(new User(1,"John Mac","john@gmail.com",address,"6509873442",
//                company,"john.mac@google.com"));
//
//        Address address2 = Mockito.spy(new Address("101 E San Fernando St", "San Jose", "95113"));
//        Company company2 = Mockito.spy(new Company("Google"));
//        User user2 = Mockito.spy(new User(2,"Adam Mac","adam@gmail.com",
//                address2,"6509873442",company2,"adam.mac@google.com"));
//
//        ArrayList<User> allUsers = new ArrayList<>();
//        allUsers.add(user1);
//        allUsers.add(user2);
//
//        captor = ArgumentCaptor.forClass(Repo.OnCallbackFinished.class);
//
//        ListPresenter listPresenter = new ListPresenterImpl(listView,repo);
//        listPresenter.init();
//
//        verify(repo).getUserList(captor.capture());
//
//        captor.getValue().onSuccess(allUsers,"retrofit");
//
//        verify(repo).saveUserListToSharedPrefs(allUsers);
//
//        verify(listView).setData(allUsers);
//
//    }
//
//    @Test
//    public void callFailureTest() {
//
//        captor = ArgumentCaptor.forClass(Repo.OnCallbackFinished.class);
//
//        ListPresenter listPresenter = new ListPresenterImpl(listView,repo);
//        listPresenter.init();
//
//        verify(repo).getUserList(captor.capture());
//
//        captor.getValue().onFailure(new Throwable());
//
//        verify(listView).showToast("Network call failed!");
//
//    }
//
//}
