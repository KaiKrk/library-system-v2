package oc.projet10.Service;

import oc.projet10.Entity.*;
import oc.projet10.Repository.PickupListRepository;
import oc.projet10.bean.BookingDto;
import oc.projet10.bean.PickupDto;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PickupListService {

    @Autowired
    PickupListRepository pickupListRepository;

    @Autowired
    WaitingLineService waitingLineService;

    @Autowired
    EmailService emailService;

    @Autowired
    BookService bookService;

    public PickupList findPickupListById(int id){return pickupListRepository.findById(id);}

    public PickupList save(PickupList pickupList){
        return pickupListRepository.save(pickupList);
    }

    public PickupList getPickupList(int id){
        return pickupListRepository.findById(id);
    }

    public PickupList waitingLineToPickupLine(WaitingLine waitingLine){
        PickupList pickupList = new PickupList();
        LocalDate today = LocalDate.now();
        LocalDate expirationDate = today.plusDays(2);
        pickupList.setBook(waitingLine.getBook());
        pickupList.setExpirationDate(expirationDate);
        pickupList.setMember(waitingLine.getMember());
        pickupList.setStatus(WaitingPickingStatus.Actif.toString());
        return save(pickupList);
    }


    public List<PickupDto> getActivePickups(){
        List<PickupDto> activePickups = pickupListToDto(pickupListRepository.findAllByStatus(WaitingPickingStatus.Actif.toString()));
        return activePickups;
    }

    public List<PickupDto> pickupListToDto(List<PickupList> pickupLists){
        List<PickupDto> pickupDtoList = new ArrayList<>();
        for (PickupList pickupList : pickupLists) {
            pickupDtoList.add(new PickupDto(pickupList));
        }
        return pickupDtoList;
    }

    public void delete(PickupList pickup){
        pickupListRepository.delete(pickup);
    }

    public void endPickup(PickupList pickupList) throws Exception {
        Book returnedBook = pickupList.getBook();
        List<WaitingLine> waitingListsForThisBook = waitingLineService.getWaitingListbyBook(returnedBook);
        System.out.println("Membre dans la liste d'attente " +waitingListsForThisBook.size());
        if (waitingListsForThisBook.size() != 0){
            WaitingLine waitingLinefirst = waitingLineService.getTheFirstOfWaitingList(returnedBook);
            String email = waitingLinefirst.getMember().getEmail();
            emailService.sendEmailForPickup(email, waitingLinefirst.getBook());
            pickupList.setStatus(WaitingPickingStatus.Expiree.toString());
            save(pickupList);
            Book book = pickupList.getBook();
            int actualCopies = pickupList.getBook().getCopies();
            book.setCopies(actualCopies+1);
            bookService.save(book);
            waitingLineService.updateStatus(waitingLinefirst);
        }
    }
}
