package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.List;

public interface TimeEntryRepository {

    TimeEntry create(TimeEntry timeEntry);
     TimeEntry find(long id);
     List<TimeEntry> list();
     TimeEntry update(long id,TimeEntry timeEntry);
     void delete(long id);

}
