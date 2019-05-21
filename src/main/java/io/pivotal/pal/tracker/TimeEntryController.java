package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
      this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry)
    {
        TimeEntry responseTimeEntry = timeEntryRepository.create(timeEntry);
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (responseTimeEntry != null) {
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<TimeEntry>(responseTimeEntry, status);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimeEntry> read(@PathVariable long id)
    {
        TimeEntry responseTimeEntry = timeEntryRepository.find(id);
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (responseTimeEntry != null) {
            status = HttpStatus.OK;
        }
        return new ResponseEntity<TimeEntry>(responseTimeEntry, status);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TimeEntry>> list()
    {
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (timeEntryList != null) {
            status = HttpStatus.OK;
        }
        final ResponseEntity<List<TimeEntry>> listResponseEntity = new ResponseEntity<>(timeEntryList, status);
        return listResponseEntity;
    }

    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimeEntry> update(@PathVariable Long id,@RequestBody TimeEntry timeEntry)
    {
        TimeEntry responseTimeEntry = timeEntryRepository.update(id,timeEntry);
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (responseTimeEntry != null) {
            status = HttpStatus.OK;
        }
        return new ResponseEntity<TimeEntry>(responseTimeEntry, status);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id)
    {
        TimeEntry timeEntry = new TimeEntry();
        timeEntryRepository.delete(id);
        return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NO_CONTENT);
    }
}
