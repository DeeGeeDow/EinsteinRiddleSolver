public class Main {
    public static void main(String[] args){
        Riddle r = new Riddle(5,5);

        /*
        Daftar Clues:
        0. Color {
            0. blue
            1. green
            2. red
            3. white
            4. yellow
        }
        1. Nationality {
            0. Brit
            1. Dane
            2. German
            3. Norwegian
            4. Swede
        }
        2. Drink {
            0. beer
            1. coffee
            2. milk
            3. tea
            4. water
        }
        3. Cigarette{
            0. Blends
            1. Blue Master
            2. Dunhill
            3. Pall Mall
            4. Prince
        }
        4. Pet{
            0. Birds
            1. Cats
            2. Dogs
            3. Horses
            4. Fishes
        }
         */
        r.addClues(new Petunjuk2(1,1,0,3)); // Brit, red
        r.addClues(new Petunjuk2(1,5,4,3)); // Swede, dog
        r.addClues(new Petunjuk2(1,2,2,4)); // Dane, tea
        r.addClues(new Petunjuk3(0,2,0,4, Relation.LEFT)); // Green, White, Left
        r.addClues(new Petunjuk2(0,2,2,2)); // green, coffee
        r.addClues(new Petunjuk2(3,4,4,1)); // Pall Mall, bird
        r.addClues(new Petunjuk2(0,5,3,3)); // yellow, Dunhill
        r.addClues(new Petunjuk1(2,2,3)); // center, milk
        r.addClues(new Petunjuk1(0,1,4)); // first, norwegian
        r.addClues(new Petunjuk3(3,1,4,2,Relation.NEIGHBOR)); // Blends, cat, neighbor
        r.addClues(new Petunjuk3(4,4,3,3,Relation.NEIGHBOR)); // horse, Dunhill, neighbor
        r.addClues(new Petunjuk2(3,2,2,1)); // Blue Master, beer
        r.addClues(new Petunjuk2(1,3,3,5)); // German, Prince
        r.addClues(new Petunjuk3(1,4,0,1,Relation.NEIGHBOR)); // Norwegian, blue, neighbor
        r.addClues(new Petunjuk3(3,1,2,5,Relation.NEIGHBOR)); // Blends, Water, neighbor

        r.solve();
    }
}
