package com.example.SecurityManagementSystem.serviceImpl;

import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.entity.User;
import com.example.SecurityManagementSystem.exception.SocietyUserNotFoundException;
import com.example.SecurityManagementSystem.repository.SocietyUserRepository;
import com.example.SecurityManagementSystem.repository.UserRepository;
import com.example.SecurityManagementSystem.service.SocietyUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocietyUserServiceImpl implements SocietyUserService {

    public static final Logger logger = LoggerFactory.getLogger(SocietyUserServiceImpl.class);

    @Autowired
    private SocietyUserRepository societyUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public SocietyUser getSocietyUserByUserId(Long userId) throws SocietyUserNotFoundException {
        Optional<SocietyUser> societyUser = societyUserRepository.findByUserUserId(userId);
        if (!societyUser.isPresent()) {
            logger.error("No such society member.");
            throw new SocietyUserNotFoundException("Society member not found");
        }
        logger.debug(societyUser.get().toString());
        return societyUser.get();
    }

    @Override
    public SocietyUser addSocietyUser(SocietyUser societyUser) {
        User user = societyUser.getUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1 = userRepository.save(user);
        if (user1.getUserId() == null){
            logger.error("User data cannot be added.");
            return null;
        }
        societyUser.setUser(user1);
        return societyUserRepository.save(societyUser);
    }

    @Override
    public SocietyUser updateSocietyUserByUserId(Long userId, SocietyUser societyUser) throws SocietyUserNotFoundException {
        Optional<SocietyUser> societyUser1 = societyUserRepository.findByUserUserId(userId);
        if (!societyUser1.isPresent()) {
            logger.error("No such society member.");
            throw new SocietyUserNotFoundException("Society member not found");
        }
        if (societyUser1.get().getIsAdmin() != societyUser.getIsAdmin()){
            societyUser1.get().setIsAdmin(societyUser.getIsAdmin());
        }
        if (!societyUser1.get().getOwnerName().equals(societyUser.getOwnerName())){
            societyUser1.get().setOwnerName(societyUser.getOwnerName());
        }
        if (!societyUser1.get().getFlatNo().equals(societyUser.getFlatNo())){
            societyUser1.get().setFlatNo(societyUser.getFlatNo());
        }
        if (!societyUser1.get().getUser().getUserName().equals(societyUser.getUser().getUserName())){
            societyUser1.get().getUser().setUserName(societyUser.getUser().getUserName());
        }
        if (!societyUser1.get().getUser().getAge().equals(societyUser.getUser().getAge())){
            societyUser1.get().getUser().setAge(societyUser.getUser().getAge());
        }
        if (!societyUser1.get().getUser().getContactNo().equals(societyUser.getUser().getContactNo())){
            societyUser1.get().getUser().setContactNo(societyUser.getUser().getContactNo());
        }
        if (!societyUser1.get().getUser().getGender().equals(societyUser.getUser().getGender())){
            societyUser1.get().getUser().setGender(societyUser.getUser().getGender());
        }
        return societyUserRepository.save(societyUser1.get());

    }

    @Override
    public void deleteSocietyUser(Long userId) throws SocietyUserNotFoundException {
        Optional<SocietyUser> societyUser = societyUserRepository.findByUserUserId(userId);
        if (!societyUser.isPresent()) {
            logger.error("No such society member.");
            throw new SocietyUserNotFoundException("Society member not found");
        }
        societyUserRepository.delete(societyUser.get());
        userRepository.deleteById(userId);
        logger.info("Society member deletion successful.");
    }

    @Override
    public List<SocietyUser> getAllSocietyUsers() {
        return societyUserRepository.findAll();
    }
}
