Slides are available at:

[https://andreatp.github.io/scalaX2017/](https://andreatp.github.io/scalaX2017/#/)

To run the example you need node and sbt installed.

in the ```server``` directory run:

```
npm install websocket
npm install node-tweet-stream
sbt run
```

Remember to provide a `.credentials` file for twitter suche as:

```
{
  "consumer_key": "XYZ",
  "consumer_secret": "XYZ",
  "token": "XYZ",
  "token_secret": "XYZ"
}
```

in the ```client``` directory run:

```
sbt fullOptJS
```
 and then open ```client/ui/index.html``` in a recent browser.
