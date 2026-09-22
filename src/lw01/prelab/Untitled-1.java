// kebun binatang
 
Animal[] A = new Animal[5];

A[0] = new Cat("Tom");
A[1] = new Dog("Pluto");
A[2] = new Dog("Snoopy");
A[3] = new Cat("Garfield");
A[4] = new Animal();

for (Animal animal : A) {
    System.out.print(animal.getClass().getSimpleName() + " : ");

    if(animal.getClass() == Cat.class) {
        animal.scratch();
    } else {
        animal.eat();
    }
}
