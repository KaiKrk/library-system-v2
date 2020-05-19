package oc.projet10.Service;

import oc.projet10.Entity.Book;
import oc.projet10.Entity.WaitingLine;
import oc.projet10.Entity.WaitingPickingStatus;
import oc.projet10.Repository.WaitingLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingLineService {

    @Autowired
    WaitingLineRepository waitingLineRepository;

    public List<WaitingLine> getWaitingListbyBook(Book book){
        return waitingLineRepository.findAllByBookAndStatusOrderByRegisteredDateAsc(book ,WaitingPickingStatus.Actif.toString());
    }

    public WaitingLine getTheFirstOfWaitingList(Book book){
        WaitingLine firstWaitingLine = getWaitingListbyBook(book).get(0);
        return firstWaitingLine;
    }

    public void updateStatus(WaitingLine waitingLine){
        waitingLine.setStatus(WaitingPickingStatus.Terminee.toString());
        waitingLineRepository.save(waitingLine);
    }
}
