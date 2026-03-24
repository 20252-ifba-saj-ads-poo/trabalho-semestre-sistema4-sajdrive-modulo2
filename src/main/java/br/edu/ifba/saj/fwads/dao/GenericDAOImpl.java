package br.edu.ifba.saj.fwads.dao;

import br.edu.ifba.saj.fwads.model.gestaoEmbarque.AbstractModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class GenericDAOImpl<T extends AbstractModel<UUID>> implements GenericDAO<T, UUID> {
    
    // O Mapa que guarda os dados 
    protected Map<UUID, T> bancoDeDados = new HashMap<>();

    @Override
    public UUID salvar(T entidade) {
        if (entidade.getId() == null) {
            entidade.setId(UUID.randomUUID());
        }
        entidade.setCreatedAt(LocalDateTime.now());
        bancoDeDados.put(entidade.getId(), entidade);
        return entidade.getId();
    }

    @Override
    public void atualizar(T entidade) {
        if (entidade.getId() != null && bancoDeDados.containsKey(entidade.getId())) {
            entidade.setUpdatedAt(LocalDateTime.now());
            bancoDeDados.put(entidade.getId(), entidade);
        }
    }

    @Override
    public void deletar(UUID id) {
        bancoDeDados.remove(id);
    }

    @Override
    public T buscarPorId(UUID id) {
        return bancoDeDados.get(id);
    }

    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(bancoDeDados.values());
    }
}