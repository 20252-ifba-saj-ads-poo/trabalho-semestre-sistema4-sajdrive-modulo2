package br.edu.ifba.saj.fwads.model.gestaoEmbarque;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class AbstractModel<T> {
    private T id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AbstractModel() {
        this.createdAt = LocalDateTime.now();
    }

    //Gets e Sets

    public T getId(){
        return id;
    }

    public void setId(T id) { 
        this.id = id; 
    }

    public LocalDateTime getCreatedAt() { 
        return createdAt; 
    }

    public void setCreatedAt(LocalDateTime createdAt) 
    { 
        this.createdAt = createdAt;

    }
    public LocalDateTime getUpdatedAt() { 
        return updatedAt; 
    }
    public void setUpdatedAt(LocalDateTime updatedAt) { 
        this.updatedAt = updatedAt; 
    }
}
