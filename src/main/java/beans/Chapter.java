/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author Denis
 */
public class Chapter implements Serializable {
    private int id;
    private String name;
    private Integer idTestCase;
    
    public Chapter (){
    }
    
    private Chapter (Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.idTestCase = builder.idTestCase;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdTestCase() {
        return idTestCase;
    }

    public void setIdTestCase(Integer idTestCase) {
        this.idTestCase = idTestCase;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chapter chapter = (Chapter) o;

        return id == chapter.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "Chapter{" + "id=" + id + ", name=" + name + ", idTestCase=" + idTestCase + '}';
    }
    
    public static class Builder{
        private int id;
        private String name;
        private Integer idTestCase;
        
    public Builder setId(int id){
        this.id = id;
        return this;
    }
    
    public Builder setName(String name){
        this.name = name;
        return this;
    }
    
     public Builder setIdTestCase(Integer idTestCase){
        this.idTestCase = idTestCase;
        return this;
    }
     
    public Chapter build(){
        return new Chapter(this);
    }
    
}}
