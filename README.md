# sangria-play-sample

use Play 2 with sangria.

## run  

```
> sbt run 
```

then, open `localhost:9000`

## graphql  
### query

sample query.
```graphQl
query Query {
  movie (id: 1) {
    id
    title
    start
  }
}
```

POST to `localhost:9000/graphql`
```shell
$ curl -v -X POST localhost:9000/graphql -H "Content-Type:application/json" \
  -d '{"query": "{movie(id: 1) {id title start}}"}'
```

### mutation  
T.B.D.
