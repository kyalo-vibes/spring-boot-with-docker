package com.kyalo.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/demo")
public class DemoController {
    private final DemoRepository demoRepository;

    public DemoController(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @GetMapping
    public List<DemoEntity> getAll() {
        return demoRepository.findAll();
    }

    @PostMapping
    public DemoEntity create(DemoEntity demoEntity) {
        return demoRepository.save(demoEntity);
    }

    @GetMapping("/id")
    public DemoEntity getById(Integer id) {
        return demoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        demoRepository.deleteById(id);
    }
}
