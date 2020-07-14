package com.vvm.sh.repositorios;

import io.reactivex.Single;

public interface Repositorio<T> {

    Single<Long> inserir(T item);

    Single<Integer> atualizar(T item);

    Single<Integer> remover(T item);
}
