package learn.hotel.data;

import learn.hotel.models.Host;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HostRepositoryDouble implements HostRepository {

    public final static Host host =  makeHost();

    private final ArrayList<Host> hosts = new ArrayList<>();

    public HostRepositoryDouble() {hosts.add(host);}

    @Override
    public List<Host> findAll() {
        return hosts;
    }

    @Override
    public Host findById(String id) {
        return hosts.stream()
                .filter(h -> h.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Host add(Host host) throws DataException {
        List<Host> hosts = findAll();
        hosts.add(host);
        return host;
    }

    public Host findByLastName(String prefix) {
        return findAll().stream()
                .filter(h -> h.getLastName().startsWith(prefix))
                .findFirst()
                .orElse(null);
    }

    private static Host makeHost() {
        Host host = new Host();
        host.setLastName("Doe");
        host.setId("abcdefg");
        host.setWeekERate(new BigDecimal(100));
        host.setRegRate(new BigDecimal(200));
        return host;
    }

}
