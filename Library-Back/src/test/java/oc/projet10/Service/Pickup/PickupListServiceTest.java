package oc.projet10.Service.Pickup;

import oc.projet10.Entity.PickupList;
import oc.projet10.Entity.WaitingLine;
import oc.projet10.Service.PickupListService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PickupListServiceTest {

    @Autowired
    PickupListService pickupListService;

    WaitingLine newWaitingLine = new WaitingLine();


    @Test
    public void testGetActivePickups(){
        assertTrue(pickupListService.getActivePickups() != null);
    }

    @Test
    public void testCreatePickupFromWaitingLine(){
        PickupList pickup = pickupListService.waitingLineToPickupLine(newWaitingLine);
        assertEquals(pickupListService.getPickupList(pickup.getId()).getStatus(),"Actif");
        pickupListService.delete(pickup);
    }

}
