package oc.projet10.Controller;

import oc.projet10.Entity.PickupList;
import oc.projet10.Entity.WaitingPickingStatus;
import oc.projet10.Repository.PickupListRepository;
import oc.projet10.Service.PickupListService;
import oc.projet10.bean.PickupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PickupController {

    @Autowired
    PickupListRepository pickupListRepository;

    @Autowired
    PickupListService pickupListService;

    @PostMapping("/expiredPickups")
    public void changeStatusExpiredPickups(List<PickupList> expiredPickup){
        for (PickupList pickupList: expiredPickup) {
            pickupList.setStatus(WaitingPickingStatus.Expiree.toString());
            pickupListRepository.save(pickupList);
        }

    }

    @GetMapping("/activePickups")
    public List<PickupDto> getActivePickups(){
       return pickupListService.getActivePickups();
    }
}
