package example.config;

import com.google.common.collect.ImmutableMap;
import graphql.ExceptionWhileDataFetching;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphQLService {

  @Autowired
  private GraphQL graphQL;

  public GraphQLResponse<?> execute(GraphQLRequest request, Object context) {
    GraphQLResponse qlResponseBuilder = new GraphQLResponse();

    ExecutionInput executionInput = ExecutionInput.newExecutionInput()
        .query(request.getQuery())
        .context(context)
        .variables(request.getVariables())
        .build();
    ExecutionResult executionResult = graphQL.execute(executionInput);
    if (executionResult.getErrors().size() > 0) {
      List<Map<String, ?>> errors = new ArrayList<>();

      executionResult.getErrors().forEach(e -> {
        if(e instanceof ExceptionWhileDataFetching) {
          ExceptionWhileDataFetching ewd = (ExceptionWhileDataFetching)e;
          if(ewd.getException() != null && ewd.getException() instanceof ValidationException) {
            ValidationException ve = (ValidationException)ewd.getException();
            return;
          }
          errors.add(ImmutableMap.of("errorType", e.getErrorType(), "message", ewd.getException().getMessage()));
          return;
        }
        errors.add(ImmutableMap.of("errorType", e.getErrorType(), "message", e.getMessage()));
      });

      qlResponseBuilder.setErrors(errors);
    }
    qlResponseBuilder.setData(executionResult.getData());
    return qlResponseBuilder;
  }
}
