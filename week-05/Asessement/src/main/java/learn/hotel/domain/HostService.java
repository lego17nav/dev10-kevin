package learn.hotel.domain;

import learn.hotel.data.HostFileRepository;
import learn.hotel.models.Host;
import learn.hotel.ui.Controller;

import java.util.List;
import java.util.stream.Collectors;

public class HostService {

    HostFileRepository hostFileRepository;

    public HostService(HostFileRepository hostFileRepository) {this.hostFileRepository = hostFileRepository;}

    public List<Host> findAll() {
        return hostFileRepository.findAll().stream()
                .collect(Collectors.toList());

    }

    public List<Host> findByLastName(String prefix) {
        return findAll().stream()
                .filter(h -> h.getLastName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    public List<Host> findByHostId(String hostId) {
        List<Host> results = findAll().stream()
                .filter(h -> h.getId().equals(hostId))
                .collect(Collectors.toList());

        return results;
    }
}
