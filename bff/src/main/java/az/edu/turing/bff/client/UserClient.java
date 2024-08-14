package az.edu.turing.bff.client;

import az.edu.turing.bff.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@Configuration
@FeignClient(name = "userClient", url = "http://localhost:8085/api/v1/users")
public interface UserClient {
    @GetMapping("/")
    ResponseEntity<List<UserDto>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<UserDto> findById(@PathVariable UUID id);

    @PostMapping("/create")
    ResponseEntity<UserDto> create(@RequestBody UserDto userDto);

    @PutMapping("/{id}")
    ResponseEntity<UserDto> update(@PathVariable UUID id, @RequestBody UserDto userDto);

    @DeleteMapping("/")
    ResponseEntity<Boolean> deleteAll();

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteById(@PathVariable UUID id);

    @PatchMapping("/{id}/email")
    ResponseEntity<UserDto> updateEmail(@PathVariable UUID id, @RequestBody String email);
}
