package br.com.zup.edu.livraria.livro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Entity
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Livro livro;

    @Column(nullable = false)
    private boolean reservado=false;

    public Exemplar(Livro livro) {
        this.livro = livro;
    }

    /**
     * @deprecated construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Exemplar() {
    }
    
    public Long getId() {
        return id;
    }
    
    private boolean isReservado() {
		return this.reservado;
	}
    
    public void reservar() {
		if(isReservado()) {
    		throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Este exemplar está reservado");
    	}
		
		this.reservado = true;
	}
    
}
