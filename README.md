Play & Akka Websockets client for echo server
=============================================

This application is simply a client for a websocket echo server which creates an actor per websocket connection, just
connects and sends a Hello message and keeps the connection open for the load testing purposes

Run
===

1.  `arefaey@arefaey-MacBook-Pro ~/w/p/s/w/ws-echo-client> ./activator run`
2.  Change the value of the URL of the echo server in the configuration file `conf/application.conf`

```
#Echo Server
server.url="ws://localhost:9999/ws"
```

3.  Send an HTTP request to the `http://localhost:9000` using a web browser or `$ curl http://localhost:9000` to start
the client

Enjoy!

