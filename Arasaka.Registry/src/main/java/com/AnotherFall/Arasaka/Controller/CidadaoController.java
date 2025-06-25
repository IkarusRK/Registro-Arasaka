package com.AnotherFall.Arasaka.Controller;
import com.AnotherFall.Arasaka.Registry.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/cidadaos")
@CrossOrigin(origins = "*")
public class CidadaoController {

    @Autowired
    private CidadaoService cidadaoService;

    @GetMapping
    public List<Cidadao> listarTodos() {
        return cidadaoService.findAll();
    }
    
    @GetMapping("/search")
    public ResponseEntity<Cidadao> buscar(@RequestParam String query) {
        return cidadaoService.buscarPorNomeOuHwid(query)
                .map(ResponseEntity::ok) // Se encontrar, retorna o cidadão com status 200 OK
                .orElse(ResponseEntity.notFound().build()); // Se não encontrar, retorna status 404 Not Found
    }

    @GetMapping("/{id}")
    public Cidadao buscarPorId(@PathVariable Long id) {
        return cidadaoService.findById(id).orElse(null);
    }

    @PostMapping
    public Cidadao salvar(@RequestBody Cidadao cidadao) {
        return cidadaoService.salvar(cidadao);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        cidadaoService.deleteById(id);
    }

    @GetMapping("/classe/{classe}")
    public List<Cidadao> buscarPorClasse(@PathVariable Classe classe) {
        return cidadaoService.buscarPorClasse(classe);
    }

    @GetMapping("/nivel/{nivel}")
    public List<Cidadao> buscarPorNivel(@PathVariable NivelAcesso nivel) {
        return cidadaoService.buscarPorNivel(nivel);
    }

    @GetMapping("/filtro")
    public List<Cidadao> buscarPorClasseOuNivel(@RequestParam Classe classe, @RequestParam NivelAcesso nivel) {
        return cidadaoService.buscarPorClasseOuNivel(classe, nivel);
    }
}