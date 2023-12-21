package br.com.deveficiente.cdc.validacao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.deveficiente.cdc.autor.Autor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidatorContext;

public class AtributoValorUnicoValidatorTest {

    private EntityManager managerMock;

    private Query queryMock;

    @InjectMocks
    private AtributoValorUnicoValidator validator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        
        managerMock = mock(EntityManager.class);
        queryMock = mock(Query.class);

        ReflectionTestUtils.setField(validator, "manager", managerMock);
        ReflectionTestUtils.setField(validator, "query", queryMock);
    }

    @Test
    public void deveRetornarTrueQuandoNaoEncontrarUmEmailCadastrado() throws IllegalArgumentException, IllegalAccessException {
        AtributoValorUnico params = mock(AtributoValorUnico.class);
        when(params.atributo()).thenReturn("email");

        Class<?> classe = Object.class;        
        OngoingStubbing<Class<?>> ongoingStubbing = when(params.classe());
        ongoingStubbing.thenReturn(classe);

        validator.initialize(params);        
        
        when(managerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
        
        when(queryMock.getResultList()).thenReturn(new ArrayList<Autor>());

        boolean result = validator.isValid("valido@mail.com", mock(ConstraintValidatorContext.class));

        assertTrue(result);
    }

    @Test
    public void deveRetornarFalseQuandoEncontrarUmEmailCadastrado() throws IllegalArgumentException, IllegalAccessException {
        AtributoValorUnico params = mock(AtributoValorUnico.class);
        when(params.atributo()).thenReturn("email");
        
        Class<?> classe = Object.class;        
        OngoingStubbing<Class<?>> ongoingStubbing = when(params.classe());
        ongoingStubbing.thenReturn(classe);

        validator.initialize(params);        
        
        when(managerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
        
        var autores = new ArrayList<Autor>();
        autores.add(new Autor("Existente", "emailexistente@mail.com", "É um autor existente"));

        when(queryMock.getResultList()).thenReturn(autores);

        boolean result = validator.isValid("emailexistente@mail.com", mock(ConstraintValidatorContext.class));

        assertFalse(result);
    }
    
    @Test()
    public void deveLancarExceptionQuandoEncontrarMaisDeUmEmailCadastrado() throws IllegalArgumentException, IllegalAccessException {
        AtributoValorUnico params = mock(AtributoValorUnico.class);
        when(params.atributo()).thenReturn("email");
        
        Class<?> classe = Object.class;        
        OngoingStubbing<Class<?>> ongoingStubbing = when(params.classe());
        ongoingStubbing.thenReturn(classe);

        validator.initialize(params);        
        
        when(managerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), any())).thenReturn(queryMock);
        
        var autores = new ArrayList<Autor>();
        autores.add(new Autor("Existente", "emailexistente@mail.com", "É um autor existente"));
        autores.add(new Autor("Existente 2", "emailexistente@mail.com", "É um autor existente"));

        when(queryMock.getResultList()).thenReturn(autores);

        assertThrows(IllegalStateException.class, () -> {
            validator.isValid("emailexistente@mail.com", mock(ConstraintValidatorContext.class));
        });

    }    

}
