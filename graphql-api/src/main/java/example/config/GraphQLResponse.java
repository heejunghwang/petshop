package example.config;

import java.util.List;
import lombok.Data;

@Data
public class GraphQLResponse<T> {
    private T data;
    private List errors;
}


