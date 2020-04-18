package protocol.dubbo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.http.Invocation;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private Invocation invocation;
    private Future future;

    public Invocation getInvocation() {
        return invocation;
    }

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        future = ctx.writeAndFlush(invocation);
    }


    public Object call() throws Exception {
        return future;
    }
}
