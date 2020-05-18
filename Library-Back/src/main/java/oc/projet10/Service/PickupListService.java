package oc.projet10.Service;

import oc.projet10.Entity.PickupList;
import oc.projet10.Repository.PickupListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PickupListService {

    @Autowired
    PickupListRepository pickupListRepository;

    public void save(PickupList pickupList){
        pickupListRepository.save(pickupList);
    }
}
