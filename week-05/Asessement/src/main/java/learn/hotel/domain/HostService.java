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
}
