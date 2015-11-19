import com.strategicgains.hyperexpress.HyperExpress;
import com.strategicgains.hyperexpress.RelTypes;
import org.restexpress.RestExpress;

import java.util.Map;

public class Relationships {


        private Relationships() {
        }

        public static void define(RestExpress server) {
                final Map<String, String> routes = server.getRouteUrlsByName();

                HyperExpress.relationships()
                        .forCollectionOf(Compiler.class)
                        .rel(RelTypes.SELF, routes.get(Constants.PARSE_ROUTE))
                        .rel(RelTypes.SELF, routes.get(Constants.OCCURRENCE_ROUTE));


        }
}
