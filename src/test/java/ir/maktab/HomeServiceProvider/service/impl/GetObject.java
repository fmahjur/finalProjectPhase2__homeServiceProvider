package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.Utils.DateUtil;
import ir.maktab.HomeServiceProvider.data.enums.City;
import ir.maktab.HomeServiceProvider.data.model.*;
import ir.maktab.HomeServiceProvider.validation.PictureValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
@Component
public class GetObject {
    @Autowired
    private BaseServiceServiceImpl baseServiceService;
    @Autowired
    private SubServiceServiceImpl subServiceService;
    @Autowired
    private ExpertServiceImpl expertService;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private CommentServiceImp commentService;
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private OfferServiceImpl offerService;

    public void saveData(){
        baseServiceService.add(getBaseService1());
        baseServiceService.add(getBaseService2());
        baseServiceService.add(getBaseService3());
        subServiceService.add(getSubService1());
        subServiceService.add(getSubService2());
        subServiceService.add(getSubService3());
        expertService.add(getExpert1());
        expertService.add(getExpert2());
        expertService.add(getExpert3());
        customerService.add(getCustomer1());
        customerService.add(getCustomer2());
        customerService.add(getCustomer3());
        commentService.add(getComment1());
        commentService.add(getComment2());
        commentService.add(getComment3());
        orderService.add(getOrder1());
        orderService.add(getOrder2());
        orderService.add(getOrder3());
        offerService.add(getOffer1());
        offerService.add(getOffer2());
        offerService.add(getOffer3());
    }

    public BaseService getBaseService1() {
        return new BaseService(1L, "service1");
    }

    public BaseService getBaseService2() {
        return new BaseService(2L, "service2");
    }

    public BaseService getBaseService3() {
        return new BaseService(3L, "service3");
    }

    public SubService getSubService1() {
        return new SubService(1L, getBaseService1(), "subService1", "description", 100.2);
    }

    public SubService getSubService2() {
        return new SubService(2L, getBaseService2(), "subService2", "description", 200.2);
    }

    public SubService getSubService3() {
        return new SubService(3L, getBaseService3(), "subService3", "description", 300.2);
    }

    public Expert getExpert1() {
        return new Expert(1L, "fatemeh", "mahjour", "fmahjour@gmail.com", "Fm@123456", new Credit(1000L), getImage());
    }

    public Expert getExpert2() {
        return new Expert(2L, "zahra", "mahjour", "zmahjour@gmail.com", "Zm@123456", new Credit(2000L), getImage());
    }

    public Expert getExpert3() {
        return new Expert(3L, "mohammmad", "mahjour", "m.mahjour@gmail.com", "Mm@123456", new Credit(3000L), getImage());
    }

    public Customer getCustomer1() {
        return new Customer(3L, "hamid", "mahjour", "hmahjour@gmail.com", "Hm@1234568", new Credit(1500L));
    }

    public Customer getCustomer2() {
        return new Customer(4L, "ali", "mahjour", "amahjour@gmail.com", "Am@1234568", new Credit(2500L));
    }

    public Customer getCustomer3() {
        return new Customer(5L, "narges", "mahjour", "Nmahjour@gmail.com", "Nm@1234568", new Credit(3500L));
    }

    public Comment getComment1() {
        return new Comment(1L, "Comment1", 5.0, getExpert1());
    }

    public Comment getComment2() {
        return new Comment(2L, "Comment2", 4.0, getExpert2());
    }

    public Comment getComment3() {
        return new Comment(3L, "Comment3", 3.0, getExpert3());
    }

    public Address getAddress1() {
        return new Address(City.TEHRAN, "Enghelab");
    }

    public Address getAddress2() {
        return new Address(City.TEHRAN, "satarkhan");
    }

    public Address getAddress3() {
        return new Address(City.TEHRAN, "azadi");
    }

    public Orders getOrder1() {
        return new Orders(1L, "CO123", getCustomer1(), getSubService1(), "description", 200L,
                DateUtil.asDate(LocalDateTime.of(2023, 2, 5, 12, 30)),
                3, getAddress1());
    }

    public Orders getOrder2() {
        return new Orders(2L, "CO234", getCustomer2(), getSubService2(), "description", 300L,
                DateUtil.asDate(LocalDateTime.of(2023, 2, 6, 12, 30)),
                4, getAddress2());
    }

    public Orders getOrder3() {
        return new Orders(3L, "CO345", getCustomer3(), getSubService3(), "description", 400L,
                DateUtil.asDate(LocalDateTime.of(2023, 2, 7, 12, 30)),
                5, getAddress3());
    }

    public Offer getOffer1() {
        return new Offer(1L, getExpert1(), getOrder1(), 220L,
                DateUtil.asDate(LocalDateTime.of(2023, 2, 5, 13, 30)),
                4);
    }

    public Offer getOffer2() {
        return new Offer(2L, getExpert2(), getOrder2(), 320L,
                DateUtil.asDate(LocalDateTime.of(2023, 2, 6, 13, 30)),
                5);
    }

    public Offer getOffer3() {
        return new Offer(3L, getExpert1(), getOrder3(), 420L,
                DateUtil.asDate(LocalDateTime.of(2023, 2, 7, 13, 30)),
                6);
    }

    public byte[] getImage() {
        String imageFilePath = "C:\\Users\\paage\\OneDrive\\Documents\\reihaneh\\1111.jpg";
        PictureValidator.isValidImageFile(imageFilePath);
        File file = new File(imageFilePath);
        byte[] bFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bFile;
    }
}
