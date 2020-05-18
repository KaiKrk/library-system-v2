package oc.projet10.Repository;

import oc.projet10.Entity.PickupList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupListRepository extends JpaRepository<PickupList,Integer> {
}
