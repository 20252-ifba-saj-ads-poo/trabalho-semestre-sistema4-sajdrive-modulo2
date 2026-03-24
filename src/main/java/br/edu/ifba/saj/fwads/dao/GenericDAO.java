package br.edu.ifba.saj.fwads.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    ID salvar(T entidade);
    void atualizar(T entidade);
    void deletar(ID id);
    T buscarPorId(ID id);
    List<T> listarTodos();
}