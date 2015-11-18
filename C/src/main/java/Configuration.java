import org.restexpress.RestExpress;
import org.restexpress.util.Environment;

import java.util.Properties;

public class Configuration extends Environment{


    private static final String PORT_PROPERTY = "port";
    private static final String BASE_URL_PROPERTY = "base.url";

    private int port;
    private String baseUrl = null;

    private CompilerController compilerController = null;

    @Override
    protected void fillValues(Properties p)
    {
        port = Integer.parseInt(p.getProperty(PORT_PROPERTY, String.valueOf(RestExpress.DEFAULT_PORT)));
        baseUrl = p.getProperty(BASE_URL_PROPERTY, "http://localhost:" + port);
        compilerController= new CompilerController();
    }

    public CompilerController getCompilerController() {
        return compilerController;
    }

    public void setCompiler(CompilerController compilerController) {
        this.compilerController = compilerController;
    }


    // SECTION: ACCESSORS - PUBLIC

    public String getBaseUrl()
    {
        return baseUrl;
    }

    public int getPort()
    {
        return port;
    }



}
