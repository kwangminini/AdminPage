package com.example.admin.service;

import com.example.admin.ifs.CrudInterface;
import com.example.admin.model.entity.User;
import com.example.admin.model.enumclass.UserStatus;
import com.example.admin.model.network.Header;
import com.example.admin.model.network.request.UserApiRequest;
import com.example.admin.model.network.response.UserApiResponse;
import com.example.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Service //프로젝트의 서비스 로직 담당 , 데이터 변경 수정 생성
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    //1. request data
    //2. user 생성
    //3. 생성된 데이터 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        //1. request data
        UserApiRequest userApiRequest = request.getData();

        //2. user생성
        User user = User.builder()
                        .account(userApiRequest.getAccount())
                        .password(userApiRequest.getPassword())
                        .status(UserStatus.REGISTERED)
                        .phoneNumber(userApiRequest.getPhoneNumber())
                        .email(userApiRequest.getEmail())
                        .registeredAt(LocalDateTime.now())
                        .build();

        User newUser = userRepository.save(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // id -> repository getOne, getById
        Optional<User> optional = userRepository.findById(id);
        // user -> userApiResponse return

        return optional
                .map(user -> response(user))
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        // 1. data
        UserApiRequest userApiRequest = request.getData();

        // 2. id -> user 데이터를 찾고
        Optional<User> optional = userRepository.findById(userApiRequest.getId());
        return optional.map(user -> {
            // 3.update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
        })
                .map(user->userRepository.save(user)) //update
                .map(updateUser->response(updateUser))  //userApiResponse
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        // id -> repository -> user
        Optional<User> optional = userRepository.findById(id);
        //repository -> delete
        return optional.map(user -> {
            userRepository.delete(user);
            return Header.OK();
        })
                .orElseGet(()->Header.ERROR("데이터 없음"));

        // return response
    }
    private Header<UserApiResponse> response(User user){
        //user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) //todo 암호화, 길이제한
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return Header.OK(userApiResponse);
    }
}
