package oc.projet10.Controller;

import oc.projet10.Entity.PickupList;
import oc.projet10.Entity.WaitingPickingStatus;
import oc.projet10.Repository.PickupListRepository;
import oc.projet10.Service.PickupListService;
import oc.projet10.bean.PickupDto;
import oc.projet10.bean.PickupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PickupController {

    @Autowired
    PickupListRepository pickupListRepository;

    @Autowired
    PickupListService pickupListService;

    @PostMapping("/expiredPickups")
    public ResponseEntity<PickupDto> changeStatusExpiredPickups(@RequestBody PickupRequest expiredPickup){
            PickupList expPickups  = pickupListService.findPickupListById(expiredPickup.getId());
            expPickups.setStatus(WaitingPickingStatus.Expiree.toString());
            pickupListService.save(expPickups);
        return new ResponseEntity(expiredPickup,HttpStatus.OK);
    }

    @GetMapping("/activePickups")
    public List<PickupDto> getActivePickups(){
       return pickupListService.getActivePickups();
    }
}
