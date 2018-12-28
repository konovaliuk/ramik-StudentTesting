package beans;

import java.io.Serializable;

public class TestCase implements Serializable{
    
    private int id;
    private String name;

    public TestCase() {
    }
    
    private TestCase(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
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
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCase testCase = (TestCase) o;

        return id == testCase.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "TestCase{" + "id=" + id + ", name=" + name + '}';
    }

   
    
    public static class Builder{
        private int id;
        private String name;
        
        public Builder setId(int id){
            this.id = id;
            return this;
        }
        
        public Builder setName(String name){
            this.name = name;
            return this;
        }
        public TestCase build(){
            return new TestCase(this);
        }
        
    }

   
   
    
    
}
