package oc.projet10.Service;

import oc.projet10.Entity.Book;
import oc.projet10.Entity.Member;
import oc.projet10.Entity.WaitingLine;
import oc.projet10.Entity.WaitingPickingStatus;
import oc.projet10.Repository.WaitingLineRepository;
import oc.projet10.bean.MemberWaitingLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WaitingLineService {

    @Autowired
    WaitingLineRepository waitingLineRepository;

    @Autowired
    WaitingLineService waitingLineService;

    public WaitingLine getWaitingLineById(int id){
        return waitingLineRepository.findById(id);
    }

    public List<WaitingLine> getWaitingListbyBook(Book book){
        System.out.println(waitingLineRepository.findAllByBookAndStatusOrderByRegisteredDateAsc(book ,WaitingPickingStatus.Actif.toString()));
        return waitingLineRepository.findAllByBookAndStatusOrderByRegisteredDateAsc(book ,WaitingPickingStatus.Actif.toString());
    }

    public List<WaitingLine> getWaitingLinesByMember(Member member){
        return waitingLineRepository.findAllByMemberAndStatus(member, WaitingPickingStatus.Actif.toString());
    }

    public WaitingLine getTheFirstOfWaitingList(Book book){
        WaitingLine firstWaitingLine = getWaitingListbyBook(book).get(0);
        return firstWaitingLine;
    }

    public void updateStatus(WaitingLine waitingLine){
        waitingLine.setStatus(WaitingPickingStatus.Terminee.toString());
        waitingLineRepository.save(waitingLine);
    }

    public WaitingLine save(WaitingLine waitingLine){
        return  waitingLineRepository.save(waitingLine);
    }

    public List<MemberWaitingLineDto> waitingLineToMemberWaitingLineDto(List<WaitingLine> waitingLines){
        List<MemberWaitingLineDto> waitingLineDto = new ArrayList<>();

        for (WaitingLine waitingLine: waitingLines
             ) {
            List<WaitingLine> waitingLineByBook = waitingLineService.getWaitingListbyBook(waitingLine.getBook());
            int placeInWaitingLine = getWaitingPositionInWaitingLine(waitingLine);
            LocalDate closestReturnDate = waitingLineByBook.get(0).registeredDate.toLocalDate();
            waitingLineDto.add(new MemberWaitingLineDto(waitingLine, placeInWaitingLine, closestReturnDate));

        }
        return waitingLineDto;
    }

    public int getWaitingPositionInWaitingLine(WaitingLine waitingLine){
       List<WaitingLine> waitingLineOfBook = waitingLineService.getWaitingListbyBook(waitingLine.getBook());
       int waitingPositionInWaitingLine = -1;
        for (WaitingLine waitingLineFromList: waitingLineOfBook
             ) {
            if (waitingLine == waitingLineFromList){waitingPositionInWaitingLine = waitingLineOfBook.indexOf(waitingLineFromList)+1;}
        }
        return waitingPositionInWaitingLine;
    }

    public void delete(WaitingLine waitingLine){
        waitingLineRepository.delete(waitingLine);
    }
}
