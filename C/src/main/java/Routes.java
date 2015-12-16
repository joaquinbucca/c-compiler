import io.netty.handler.codec.http.HttpMethod;
import org.restexpress.RestExpress;

public class Routes {



    public static void define(Configuration config, RestExpress server)
    {
        server.uri("/parse", config.getCompilerController())
//                .action("parse", HttpMethod.GET)
                .method(HttpMethod.POST)
                .name(Constants.PARSE_ROUTE);
        server.uri("/occurrence/{leaf}", config.getCompilerController())
//                .action("occurrence", HttpMethod.GET)
                .method(HttpMethod.GET)
                .name(Constants.OCCURRENCE_ROUTE);

    }
}
