# Twitter stream in scala
-----------------------

The goal here is to create a semantic analysis with twitter data.


Technologies used
 - Scala - main language used in the project
 - Play - Web framework for Scala
 - Kafka - A messagebroker that is very flexible to use, it can be used as a data store and streaming engine.
 - Twitter hosebird client - streaming for twitter

Architecture diagram

 ![Twitterstream 1.0](/twitter-stream-diagram.png?raw=true)

Start Kafka Server 

    ./sbt kafkaServer/run
 
 
Start Twitter Streamer App

- twitter stream config, provide your details

```
    twitter {
      consumer_key = ""
      consumer_secret = ""
      token = ""
      token_secret = ""
      terms = ["hashtag", "#hashtag"]
    }
```

```
    ./sbt twitterStreamerApp/run
```

    
Start Word Count Engine 

    ./sbt wordCountEngine/run
    
    
Start Webapp
     
     ./sbt webapp/run