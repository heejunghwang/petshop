package example.config;

import com.coxautodev.graphql.tools.SchemaParser;
import example.resolvers.Mutation;
import example.resolvers.Query;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

  @Autowired
  private Query queryRootResolver;

  @Autowired
  private Mutation mutationResolver;

  @Bean
  public GraphQL createGraphQLSchema() {
    return GraphQL.newGraphQL(getGraphQLSchema())
        .build();
  }

  @Bean
  public GraphQLSchema getGraphQLSchema() {
    return SchemaParser.newParser()
        .file("graphql/common.graphqls")
        .file("graphql/petshop.graphqls")
        .resolvers(
            queryRootResolver,
            mutationResolver
        )
        .build()
        .makeExecutableSchema();
  }

}
