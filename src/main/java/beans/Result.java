package beans;

import java.io.Serializable;

public class Result implements Serializable {
    private int id;
    private Integer userId;
    private String mark;
    private Integer idChapter;

    public Result() {
    }
    
    private Result(Builder builder){
        this.id = builder.id;
        this.userId = builder.userId;
        this.mark = builder.mark;
        this.idChapter = builder.idChapter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(Integer idChapter) {
        this.idChapter = idChapter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        return id == result.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", userId=" + userId +
                ", mark='" + mark + '\'' +
                ", idChapter=" + idChapter +
                '}';
    }

    public static class Builder{
        private int id;
        private Integer userId;
        private String mark;
        private Integer idChapter;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder setMark(String mark) {
            this.mark = mark;
            return this;
        }

        public Builder setIdChapter(Integer idChapter){
            this.idChapter = idChapter;
            return this;
        }
        
        public Result build(){
            return new Result(this);
        }
        
        
    }
}
