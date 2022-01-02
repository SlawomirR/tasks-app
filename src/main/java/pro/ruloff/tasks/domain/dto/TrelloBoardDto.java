package pro.ruloff.tasks.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBoardDto {

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

/*
    @JsonProperty("closed")
    private boolean isClosed;
*/

  @JsonProperty("lists")
  private List<TrelloListDto> lists;
}
