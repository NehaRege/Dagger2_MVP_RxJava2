//package com.example.nrege.myapplication;
//
//import android.util.Log;
//
//import com.example.nrege.myapplication.Detail.DetailPresenter;
//import com.example.nrege.myapplication.Detail.DetailPresenterImpl;
//import com.example.nrege.myapplication.Detail.DetailView;
//import com.example.nrege.myapplication.List.ListPresenter;
//import com.example.nrege.myapplication.List.ListPresenterImpl;
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
//import dagger.Component;
//
//import static org.mockito.Matchers.eq;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.verify;
//
//
///**
// * Created by nrege on 6/15/17.
// */
//@RunWith(MockitoJUnitRunner.class)
//public class DetailPresenterTest {
//
//    private String position = "2";
//
//    @Rule
//    public MockitoRule rule = MockitoJUnit.rule();
//
//
//    @Mock
//    DetailView detailView;
//
//    @Captor
//    ArgumentCaptor<Repo.OnCallbackFinished> captor;
//
//    @Mock
//    Repo repo;
//
//    @Test
//    public void callSuccessful_Retrofit() {
//
//        String s = "retrofit";
//
//        captor = ArgumentCaptor.forClass(Repo.OnCallbackFinished.class);
//
//        DetailPresenter detailPresenter = new DetailPresenterImpl(detailView,repo);
//        detailPresenter.init("1");
//
//        verify(repo).getSingleUser(eq("1"), captor.capture());
//
//        Address address = Mockito.spy(new Address("101 E San Fernando St", "San Jose", "95113"));
//        Company company = Mockito.spy(new Company("Google"));
//
//        User user = Mockito.spy(new User(1,"John Mac","john@gmail.com",address,"6509873442"
//                ,company,"john.mac@google.com"));
//
//        captor.getValue().onSuccess(user,s);
//
////        verify(detailView, never()).showToast(eq("No internet connection. Retrieving user info from Shared Preferences!"));
//        verify(detailView).showToast(eq("User info received from Retrofit Call"));
//
//
//        verify(repo).saveSingleUserToSharedPrefs(user);
//
//        verify(detailView).setText(
//                user.getEmail(),
//                user.getPhone(),
//                user.getName(),
//                user.getAddress().getStreet() + ", " +
//                        user.getAddress().getCity() + ", "+
//                        user.getAddress().getZipcode(),
//                user.getCompany().getName(),
//                user.getWebsite()
//        );
//    }
//
//    @Test
//    public void whenTheCallFails_ShowToast() {
//        captor = ArgumentCaptor.forClass(Repo.OnCallbackFinished.class);
//        DetailPresenter detailPresenter = new DetailPresenterImpl(detailView,repo);
//        detailPresenter.init("1");
//
//        verify(repo).getSingleUser(eq("1"),captor.capture());
//        captor.getValue().onFailure(new Throwable());
//
//        verify(detailView).showToast(eq("Network call failure"));
//
//    }
//}
