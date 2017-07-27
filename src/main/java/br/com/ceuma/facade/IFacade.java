package br.com.ceuma.facade;

public interface IFacade {

    <T> T get(Class<T> clazz) throws Exception;
}
