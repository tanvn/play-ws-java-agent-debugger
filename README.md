## How to reproduce the Exeception

1. Run `./sbt dist`
2. Go to `./target/universal/` then execute `unzip play-ws-java-agent-debugger-1.0-SNAPSHOT.zip`
3. Edit `./target/universal/play-ws-java-agent-debugger-1.0-SNAPSHOT/bin//universal/play-ws-java-agent-debugger-1.0-SNAPSHOT` file, add the following options from line 354 (right after the last `addJava` line)
    ```
    addJava "-Ddd.integration.play.enabled=true"
    addJava "-Ddatadog.slf4j.simpleLogger.defaultLogLevel=debug"
    addJava "-Ddd.trace.debug=true"
    ```
4. Then from `./target/universal/` directory, execute the below command 
    ```
    ./play-ws-java-agent-debugger-1.0-SNAPSHOT/bin/play-ws-java-agent-debugger
    ```
   you will see the logs created by the application when it starts and runs.
5. Now call the test api by send a http GET request to `http://localhost:9000/test`

In the log console of the application, the log of the thrown exception will be shown.
You can find something like 

```

...
[dd.trace 2020-02-29 01:55:56:708 +0900] [AsyncHttpClient-3-1] DEBUG datadog.trace.agent.ot.DDSpan - Finished: DDSpan [ t_id=775006457432349975, s_id=5943652219557077233, p_id=2832827016544550484] trace=unnamed-java-app/play-ws.request/GET /files/uploads/photos/?/milky-way-astrophotography-l.jpg metrics={} *errored* tags={component=play-ws, error=true, error.msg=Should not have received bodypart, error.stack=java.lang.IllegalStateException: Should not have received bodypart
	at play.api.libs.ws.ahc.DefaultStreamedAsyncHandler.onBodyPartReceived(Streamed.scala:64)
	at datadog.trace.instrumentation.playws21.AsyncHandlerWrapper.onBodyPartReceived(AsyncHandlerWrapper.java:35)
	at play.shaded.ahc.org.asynchttpclient.netty.handler.HttpHandler.handleChunk(HttpHandler.java:109)
	at play.shaded.ahc.org.asynchttpclient.netty.handler.HttpHandler.handleRead(HttpHandler.java:143)
	at play.shaded.ahc.org.asynchttpclient.netty.handler.AsyncHttpClientHandler.channelRead(AsyncHttpClientHandler.java:78)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
	at play.shaded.ahc.io.netty.handler.codec.MessageToMessageDecoder.channelRead(MessageToMessageDecoder.java:102)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
	at play.shaded.ahc.io.netty.channel.CombinedChannelDuplexHandler$DelegatingChannelHandlerContext.fireChannelRead(CombinedChannelDuplexHandler.java:438)
	at play.shaded.ahc.io.netty.handler.codec.ByteToMessageDecoder.fireChannelRead(ByteToMessageDecoder.java:328)
	at play.shaded.ahc.io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:302)
	at play.shaded.ahc.io.netty.channel.CombinedChannelDuplexHandler.channelRead(CombinedChannelDuplexHandler.java:253)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
	at play.shaded.ahc.io.netty.handler.ssl.SslHandler.unwrap(SslHandler.java:1475)
	at play.shaded.ahc.io.netty.handler.ssl.SslHandler.decodeJdkCompatible(SslHandler.java:1224)
	at play.shaded.ahc.io.netty.handler.ssl.SslHandler.decode(SslHandler.java:1271)
	at play.shaded.ahc.io.netty.handler.codec.ByteToMessageDecoder.decodeRemovalReentryProtection(ByteToMessageDecoder.java:505)
	at play.shaded.ahc.io.netty.handler.codec.ByteToMessageDecoder.callDecode(ByteToMessageDecoder.java:444)
	at play.shaded.ahc.io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:283)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
	at play.shaded.ahc.io.netty.channel.DefaultChannelPipeline$HeadContext.channelRead(DefaultChannelPipeline.java:1422)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	at play.shaded.ahc.io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	at play.shaded.ahc.io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:931)
	at play.shaded.ahc.io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:163)
	at play.shaded.ahc.io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:700)
	at play.shaded.ahc.io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:635)
	at play.shaded.ahc.io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:552)
	at play.shaded.ahc.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:514)
	at play.shaded.ahc.io.netty.util.concurrent.SingleThreadEventExecutor$6.run(SingleThreadEventExecutor.java:1044)
	at play.shaded.ahc.io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
	at play.shaded.ahc.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)
, error.type=java.lang.IllegalStateException, http.method=GET, http.url=https://vastphotos.com/files/uploads/photos/10245/milky-way-astrophotography-l.jpg, peer.hostname=vastphotos.com, span.kind=client, thread.id=36, thread.name=application-akka.actor.default-dispatcher-6}, duration_ns=1195336113
[dd.trace 2020-02-29 01:55:56:709 +0900] [AsyncHttpClient-3-1] DEBUG datadog.trace.agent.ot.PendingTrace - traceId: 775006457432349975 -- Expired reference. count = 1
...

```
