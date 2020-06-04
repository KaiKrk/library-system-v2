package oc.projet10.Repository;

import oc.projet10.Entity.PickupList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickupListRepository extends JpaRepository<PickupList,Integer> {
    public PickupList findById(int id);

    public List<PickupList> findAllByStatus(String status);

}
