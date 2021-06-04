package br.edu.ceepplg.agenda.service;

import br.edu.ceepplg.agenda.model.Contato;
import br.edu.ceepplg.agenda.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public Contato salvar(Contato contato) {
        contato.setNome(contato.getNome().trim().toUpperCase());
        return repository.save(contato);
    }

    public Boolean remover(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Contato> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Contato> buscarTodos() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

}
