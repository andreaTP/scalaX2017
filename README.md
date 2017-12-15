Slides are available at:

[https://andreatp.github.io/meetupLX/](https://andreatp.github.io/meetupLX/#/)

To run the example you need node and sbt installed.

in the `messages` directory run:

```
sbt publishLocal
```

in the ```server``` directory run:

```
npm install websocket
npm install node-tweet-stream
sbt run
```

in the ```client``` directory run:

```
sbt fullOptJS
```
 and then open ```client/ui/index.html``` in a recent browser.
