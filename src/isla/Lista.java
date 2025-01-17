package isla;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Lista {
    static ArrayList<Animal> animales = new ArrayList<>(Ajustes.cantidadInicialAnimales);
    static ArrayList<Planta> plantas = new ArrayList<>(Ajustes.cantidadInicialPlantas);

    // VER SI de verdad se precisan estos getter y setter
    public static Animal getAnimal(int posicion)  {
        return animales.get(posicion);
    }  // getter
    public static void setAnimal (int posicion, Animal animal) {
        animales.set(posicion, animal);
    } // setter

    static void aumentarUnidadEdadDeAnimales()  {
        for (Animal animal : animales) {
            animal.setEdad(animal.getEdad()+1);
        }  // for
    }  // method

    static void aumentarUnidadEnergiaDePlantas()  {
        for (Planta planta : plantas) {
            int energiaDePlanta = planta.getEnergia();
            if (energiaDePlanta <= Ajustes.energiaTopePlanta)
                planta.setEnergia(energiaDePlanta+1);
        }  // for
    }  // method

    static void matarAnimalesViejos()  {
        Iterator<Animal> it = animales.iterator();
        Animal animal;
        while (it.hasNext()) {
            animal = it.next();
            if (animal.getEdad() >= Ajustes.edadDeMuerteAnimal) {
                it.remove();
                Salida.muertes++;
            } // if
        } // while
    }  // method

    static void matarAnimalesDesenergizados()  {
        Iterator<Animal> it = animales.iterator();
        Animal animal;
        while (it.hasNext()) {
            animal = it.next();
            if (animal.getEnergia() <= 0)  {
                it.remove();
                Salida.muertes++;
            } // if
        } // while
    }  // method

    static void matarPlantasDesenergizadas()  {
        Iterator<Planta> it = plantas.iterator();
        Planta planta;
        while (it.hasNext()) {
            planta = it.next();
            if (planta.getEnergia() <= 0) {
                it.remove();
                Salida.muertes++;
            } // if
        } // while
    }  // method

    static HashSet<Ser> todosLosSeres() {
        HashSet<Ser> seres = new HashSet<>(0);
        seres.addAll(Lista.animales);
        seres.addAll(Lista.plantas);
        return seres;
    } // method

} // class