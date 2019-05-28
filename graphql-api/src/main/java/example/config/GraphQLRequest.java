package example.config;

import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class GraphQLRequest {

  private String query;
  private Map<String, Object> variables;

}


