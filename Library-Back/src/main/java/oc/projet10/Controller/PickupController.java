package oc.projet10.Controller;

import oc.projet10.Entity.PickupList;
import oc.projet10.Entity.WaitingPickingStatus;
import oc.projet10.Repository.PickupListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PickupController {

    @Autowired
    PickupListRepository pickupListRepository;

    @PostMapping("/expiredPickups")
    public void changeStatusExpiredPickups(List<PickupList> expiredPickup){
        for (PickupList pickupList: expiredPickup) {
            pickupList.setStatus(WaitingPickingStatus.Expiree.toString());
            pickupListRepository.save(pickupList);
        }

    }
}
