package quarkus.bank;

import config.BankSupportConfig;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Path("/bank")
public class BankResource {
    BankSupportConfig supportConfig;

    public BankResource(BankSupportConfig config) {
        this.supportConfig = config;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/support")
    public Map<String, String> getSupport() {
        HashMap<String, String> map = new HashMap<>();
        map.put("email", supportConfig.email);
        map.put("phone", supportConfig.getPhone());
        return map;
    }

    @ConfigProperty(name = "bank.name",
            defaultValue = "Bank of Default")
    String name;

    @GET
    @Path("/name")
    @Produces(MediaType.TEXT_PLAIN)
    public String getName() {
        return name;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @ConfigProperty(name = "app.mobileBanking")
    Optional<Boolean> mobileBanking;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/mobilebanking")
    public Boolean getMobileBanking() {
        return mobileBanking.orElse(false);
    }
}