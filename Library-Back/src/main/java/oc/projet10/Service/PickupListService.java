package oc.projet10.Service;

import oc.projet10.Entity.PickupList;
import oc.projet10.Entity.WaitingLine;
import oc.projet10.Entity.WaitingPickingStatus;
import oc.projet10.Repository.PickupListRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PickupListService {

    @Autowired
    PickupListRepository pickupListRepository;

    public void save(PickupList pickupList){
        pickupListRepository.save(pickupList);
    }

    public void waitingLineToPickupLine(WaitingLine waitingLine){
        PickupList pickupList = new PickupList();
        LocalDate today = LocalDate.now();
        LocalDate expirationDate = today.plusDays(2);
        pickupList.setBook(waitingLine.getBook());
        pickupList.setExpirationDate(expirationDate);
        pickupList.setMember(waitingLine.getMember());
        pickupList.setStatus(WaitingPickingStatus.Actif.toString());
        save(pickupList);
    }
}
