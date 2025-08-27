package com.myproject.ridecabapp.services.impl;

import com.myproject.ridecabapp.dto.DriverDto;
import com.myproject.ridecabapp.dto.SignupDto;
import com.myproject.ridecabapp.dto.UserDto;
import com.myproject.ridecabapp.entities.Driver;
import com.myproject.ridecabapp.entities.User;
import com.myproject.ridecabapp.entities.enums.Role;
import com.myproject.ridecabapp.exceptions.RuntimeConflictException;
import com.myproject.ridecabapp.repositories.UserRepository;
import com.myproject.ridecabapp.services.AuthService;
import com.myproject.ridecabapp.services.DriverService;
import com.myproject.ridecabapp.services.RiderService;
import com.myproject.ridecabapp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.myproject.ridecabapp.entities.enums.Role.DRIVER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String[] login(String email, String password) {
        String [] tokens= new String[2];



        return tokens;
    }

    @Override
    @Transactional //either whole thing will be completed or will be rolled back
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(user != null)
                throw new RuntimeConflictException("Cannot signup, User already exists with email "+signupDto.getEmail()); //we will handle this exception seperately

        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        //we give any user a rider at first, admin will give the drivver role
        User savedUser = userRepository.save(mappedUser);

//        create user related entities
        //create user related entitities
        //creating a rider
        riderService.createNewRider(savedUser);
//        TODO add wallet related service here
        walletService.createNewWallet(savedUser);

        // also need to add wallet
        return modelMapper.map(savedUser, UserDto.class); // return userdto
    }

    @Override
    public DriverDto onboardNewDriver(Long userId,String vehicleId ) {
       User user=userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
       if(user.getRoles().contains(DRIVER))
       {
           throw new RuntimeException("User already a driver");
       }
        Driver createDriver= Driver.builder()
                .id(userId)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();
       user.getRoles().add(DRIVER);
       userRepository.save(user);
       Driver savedDriver= driverService.createNewDriver(createDriver);

       return modelMapper.map(savedDriver,DriverDto.class);

    }
}
