package bgu.spl.net.impl.stomp;

import bgu.spl.net.api.MessageEncDecImp;
import bgu.spl.net.api.StompMessagingProtocolImp;
import bgu.spl.net.impl.newsfeed.NewsFeed;
import bgu.spl.net.impl.rci.ObjectEncoderDecoder;
import bgu.spl.net.impl.rci.RemoteCommandInvocationProtocol;
import bgu.spl.net.srv.Server;

public class StompServer {

    public static void main(String[] args) {
        if(args[1]=="tpc") //
        Server.threadPerClient(
                Integer.getInteger(args[0]), //port
                new StompMessagingProtocolImp(), //protocol factory
                new MessageEncDecImp()//message encoder decoder factory
        ).serve();
        else if (args[1]=="reactor")
        Server.reactor(
                Runtime.getRuntime().availableProcessors(),
                Integer.getInteger(args[0]), //port
                new StompMessagingProtocolImp(), //protocol factory
                new MessageEncDecImp()//message encoder decoder factory
        ).serve();
        else
            System.out.println("Wrong args");

    }


}
