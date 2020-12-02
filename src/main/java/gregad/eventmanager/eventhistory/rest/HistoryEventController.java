package gregad.eventmanager.eventhistory.rest;

import gregad.eventmanager.eventhistory.dto.EventHistoryDto;
import gregad.eventmanager.eventhistory.model.EventEntity;
import gregad.eventmanager.eventhistory.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static gregad.eventmanager.eventhistory.api.ApiConstants.*;

/**
 * @author Greg Adler
 */
@RestController
@RequestMapping(HISTORY)
public class HistoryEventController {
    @Autowired
    private HistoryService historyService;
    
    @PostMapping
    public boolean addAllEvents(@RequestBody List<EventEntity>events){
        return historyService.addAllEvent(events);
    }
    
    @GetMapping(value = SEARCH)
    public EventHistoryDto getEventById(@RequestParam long eventId,@RequestParam String ownerId){
        return historyService.getEventById(eventId,ownerId);
    }
    
    @GetMapping(value = SEARCH+BY_TITLE)
    public List<EventHistoryDto> getEventsByTitle(@RequestParam String ownerId,@RequestParam String title){
        return historyService.getEventByTitle(ownerId, title);
    }
    
    @GetMapping(value = SEARCH+BY_DATES)
    public List<EventHistoryDto> getEventsByDateRange(@RequestParam String ownerId,
                                                      @RequestParam LocalDate from,
                                                      @RequestParam LocalDate to){
        return historyService.getEventsByDate(ownerId, from, to);
    }
    
    @GetMapping(value = SEARCH+BY_NETWORKS)
    public List<EventHistoryDto> getEventsByNetworks(@RequestParam String ownerId, @RequestParam List<String>networks){
        return historyService.getEventsBySentNetworks(ownerId, networks);
    }
}