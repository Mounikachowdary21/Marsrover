class House {
    private String foundation;
    private String structure;
    private String roof;

    private House(HouseBuilder builder) {
        this.foundation = builder.foundation;
        this.structure = builder.structure;
        this.roof = builder.roof;
    }

    @Override
    public String toString() {
        return "House with " + foundation + ", " + structure + ", and " + roof;
    }

    static class HouseBuilder {
        private String foundation;
        private String structure;
        private String roof;

        public HouseBuilder buildFoundation(String foundation) {
            this.foundation = foundation;
            return this;
        }

        public HouseBuilder buildStructure(String structure) {
            this.structure = structure;
            return this;
        }

        public HouseBuilder buildRoof(String roof) {
            this.roof = roof;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }
}

// Client code
public class Creational_builder{
    public static void main(String[] args) {
        House house = new House.HouseBuilder()
                .buildFoundation("Concrete foundation")
                .buildStructure("Wooden structure")
                .buildRoof("Tiled roof")
                .build();
        System.out.println(house); // Output: House with Concrete foundation, Wooden structure, and Tiled roof.
    }
}
