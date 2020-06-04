package com.OC.librarybatch;

import com.OC.librarybatch.Entity.Pickup;
import com.OC.librarybatch.Service.PickupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BatchPickupServiceTest {

    @Autowired
    PickupService pickupService;

    @Test
    public void testGetActivePickups() throws Exception {
        List<Pickup> activePickup = pickupService.findAllActivePickup();
        System.out.println(activePickup.size());
        System.out.println(activePickup.get(0).getStatus());
        if (activePickup.size() > 0 ){
            assertEquals("Actif",activePickup.get(0).getStatus());
        }
    }
}
