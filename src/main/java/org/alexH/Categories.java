package org.alexH;

public enum Categories
{
    ANIMALS("Animals.json"),
    ANIME("Anime.json"),
    CLASSIC_LITERATURE("Classic_Literature.json"),
    GEOGRAPHY("Geography.json"),
    HISTORY("History.json"),
    POP_CULTURE("Pop_Culture.json"),
    SCIENCE("Science.json"),
    ALL("Combined.json");


    public final String label;

    Categories(String label)
    {
        this.label = label;
    }

    public static Categories getCategoryByIndex(byte index)
    {
        return switch (index)
        {
            case 1 -> ANIMALS;
            case 2 -> ANIME;
            case 3 -> CLASSIC_LITERATURE;
            case 4 -> GEOGRAPHY;
            case 5 -> HISTORY;
            case 6 -> POP_CULTURE;
            case 7 -> SCIENCE;
            case 8 -> ALL;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

}
