package nl.tudelft.oopp.demo.onetoonebi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Controller
public class OneToOneController {
    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("getLibraries")
    @ResponseBody
    public List<Library> getLibraries() {
        return libraryRepository.findAll();
    }

    @GetMapping("getAddresses")
    @ResponseBody
    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    @PostMapping("addAddress")
    public Address addAddress(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    //Add a library for the given addressId its fk matches to
    @PostMapping("addLibrary/{address_id}")
    public Optional<Library> addLibrary(@PathVariable (value = "address_id") int add_id, @RequestBody Library library) {
        return addressRepository.findById(add_id).map(address -> {
            library.setAddress(address);
            return libraryRepository.save(library);
        });
    }

    @GetMapping("getJoin")
    @ResponseBody
    public List<Library> joinTables() {
        List<Library> libraries = libraryRepository.findAll();

        List<Library> result = new ArrayList<>();
        for (Library l: libraries) {
            result.add(l);
            l.setAddress(l.getAddress());
        }
        return result;
    }

}
