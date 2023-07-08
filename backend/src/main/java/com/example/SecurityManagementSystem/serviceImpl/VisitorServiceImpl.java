package com.example.SecurityManagementSystem.serviceImpl;

import com.example.SecurityManagementSystem.entity.Notification;
import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.entity.Visitor;
import com.example.SecurityManagementSystem.exception.SocietyUserNotFoundException;
import com.example.SecurityManagementSystem.exception.VisitorAlreadyExitException;
import com.example.SecurityManagementSystem.exception.VisitorNotFoundException;
import com.example.SecurityManagementSystem.repository.NotificationRepository;
import com.example.SecurityManagementSystem.repository.SocietyUserRepository;
import com.example.SecurityManagementSystem.repository.VisitorRepository;
import com.example.SecurityManagementSystem.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class VisitorServiceImpl implements VisitorService {

    public static final Logger logger = LoggerFactory.getLogger(VisitorServiceImpl.class);

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private SocietyUserRepository societyUserRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public Visitor updateVisitorOutTime(Visitor visitor) throws Exception {
        Optional<Visitor> visitor1 = visitorRepository.findByVisitorName(visitor.getVisitorName());
        if (!visitor1.isPresent()) {
            logger.error("Visitor data not available.");
            throw new VisitorNotFoundException("No such visitor entered.");
        }
        if (Objects.isNull(visitor1.get().getOutTime())){
            visitor1.get().setOutTime(LocalTime.now());
            visitor1.get().setVisitDuration(calculateDurationOfVisit(visitor1.get().getInTime(), LocalTime.now()));
        } else {
            logger.error("OutTime is not null.");
            throw new VisitorAlreadyExitException("This visitor has already exited.");
        }
        return visitorRepository.save(visitor1.get());
    }

    private LocalTime calculateDurationOfVisit(LocalTime inTime, LocalTime outTime) {

        int hours = (int) ChronoUnit.HOURS.between(inTime, outTime);
        int minutes = (int) (ChronoUnit.MINUTES.between(inTime, outTime) % 60);
        int seconds = (int) (ChronoUnit.SECONDS.between(inTime, outTime) % 60);

        return LocalTime.of(hours, minutes, seconds);

    }

    @Override
    public Visitor addVisitor(Visitor visitor) {
        SocietyUser societyUser = societyUserRepository.findByFlatNo(visitor.getSocietyUser().getFlatNo());
        Visitor visitor1 = Visitor.builder()
                .age(visitor.getAge())
                .contactNo(visitor.getContactNo())
                .gender(visitor.getGender())
                .date(LocalDate.now())
                .inTime(LocalTime.now())
                .outTime(null)
                .purpose(visitor.getPurpose())
                .visitorName(visitor.getVisitorName())
                .societyUser(societyUser)
                .guardName(visitor.getGuardName())
                .build();
        Visitor savedVisitor = visitorRepository.save(visitor1);

        String message = "New visitor: " + savedVisitor.getVisitorName() + ". Sent by: " + savedVisitor.getGuardName();

        Notification notification = Notification.builder()
                .message(message)
                .timestamp(LocalDateTime.now())
                .flatNo(visitor.getSocietyUser().getFlatNo())
                .visitor(savedVisitor)
                .build();
        notificationRepository.save(notification);

        return savedVisitor;
    }

    @Override
    public List<Visitor> getAllVisitorsByDate(LocalDate date) {
        return visitorRepository.findAllByDate(date);
    }

    @Override
    public List<Visitor> getAllVisitorsByGender(String gender) {
        return visitorRepository.findAllByGender(gender);
    }

    @Override
    public List<Visitor> getAllVisitorsByAge(Integer age) {
        return visitorRepository.findAllByAge(age);
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    @Override
    public List<Visitor> getAllVisitorsByFlatNo(String email) throws SocietyUserNotFoundException {
        SocietyUser societyUser = societyUserRepository.findByUserEmail(email);
        if (societyUser == null) {
            throw new SocietyUserNotFoundException("No such society user by this credentials");
        }
        return visitorRepository.findAllBySocietyUserFlatNo(societyUser.getFlatNo());
    }

    @Override
    public boolean updateVisitorApprovalStatus(Long visitorId, Visitor visitor) {
        Optional<Visitor> requestedVisitor = visitorRepository.findById(visitorId);
        if (requestedVisitor.isPresent()){
            requestedVisitor.get().setIsApproved(visitor.getIsApproved());
            requestedVisitor.get().setApproverName(visitor.getApproverName());
            visitorRepository.save(requestedVisitor.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Visitor> getAllVisitorsByDateAndFlatNo(LocalDate date, String email) throws SocietyUserNotFoundException {
        SocietyUser societyUser = societyUserRepository.findByUserEmail(email);
        if (societyUser == null) {
            throw new SocietyUserNotFoundException("No society user with such credentials");
        }
        return visitorRepository.findAllByDateAndSocietyUserFlatNo(date, societyUser.getFlatNo());
    }
}
