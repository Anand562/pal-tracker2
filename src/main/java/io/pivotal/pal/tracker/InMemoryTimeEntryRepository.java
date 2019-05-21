package io.pivotal.pal.tracker;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long,TimeEntry> timeEntryMap = new HashMap<Long,TimeEntry>();
    private long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry)
    {
        Long id = currentId++;
        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntryMap.put(id, newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long id)
    {
       return timeEntryMap.get(id);
    }

    @Override
    public List<TimeEntry> list()
    {
        return new ArrayList<>(timeEntryMap.values());
    }

    @Override
    public TimeEntry update(long id,TimeEntry timeEntry)
    {
        if (find(id) == null) return null;

        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntryMap.replace(id, updatedEntry);
        return updatedEntry;
    }

    @Override
    public void delete(long id)
    {

            timeEntryMap.remove(id);

    }
}
