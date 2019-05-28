# How to run
```
gradle :graphql-api:run
gradle :rest-api:run
```

It will run 2 applications.
1) RestApi : localhost:8081
2) Graphql : http://localhost:8080/graphiql
```JSON
{
  pets {
    name
    age
    type
  }
}
```

## Examples
1. You can run the mutation query like this.
```
mutation createPetMutation($pet:PetInput) {
  createPet(input: $pet) {
    type
    name
    age
  }
}
```

This is arguments
```
{
  "pet" : {
    "type" : "DOG",
    "name" : "petName",
    "age" :10
  }
}
```

This query will parse like this format in your browser.

POST
localhost:8080/graphql

``` JSON
{"query":"mutation createPetMutation($pet:PetInput) {\n  createPet(input: $pet) {\n    type\n    name\n    age\n  }\n}","variables":{"pet":{"type":"DOG","name":"petName","age":10}},"operationName":"createPetMutation"}
```  