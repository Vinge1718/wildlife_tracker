public class Animal{
    private String name;

    public Animal(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object otherAnimal){
        if(!(otherAnimal instanceof Animal)){
            return false;
        }else{
            Animal newAnimal = (Animal) otherAnimal;
            return this.getName().equals(newAnimal.getName());
        }
    }
}
