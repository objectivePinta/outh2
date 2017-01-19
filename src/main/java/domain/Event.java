package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Event {
  private String name;

  @JsonIgnore
  public static List<Event> manyEvents() {
    List<Event> events = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      events.add(Event.builder().name("event " + Integer.toString(i)).build());
    }
    return events;
  }
}
