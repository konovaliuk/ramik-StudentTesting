package beans;

import java.io.Serializable;

public class Question implements Serializable {
    
    private int id;
    private String text;
    private Integer chapterId;

    public Question() {
    }

    
    private Question(Builder builder){
        this.id = builder.id;
        this.text = builder.text;
        this.chapterId = builder.chapterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return id == question.id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", text=" + text + ", chapterId=" + chapterId + '}';
    }
    
    public static class Builder{
        private int id;
        private String text;
        private Integer chapterId;
        
    public Builder setId(int id){
        this.id = id;
        return this;
    }
    
    public Builder setText(String text){
        this.text = text;
        return this;
    }
    
     public Builder setChapterId(Integer chapterId){
        this.chapterId = chapterId;
        return this;
    }
     
    public Question build(){
        return new Question(this);
    }
    
    
}
    
    
}
